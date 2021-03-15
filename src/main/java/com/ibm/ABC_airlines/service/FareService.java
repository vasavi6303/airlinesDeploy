package com.ibm.ABC_airlines.service;

import java.util.List;

import com.ibm.ABC_airlines.entity.Fare;

public interface FareService {

	public Boolean save(Fare f);

	public Fare getFareById(int id);

	public List<Fare> getFares();

	public void deleteFareById(int id);

	public Fare getFareByFlightId(int flightid);

}
