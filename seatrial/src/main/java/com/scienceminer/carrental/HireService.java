package com.scienceminer.carrental;

import java.sql.Date;
import java.sql.SQLException;


/*
 * Main hire service class
 */
public class HireService {
	private DbService db;
	
	//called by rest servlet to make booking
	public long hire(String name, String licenseNumber, String cd, String reg, String start, int days, double rate) throws SQLException {
		boolean hired = false;
		Client client = getClient(name, licenseNumber, cd);
		Car car = getCarDetails(reg, cd);
		if(car == null || car.hired) {
			return -1;
		}
		
		long hireNumber = System.nanoTime(); //use as unique id
		HireRecord hire = new HireRecord();
		client.addHirerecord(hire);
		hire.setCar(car); hire.setDays(days);
		hire.setClient(client.getName());		
		hire.setStartDate(Date.valueOf(start));
		hire.setRate(rate); hire.setState(1);
		hire.setHireno(hireNumber);
		hire.setDays(days);		
		db.saveToDatabase(client, cd);
		car.hire(db, cd, hire);
		return hireNumber;
	}
	
	public void markReturned(String cd, long hireno)  {
		try {
			Car car = (Car) db.loadFromDb(cd, "select * from crs where hrrnm = " + hireno, Car.class);
			car.release(db, cd);
		} catch (SQLException e) {
			//nothing we can do
		}
	}
	
	public Car getCarDetails(String rg, String cd)  {
		try {
			return (Car) db.loadFromDb(cd, "select * from crs where rg = " + rg,Car.class);
		} catch (SQLException e) {
			return new Car(rg);
		}
	}
	
	//dont use
	private DbService getDb() {
		return db;
	}

	public void setDb(DbService db) {
		this.db = db;
	}

	public Client getClient(String name, String licenseNumber, String cd)  {
		Client client;
		try {
			client = (Client) db.loadFromDb(cd, "select * from clients where clientId = " + name,Client.class);
		} catch (Exception e1) {
			throw new RuntimeException();
		}
		
		if(client == null) {
			client = new Client(name,licenseNumber,cd);
			try {
				db.saveToDatabase(client, cd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return client;
	}
}
