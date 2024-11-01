package com.main.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test02DefaultValues {
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
        System.out.println("lista valor default " + roomServiceMock.getAvailableRooms());
        System.out.println( "Objecto " + roomServiceMock.findAvailableRoomId(null));
        System.out.println("Primitive return "+ roomServiceMock.getRoomCount());
    }


    @Test
    void test_countAvailablePlacesCorrect(){

        //GIVEN
        int expected = 0;
        //WHEN
    int actual = bookingService.getAvailablePlaceCount();
        // THEN

        assertEquals(expected,actual);
    }


}
