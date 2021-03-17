package com.ibm.abcairlines.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.abcairlines.common.Common.Gender;
import com.ibm.abcairlines.common.Common.PreferredClass;
import com.ibm.abcairlines.entity.Booking;
import com.ibm.abcairlines.entity.Flight;
import com.ibm.abcairlines.entity.Passenger;
import com.ibm.abcairlines.repository.BookingRepository;

@SpringBootTest
public class BookingServiceTest {

	@MockBean
	BookingRepository bookingRepository;

	@Autowired

	BookingService bookingService;

	@Test
	@DisplayName("Test getBookingById when booking ID found")
	public void testGetBookingById() {
		MockitoAnnotations.openMocks(this);
		when(bookingRepository.findById(anyInt())).thenReturn(Optional.of((mock(Booking.class))));
		Booking booking = bookingService.getBookingById(1);
		assertNotNull(booking);
	}

	@Test
	@DisplayName("Test getBookingById when booking ID not found")
	public void testGetBookingByInvalidId() {
		Booking booking = bookingService.getBookingById(1);
		assertNull(booking);
	}

	@Test
	public void testGetFlights() {
		Booking mockbooking1 = new Booking();
		mockbooking1.setBookedDate(Date.valueOf("2021-03-24"));
		mockbooking1.setId(1);
		mockbooking1.setOnBoardingDate(Date.valueOf("2021-04-02"));
		mockbooking1.setPreferredClass(PreferredClass.BUSINESSCLASS);
		mockbooking1.setFlight(getflight());
		mockbooking1.setPassenger(getPassenger1());
		mockbooking1.setFare(5000);
		Booking mockbooking2 = new Booking();
		mockbooking2.setBookedDate(Date.valueOf("2021-03-26"));
		mockbooking2.setId(2);
		mockbooking2.setOnBoardingDate(Date.valueOf("2021-04-04"));
		mockbooking2.setPreferredClass(PreferredClass.ECONOMYCLASS);
		mockbooking2.setFlight(getflight());
		mockbooking2.setPassenger(getPassenger1());
		mockbooking2.setFare(2000);

		doReturn(Arrays.asList(mockbooking1, mockbooking2)).when(bookingRepository).findAll();

		Iterable<Booking> booking = bookingService.getBookings();
		assertEquals(2, ((Collection<?>) booking).size());

	}

	public Passenger getPassenger1() {
		Passenger passenger = new Passenger();
		passenger.setName("Rohan");
		passenger.setAge(56);
		passenger.setContact(123);
		passenger.setGender(Gender.MALE);

		return passenger;
	}

	public Flight getflight() {
		Flight mockFlight = new Flight();
		mockFlight.setId(1);
		mockFlight.setDate(Date.valueOf("2021-04-07"));
		mockFlight.setDestination("Delhi");
		mockFlight.setSource("Pune");
		mockFlight.setAvailableBusinessClassSeats(0);
		mockFlight.setAvailableEconomyClassSeats(30);
		return mockFlight;
	}

	@Test
	@DisplayName("Test Save booking")
	public void testFlightSavedSuccesfully() {

		Booking mockbooking1 = new Booking();
		mockbooking1.setBookedDate(Date.valueOf("2021-03-24"));
		mockbooking1.setId(1);
		mockbooking1.setOnBoardingDate(Date.valueOf("2021-04-02"));
		mockbooking1.setPreferredClass(PreferredClass.BUSINESSCLASS);
		mockbooking1.setFlight(getflight());
		mockbooking1.setPassenger(getPassenger1());
		mockbooking1.setFare(5000);
		// given 4 flights in the database

		// when

		doReturn(mockbooking1).when(bookingRepository).save(mockbooking1);
		Booking savedbooking = bookingRepository.save(mockbooking1);
		// then
		System.out.println(savedbooking);
		assertNotNull(savedbooking, "Booking should be saved");
		assertNotNull(savedbooking.getId(), "Booking should get an id");
		assertEquals(mockbooking1.getFlight(), savedbooking.getFlight());

	}

}
