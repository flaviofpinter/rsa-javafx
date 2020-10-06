package dev.pinter.jfxapp;

import dev.pinter.jfxapp.core.Constants;
import dev.pinter.jfxapp.core.Language;
import dev.pinter.jfxapp.gui.SystemTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private SystemTrayIcon systemTrayIcon;

    public static void main(String... args) {
        logger.info(Language.get().getMsg("log.debug.enter-main"));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.MAIN_FXML), Language.get().getResourceBundleUI());
        logger.info(Language.get().getMsg("log.debug.enter-start"));

        systemTrayIcon = setupSystray(primaryStage);

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

        primaryStage.iconifiedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                try {
                    if(oldValue.equals(newValue))
                        return;

                    if(newValue.equals(true)) {
                        if (systemTrayIcon == null) {
                            systemTrayIcon = setupSystray(primaryStage);
                        }

                        if(!systemTrayIcon.isShowing()) {
                            systemTrayIcon.showTrayIcon();
                        }
                        primaryStage.hide();
                        primaryStage.setIconified(false);
                    }
                } catch (IOException | AWTException e) {
                    logger.error("Error",e);
                }
            }
        });

        primaryStage.show();
    }

    private SystemTrayIcon setupSystray(Stage primaryStage) throws IOException, AWTException {
        SystemTrayIcon systemTrayIcon = new SystemTrayIcon(primaryStage);
        PopupMenu popupMenu = new PopupMenu();

        MenuItem menuItem = new MenuItem(Language.get().getMsg("fxml.main.helloWorldText"));
        menuItem.addActionListener(e -> {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(Language.get().getMsg("fxml.main.helloWorldText"));
                alert.setContentText(Language.get().getMsg("fxml.main.helloWorldText"));
                alert.show();
            });
        });
        popupMenu.add(menuItem);

        MenuItem disableTrayItem = new MenuItem(Language.get().getMsg("systray.menu.disableTray"));
        disableTrayItem.addActionListener(e -> {
            Platform.runLater(() -> {
                primaryStage.show();
                systemTrayIcon.removeTray();
                Platform.setImplicitExit(true);
            });
        });
        popupMenu.add(disableTrayItem);

        MenuItem exitItem = new MenuItem(Language.get().getMsg("systray.menu.exit"));
        exitItem.addActionListener(e -> {
            systemTrayIcon.getSystemTray().remove(systemTrayIcon.getTrayIcon());
            Platform.exit();
            System.exit(0);
        });
        popupMenu.add(exitItem);


        systemTrayIcon.setPopupMenu(popupMenu);
        systemTrayIcon.showTrayIcon();

        return systemTrayIcon;
    }
}
