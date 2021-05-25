package com.scienceminer.dinphi;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

	final static Logger logger = LogManager.getLogger(Controller.class);

	private int _size = 0;
	private ArrayList<Resource>  _resources;
	private ArrayList<Agent>  _agents;

	Controller ( int size ) {
		_size = size;
		initialiseResources(size);
		initialiseAgents(5,this);
	}

	// Left is always DOWN, Right is UP
	public int getLeft(Agent a) {
		
		int id = a.getId(); 

		// to the left is always the same id 
		return id; 

	}

	public int getRight (Agent a) {

		int id = a.getId();

		if (id > 1 && id <= _size)
			return id-1;
		else
			return _size;
	}

	private void initialiseResources(int limit) 
	{
		_resources = new ArrayList<Resource>(); 

		for (int i = 1; i <= limit; i++ ) {
			_resources.add(new Resource(i));
		}
	}


	public boolean acquireResource(int id, Agent a)  {
		Resource r = _resources. get (id-1); 

		if (!r.isInUse() && r.getUser() == null ) 
		{
			synchronized (this) { 
				r.setInUse(true);
				r.setUser(a);
			}
			
			return true;
			
		}
		return false; 

	}


	public boolean dropResource(int id, Agent a) {
		Resource r = _resources. get(id-1); 
		if (r.isInUse() && r.getUser().getId() == 	a.getId() ) 
		{
			synchronized (this) { 
				r.setInUse(false); 
				r.setUser (null) ; 
			}
			return true; 
		}
		return false; 
	}

	private void initialiseAgents(int limit, Controller c ) {
		
		_agents = new ArrayList<Agent>(); 
		
		for (int i = 1; i <= limit; i++) 
		{
			_agents. add(new Agent(i, c) );
		}

	}


	public static void main (String[] args)  {

		boolean preventDeadlock = false;
		String arg;
		int j = 0;
		while (j < args.length && args[j].startsWith("-")) {
            arg = args[j++];

            // use this type of check for "deadlock" arg
            if (arg.equals("-deadlock")) {
                System.out.println("deadlock mode off");
                preventDeadlock = true;
            }
		}
		
		int limit = 5; 
		Controller c1 = new Controller(limit); 
		Thread threads[] = new Thread[limit]; 
		int i = 0; 

		Monitor m = new Monitor();
		
		// for each agent kick off a thread 
		// thread tries to eat by grabbing both left and right - if it can it will 
		// eat for a random amount of time up to 10 
		// then enter state THINK for a random amount up to 10 
		// if resource hasn't eaten for a while it will die 
		for ( Agent a : c1._agents ) {
			a.setDeadlockPrevent(preventDeadlock);
			threads[i] = new Thread(a); 
			//a.run(); // will execute on current thread! !! 
			threads [i++].start(); 
			m.addAgent(a);
			logger.debug(" --> Next thread: 	" + a.getId() ) ;
		}

		new Thread(m).start();
		
	}

}