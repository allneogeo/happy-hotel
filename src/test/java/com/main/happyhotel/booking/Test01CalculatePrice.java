package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test01CalculatePrice {
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
    void should_CalculateCorrectPrice_When_CorrectInputTest(){
    //given
    BookingRequest bookingRequest = new BookingRequest( "1",
            LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
    ,2,false);
    double expected = 4 * 2 * 50.0;

    //when
double actual = bookingService.calculatePrice(bookingRequest);

    //then
    assertEquals(expected,actual,"failed the price calculation");
    }


}
