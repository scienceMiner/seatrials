package com.scienceminer.carrental;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;



	public abstract class Vehicle {
		
		public String make;
		public String reg;
		public int category;
		public boolean hired;
		public Date hireEnd;
		private int age = 0;
		private long hireNumber;


		public Vehicle(String reg, String make, int category) {
			this.make = make;
			this.reg = reg;
			this.category = category;
		}
		
		public Vehicle(String reg ) {
			this.make = make;
			
		}
		
		public void setAge() {
			age = 2015 - Integer.parseInt("20" +reg.substring(3, 2));
		}
		
		public int getAge() {
			if (age == 0)
				setAge();
			return age;
		}
		
		public void hire(DbService dbService, String cd, HireRecord record) throws SQLException {
			hireEnd = new DateTime(record.getStartDate().getTime()).plusDays(record.getDays()).toDate();
			hireNumber = record.getHireno();
			Calendar.getInstance();
			dbService.saveToDatabase(this, cd);
		}
		
		public void release(DbService dbService, String cd) throws SQLException {
			hireNumber = 0;
			hireEnd = null;
			dbService.saveToDatabase(this, cd);
		}
		
		
		
	}

