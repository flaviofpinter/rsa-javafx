package dev.pinter.jfxapp.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Constants {
    public static final String MAIN_FXML = "/fxml/main.fxml";
    public static final String RESOURCE_BUNDLE_UI = "i18n.UI";

    public static final List<Locale> SUPPORTED_LANGUAGES = new ArrayList<>() {{
        add(Locale.ENGLISH);
        add(new Locale("pt","BR"));
    }};

}
