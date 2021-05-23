package com.scienceminer.dinphi;

public class Resource {

	private int _id;
	private boolean inUse;
	private Agent _user;

	Resource(int id ) {
		_id = id;
		inUse = false;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id ) {
		this._id = _id;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse ) {
		this.inUse = inUse;
	}

	public Agent getUser() {
		return _user;
	}

	public void setUser(Agent _user) {
		this._user = _user;
	}


}


