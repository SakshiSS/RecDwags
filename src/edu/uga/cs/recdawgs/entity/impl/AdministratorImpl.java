package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Administrator;

public class AdministratorImpl implements Administrator{

	private long id;
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private String password;
	private String type_user;
	
	public AdministratorImpl(String firstname,String lastname,String username,String password,String email){
		
		this.username=username;
		this.email=email;
		this.firstname=firstname;
		this.lastname=lastname;
		this.password=password;
	}
	
	public String gettype_user(){
		return type_user;
	}
	
	public void settype_user(){
		this.type_user=type_user;
	}
	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstname;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		this.firstname=firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastname;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		this.lastname=lastName;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		this.username=userName;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password=password;
	}

	@Override
	public String getEmailAddress() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		this.email=emailAddress;
	}

	
	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return id>=0;
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

}
