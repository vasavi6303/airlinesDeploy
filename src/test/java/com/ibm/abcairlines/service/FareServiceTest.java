package com.ibm.abcairlines.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.abcairlines.entity.Fare;
import com.ibm.abcairlines.repository.FareRepository;
import com.ibm.abcairlines.service.impl.FareServiceImpl;


@SpringBootTest
public class FareServiceTest {
	
	@InjectMocks
	private FareServiceImpl service;

	@Mock
	private FareRepository repository;

	@Test
	@DisplayName("Testing FareService when flight Id found")
	public void testFareService() {
		when(repository.findByFlightId(1)).thenReturn(mock(Fare.class));
		Fare fare = service.getFareByFlightId(1);
		assertNotNull(fare);
	}
	
	@Test
	@DisplayName("Testing FareService with invalid FlightId")
	public void testFareServiceByInvalidId() {
		Fare fare = service.getFareByFlightId(1);
		assertNull(fare);
		
	}
	
}