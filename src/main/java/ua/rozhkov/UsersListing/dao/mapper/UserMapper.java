package ua.rozhkov.UsersListing.dao.mapper;

import ua.rozhkov.UsersListing.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
	
	public List <User> map(ResultSet resultSet) throws SQLException {
		List <User> usersList = new ArrayList <User>();
		while (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setFirstName(resultSet.getString("firstName"));
			user.setLastName(resultSet.getString("lastName"));
			user.setSalary(resultSet.getFloat("salary"));
			user.setAge(resultSet.getInt("age"));
			user.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
			usersList.add(user);
		}
		return usersList;
	}
}
