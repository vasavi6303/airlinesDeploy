package com.ibm.ABC_airlines.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.ABC_airlines.AbcAirlinesApplication;
import com.ibm.ABC_airlines.entity.Fare;



@SpringBootTest
public class FareRepositoryTest extends AbcAirlinesApplication{
	@Autowired
	FareRepository repository;
	
	@Test
	@DisplayName("Test Fare not found for non existing id")
	public void testFareNotFoundForNonExistingId() {
		Fare retrievedFare = repository.findByFlightId(1);
		assertEquals(4000, retrievedFare.getBusinessClassFare());

	}

}