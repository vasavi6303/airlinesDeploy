package com.ibm.ABC_airlines.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fare {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	int id;

	@Column(name = "business_class_fare", nullable = false)
	int businessClassFare;

	@Column(name = "economy_class_fare", nullable = false)
	int economyClassFare;

	@OneToOne(cascade = { CascadeType.ALL })
	private Flight flight;
	
	public Fare() {
		
	}

	public Fare(int id, int businessClassFare, int economyClassFare, Flight flight) {
		super();
		this.id = id;
		this.businessClassFare = businessClassFare;
		this.economyClassFare = economyClassFare;
		this.flight= flight;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusinessClassFare() {
		return businessClassFare;
	}

	public void setBusinessClassFare(int businessClassFare) {
		this.businessClassFare = businessClassFare;
	}

	public int getEconomyClassFare() {
		return economyClassFare;
	}

	public void setEconomyClassFare(int economyClassFare) {
		this.economyClassFare = economyClassFare;
	}

}
