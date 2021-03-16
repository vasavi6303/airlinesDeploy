package com.ibm.abcairlines.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.abcairlines.entity.Booking;

@Transactional
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
