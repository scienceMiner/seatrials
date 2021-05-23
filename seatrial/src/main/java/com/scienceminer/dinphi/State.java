package com.scienceminer.dinphi;

public enum State {
	EAT, THINK, HUNGRY, STARVING;

	public State transition(State s1) {

		if (s1.equals(State.EAT))
		{
			return State.THINK;
		}
		else
			return State.EAT;

	}

}
