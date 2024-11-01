package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class Test08miniespías2LaIslaCalavera {
    private BookingService bookingService;



    private PaymentService paymentServiceMock;
    private  RoomService roomServiceMock;
    private  BookingDAO bookingDAOMock;
    private  MailSender mailSenderMock;

    @BeforeEach
    void setup(){
        this.paymentServiceMock =mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = spy(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock,
                roomServiceMock,bookingDAOMock,  mailSenderMock);
    }


    @Test
    @DisplayName("Booking creation test")
    void should_make_boookingTest(){
    //given
    BookingRequest bookingRequest = new BookingRequest( "1",
            LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
    ,2,true);

    //when
   String bookingID = bookingService.makeBooking(bookingRequest);

    //then

verify(bookingDAOMock).save(bookingRequest);
System.out.println( "booking ID "+ bookingID);


    }


    @Test
    void should_cancell_boookingwhenInputOkTest(){
        //given
        BookingRequest bookingRequest = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
                ,2,true);
    bookingRequest.setRoomId("1.3");
        String bookingId ="1";

        doReturn(bookingRequest).when(bookingDAOMock).get(any());
        //when

        bookingService.cancelBooking(bookingId);
    }

}
