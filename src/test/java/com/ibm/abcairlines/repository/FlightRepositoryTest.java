package com.ibm.abcairlines.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.abcairlines.entity.Flight;

@SpringBootTest
public class FlightRepositoryTest {
	@Autowired
	private FlightRepository repository;

	private static File DATA_JSON = Paths.get("resources", "flights.json").toFile();

	@BeforeEach
	void setup() throws JsonParseException, JsonMappingException, IOException {

		Flight flights[] = new ObjectMapper().readValue(DATA_JSON, Flight[].class);
		Arrays.stream(flights).forEach(repository::save);

	}

	@AfterEach

	void tearDown() {

		repository.deleteAll();
	}

	@Test
	@DisplayName("Test Flight not found for non existing id")
	public void testFlightNotFoundForNonExistingDate() {

		// given 4 flights in the database

		// when
		Flight retrievedFlight = repository.findBySourceAndDestinationAndDate("Pune", "Chennai",
				Date.valueOf("2022-04-02"));

		// then

		assertNull(retrievedFlight, "Flight not available");

	}

	@Test
	@DisplayName("Test Flight not available")
	public void testFlightSavedSuccesfully() {

		// Prepare Mock Product
		Flight flight = new Flight();
		flight.setId(1);
		flight.setSource("Pune");
		flight.setDestination("Chennai");
		flight.setAvailableBusinessClassSeats(30);
		flight.setAvailableEconomyClassSeats(30);

		// when
		Flight savedFlight = repository.save(flight);

		// then

		assertNotNull(savedFlight, "Flight should be saved");
		assertNotNull(savedFlight.getId(), "Flight should have id after saving");
		assertEquals(flight.getId(), 1);
		assertEquals(flight.getSource(), "Pune");
		assertEquals(flight.getDestination(), "Chennai");
		assertEquals(flight.getAvailableBusinessClassSeats(), 30);
		assertEquals(flight.getAvailableEconomyClassSeats(), 30);

	}

}
