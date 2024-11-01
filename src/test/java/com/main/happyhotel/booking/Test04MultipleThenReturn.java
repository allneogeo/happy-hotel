package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test04MultipleThenReturn {
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
    void test_countAvailablePlaces_WhenCallMultipleTimes(){
        //GIVEN
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(
                Collections.singletonList(new Room("room 1",2))
        ).thenReturn(Collections.emptyList());
        int expectedFirst =2;
        int expectedSecond= 0;
        //WHEN
int actualFirst = bookingService.getAvailablePlaceCount();
int actualSecond = bookingService.getAvailablePlaceCount();

        // THEN
assertAll(
        () -> assertEquals(expectedFirst,actualFirst),
        () -> assertEquals(expectedSecond,actualSecond)
);
    }


}
