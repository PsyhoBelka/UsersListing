package ua.rozhkov.UsersListing.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ua.rozhkov.UsersListing.entity.User;
import ua.rozhkov.UsersListing.service.UserService;
import ua.rozhkov.UsersListing.service.implementation.JdbcUserService;

import java.sql.Date;

public class UserEditingController {
	
	private boolean userEditingMode;
	private Stage userEditingStage;
	private User user;
	private UserService userService = new JdbcUserService();
	private UsersListController usersListController;
	
	@FXML
	public BorderPane userEditingBorderPane;
	@FXML
	public TextField firstNameEdit;
	@FXML
	public TextField lastNameEdit;
	@FXML
	public TextField salaryEdit;
	@FXML
	public TextField ageEdit;
	@FXML
	public DatePicker dateOfBirthDatePicker;
	@FXML
	public Button saveButton;
	@FXML
	public Button cancelButton;
	@FXML
	public Label editModeLabel;
	
	
	public void setUserEditingStage(Stage userEditingStage) {
		this.userEditingStage = userEditingStage;
	}
	
	public void setUserEditingMode(boolean userEditingMode) {
		this.userEditingMode = userEditingMode;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUsersListController(UsersListController usersListController) {
		this.usersListController = usersListController;
	}
	
	@FXML
	public void saveButtonClick(ActionEvent actionEvent) {
		if (userEditingMode) {
			saveEdit();
		} else {
			saveAdd();
		}
		usersListController.refreshUsersListButton.fire();
		cancelButton.fire();
	}
	
	@FXML
	public void cancelButtonClick(ActionEvent actionEvent) {
		userEditingStage.close();
	}
	
	private void saveAdd() {
		userService.addUser(saveUser());
		clearEdits();
	}
	
	private void saveEdit() {
		userService.editUser(saveUser());
		clearEdits();
	}
	
	private User saveUser() {
		User tempUser = new User();
		tempUser.setId(user.getId());
		tempUser.setFirstName(firstNameEdit.getText());
		tempUser.setLastName(lastNameEdit.getText());
		tempUser.setSalary(Float.valueOf(salaryEdit.getText()));
		tempUser.setAge(Integer.valueOf(ageEdit.getText()));
		tempUser.setDateOfBirth(Date.valueOf(dateOfBirthDatePicker.getValue()));
		return tempUser;
	}
	
	public void onShown() {
		clearEdits();
		if (user != null) {
			firstNameEdit.setText(user.getFirstName());
			lastNameEdit.setText(user.getLastName());
			salaryEdit.setText(Float.toString(user.getSalary()));
			ageEdit.setText(Integer.toString(user.getAge()));
			dateOfBirthDatePicker.setValue(user.getDateOfBirth().toLocalDate());
		}
	}
	
	private void clearEdits() {
		firstNameEdit.clear();
		lastNameEdit.clear();
		salaryEdit.clear();
		ageEdit.clear();
		dateOfBirthDatePicker.setValue(null);
	}
}
