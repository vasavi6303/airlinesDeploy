package com.ibm.ABC_airlines.service;

import java.util.List;

import com.ibm.ABC_airlines.entity.Passenger;

public interface PassengerService {

	public Boolean save(Passenger p);

	public Passenger getPassengerById(int id);

	public Passenger getPassengerByName(String name);

	public List<Passenger> getCustomers();

	public void deletePassengerById(int id);

}
