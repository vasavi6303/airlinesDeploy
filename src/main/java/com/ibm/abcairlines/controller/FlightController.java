package com.ibm.abcairlines.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.abcairlines.entity.Flight;
import com.ibm.abcairlines.handler.ControllerExceptionHandler;
import com.ibm.abcairlines.service.FlightService;

@RestController
public class FlightController {

	@Autowired
	FlightService flightService;

	@GetMapping("/flights")
	public List<Flight> getFlights() {
		return flightService.getFlights();
	}

	@PostMapping("/search-flight")
	public ResponseEntity<?> searchFlight(@RequestBody Map<String, String> inputMap) {
		Flight flight = flightService.getFlightBySourceAndDestinationAndDate(inputMap.get("source"),
				inputMap.get("destination"), Date.valueOf(inputMap.get("date")));
		if (flight == null)
			return new ResponseEntity<ControllerExceptionHandler>(
					new ControllerExceptionHandler("No flight found for the given criteria"), HttpStatus.NOT_FOUND);
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);
	}

}
