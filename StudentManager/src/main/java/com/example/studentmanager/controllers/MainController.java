package com.example.studentmanager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.studentmanager.models.Student;

public class MainController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField majorField;

    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        studentTable.setItems(studentData);
    }

    @FXML
    private void handleAdd() {
        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || emailField.getText().isEmpty() || majorField.getText().isEmpty()) {
            showAlert("Validation Error", "Please fill all fields");
            return;
        }

        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String major = majorField.getText();

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setEmail(email);
            student.setMajor(major);
            studentData.add(student);

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "ID must be a number");
        }
    }

    @FXML
    private void handleUpdate() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            if (idField.getText().isEmpty() || nameField.getText().isEmpty() || emailField.getText().isEmpty() || majorField.getText().isEmpty()) {
                showAlert("Validation Error", "Please fill all fields");
                return;
            }

            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String major = majorField.getText();

                selectedStudent.setId(id);
                selectedStudent.setName(name);
                selectedStudent.setEmail(email);
                selectedStudent.setMajor(major);

                studentTable.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                showAlert("Input Error", "ID must be a number");
            }
        } else {
            showAlert("Selection Error", "Please select a student to update");
        }
    }

    @FXML
    private void handleDelete() {
        int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentTable.getItems().remove(selectedIndex);
        } else {
            showAlert("Selection Error", "Please select a student to delete");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        majorField.clear();
    }
}
