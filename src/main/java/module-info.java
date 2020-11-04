module rsaapp.main {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires java.desktop;
    requires static org.slf4j;
    requires static com.sun.jna;
    requires static com.sun.jna.platform;
    requires static org.apache.commons.lang3;
    requires javafx.base;
    exports dev.pinter.rsaapp.gui to javafx.fxml;
    exports dev.pinter.rsaapp.core to javafx.fxml;
    exports dev.pinter.rsaapp to javafx.graphics;
    opens dev.pinter.rsaapp.gui to javafx.fxml;
}