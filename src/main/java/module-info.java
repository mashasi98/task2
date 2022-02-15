module com.example.task2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens com.example.task2 to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.example.task2;
}