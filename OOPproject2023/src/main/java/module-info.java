module yourmodule {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;


    opens com.example.javaproject2023 to javafx.fxml;
    exports com.example.javaproject2023;
}