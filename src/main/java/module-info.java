module com.example.tablaPersonas {
    requires javafx.controls;
    requires javafx.fxml;

    opens controler to javafx.fxml;
    opens model to javafx.base;

    exports controler;
    exports model;
}