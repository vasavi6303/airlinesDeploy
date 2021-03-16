package com.ibm.abcairlines.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.abcairlines.entity.Flight;

@SpringBootTest

public class FlightRepositoryTest {

	@Autowired
	private FlightRepository repository;

	@Test
	@DisplayName("Test Flight not found for non existing id")
	public void testFlightNotFoundForNonExistingId() {

		Flight retrievedFlight = repository.findBySourceAndDestinationAndDate("Pune", "Chennai",
				Date.valueOf("2021-04-02"));

		assertEquals(1, retrievedFlight.getId());

	}

}
