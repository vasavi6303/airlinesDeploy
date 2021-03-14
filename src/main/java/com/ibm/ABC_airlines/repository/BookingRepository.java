package com.ibm.ABC_airlines.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ABC_airlines.entity.Booking;

@Transactional
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
