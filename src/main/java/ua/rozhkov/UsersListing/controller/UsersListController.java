package ua.rozhkov.UsersListing.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import ua.rozhkov.UsersListing.service.ServiceLocator;
import ua.rozhkov.UsersListing.service.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class UsersListController {
	
	private ObservableList <User> usersObservableList;
	private Stage usersListStage;
	//private UserService userService = new JdbcUserService();
	private UserService userService;
	
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
	
	public UsersListController() {
		this.userService = ServiceLocator.getInstance().getService(UserService.class);
	}
	
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
		usersTableView.setItems((ObservableList <User>) filterList(usersObservableList, searchFirstNameEdit.getText(), "first"));
		if (keyEvent.getCode() == KeyCode.getKeyCode(KeyCode.ESCAPE.getName())) {
			searchFirstNameEdit.clear();
			usersTableView.setItems(usersObservableList);
		}
	}
	
	@FXML
	public void searchLastNameEditKeyReleased(KeyEvent keyEvent) {
		usersTableView.setItems((ObservableList <User>) filterList(usersObservableList, searchLastNameEdit.getText(), "last"));
		if (keyEvent.getCode() == KeyCode.getKeyCode(KeyCode.ESCAPE.getName())) {
			searchFirstNameEdit.clear();
			usersTableView.setItems(usersObservableList);
		}
	}
	
	@FXML
	public void dateSearchButtonClick(ActionEvent actionEvent) {
		usersTableView.setItems(FXCollections.observableArrayList(userService.searchBetweenDate(startDateSearch.getValue(), endDateSearch.getValue())));
		startDateSearch.setValue(null);
		endDateSearch.setValue(null);
	}
	
	private void showUserEditing(boolean mode) throws IOException {
		
		Stage userEditingStage = new Stage();
		userEditingStage.setTitle("Edit user's info");
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(System.class.getResource("/fxml/userEditing.fxml"));
		final UserEditingController userEditingController = new UserEditingController(userService);
		fxmlLoader.setController(userEditingController);
		BorderPane borderPane = fxmlLoader.load();
		
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
	
	private List <User> filterList(List <User> userList, String expression, String field) {
		FilteredList <User> usersFilteredList = new FilteredList <>((ObservableList <User>) userList, null);
		if (field.equals("first")) {
			usersFilteredList.setPredicate(new Predicate <User>() {
				@Override
				public boolean test(User user) {
					return ((user.getFirstName().toLowerCase().contains(expression)) && (user.getFirstName() != null));
				}
			});
			return usersFilteredList;
		} else {
			usersFilteredList.setPredicate(new Predicate <User>() {
				@Override
				public boolean test(User user) {
					return ((user.getLastName().toLowerCase().contains(expression)) && (user.getLastName() != null));
				}
			});
			return usersFilteredList;
		}
	}
}
