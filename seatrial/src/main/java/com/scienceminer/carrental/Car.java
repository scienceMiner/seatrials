package com.scienceminer.carrental;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;


public class Car extends Vehicle {
	public String make;
	public String reg;
	public int category;
	public boolean hired;
	public Date hireEnd;
	private int age;
	private long hireNumber;

	public Car(String reg) {
		super(reg);
	}

	public Car(String reg, String make, int category) {
		super(reg,make,category);
	}

}
