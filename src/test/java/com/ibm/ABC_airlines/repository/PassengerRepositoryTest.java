package com.ibm.ABC_airlines.repository;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.ABC_airlines.entity.Passenger;

@SpringBootTest
public class PassengerRepositoryTest {
	@Autowired
	private PassengerRepository passengerRepository;

	@Test
	@DisplayName("Passenger not found for given name")
	public void testPassengerForNonExistingName() {
		Passenger getPassenger = passengerRepository.findTopByName("harika");
		assertNull(getPassenger);
	}
}