module com.example.studentmanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.studentmanager to javafx.fxml;
    opens com.example.studentmanager.controllers to javafx.fxml;
    opens com.example.studentmanager.models to javafx.base;

    exports com.example.studentmanager;
    exports com.example.studentmanager.controllers;
}
