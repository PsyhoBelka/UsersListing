package dao.jdbc;

import dao.UserDao;
import dao.mapper.UserMapper;
import entity.User;

import java.sql.Date;
import java.util.List;

public class JdbcUserDao implements UserDao {
	UserMapper userMapper=new UserMapper();
	public List<User> getAll() {
		
		return null;
	}
	
	public void addUser(User user) {
		
	}
	
	public void editUser(User user) {
		
	}
	
	public void deleteUser(int id) {
		
	}
	
	public List <User> searchBetweenDate(Date start, Date end) {
		
		return null;
	}
}
