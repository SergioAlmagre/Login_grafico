module com.example.login_grafico {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.sql;


    opens com.example.login_grafico to javafx.fxml;
    exports com.example.login_grafico;
}