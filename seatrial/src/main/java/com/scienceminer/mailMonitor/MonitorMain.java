package com.scienceminer.mailMonitor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MonitorMain {

	public static final Logger log4j = LogManager.getLogger(MonitorMain.class);
	
	public static void main(String[] args)
	{
		
		try
		{
			
			log4j.debug("Start Monitoring... ");
			
			// 4. Start a cache monitor thread
			MonitorThread lMon = new MonitorThread();			
			Thread t = new Thread(lMon);
			t.start();
			
			
		}
		catch (Exception ex)
		{
			log4j.error(" IO Exception " + ex.getLocalizedMessage());
			
		}
		
	}
	
	
	

	
	private static Date createDate(String inputDate)
	{
		 DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); 
         Date startDate=null;
         
         try 
         {
             startDate = df.parse(inputDate);
         } catch (ParseException e) 
         {
             e.printStackTrace();
         }
         
         return startDate;
	}
}
