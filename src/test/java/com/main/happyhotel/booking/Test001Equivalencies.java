package com.main.happyhotel.booking;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class Test001Equivalencies {

    @InjectMocks
    private BookingService bookingService;


    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private  RoomService roomServiceMock;
    @Spy
    private  BookingDAO bookingDAOMock;
    @Mock
    private  MailSender mailSenderMock;


    @Captor
    private ArgumentCaptor<Double> doubleArgumentCaptor;

//    @BeforeEach
//    void setup(){
    //        this.bookingService = new BookingService(paymentServiceMock,
//                roomServiceMock,bookingDAOMock,  mailSenderMock);

//        this.paymentServiceMock =mock(PaymentService.class);
//        this.roomServiceMock = mock(RoomService.class);
//        this.bookingDAOMock = mock(BookingDAO.class);
//        this.mailSenderMock = mock(MailSender.class);
//


//        this.doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
//    }


    @Test
    void test_countAvailablePlaces_When_oneRoomAvailable(){
        //GIVEN
        given(this.roomServiceMock.getAvailableRooms()).willReturn(
                Collections.singletonList(new Room("room 1",2))
        );
        int expected =2 ;
        //WHEN
        int actual = bookingService.getAvailablePlaceCount();
        // THEN
        assertEquals(expected,actual,"failed room not correct");
    }


    // WHEN ... TO ... thenReturn
    //given ... to ... willReturn
    @Test
    void should_Call_Payment_when_prepaidTest(){
        //given
        BookingRequest bookingRequest = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
                ,2,true);

        //when
        bookingService.makeBooking(bookingRequest);

        //then
/// when goes to then
        then(paymentServiceMock).should(times(1)).pay(bookingRequest,400);
        verifyNoMoreInteractions(paymentServiceMock);
    }

    @Test
    @Disabled
    void InnecesarystubbingTest(){
        //given
        BookingRequest bookingRequest = new BookingRequest( "1",
                LocalDate.of(2024,01,1),LocalDate.of(2024,01,5)
                ,2,false);
        lenient().when( paymentServiceMock.pay(any(),anyDouble())).thenReturn("1");
        //when
        bookingService.makeBooking(bookingRequest);

        //then

    }

}
