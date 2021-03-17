package com.ibm.abcairlines.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ibm.abcairlines.AbcAirlinesApplicationTests;
import com.ibm.abcairlines.common.Common.Gender;
import com.ibm.abcairlines.common.Common.PreferredClass;
import com.ibm.abcairlines.dto.BookingDto;
import com.ibm.abcairlines.entity.Booking;
import com.ibm.abcairlines.entity.Fare;
import com.ibm.abcairlines.entity.Flight;
import com.ibm.abcairlines.entity.Passenger;
import com.ibm.abcairlines.service.BookingService;
import com.ibm.abcairlines.service.FareService;
import com.ibm.abcairlines.service.FlightService;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest extends AbcAirlinesApplicationTests {
	
	@InjectMocks
	BookingController bookingController;
	
	@Mock
	FlightService flightService;
	
	@Mock
	BookingService bookingService;
	
	@Mock
	FareService fareService;

	private MockMvc mockMvc; 
	
	String source = "chennai";
	String destination = "mumbai";
	String dateString = "2021-03-18";
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}
	
	@Test
	public void testbookTicket() throws Exception {
		
		BookingDto bookingDto = getBookingDto();
		Flight flight = getFlight();
		when(flightService.searchFlight(anyString(), anyString(), any(Date.class))).thenReturn(flight);
		when(flightService.checkSeatAvailability(flight, bookingDto.getPreferredClass())).thenReturn(Boolean.TRUE);
		Fare fare = getFare();
		when(fareService.getFareByFlightId(anyInt())).thenReturn(fare);
		when(bookingService.confirmBooking(any(Passenger.class), any(Flight.class), any(Date.class), any(PreferredClass.class), any(Fare.class))).thenReturn(getBooking());
		
		mockMvc.perform(post("/bookTicket").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(bookingDto)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.source", Matchers.equalTo(source)))
				.andExpect(jsonPath("$.date", Matchers.equalTo(dateString)));
	}
	
	@Test
	public void testbookTicket_noFlightFound() throws Exception {
		BookingDto bookingDto = getBookingDto();
		mockMvc.perform(post("/bookTicket").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(bookingDto)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.Error", Matchers.equalTo("No flight found for the selected cities")));
	}
	
	@Test
	public void testbookTicket_noSeatsAvailable() throws Exception {
		BookingDto bookingDto = getBookingDto();
		Flight flight = getFlight();
		when(flightService.searchFlight(anyString(), anyString(), any(Date.class))).thenReturn(flight);
		when(flightService.checkSeatAvailability(flight, bookingDto.getPreferredClass())).thenReturn(Boolean.FALSE);
		mockMvc.perform(post("/bookTicket").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(bookingDto)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.Sorry!", Matchers.equalTo("No " + bookingDto.getPreferredClass().toString() + " seats available")));
	}
	
	
	@Test
	public void testGetTicket_noBookingFoundForGivenId() throws Exception {
		int id = 1;
		MvcResult result = mockMvc.perform(get("/getTicket").param("id", id+"")).andExpect(status().isOk()).andReturn();
		assertEquals("<h1>Welcome to ABC Airlines </h1> <h2>No booking found with the id:" + id + " </h2>", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetTicket() throws Exception {
		int id = 1;
		when(bookingService.getBookingById(id)).thenReturn(getBooking());
		MvcResult result = mockMvc.perform(get("/getTicket").param("id", id+"")).andExpect(status().isOk()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("<h1>Welcome to ABC Airlines </h1> "));
		assertTrue(result.getResponse().getContentAsString().contains(getPassenger().getName()));
	}
	
	@Test
	public void testGetAllBookings() throws Exception {
		List<Booking> bookings = List.of(getBooking());
		when(bookingService.getBookings()).thenReturn(bookings);
		mockMvc.perform(get("/all-bookings")).andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(1)))
		.andExpect(jsonPath("$[0].id", Matchers.equalTo(1)));
	}

	private Booking getBooking() {
		Booking booking = new Booking();
		booking.setId(1);
		booking.setFare(getFare().getEconomyClassFare());
		booking.setPassenger(getPassenger());
		booking.setFlight(getFlight());
		booking.setFare(getFare().getEconomyClassFare());
		return booking;
	}

	private BookingDto getBookingDto() {
		BookingDto bookingDto = new BookingDto();
		bookingDto.setDestination(destination);
		bookingDto.setPreferredClass(PreferredClass.ECONOMYCLASS);
		bookingDto.setSource(source);
		bookingDto.setDate(Date.valueOf(dateString ));
		bookingDto.setPassenger(getPassenger());
		return bookingDto;
	}

	public Passenger getPassenger() {
		Passenger passenger = new Passenger();
		passenger.setName("Riya");
		passenger.setAge(23);
		passenger.setContact(12345678);
		passenger.setGender(Gender.FEMALE);
		return passenger;
	}
	
	private Flight getFlight() {
		Flight flight = new Flight();
		flight.setId(101);
		flight.setSource(source);
		flight.setDestination(destination);
		flight.setDate(Date.valueOf(dateString));
		return flight;
	}
	
	private Fare getFare() {
		Fare fare = new Fare();
		fare.setFlight(getFlight());
		fare.setBusinessClassFare(4000);
		fare.setEconomyClassFare(2000);
		return fare;
	}
}
