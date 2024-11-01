package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Test09VoidMethods {
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
    @DisplayName("no room exception")
    void throwWhenMailNotAvailableExceptionTest(){
    //given
    BookingRequest bookingRequest = new BookingRequest( "1",
            LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
    ,2,false);

    doThrow(new BusinessException ()).when(mailSenderMock).sendBookingConfirmation(any());

    //when
        Executable executable = () -> bookingService.makeBooking(bookingRequest);
    //then
        assertThrows(BusinessException.class,executable);
    }

    @Test
    @DisplayName("no room exception")
    void NothrowWhenMailNotAvailableExceptionTest(){
        //given
        BookingRequest bookingRequest = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
                ,2,false);

        doNothing().when(mailSenderMock).sendBookingConfirmation(any());
        //when

        bookingService.makeBooking(bookingRequest);

        //then
verify(mailSenderMock,times(1)).sendBookingConfirmation(any());
        //nothing is expected here
    }


}
