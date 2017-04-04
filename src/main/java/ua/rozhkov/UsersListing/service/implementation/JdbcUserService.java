package ua.rozhkov.UsersListing.service.implementation;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import ua.rozhkov.UsersListing.dao.UserDao;
import ua.rozhkov.UsersListing.dao.jdbcUserDao.JdbcUserDao;
import ua.rozhkov.UsersListing.entity.User;
import ua.rozhkov.UsersListing.service.UserService;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;

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
	
	@Override
	public List <User> filterList(List <User> userList, final String expression) {
		FilteredList <User> usersFilteredList = new FilteredList <>((ObservableList <User>) userList, null);
		usersFilteredList.setPredicate(new Predicate <User>() {
			@Override
			public boolean test(User user) {
				return ((user.getFirstName().toLowerCase().contains(expression)) && (user.getFirstName() != null));
			}
		});
		return usersFilteredList;
	}
}
