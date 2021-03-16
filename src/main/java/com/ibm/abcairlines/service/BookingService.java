package com.ibm.abcairlines.service;

import java.sql.Date;
import java.util.List;

import com.ibm.abcairlines.common.Common.PreferredClass;
import com.ibm.abcairlines.entity.Booking;
import com.ibm.abcairlines.entity.Fare;
import com.ibm.abcairlines.entity.Flight;
import com.ibm.abcairlines.entity.Passenger;

public interface BookingService {

	public Booking save(Booking b);

	public Booking getBookingById(int id);

	public List<Booking> getBookings();

	public void deleteBookingById(int id);

	public Booking confirmBooking(Passenger passenger, Flight flight, Date date, PreferredClass preferredClass,
			Fare fare);
}
