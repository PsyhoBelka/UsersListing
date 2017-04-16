package ua.rozhkov.UsersListing.dao.jdbcUserDao;

import org.apache.commons.dbcp2.BasicDataSource;
import ua.rozhkov.UsersListing.dao.mapper.UserMapper;
import ua.rozhkov.UsersListing.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class JdbcUserDao implements ua.rozhkov.UsersListing.dao.UserDao {
	
	private String getAllSQL = "SELECT u.id,u.firstName,u.lastName,u.salary, u.age,u.dateOfBirth FROM mydb.user AS u";
	private String addUserSQL = "INSERT INTO mydb.user (firstName,lastName,salary,age,dateOfBirth) VALUES(?,?,?,?,?)";
	private String editUserSQL = "UPDATE mydb.user SET `firstName`=?, `lastName`=?, `salary`=?, `age`=?, dateOfBirth=? WHERE `id`=?";
	private String deleteUserSQL = "DELETE FROM mydb.user WHERE `id`=?";
	private String searchBetweenDateSQL = "SELECT * FROM mydb.user WHERE dateofbirth BETWEEN ? AND ?";
	
	//	private UserMapper userMapper = new UserMapper();
	private UserMapper userMapper = new UserMapper();
	private BasicDataSource dataSource;
	
	public JdbcUserDao(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List <User> getAll() {
		
		try (Connection connection = connect();
			 PreparedStatement preparedStatement = connection.prepareStatement(getAllSQL);
			 ResultSet resultSet = preparedStatement.executeQuery(getAllSQL)) {
			
			return userMapper.map(resultSet);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addUser(User user) {
		
		try (Connection connection = connect();
			 PreparedStatement preparedStatement = connection.prepareStatement(addUserSQL)) {
			
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDouble(3, user.getSalary());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.setDate(5, Date.valueOf(user.getDateOfBirth()));
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editUser(User user) {
		
		try (Connection connection = connect();
			 PreparedStatement preparedStatement = connection.prepareStatement(editUserSQL)) {
			
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDouble(3, user.getSalary());
			preparedStatement.setInt(4, user.getAge());
			preparedStatement.setDate(5, Date.valueOf(user.getDateOfBirth()));
			preparedStatement.setInt(6, user.getId());
			preparedStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id) {
		
		if (id >= 0) {
			try (Connection connection = connect();
				 PreparedStatement preparedStatement = connection.prepareStatement(deleteUserSQL)) {
				
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List <User> searchBetweenDate(LocalDate start, LocalDate end) {
		
		try (Connection connection = connect();
			 PreparedStatement preparedStatement = connection.prepareStatement(searchBetweenDateSQL)) {
			
			preparedStatement.setDate(1, Date.valueOf(start));
			preparedStatement.setDate(2, Date.valueOf(end));
			ResultSet resultSet = preparedStatement.executeQuery();
			return userMapper.map(resultSet);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Connection connect() throws SQLException {
		/*String url = "jdbc:mysql://127.0.0.1:3306/mydb";
		String user_name = "PsyhoBelka";
		String password = "admin";*/
		
		return dataSource.getConnection();
	}
}
