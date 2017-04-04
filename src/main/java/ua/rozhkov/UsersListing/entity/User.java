package ua.rozhkov.UsersListing.entity;

import java.sql.Date;

public class User {
	private int id;
	private String firstName;
	private String lastName;
	private float salary;
	private int age;
	private Date dateOfBirth;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public float getSalary() {
		return salary;
	}
	
	public int getAge() {
		return age;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"id=" + this.id +
				"firstName=" + this.firstName +
				"lastName=" + this.lastName +
				"salary=" + this.salary +
				"dateOfBirth=" + this.dateOfBirth.toString() + "}";
	}
}

