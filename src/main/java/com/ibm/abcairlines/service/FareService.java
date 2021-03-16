package com.ibm.abcairlines.service;

import java.util.List;

import com.ibm.abcairlines.entity.Fare;

public interface FareService {

	public Boolean save(Fare f);

	public Fare getFareById(int id);

	public List<Fare> getFares();

	public void deleteFareById(int id);

	public Fare getFareByFlightId(int flightid);

}
