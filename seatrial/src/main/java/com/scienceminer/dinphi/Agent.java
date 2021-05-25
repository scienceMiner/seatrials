package com.scienceminer.dinphi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Agent implements Runnable { 

	final static Logger logger = LogManager.getLogger(Agent.class);

	private int _id; 
	private State _state; 
	private Controller _c; 
	public static Integer MAX_EAT_MULTIPLIER = 1000; 
	public static Integer THINKING_MULTIPLIER = 500; 
	public Boolean preventDeadlock;
	
	Agent(int id, Controller c)  {
		_id = id ;
		_c = c;
		_state = State.THINK; 

	}

	public int getId() { 
		return _id; 
	}

	public void setld(int id) { 
		this._id = id; 
	}

	public State getState() { 
		return _state; 
	}

	public void setState(State _state) {
		this._state = _state; 
	}
	
	public void setDeadlockPrevent(Boolean prevent) {
		this.preventDeadlock = prevent; 
	}

	@Override 
	public 
	void run() { 
		// for each agent kick off a thread 
		// thread tries to eat by grabbing both left and right - if it can it will 
		// eat for a random amount of time up to IØ sg.gs. 
		// then enter state THINK for a random amount up to IØ 

		boolean hasLeft = false; 
		boolean hasRight = false; 
		int leftId = _c.getLeft(this) ; 
		int rightId = _c.getRight (this) ; 
		int thinkingCounter = 0; 
		logger.debug(" ID: "  + _id +  " LEFT ::"  + leftId + 	" RIGHT :: " +  rightId  ) ; 
		while (true) { 	
			// attempt to acquire LEFT and RIGHT resources 
			// keep whatever resource you get! 
			synchronized(this) { // is this sync needed? no difference in behaviour 

				if (!hasLeft) 
				{
					hasLeft = _c. acquireResource (leftId, this); 
					if (hasLeft) 
						logger.debug(_id + " has got LEFT RESOURCE: " + leftId); 
				}

				if (!hasRight) 
				{
					hasRight = _c. acquireResource (rightId, this); 
					if (hasRight) 
						logger.debug(_id + " has got RIGHT RESOURCE : " + rightId); 
				}
			}	

			// if successful - transition state for up to IØ 
			// - release resources 


			if (hasLeft && hasRight) 
			{
				logger.debug( _id + " ACQUIRED RESOURCES " + leftId + " and " + rightId );

				this.setState (State. EAT) ; 
				logger.debug( _id + " Eating for a while " );

				try { 
					Thread.sleep( (long) (Math.random() * Agent.MAX_EAT_MULTIPLIER ));
				} catch (InterruptedException e) { 
					throw new RuntimeException(e); 
				}

				logger.debug( _id + " Finished Eating " );

				hasLeft = _c.dropResource(leftId , this );
				hasRight = _c.dropResource(rightId, this );
				this. setState(State. THINK) ; 
				thinkingCounter = 0;  // just started to think. 

				logger.debug( _id + " DROPPED RESOURCES " + leftId + " and " +rightId );

			}
			else 
			{

				// holding at most ONE resource so think for a bit 
				this.setState(State.THINK) ; 

				logger.debug( _id + " Thinking for a while " );
				if (hasLeft) 
					logger.debug(" whilst holding left: " + leftId );
				if (hasRight) 
					logger.debug(" whilst holding right: " + rightId );

				logger.debug( " \t THINKING FOR: " + thinkingCounter );

				try { 
					Thread.sleep ( (long) (Math. random( )  * Agent.THINKING_MULTIPLIER ));
				} catch (InterruptedException e) { 
					throw new RuntimeException(e); 
				}

				thinkingCounter++; 

				if (thinkingCounter == 5) 
				{
					logger.debug( _id + " Thinking for so long... 	I'm hungry " );
					this.setState (State.HUNGRY) ; 
				}

				// so releasing a resource when you 're starving stops deadlock 
				if (thinkingCounter == 10) 
				{
					logger.debug( _id + " Thinking for so long...  starving " );
					this.setState (State.STARVING) ; 
					
					if (preventDeadlock) 
					{
						thinkingCounter = 0; 

						// reset as we are now STARVING and cannot hold any 

						synchronized (this) { 
							if (hasLeft) 
							{
								logger.debug( _id + " dropping my left " );
								hasLeft = _c. dropResource (leftId, this); 
							}

							if (hasRight)  
							{
								logger.debug( _id + " dropping my right " );
								hasRight = _c. dropResource (rightId, this) ; 
							}

						}

					}
					
					if (thinkingCounter % 100 == 0 )
					{
						logger.debug( _id + " MIGHT as well be DEAD:  " + thinkingCounter  );
					}
				}

			}

		}

	} // end run

}