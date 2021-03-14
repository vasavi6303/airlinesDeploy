package com.ibm.ABC_airlines.service;

import java.sql.Date;
import java.util.List;

import com.ibm.ABC_airlines.Common.Common.PreferredClass;
import com.ibm.ABC_airlines.entity.Booking;
import com.ibm.ABC_airlines.entity.Fare;
import com.ibm.ABC_airlines.entity.Flight;
import com.ibm.ABC_airlines.entity.Passenger;

public interface BookingService {

	public Booking save(Booking b);

	public Booking getBookingById(int id);

	public List<Booking> getBookings();

	public void deleteBookingById(int id);

	public Booking confirmBooking(Passenger passenger, Flight flight, Date date, PreferredClass preferredClass,
			Fare fare);
}
