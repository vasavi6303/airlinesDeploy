package com.ibm.abcairlines.service;

import java.util.List;

import com.ibm.abcairlines.entity.Passenger;

public interface PassengerService {

	public Boolean save(Passenger p);

	public Passenger getPassengerById(int id);

	public Passenger getPassengerByName(String name);

	public List<Passenger> getPassengers();
}
