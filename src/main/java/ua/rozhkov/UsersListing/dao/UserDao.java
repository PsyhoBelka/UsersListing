package ua.rozhkov.UsersListing.dao;


import ua.rozhkov.UsersListing.entity.User;

import java.sql.Date;
import java.util.List;

public interface UserDao {
	
	List<User> getAll();
	
	void addUser (User user);
	
	void editUser (User user);
	
	void deleteUser(int id);
	
	List<User> searchBetweenDate(Date start, Date end);
}
