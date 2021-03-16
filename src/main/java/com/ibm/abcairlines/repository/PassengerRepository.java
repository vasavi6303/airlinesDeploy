package com.ibm.abcairlines.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.abcairlines.entity.Passenger;

@Transactional
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	public Passenger findTopByName(String name);

}
