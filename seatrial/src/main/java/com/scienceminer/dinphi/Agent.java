package com.scienceminer.dinphi;

public class Agent implements Runnable { 

	private int _id; 
	private State _state; 
	private Controller _c; 
	public static Integer MAX_EAT_MULTIPLIER = 1000; 
	public static Integer THINKING_MULTIPLIER = 500; 

	Agent(int id, Controller c)  {
		_id = id ;
		_c = c;
		_state = State. THINK; 

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
		System.out.println(" ID: "  + _id +  " LEFT ::"  + leftId + 	" RIGHT :: " +  rightId  ) ; 
		while (true) { 	
			// attempt to acquire LEFT and RIGHT resources 
			// keep whatever resource you get! 
			synchronized(this) { // is this sync needed? no difference in behaviour 

				if (!hasLeft) 
				{
					hasLeft = _c. acquireResource (leftId, this); 
					if (hasLeft) 
						System.out.println(_id + " has got LEFT RESOURCE: " + leftId); 
				}

				if (!hasRight) 
				{
					hasRight = _c. acquireResource (rightId, this); 
					if (hasRight) 
						System.out.println(_id + " has got RIGHT RESOURCE : " + rightId); 
				}
			}	

			// if successful - transition state for up to IØ 
			// - release resources 


			if (hasLeft && hasRight) 
			{
				System.out.println( _id + " ACQUIRED RESOURCES " + leftId + " and " + rightId );

				this.setState (State. EAT) ; 
				System.out. println( _id + " Eating for a while " );

				try { 
					Thread.sleep( (long) (Math.random() * Agent.MAX_EAT_MULTIPLIER ));
				} catch (InterruptedException e) { 
					throw new RuntimeException(e); 
				}

				System.out.println( _id + " Finished Eating " );

				hasLeft = _c.dropResource(leftId , this );
				hasRight = _c.dropResource(rightId, this );
				this. setState(State. THINK) ; 
				thinkingCounter = 0;  // just started to think. 

				System.out.println( _id + " DROPPED RESOURCES " + leftId + " and " +rightId );

			}
			else 
			{

				// holding at most ONE resource so think for a bit 
				this.
				setState(State.THINK) ; 

				System.out.print( _id + " Thinking for a while " );
				if (hasLeft) 
					System.out.print(" whilst holding left: " + leftId );
				if (hasRight) 
					System.out.print(" whilst holding right: " + rightId );

				System.out.println ( " \t THINKING FOR: " + thinkingCounter );

				try { 
					Thread.sleep ( (long) (Math. random( )  * Agent.THINKING_MULTIPLIER ));
				} catch (InterruptedException e) { 
					throw new RuntimeException(e); 
				}

				thinkingCounter++; 

				if (thinkingCounter == 5) 
				{
					System.out.println( _id + " Thinking for so long... 	I'm hungry " );
					this. setState (State. HUNGRY) ; 
				}

				// so releasing a resource when you 're starving stops deadlock 
				if (thinkingCounter == 10) 
				{
					System.out.println( _id + " Thinking for so long...  starving " );
					this.setState (State. STARVING) ; 
					thinkingCounter = 0; 
					// reset as we are now STARVING and cannot hold any 
					synchronized (this) { 
						if (hasLeft) 
						{
							System.out.println( _id + " dropping my left " );
							hasLeft = _c. dropResource (leftId, this); 
						}

						if (hasRight)  
						{
							System.out.println( _id + " dropping my right " );
							hasRight = _c. dropResource (rightId, this) ; 
						}

					}

				}

			}

		}

	} // end run

}