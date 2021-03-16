package com.ibm.abcairlines.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.abcairlines.AbcAirlinesApplicationTests;
import com.ibm.abcairlines.common.Common.Gender;
import com.ibm.abcairlines.common.Common.PreferredClass;
import com.ibm.abcairlines.dto.BookingDto;
import com.ibm.abcairlines.entity.Passenger;

@SpringBootTest
@AutoConfigureMockMvc

public class BookingControllerTest extends AbcAirlinesApplicationTests {
	@Mock
	BookingController bookingController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testbookTicket() throws Exception {
		BookingDto bookingDto = new BookingDto();
		bookingDto.setDestination("Delhi");
		bookingDto.setPreferredClass(PreferredClass.BUSINESSCLASS);
		bookingDto.setSource("Pune");
		bookingDto.setDate(Date.valueOf("2021-04-07"));
		bookingDto.setPassenger(getPassenger());

		doReturn(getBookingdetails()).when(bookingController).bookTicket(ArgumentMatchers.any());
		mockMvc.perform(post("/bookTicket").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(new ObjectMapper().writeValueAsString(bookingDto)))

				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.destination", is("Delhi")));

	}

	public Map<String, Object> getBookingdetails() {
		Map<String, Object> map = new HashMap<>();
		map.put("passenger", getPassenger());
		map.put("source", "Pune");
		map.put("destination", "Delhi");
		map.put("date", Date.valueOf("2021-04-07"));
		map.put("preferredClass", PreferredClass.BUSINESSCLASS);
		return map;
	}

	public Passenger getPassenger() {
		Passenger passenger = new Passenger();
		passenger.setName("vasavi");
		passenger.setAge(23);
		passenger.setContact(12345678);
		passenger.setGender(Gender.FEMALE);

		return passenger;
	}
}
