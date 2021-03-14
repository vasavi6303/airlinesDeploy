package com.ibm.ABC_airlines.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ABC_airlines.entity.Passenger;
import com.ibm.ABC_airlines.repository.PassengerRepository;
import com.ibm.ABC_airlines.service.PassengerService;

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
	public List<Passenger> getCustomers() {
		return passengerRepo.findAll();
	}

	@Override
	public void deletePassengerById(int id) {
		passengerRepo.deleteById(id);

	}

	@Override
	public Passenger getPassengerByName(String name) {
		return passengerRepo.findTopByName(name);
	}

}
