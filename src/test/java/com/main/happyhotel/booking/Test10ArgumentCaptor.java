package com.main.happyhotel.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class Test10ArgumentCaptor {
    private BookingService bookingService;



    private PaymentService paymentServiceMock;
    private  RoomService roomServiceMock;
    private  BookingDAO bookingDAOMock;
    private  MailSender mailSenderMock;
    private ArgumentCaptor<Double> doubleArgumentCaptor;

    @BeforeEach
    void setup(){
        this.paymentServiceMock =mock(PaymentService.class);
        this.roomServiceMock = mock(RoomService.class);
        this.bookingDAOMock = mock(BookingDAO.class);
        this.mailSenderMock = mock(MailSender.class);

        this.bookingService = new BookingService(paymentServiceMock,
                roomServiceMock,bookingDAOMock,  mailSenderMock);

        this.doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
    }


    @Test
    void should_PaymCorrectPrice_when_prepaidTest(){
    //given
    BookingRequest bookingRequest = new BookingRequest( "1",
            LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
    ,2,true);

    //when
    bookingService.makeBooking(bookingRequest);

    //then

    verify(paymentServiceMock,times(1)).pay(eq(bookingRequest),doubleArgumentCaptor.capture());
    double argumentUsed = doubleArgumentCaptor.getValue();
    System.out.println(argumentUsed);

    //ASSERTEQUALS (DESIREDARGUMENT, CAPTURED ARGUMENT)
    }

    @Test
    void should_PaymCorrectPrice_when_MultipleCallsTest(){
        //given
        BookingRequest bookingRequest = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
                ,2,true);

        BookingRequest bookingRequest2 = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,2)
                ,2,true);


        List<Double> expectedValues = Arrays.asList(400.0,100.0);
        //when
        bookingService.makeBooking(bookingRequest);
        bookingService.makeBooking(bookingRequest2);
        //then

        verify(paymentServiceMock,atLeastOnce()).pay(any(),doubleArgumentCaptor.capture());
        List<Double> argumentsUsed = doubleArgumentCaptor.getAllValues();
        System.out.println(argumentsUsed);

        Assertions.assertEquals(expectedValues,argumentsUsed);    }

}
