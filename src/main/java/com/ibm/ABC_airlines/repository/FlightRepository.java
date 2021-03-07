package com.ibm.ABC_airlines.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ABC_airlines.entity.Flight;

@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	public Flight findBySourceAndDestinationAndDate(String source, String destination, Date d);

}
