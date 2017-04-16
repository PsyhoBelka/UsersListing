package ua.rozhkov.UsersListing.dao.mapper;

import org.junit.Test;
import org.mockito.Mockito;
import ua.rozhkov.UsersListing.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserMapperTest {
	@Test
	public void testMap() throws Exception {
		//before
		ResultSet resultSet= Mockito.mock(ResultSet.class);
		when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(resultSet.getInt("id")).thenReturn(0).thenReturn(1);
		when(resultSet.getString("firstName")).thenReturn("Alex").thenReturn("Kate");
		when(resultSet.getString("lastName")).thenReturn("Rozhkov").thenReturn("Guz");
		when(resultSet.getFloat("salary")).thenReturn(Float.valueOf(100)).thenReturn(Float.valueOf(200));
		when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf("1988-01-01")).thenReturn(Date.valueOf("1990-01-01"));
		//when
		UserMapper userMapper=new UserMapper();
		List<User> usersList=userMapper.map(resultSet);
		//then
		assertEquals(2,usersList.size());
		User userOne=usersList.get(0);
		assertEquals(0,userOne.getId());
		assertEquals("Alex",userOne.getFirstName());
		
		User userTwo=usersList.get(1);
		assertEquals(200,userTwo.getSalary(),20);
		assertEquals(Date.valueOf("1990-01-01"),userTwo.getDateOfBirth());
	}
	
}