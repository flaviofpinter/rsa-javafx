module jfxapp.main {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires static org.slf4j;
    exports dev.pinter.jfxapp.gui to javafx.fxml;
    exports dev.pinter.jfxapp to javafx.graphics;
    opens dev.pinter.jfxapp.gui to javafx.fxml;
}