package dev.pinter.jfxapp.core;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Constants {
    public static final String MAIN_FXML = "/fxml/main.fxml";
    public static final String RESOURCE_BUNDLE_UI = "i18n.UI";
    public static final String TRAY_ICON = "/icon/java32.png";
    public static final List<Locale> SUPPORTED_LANGUAGES = new ArrayList<>() {{
        add(Locale.ENGLISH);
        add(new Locale("pt","BR"));
    }};

    public static final List<Image> ALL_PNG_ICONS = new ArrayList<>() {{
        add(new javafx.scene.image.Image(getClass().getResource("/icon/java24.png").toExternalForm()));
        add(new javafx.scene.image.Image(getClass().getResource("/icon/java32.png").toExternalForm()));
        add(new javafx.scene.image.Image(getClass().getResource("/icon/java64.png").toExternalForm()));
        add(new javafx.scene.image.Image(getClass().getResource("/icon/java128.png").toExternalForm()));
        add(new javafx.scene.image.Image(getClass().getResource("/icon/java256.png").toExternalForm()));
    }};
}
