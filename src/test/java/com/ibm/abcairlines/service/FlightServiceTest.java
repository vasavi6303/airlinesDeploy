package com.ibm.abcairlines.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.abcairlines.common.Common.PreferredClass;
import com.ibm.abcairlines.entity.Flight;
import com.ibm.abcairlines.repository.FlightRepository;
import com.ibm.abcairlines.service.impl.FlightServiceImpl;

@SpringBootTest
public class FlightServiceTest {
	@Autowired
	FlightServiceImpl service;

	@MockBean
	private FlightRepository repository;

	@Test

	public void testFlightbySourceDestinationAndDate() {
		Flight mockFlight = new Flight();
		mockFlight.setId(1);
		mockFlight.setDate(Date.valueOf("2021-04-07"));
		mockFlight.setDestination("Goa");
		mockFlight.setSource("Nagpur");
		mockFlight.setAvailableBusinessClassSeats(0);
		mockFlight.setAvailableEconomyClassSeats(30);

		doReturn(mockFlight).when(repository).findBySourceAndDestinationAndDate("Goa", "Nagpur",
				Date.valueOf("2021-04-07"));

		Flight foundFlight = service.getFlightBySourceAndDestinationAndDate("Goa", "Nagpur",
				Date.valueOf("2021-04-07"));
		assertNotNull(foundFlight);
		assertSame("Goa", foundFlight.getDestination());

	}

	@Test
	public void testGetFlights() {

		Flight mockFlight1 = new Flight();
		mockFlight1.setId(1);
		mockFlight1.setDate(Date.valueOf("2021-04-07"));
		mockFlight1.setDestination("Goa");
		mockFlight1.setSource("Nagpur");
		mockFlight1.setAvailableBusinessClassSeats(0);
		mockFlight1.setAvailableEconomyClassSeats(30);
		Flight mockFlight2 = new Flight();
		mockFlight2.setId(2);
		mockFlight2.setDate(Date.valueOf("2021-04-09"));
		mockFlight2.setDestination("Amritsar");
		mockFlight2.setSource("Nagpur");
		mockFlight2.setAvailableBusinessClassSeats(20);
		mockFlight2.setAvailableEconomyClassSeats(0);

		doReturn(Arrays.asList(mockFlight1, mockFlight2)).when(repository).findAll();

		Iterable<Flight> flights = service.getFlights();
		assertEquals(2, ((Collection<?>) flights).size());

	}

	@Test

	public void testSaveFlight() {
		Flight mockFlight = new Flight();
		mockFlight.setId(1);
		mockFlight.setDate(Date.valueOf("2021-04-07"));
		mockFlight.setDestination("Goa");
		mockFlight.setSource("Nagpur");
		mockFlight.setAvailableBusinessClassSeats(0);
		mockFlight.setAvailableEconomyClassSeats(30);

		doReturn(mockFlight).when(repository).save(mockFlight);
		Flight foundAllFlight = service.save(mockFlight);
		assertNotNull(foundAllFlight);
		assertEquals("Goa", foundAllFlight.getDestination());

	}

	public Boolean CheckSeat(PreferredClass preferredClass, String source, String destination, Date date) {

		Flight fid = service.searchFlight(source, destination, date);

		if (fid != null) {
			return service.checkSeatAvailability(fid, preferredClass);
		} else
			return false;

	}

	@Test
	public void checkSeat() {
		Date d1 = Date.valueOf("2021-04-07");
		Flight fid = service.searchFlight("Pune", "Delhi", d1);
		System.out.print(fid);
		// assertTrue(CheckSeat(PreferredClass.BUSINESSCLASS, 6));
		// assertFalse(CheckSeat(PreferredClass.ECONOMYCLASS, 8));
		// assertTrue(CheckSeat(PreferredClass.BUSINESSCLASS, 4));
		assertFalse(CheckSeat(PreferredClass.BUSINESSCLASS, "Pune", "Delhi", d1));
		// assertFalse(CheckSeat(PreferredClass.BUSINESSCLASS, 11));
		// assertFalse(CheckSeat(PreferredClass.ECONOMYCLASS, 3));

	}

}
