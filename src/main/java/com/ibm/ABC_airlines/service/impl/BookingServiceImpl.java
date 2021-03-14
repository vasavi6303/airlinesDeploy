package com.ibm.ABC_airlines.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ABC_airlines.Common.Common.PreferredClass;
import com.ibm.ABC_airlines.entity.Booking;
import com.ibm.ABC_airlines.entity.Fare;
import com.ibm.ABC_airlines.entity.Flight;
import com.ibm.ABC_airlines.entity.Passenger;
import com.ibm.ABC_airlines.repository.BookingRepository;
import com.ibm.ABC_airlines.service.BookingService;
import com.ibm.ABC_airlines.service.FareService;
import com.ibm.ABC_airlines.service.FlightService;
import com.ibm.ABC_airlines.service.PassengerService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	FlightService flightService;

	@Autowired
	PassengerService passengerService;

	@Autowired
	FareService fareService;

	@Autowired
	BookingService bookingService;

	@Override
	public Booking save(Booking b) {
		return bookingRepo.save(b);
	}

	@Override
	public Booking getBookingById(int id) {
		return bookingRepo.findById(id).orElse(null);
	}

	@Override
	public List<Booking> getBookings() {
		return bookingRepo.findAll();
	}

	@Override
	public void deleteBookingById(int id) {
		bookingRepo.deleteById(id);
	}

	@Override
	public Booking confirmBooking(Passenger passenger, Flight flight, Date date, PreferredClass preferredClass,
			Fare fare) {
		Booking booked = null;
		passengerService.save(passenger);
		if (preferredClass == PreferredClass.BUSINESSCLASS)
			booked = bookingService
					.save(new Booking(passenger, flight, date, fare.getBusinessClassFare(), preferredClass));
		else if (preferredClass == PreferredClass.ECONOMYCLASS)
			booked = bookingService
					.save(new Booking(passenger, flight, date, fare.getEconomyClassFare(), preferredClass));

		flightService.updateSeatAvaialability(flight, preferredClass);
		return booked;
	}

}
