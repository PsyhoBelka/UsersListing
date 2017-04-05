package ua.rozhkov.UsersListing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ua.rozhkov.UsersListing.controller.UserEditingController;
import ua.rozhkov.UsersListing.controller.UsersListController;
import ua.rozhkov.UsersListing.dao.UserDao;
import ua.rozhkov.UsersListing.dao.jdbcUserDao.JdbcUserDao;
import ua.rozhkov.UsersListing.dao.mapper.UserMapper;
import ua.rozhkov.UsersListing.service.UserService;
import ua.rozhkov.UsersListing.service.implementation.JdbcUserService;

import java.io.IOException;

public class Starter extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		UserMapper userMapper=new UserMapper();
		UserDao jdbcUserDao = new JdbcUserDao(userMapper);
		UserService userService=new JdbcUserService(jdbcUserDao);
		UsersListController usersListController=new UsersListController(userService);
//		UserEditingController userEditingController=new UserEditingController(userService);
		
		
		Stage usersListStage = new Stage();
		usersListStage.setTitle("UsersAccounting");
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(System.class.getResource("/fxml/usersList.fxml"));
		fxmlLoader.setController(usersListController);
		GridPane usersListGridPane = fxmlLoader.load();
		
		Scene usersListScene = new Scene(usersListGridPane);
		
//		UsersListController usersListController = fxmlLoader.getController();
		usersListController.setUsersListStage(usersListStage);
		
		usersListStage.setScene(usersListScene);
		usersListStage.show();
	}
}
