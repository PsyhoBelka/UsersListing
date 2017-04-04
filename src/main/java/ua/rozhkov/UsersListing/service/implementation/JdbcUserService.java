package ua.rozhkov.UsersListing.service.implementation;

import ua.rozhkov.UsersListing.dao.UserDao;
import ua.rozhkov.UsersListing.dao.jdbcUserDao.JdbcUserDao;
import ua.rozhkov.UsersListing.entity.User;
import ua.rozhkov.UsersListing.service.UserService;

import java.sql.Date;
import java.util.List;

public class JdbcUserService implements UserService {
	private UserDao jdbcUserDao = new JdbcUserDao();
	
	@Override
	public List <User> getAll() {
		return jdbcUserDao.getAll();
	}
	
	@Override
	public void addUser(User user) {
		jdbcUserDao.addUser(user);
	}
	
	@Override
	public void editUser(User user) {
		jdbcUserDao.editUser(user);
	}
	
	@Override
	public void deleteUser(int id) {
		jdbcUserDao.deleteUser(id);
	}
	
	@Override
	public List <User> searchBetweenDate(Date start, Date end) {
		return jdbcUserDao.searchBetweenDate(start, end);
	}
}