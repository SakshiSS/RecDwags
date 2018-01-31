package edu.uga.cs.recdawgs.entity.impl;

import edu.uga.cs.recdawgs.entity.Student;

public class StudentImplementor implements Student{

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String studentid;
	private long id;
	private String majors;
	private String address;
	public StudentImplementor(String firstName,String lastName,String userName,String password,String email,String studentid,String majors,String address){
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.password=password;
		this.email=email;
		this.studentid=studentid;
		
		this.majors=majors;
		this.address=address;
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
	public String getStudentId() {
		// TODO Auto-generated method stub
		return studentid;
	}

	@Override
	public void setStudentId(String studentId) {
		// TODO Auto-generated method stub
		this.studentid=studentId;
	}

	@Override
	public String getMajor() {
		// TODO Auto-generated method stub
		return majors;
	}

	@Override
	public void setMajor(String major) {
		// TODO Auto-generated method stub
		this.majors=major;
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
