package com.ibm.ABC_airlines.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ABC_airlines.entity.Fare;
import com.ibm.ABC_airlines.service.FareService;

@RestController
public class FareController {

	@Autowired
	FareService fareService;

	@GetMapping("/fare-by-flight-id")
	public String getFareByFlightId(@RequestParam(name = "id", required = true) int id) {
		Fare fare = fareService.getFareByFlightId(id);
		if (fare == null)
			return "<h1>Welcome to ABC Airlines </h1> <h2>Kindly enter valid flight Id</h2>";
		return "<h1>Welcome to ABC Airlines </h1> " + "<h2>Here are the fare details for requested flightId: </h2>"
				+ "<strong>Flight ID: </strong>" + fare.getFlight().getId() + "<br><strong>Date: </strong>"
				+ fare.getFlight().getDate() + "<br><strong>Source: </strong> " + fare.getFlight().getSource()
				+ "<br><strong>Destination: </strong>" + fare.getFlight().getDestination()
				+ "<br><strong>Available BusinessClass Seats: </strong>"
				+ fare.getFlight().getAvailableBusinessClassSeats()
				+ "<br><strong>Available EconomyClass Seats:</strong>"
				+ +fare.getFlight().getAvailableEconomyClassSeats() + "<br><strong>Business Class Fare </strong>"
				+ fare.getBusinessClassFare() + "<br><strong>Economy Class Fare </strong>" + fare.getEconomyClassFare();
	}

}
