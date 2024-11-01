package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test03CustomValues {
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
    void test_countAvailablePlaces_When_oneRoomAvailable(){
        //GIVEN
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(
                Collections.singletonList(new Room("room 1",2))
        );
        int expected =2 ;
        //WHEN
int actual = bookingService.getAvailablePlaceCount();
        // THEN
assertEquals(expected,actual,"failed room not correct");
    }
    @Test
    void test_countAvailablePlaces_When_MultipleRoomsAvailable(){
        //GIVEN
        List<Room> rooms = Arrays.asList( new Room("room 1",2),new Room("room 2",5) );
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(
                rooms);
        int expected =7 ;

        //WHEN
        int actual = bookingService.getAvailablePlaceCount();
        // THEN
        System.out.println("asserting "+ actual +" vs " + expected);
        assertEquals(expected,actual,"failed room not correct");
    }

}
