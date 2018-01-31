package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.User;

public class UserImplementor implements User{

	private long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	public UserImplementor(String firstName,String lastName,String userName,String password,String email){
		
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.password=password;
		this.email=email;
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
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		this.firstName=firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		this.lastName=lastName;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		this.userName=userName;
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
	public String gettype_user() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void settype_user() {
		// TODO Auto-generated method stub
		
	}

}
