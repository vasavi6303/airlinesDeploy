package com.ibm.abcairlines.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.abcairlines.entity.Fare;
import com.ibm.abcairlines.repository.FareRepository;
import com.ibm.abcairlines.service.FareService;

@Service
public class FareServiceImpl implements FareService {

	@Autowired
	private FareRepository fareRepo;

	@Override
	public Boolean save(Fare f) {
		fareRepo.save(f);
		return true;
	}

	@Override
	public Fare getFareById(int id) {
		return fareRepo.findById(id).orElse(null);
	}

	@Override
	public List<Fare> getFares() {
		return fareRepo.findAll();
	}

	@Override
	public void deleteFareById(int id) {
		fareRepo.deleteById(id);
	}

	@Override
	public Fare getFareByFlightId(int flightid) {
		return fareRepo.findByFlightId(flightid);
	}

}
