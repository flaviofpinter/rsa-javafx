package dev.pinter.jfxapp;

import dev.pinter.jfxapp.core.Constants;
import dev.pinter.jfxapp.core.Language;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        logger.info(Language.get().getMsg("log.debug.enter-main"));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.MAIN_FXML), Language.get().getResourceBundleUI());
        logger.info(Language.get().getMsg("log.debug.enter-start"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setTitle(Language.get().getMsg("window.title"));

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (event -> {
            logger.info("key pressed: " + event.getCode().getCode());

            if (event.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
                System.exit(0);
            }
        }));

        primaryStage.show();
    }
}
