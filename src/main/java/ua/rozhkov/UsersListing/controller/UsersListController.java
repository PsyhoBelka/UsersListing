package ua.rozhkov.UsersListing.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ua.rozhkov.UsersListing.entity.User;
import ua.rozhkov.UsersListing.service.UserService;
import ua.rozhkov.UsersListing.service.implementation.JdbcUserService;

import java.io.IOException;
import java.util.Date;

public class UsersListController {
	
	private ObservableList <User> usersObservableList;
	private Stage usersListStage;
	private UserService userService = new JdbcUserService();
	
	@FXML
	Label usersListLabel;
	@FXML
	GridPane usersListGridPane;
	@FXML
	Button addButton;
	@FXML
	Button editSelectedButton;
	@FXML
	Button deleteSelectedButton;
	@FXML
	Button refreshUsersListButton;
	@FXML
	Button exitButton;
	
	@FXML
	TableView <User> usersTableView;
	@FXML
	TableColumn <User, Integer> usersIdColumn;
	@FXML
	TableColumn <User, String> usersFirstNameColumn;
	@FXML
	TableColumn <User, String> usersLastNameColumn;
	@FXML
	TableColumn <User, Float> usersSalaryColumn;
	@FXML
	TableColumn <User, Integer> usersAgeColumn;
	@FXML
	TableColumn <User, Date> usersDateOfBirthColumn;
	
	@FXML
	TextField searchFirstNameEdit;
	@FXML
	TextField searchLastNameEdit;
	@FXML
	DatePicker startDateSearch;
	@FXML
	DatePicker endDateSearch;
	@FXML
	Button dateSearchButton;
	
	public void initialize() {
		usersIdColumn.setCellValueFactory(new PropertyValueFactory <User, Integer>("id"));
		usersFirstNameColumn.setCellValueFactory(new PropertyValueFactory <User, String>("firstName"));
		usersLastNameColumn.setCellValueFactory(new PropertyValueFactory <User, String>("lastName"));
		usersSalaryColumn.setCellValueFactory(new PropertyValueFactory <User, Float>("salary"));
		usersAgeColumn.setCellValueFactory(new PropertyValueFactory <User, Integer>("age"));
		usersDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory <User, Date>("dateOfBirth"));
		
		refreshUsersListButton.fire();
	}
	
	public void setUsersListStage(Stage stage) {
		usersListStage = stage;
	}
	
	@FXML
	public void refreshUsersListButtonClick(ActionEvent actionEvent) {
		usersObservableList = FXCollections.observableArrayList(userService.getAll());
		usersTableView.setItems(FXCollections.observableArrayList(usersObservableList));
	}
	
	@FXML
	public void exitButtonClick(ActionEvent actionEvent) {
		usersListStage.close();
	}
	
	@FXML
	public void addButtonClick(ActionEvent actionEvent) throws IOException {
		showUserEditing(false);
	}
	
	@FXML
	public void editSelectedButtonClick(ActionEvent actionEvent) throws IOException {
		showUserEditing(true);
	}
	
	@FXML
	public void deleteSelectedButtonClick(ActionEvent actionEvent) {
		userService.deleteUser(usersTableView.getSelectionModel().getSelectedItem().getId());
		refreshUsersListButton.fire();
	}
	
	@FXML
	public void searchFirstNameEditKeyReleased(KeyEvent keyEvent) {
		if (keyEvent.getCode() == KeyCode.getKeyCode(KeyCode.ENTER.getName())) {
			usersTableView.setItems((ObservableList <User>) userService.filterList(usersObservableList, searchFirstNameEdit.getText()));
		} else if (keyEvent.getCode() == KeyCode.getKeyCode(KeyCode.ESCAPE.getName())) {
			searchFirstNameEdit.clear();
			usersTableView.setItems(usersObservableList);
		}
	}
	
	@FXML
	public void dateSearchButtonClick(ActionEvent actionEvent) {
		userService.searchBetweenDate(java.sql.Date.valueOf(startDateSearch.getValue()), java.sql.Date.valueOf(endDateSearch.getValue()));
	}
	
	private void showUserEditing(boolean mode) throws IOException {
		Stage userEditingStage = new Stage();
		userEditingStage.setTitle("Edit user's info");
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(System.class.getResource("/fxml/userEditing.fxml"));
		BorderPane borderPane = fxmlLoader.load();
		
		final UserEditingController userEditingController = fxmlLoader.getController();
		userEditingController.setUserEditingStage(userEditingStage);
		userEditingController.setUserEditingMode(mode);
		userEditingController.setUsersListController(this);
		if (mode) {
			userEditingController.setUser(getSelectedUser());
		}
		
		userEditingStage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler <WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				userEditingController.onShown();
			}
		});
		
		Scene userEditingScene = new Scene(borderPane);
		userEditingStage.setScene(userEditingScene);
		userEditingStage.show();
	}
	
	private User getSelectedUser() {
		return usersTableView.getSelectionModel().getSelectedItem();
	}
	
	public void searchFirstNameEditOnAction(ActionEvent actionEvent) {
	}
}
