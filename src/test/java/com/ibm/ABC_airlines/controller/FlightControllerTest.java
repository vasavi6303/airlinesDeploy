package com.ibm.ABC_airlines.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.ABC_airlines.entity.Flight;
import com.ibm.ABC_airlines.service.FlightService;

@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {

	@Mock
	FlightService flightService;

	@InjectMocks
	FlightController flightController;

	private MockMvc mockMvc;

	String source = "chennai";
	String destination = "mumbai";
	String date = "2021-03-06";

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
	}

	@Test
	public void testGetFlights() throws Exception {
		when(flightService.getFlights()).thenReturn(getFlights());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/flights");
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].source", Matchers.equalTo(source)));
	}

	@Test
	public void testSearchFlight() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("source", source);
		map.put("destination", destination);
		map.put("date", date);

		when(flightService.getFlightBySourceAndDestinationAndDate(source, destination, Date.valueOf(date)))
				.thenReturn(getFlight());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/search-flight")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(map));

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(jsonPath("source", Matchers.equalTo(source)));
	}

	@Test
	public void testSearchFlight_whenNoFlightFound() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("source", source);
		map.put("destination", destination);
		map.put("date", date);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/search-flight")
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(map));

		mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
				.andExpect(jsonPath("error", Matchers.equalTo("No flight found for the given criteria")));
	}

	private List<Flight> getFlights() {
		List<Flight> flights = new ArrayList<>();
		flights.add(getFlight());
		return flights;
	}

	private Flight getFlight() {
		Flight flight = new Flight();
		flight.setId(101);
		flight.setSource(source);
		flight.setDestination(destination);
		flight.setDate(Date.valueOf(date));
		return flight;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
