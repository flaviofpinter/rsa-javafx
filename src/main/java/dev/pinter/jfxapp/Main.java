package dev.pinter.jfxapp;

import dev.pinter.jfxapp.core.Constants;
import dev.pinter.jfxapp.core.Cryptography;
import dev.pinter.jfxapp.core.Language;
import dev.pinter.jfxapp.core.Util;
import dev.pinter.jfxapp.gui.SystemTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
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
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public KeyPair keys = new Cryptography().keyPairGen();

    public Main() throws NoSuchAlgorithmException {
    }

    public static void main(String... args) {
        logger.info(Language.get().getMsg("log.debug.enter-main"));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Constants.MAIN_FXML), Language.get().getResourceBundleUI());
        logger.info(Language.get().getMsg("log.debug.enter-start"));

        try {
            createSystray(primaryStage);
        } catch (UnsupportedOperationException e) {
            logger.error("Error", e);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setTitle(Language.get().getMsg("window.title"));
        primaryStage.getIcons().addAll(Constants.ALL_PNG_ICONS);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, (event -> {
            logger.info("key pressed: " + event.getCode().getCode());

            if (event.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
                System.exit(0);
            }
        }));

        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, (event -> {
            if (Platform.isImplicitExit()) {
                Platform.exit();
                System.exit(0);
            }
        }));
        primaryStage.show();
    }

    private SystemTrayIcon createSystray(Stage primaryStage) throws IOException, AWTException {
        if (Util.isOsLinux() && !Util.isLinuxDesktopSupportsSystray()) {
            // some linux desktop systems shows a broken legacy systray icon, don't try...
            throw new UnsupportedOperationException("Desktop blacklisted, system tray disabled");
        }

        final SystemTrayIcon systemTrayIcon = new SystemTrayIcon(primaryStage,
                "JavaFX App",
                Constants.TRAY_ICON,
                Util.isUseDarkTheme());

        systemTrayIcon.addMenuItem(Language.get().getMsg("fxml.main.helloWorldText"), e -> Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(Language.get().getMsg("fxml.main.helloWorldText"));
            alert.setContentText(Language.get().getMsg("fxml.main.helloWorldText"));
            alert.show();
        }));

        systemTrayIcon.addMenuItem(Language.get().getMsg("systray.menu.disableTray"), e -> Platform.runLater(() -> {
            primaryStage.show();
            systemTrayIcon.removeTray();
            Platform.setImplicitExit(true);
        }));

        systemTrayIcon.addComponent(Box.createVerticalStrut(5));
        systemTrayIcon.addComponent(new JSeparator());
        systemTrayIcon.addComponent(Box.createVerticalStrut(5));

        systemTrayIcon.addMenuItem(Language.get().getMsg("systray.menu.exit"), e -> {
            systemTrayIcon.getSystemTray().remove(systemTrayIcon.getTrayIcon());
            Platform.exit();
            System.exit(0);
        });

        systemTrayIcon.showTrayIcon();
        return systemTrayIcon;
    }
}
