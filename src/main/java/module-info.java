module com.example.tablaPersonas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens controler to javafx.fxml;
    exports controler;
}