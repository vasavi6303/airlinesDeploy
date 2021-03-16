package com.ibm.abcairlines.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ibm.abcairlines.entity.Fare;
import com.ibm.abcairlines.entity.Flight;
import com.ibm.abcairlines.service.FareService;

@ExtendWith(MockitoExtension.class)
public class FareControllerTest {

	@Mock
	private FareService fareService;

	@InjectMocks
	private FareController fareController;

	private MockMvc mockMvc;

	private int id = 1;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(fareController).build();
	}

	@Test
	public void testGetFareByFlightId() throws Exception {
		when(fareService.getFareByFlightId(id)).thenReturn(getFare());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fare-by-flight-id").param("id",
				id + "");
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains(
				"<h1>Welcome to ABC Airlines </h1> <h2>Here are the fare details for requested flightId: </h2"));
		assertTrue(result.getResponse().getContentAsString().contains("<h1>Welcome to ABC Airlines </h1>"));
		assertTrue(result.getResponse().getContentAsString()
				.contains("<strong>Flight ID: </strong>" + getFlight().getId()));
	}

	private Fare getFare() {
		Fare fare = new Fare();
		fare.setFlight(getFlight());
		fare.setBusinessClassFare(4000);
		fare.setEconomyClassFare(2000);
		return fare;
	}

	private Flight getFlight() {
		Flight flight = new Flight();
		flight.setId(id);
		flight.setAvailableBusinessClassSeats(30);
		flight.setAvailableEconomyClassSeats(30);
		flight.setDate(Date.valueOf("2021-03-10"));
		flight.setSource("chennai");
		flight.setDestination("Hyderabad");

		return flight;
	}

}
