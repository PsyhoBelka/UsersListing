package dao.mapper;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
	
	List<User> map(ResultSet resultSet) throws SQLException {
		List<User> usersList=new ArrayList <User>();
		User user=new User();
		user.setId(resultSet.getInt("id"));;
		user.setFirstName(resultSet.getString("firsName"));
		user.setLastName(resultSet.getString("lastName"));
		user.setSalary(resultSet.getFloat("salary"));
		user.setAge(resultSet.getInt("age"));
		user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
		usersList.add(user);
		return usersList;
	}
}
