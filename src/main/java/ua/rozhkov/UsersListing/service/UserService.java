package ua.rozhkov.UsersListing.service;

import ua.rozhkov.UsersListing.entity.User;

import java.sql.Date;
import java.util.List;

public interface UserService {
	
	List <User> getAll();
	
	void addUser(User user);
	
	void editUser(User user);
	
	void deleteUser(int id);
	
	List <User> searchBetweenDate(Date start, Date end);
	
	List <User> filterUsers(List <User> userList, String expression);
}
