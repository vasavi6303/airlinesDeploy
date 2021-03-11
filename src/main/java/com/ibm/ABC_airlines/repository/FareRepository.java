package com.ibm.ABC_airlines.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ABC_airlines.entity.Fare;

@Transactional
public interface FareRepository extends JpaRepository<Fare, Integer> {

	public Fare findByFlightId(int flight_id);

}
