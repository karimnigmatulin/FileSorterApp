module com.example.sort_folder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.sort_folder to javafx.fxml;
    exports com.example.sort_folder;
}