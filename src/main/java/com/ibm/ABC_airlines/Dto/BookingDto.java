package com.ibm.ABC_airlines.Dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.ibm.ABC_airlines.Common.Common.PreferredClass;
import com.ibm.ABC_airlines.entity.Passenger;

@Component
public class BookingDto {

	private Passenger passenger;

	private String source;

	private String destination;

	private Date date;

	private PreferredClass preferredClass;

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PreferredClass getPreferredClass() {
		return preferredClass;
	}

	public void setPreferredClass(PreferredClass preferredClass) {
		this.preferredClass = preferredClass;
	}

}
