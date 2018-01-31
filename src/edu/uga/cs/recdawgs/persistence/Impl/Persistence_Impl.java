package edu.uga.cs.recdawgs.persistence.Impl;

import edu.uga.cs.recdawgs.persistence.Persistable;

public class Persistence_Impl implements Persistable{

	private long id;
	public Persistence_Impl(){
		this.id=-1;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id=id;
	}

	@Override
	public boolean isPersistent() {
		System.out.println("In Is Persistent");
		System.out.println("id::"+id);
		return id>=0;
	}
}
