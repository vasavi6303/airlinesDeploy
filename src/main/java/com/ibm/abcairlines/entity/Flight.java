package com.ibm.abcairlines.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "source", nullable = false)
	private String source;

	@Column(name = "destination", nullable = false)
	private String destination;

	@Column(name = "business_class_count", nullable = false)
	private int availableBusinessClassSeats = 30;

	@Column(name = "economy_class_count", nullable = false)
	private int availableEconomyClassSeats = 30;

	@Column(name = "date")
	private Date date;

	public Flight() {
	}

	public Flight(String source, String destination,
			Date date) {
		this.source = source;
		this.destination = destination;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getAvailableBusinessClassSeats() {
		return availableBusinessClassSeats;
	}

	public void setAvailableBusinessClassSeats(int availableBusinessClassSeats) {
		this.availableBusinessClassSeats = availableBusinessClassSeats;
	}

	public int getAvailableEconomyClassSeats() {
		return availableEconomyClassSeats;
	}

	public void setAvailableEconomyClassSeats(int availableEconomyClassSeats) {
		this.availableEconomyClassSeats = availableEconomyClassSeats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
