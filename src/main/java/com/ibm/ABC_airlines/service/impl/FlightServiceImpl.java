package com.ibm.ABC_airlines.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ABC_airlines.Common.Common.PreferredClass;
import com.ibm.ABC_airlines.entity.Flight;
import com.ibm.ABC_airlines.repository.FlightRepository;
import com.ibm.ABC_airlines.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Override
	public Flight save(Flight f) {
		return flightRepo.save(f);

	}

	@Override
	public Flight getFlightById(int id) {
		return flightRepo.findById(id).orElse(null);
	}

	@Override
	public List<Flight> getFlights() {
		return flightRepo.findAll();
	}

	@Override
	public void deleteFlightById(int id) {
		flightRepo.deleteById(id);
	}

	@Override
	public Flight getFlightBySourceAndDestinationAndDate(String source, String destination, Date d) {
		return flightRepo.findBySourceAndDestinationAndDate(source, destination, d);
	}

	@Override
	public Flight searchFlight(String source, String destination, Date date) {
		return flightRepo.findBySourceAndDestinationAndDate(source, destination, date);
	}

	@Override
	public Boolean checkSeatAvailability(Flight flight, PreferredClass preferredClass) {
		return !(preferredClass == PreferredClass.BUSINESSCLASS && flight.getAvailableBusinessClassSeats() <= 0
				|| flight.getAvailableEconomyClassSeats() <= 0);

	}

	@Override
	public void updateSeatAvaialability(Flight flight, PreferredClass preferredClass) {
		if (preferredClass == PreferredClass.BUSINESSCLASS)
			flight.setAvailableBusinessClassSeats(flight.getAvailableBusinessClassSeats() - 1);
		else
			flight.setAvailableEconomyClassSeats(flight.getAvailableEconomyClassSeats() - 1);
		flight = flightRepo.save(flight);

	}

}