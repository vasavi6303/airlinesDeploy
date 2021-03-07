package com.ibm.ABC_airlines.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.ABC_airlines.Common.Common.PreferredClass;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "on_boarding_date")
	private Date onBoardingDate;

	@Column(name = "booked_date")
	@CreationTimestamp
	private Date bookedDate;

	@OneToOne(cascade = { CascadeType.ALL })
	private Passenger passenger;

	@JsonIgnore
	@OneToOne(cascade = { CascadeType.ALL })
	private Flight flight;

	@Column(name = "fare")
	private int fare;

	@Enumerated(EnumType.STRING)
	@Column(name = "preferred_class")
	private PreferredClass preferredClass;

	public Booking() {

	}

	public Booking(Passenger passenger, Flight flight, Date onBoardingDate, int fare, PreferredClass preferredClass) {
		this.onBoardingDate = onBoardingDate;
		this.passenger = passenger;
		this.flight = flight;
		this.fare = fare;
		this.preferredClass = preferredClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOnBoardingDate() {
		return onBoardingDate;
	}

	public void setOnBoardingDate(Date onBoardingDate) {
		this.onBoardingDate = onBoardingDate;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public PreferredClass getPreferredClass() {
		return preferredClass;
	}

	public void setPreferredClass(PreferredClass preferredClass) {
		this.preferredClass = preferredClass;
	}

}
