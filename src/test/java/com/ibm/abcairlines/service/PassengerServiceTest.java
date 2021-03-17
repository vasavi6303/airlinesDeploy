package com.ibm.abcairlines.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.abcairlines.entity.Passenger;
import com.ibm.abcairlines.repository.PassengerRepository;
import com.ibm.abcairlines.service.impl.PassengerServiceImpl;


@SpringBootTest
public class PassengerServiceTest {
	
	@InjectMocks
	PassengerServiceImpl passengerService;
	
	@Mock
	public PassengerRepository passengerRepository;
	
	private String passengerName = "John";
	
	@Test
	@DisplayName("Test when passenger Id is found")
	public void testgetPassengerByValidId() {
		when(passengerRepository.findById(1)).thenReturn(Optional.of(mock(Passenger.class)));
		Passenger passenger = passengerService.getPassengerById(1);
		assertNotNull(passenger);
	}
	
	@Test
	@DisplayName("Test when passenger Id is not found")
	public void testgetPassengerByInvalidId() {
		Passenger passenger = passengerService.getPassengerById(1);
		assertNull(passenger);
	}
	
	@Test
	public void testSavePassenger() {
		assertTrue(passengerService.save(getPassenger()));
	}
	
	@Test
	public void testSavePassenger_passengerAlreadyExists() {
		when(passengerRepository.findTopByName(getPassenger().getName())).thenReturn(getPassenger());
		assertFalse(passengerService.save(getPassenger()));
	}
	
	@Test
	public void testGetPassengers() {
		List<Passenger> list = new ArrayList<>();
		list.add(getPassenger());
		when(passengerRepository.findAll()).thenReturn(list);
		assertEquals(1, passengerService.getPassengers().size());
	}
	
	@Test
	public void testGetPassengerByName() {
		when(passengerRepository.findTopByName(passengerName)).thenReturn(getPassenger());
		assertNotNull(passengerService.getPassengerByName(passengerName));
		
	}
	
	private Passenger getPassenger() {
		Passenger passenger = new Passenger();
		passenger.setName(passengerName);
		return passenger;
	}
	
	

}
