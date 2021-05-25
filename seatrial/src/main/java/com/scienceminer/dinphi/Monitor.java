package com.scienceminer.dinphi;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Monitor implements Runnable { 

	final static Logger logger = LogManager.getLogger(Monitor.class);

	private Thread[] _threadsToMonitor; 
	private int SLEEPTIME = 5000;
	private ArrayList<Agent> agentList = new ArrayList<Agent>() ;
	
	public Monitor( )  {
		
	}
	
	public void addAgent(Agent a) {
		agentList.add(a);
	}

	@Override 
	public void run() { 

		while (true ) {
			
			try { 
				Thread.sleep( (long) SLEEPTIME );
			} catch (InterruptedException e) { 
				throw new RuntimeException(e); 
			}

			if (isDeadLocked())
				logger.info( "deadlocked" );
		}
	} // end run

	
   public boolean isDeadLocked( ) {
	   boolean isDeadlocked = true;
	   
	   for (Agent a : agentList) {
		   if ( a.getState() == State.THINK ) {
			   logger.debug(" Agent a : " + a.getId() + "  STATE: " + a.getState() ) ;
			   
		   }
		   else
			   isDeadlocked = false;
	   }
	   
	   if (isDeadlocked) {
		   for (Agent a : agentList) {
			   if ( a.getState() == State.THINK ) {
				   logger.info(" Agent a : " + a.getId() + "  STATE: " + a.getState() ) ;   
			   }
		   }
	   }
	   
	   return isDeadlocked;
   }
   
   
}