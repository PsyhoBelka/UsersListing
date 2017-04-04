package ua.rozhkov.UsersListing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ua.rozhkov.UsersListing.controller.UsersListController;

import java.io.IOException;

public class Starter extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Stage usersListStage = new Stage();
		usersListStage.setTitle("UsersAccounting");
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(System.class.getResource("/fxml/usersList.fxml"));
		GridPane usersListGridPane = fxmlLoader.load();
		
		Scene usersListScene = new Scene(usersListGridPane);
		
		UsersListController usersListController = fxmlLoader.getController();
		usersListController.setUsersListStage(usersListStage);
		
		usersListStage.setScene(usersListScene);
		usersListStage.show();
	}
}
