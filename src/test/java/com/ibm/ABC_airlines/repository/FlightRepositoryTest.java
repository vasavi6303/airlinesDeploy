package com.ibm.ABC_airlines.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.ABC_airlines.entity.Flight;



@SpringBootTest
//@ExtendWith(MockitoExtension.class)
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

//	@Test
//	@DisplayName("Test Flight not found for non existing id")
//	public void testFlightSavedSuccesfully() {
//
//		Flight mockFlight = new Flight();
//
//		mockFlight.setDate(Date.valueOf("2021-05-10"));
//		mockFlight.setDestination("kolkata");
//		mockFlight.setSource("Chennai");
//		mockFlight.setAvailableBusinessClassSeats(0);
//		mockFlight.setAvailableEconomyClassSeats(0);
//
//		Flight savedFlight = repository.save(mockFlight);
//
//		assertNotNull(savedFlight, "Flight should be saved");
//		assertNotNull(savedFlight.getId(), "Flight should have id after saving");
//		assertEquals(mockFlight.getId(), savedFlight.getId());
//
//	}

}

