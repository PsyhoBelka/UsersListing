package ua.rozhkov.UsersListing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.dbcp2.BasicDataSource;
import ua.rozhkov.UsersListing.controller.UsersListController;
import ua.rozhkov.UsersListing.dao.UserDao;
import ua.rozhkov.UsersListing.dao.jdbcUserDao.JdbcUserDao;
import ua.rozhkov.UsersListing.dao.mapper.UserMapper;
import ua.rozhkov.UsersListing.service.ServiceLocator;
import ua.rozhkov.UsersListing.service.UserService;
import ua.rozhkov.UsersListing.service.implementation.JdbcUserService;

import java.io.IOException;

public class Starter extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("PsyhoBelka");
		dataSource.setPassword("admin");
		
		//UserMapper userMapper = new UserMapper();
		UserDao jdbcUserDao = new JdbcUserDao(dataSource);
		UserService userService = new JdbcUserService(jdbcUserDao);
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		serviceLocator.register(UserService.class, userService);
		//UsersListController usersListController = new UsersListController();
		//		UserEditingController userEditingController=new UserEditingController(userService);
		
		
		Stage usersListStage = new Stage();
		usersListStage.setTitle("UsersAccounting");
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(System.class.getResource("/fxml/usersList.fxml"));
		//fxmlLoader.setController(usersListController);
		GridPane usersListGridPane = fxmlLoader.load();
		
		Scene usersListScene = new Scene(usersListGridPane);
		
				UsersListController usersListController = fxmlLoader.getController();
		usersListController.setUsersListStage(usersListStage);
		
		usersListStage.setScene(usersListScene);
		usersListStage.show();
	}
}
