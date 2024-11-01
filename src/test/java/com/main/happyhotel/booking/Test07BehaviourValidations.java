package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test07BehaviourValidations {
    private BookingService bookingService;



    private PaymentService paymentServiceMock;
    private  RoomService roomServiceMock;
    private  BookingDAO bookingDAOMock;
    private  MailSender mailSenderMock;

    @BeforeEach
    void setup(){
        this.paymentServiceMock =mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock,
                roomServiceMock,bookingDAOMock,  mailSenderMock);
    }


    @Test
    @DisplayName("calculate price correct")
    void should_Call_Payment_when_prepaidTest(){
    //given
    BookingRequest bookingRequest = new BookingRequest( "1",
            LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
    ,2,true);
    //when
    bookingService.makeBooking(bookingRequest);
    //then

    verify(paymentServiceMock,times(1)).pay(bookingRequest,400);
    verifyNoMoreInteractions(paymentServiceMock);
    }


    @Test
    @DisplayName("call to payment shouldn't be done")
    void should_notCall_Payment_when_notprepaidTest(){
        //given
        BookingRequest bookingRequest = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
                ,2,false);

        //when
        bookingService.makeBooking(bookingRequest);

        //then
verify(paymentServiceMock,never()).pay(any(),anyDouble());

    }


}
