package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test06ThrowExceptionMatchers {
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
    @DisplayName("calculate price exception")
    void notCompleteBookingPriceTooHighTest(){
    //given
    BookingRequest bookingRequest = new BookingRequest( "1",
            LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
    ,2,true);

 when(this.paymentServiceMock.pay(any(BookingRequest.class), anyDouble())).thenThrow(BusinessException.class);
   // for concrete values it should be eq() instead of anyprimitivo(), null strings use any()
    //when

        Executable executable = () -> bookingService.makeBooking(bookingRequest);
    //then
        assertThrows(BusinessException.class,executable);
    }
}
