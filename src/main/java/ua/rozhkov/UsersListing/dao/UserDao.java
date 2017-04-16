package ua.rozhkov.UsersListing.dao;


import ua.rozhkov.UsersListing.entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface UserDao {
	
	List <User> getAll();
	
	void addUser(User user);
	
	void editUser(User user);
	
	void deleteUser(int id);
	
	List <User> searchBetweenDate(LocalDate start, LocalDate end);
}
