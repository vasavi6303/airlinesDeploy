package com.ibm.ABC_airlines.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ABC_airlines.entity.Passenger;

@Transactional
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	public Passenger findTopByName(String name);

}
