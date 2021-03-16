package com.ibm.abcairlines.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.abcairlines.entity.Fare;

@Transactional
public interface FareRepository extends JpaRepository<Fare, Integer> {

	public Fare findByFlightId(int flightid);

}
