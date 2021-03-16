package com.ibm.abcairlines.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.abcairlines.entity.Booking;
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
		when(bookingRepository.findById(anyInt())).thenReturn(Optional.of(mock(Booking.class)));
		Booking booking = bookingService.getBookingById(1);
		assertNotNull(booking);
	}

	@Test
	@DisplayName("Test getBookingById when booking ID not found")
	public void testGetBookingByInvalidId() {
		Booking booking = bookingService.getBookingById(1);
		assertNull(booking);
	}

}
