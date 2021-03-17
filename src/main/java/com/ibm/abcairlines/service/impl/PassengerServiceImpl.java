package com.ibm.abcairlines.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.abcairlines.entity.Passenger;
import com.ibm.abcairlines.repository.PassengerRepository;
import com.ibm.abcairlines.service.PassengerService;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passengerRepo;

	@Override
	public Boolean save(Passenger c) {
		if (getPassengerByName(c.getName()) == null) {
			passengerRepo.save(c);
			return true;
		}
		return false;
	}

	@Override
	public Passenger getPassengerById(int id) {
		return passengerRepo.findById(id).orElse(null);
	}

	@Override
	public List<Passenger> getPassengers() {
		return passengerRepo.findAll();
	}

	@Override
	public Passenger getPassengerByName(String name) {
		return passengerRepo.findTopByName(name);
	}

}
