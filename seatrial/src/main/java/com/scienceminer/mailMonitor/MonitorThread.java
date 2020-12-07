package com.scienceminer.mailMonitor;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.scienceminer.calcs.LifeDays;

public class MonitorThread  implements Runnable {

	public static final Logger log4j = Logger.getLogger(MonitorThread.class);
	
	public MonitorThread()
	{
		
	}

	//	@Override
	public void run()
	{

		LifeDays ld = new LifeDays();

		boolean olderFileExists = false;
		int secondsInDay = 86400;
		for( ;; )
		{

			try
			{

				String interestingDateFound = ld.checkForInterestingDates();

				if (interestingDateFound != null ) {
					log4j.info(" sending mail ");
					Mailer.sendMail( "Lifedays notification" , interestingDateFound);
				}

				TimeUnit.SECONDS.sleep(secondsInDay);

			}
			catch (InterruptedException ex)
			{
				log4j.info(" Interrupted Exception " + ex.getLocalizedMessage());
				continue;
			}
			catch (Exception ex)
			{
				log4j.info(" Exception " + ex.getLocalizedMessage());
				continue;

			}

		}
	}

	public boolean inRange()
	{
		Calendar current = Calendar.getInstance();

		if (current.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY &&
				current.get(Calendar.HOUR_OF_DAY) > 22 )
			return true;

		if (current.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY  &&
				current.get(Calendar.HOUR_OF_DAY) < 5)
			return true;

		if (current.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY &&
				current.get(Calendar.HOUR_OF_DAY) > 22)
			return true;

		if (current.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY &&
				current.get(Calendar.HOUR_OF_DAY) < 5)
			return true;


		return false;

	}

}
