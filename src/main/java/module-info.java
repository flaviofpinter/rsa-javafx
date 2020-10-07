module jfxapp.main {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires java.desktop;
    requires static org.slf4j;
    requires static com.sun.jna;
    requires static com.sun.jna.platform;
    requires static org.apache.commons.lang3;
    requires javafx.base;
    exports dev.pinter.jfxapp.gui to javafx.fxml;
    exports dev.pinter.jfxapp to javafx.graphics;
    opens dev.pinter.jfxapp.gui to javafx.fxml;
}