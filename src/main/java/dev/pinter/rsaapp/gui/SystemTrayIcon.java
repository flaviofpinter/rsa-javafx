/*
 * Copyright (c) 2020. Emerson Pinter - All rights reserved.
 */

package dev.pinter.rsaapp.gui;

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class SystemTrayIcon {
    private final Stage primaryStage;
    private final JPopupMenu popupMenu;
    private TrayIcon trayIcon;
    private SystemTray systemTray;
    private JDialog hiddenDialog;
    private final String iconPath;
    private final boolean darkTheme;
    private final String tooltip;

    public SystemTrayIcon(Stage primaryStage, String tooltip, String iconPath, boolean darkTheme) throws IOException {
        this.primaryStage = primaryStage;
        this.iconPath = iconPath;
        this.darkTheme = darkTheme;
        this.tooltip = tooltip;

        popupMenu = new JPopupMenu();

        initializeTray();
    }

    private void initializeTray() throws IOException {
        Toolkit.getDefaultToolkit();

        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException("System tray is not supported");
        }

        UIManager.put("PopupMenu.font", new Font(Font.DIALOG, Font.PLAIN, 12));
        UIManager.put("MenuItem.font", new Font(Font.DIALOG, Font.PLAIN, 12));
        UIManager.put("PopupMenu.border", BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(160, 160, 160)), BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        if (darkTheme) {
            UIManager.put("MenuItem.selectionBackground", new Color(65, 65, 65));
            UIManager.put("MenuItem.selectionForeground", Color.WHITE);
            UIManager.put("MenuItem.foreground", Color.WHITE);
            UIManager.put("MenuItem.background", new Color(43, 43, 43));
            UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());
            UIManager.put("PopupMenu.background", new Color(43, 43, 43));
            UIManager.put("Separator.foreground", new Color(128, 128, 128));
            UIManager.put("Separator.background", new Color(43, 43, 43));
        } else {
            UIManager.put("MenuItem.selectionBackground", Color.WHITE);
            UIManager.put("MenuItem.selectionForeground", Color.BLACK);
            UIManager.put("MenuItem.foreground", Color.BLACK);
            UIManager.put("MenuItem.background", new Color(238, 238, 238));
            UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());
            UIManager.put("PopupMenu.background", new Color(238, 238, 238));
            UIManager.put("Separator.foreground", new Color(145, 145, 145));
            UIManager.put("Separator.background", new Color(238, 238, 238));
        }

        systemTray = SystemTray.getSystemTray();
        trayIcon = new TrayIcon(ImageIO.read(getClass().getResourceAsStream(iconPath)), tooltip);
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(event -> Platform.runLater(this::toggleStage));

        setJPopupMouseListener();

        primaryStage.iconifiedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (oldValue.equals(newValue))
                    return;

                if (newValue.equals(true)) {
                    if (!isShowing()) {
                        showTrayIcon();
                    }
                    primaryStage.hide();
                    primaryStage.setIconified(false);
                }
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });

    }

    /*
     * workaround to use swing JPopupMenu with systray
     * https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6285881
     */
    private void setJPopupMouseListener() {
        hiddenDialog = new JDialog((Frame) null);
        hiddenDialog.setUndecorated(true);
        hiddenDialog.setSize(0, 0);
        hiddenDialog.setType(Window.Type.POPUP);
        hiddenDialog.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(final WindowEvent e) {
                hiddenDialog.setVisible(false);
            }

            @Override
            public void windowGainedFocus(final WindowEvent e) {
            }
        });

        trayIcon.addMouseListener(new MouseAdapter() {
            public void handleMouseEventPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.setInvoker(hiddenDialog);
                    popupMenu.setLocation(e.getX(), e.getY());
                    hiddenDialog.setLocation(e.getX(), e.getY());

                    hiddenDialog.setVisible(true);
                    popupMenu.setVisible(true);
                }
            }
            public void mousePressed(MouseEvent e) {
                handleMouseEventPopup(e);
            }
            public void mouseReleased(MouseEvent e) {
                handleMouseEventPopup(e);
            }
        });
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

        systemTray.add(trayIcon);
    }

    public void removeTray() {
        Platform.setImplicitExit(true);

        systemTray.remove(trayIcon);
    }

    @SuppressWarnings("UnusedReturnValue")
    public JMenuItem addMenuItem(String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(formatMenuItemText(text));
        menuItem.addActionListener(actionListener);
        popupMenu.add(menuItem);
        return menuItem;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Component addComponent(Component component) {
        return popupMenu.add(component);
    }

    private String formatMenuItemText(String text) {
        return String.format("<html><p style='margin: 8 15 8 15;'>%s</p>", text);
    }

}
