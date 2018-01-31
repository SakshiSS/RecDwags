package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.SportsVenue;

public class SportsVenueImplementor implements SportsVenue {

	private long id;
	private String name;
	private boolean isIndoor;
	private String address;
	public SportsVenueImplementor(){
		id=-1;
		name=null;
		isIndoor=false;
		address=null;
	}

	public SportsVenueImplementor(String name,String address,boolean isIndoor){
		this.id=-1;
		this.name=name;
		this.isIndoor=isIndoor;
		this.address=address;
		
	}
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
	  this.id=id;	
	}

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return id>=0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
	}

	@Override
	public boolean getIsIndoor() {
		// TODO Auto-generated method stub
		return isIndoor;
	}

	@Override
	public void setIsIndoor(boolean isIndoor) {
		// TODO Auto-generated method stub
		this.isIndoor=isIndoor;
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public void setAddress(String address) {
		// TODO Auto-generated method stub
		this.address=address;
	}

}
