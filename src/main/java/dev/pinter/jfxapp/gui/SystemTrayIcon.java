package dev.pinter.jfxapp.gui;

import dev.pinter.jfxapp.core.Constants;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SystemTrayIcon {
    private static final Logger logger = LoggerFactory.getLogger(SystemTrayIcon.class);

    private final Stage primaryStage;
    private PopupMenu popupMenu;
    private TrayIcon trayIcon;
    private SystemTray systemTray;

    public SystemTrayIcon(Stage primaryStage, PopupMenu popupMenu) throws AWTException, IOException {
        this.primaryStage = primaryStage;
        this.popupMenu = popupMenu;

        initializeTray();

        showTrayIcon();
    }

    private void initializeTray() throws IOException {
        Toolkit.getDefaultToolkit();

        if (!SystemTray.isSupported()) {
            logger.error("System tray not supported");
            Platform.exit();
        }

        systemTray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(ImageIO.read(getClass().getResourceAsStream(Constants.TRAY_ICON)), "JavaFX");

        getClass().getResource(Constants.TRAY_ICON);
        trayIcon.addActionListener(event -> Platform.runLater(this::toggleStage));

    }

    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

    public SystemTray getSystemTray() {
        return systemTray;
    }

    private void toggleStage() {
        if (primaryStage.isShowing()) {
            primaryStage.hide();
        } else {
            primaryStage.show();
        }
    }

    public SystemTrayIcon(Stage primaryStage) throws IOException, AWTException {
        this.primaryStage = primaryStage;
        initializeTray();
    }

    public void setPopupMenu(PopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public boolean isShowing() {
        for (TrayIcon i : systemTray.getTrayIcons()) {
            if (i.equals(trayIcon)) {
                return true;
            }
        }

        return false;
    }

    public void showTrayIcon() throws AWTException {
        Platform.setImplicitExit(false);

        for (TrayIcon i : systemTray.getTrayIcons()) {
            if (i.equals(trayIcon)) {
                return;
            }
        }

        trayIcon.setPopupMenu(popupMenu);
        systemTray.add(trayIcon);
    }

    public void removeTray() {
        Platform.setImplicitExit(true);

        systemTray.remove(trayIcon);
    }
}
