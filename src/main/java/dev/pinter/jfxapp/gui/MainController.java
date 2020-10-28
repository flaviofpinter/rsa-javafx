package dev.pinter.jfxapp.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    public TextField helloWorldText;

    @FXML
    public Button helloWorldButton;

    @FXML
    public AnchorPane rootAnchorPane;

    @FXML
    public Label helloWorldLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helloWorldText.setText(
                String.format("%s %s", helloWorldText.getText(),
                        newRandomInt())
        );

    }

    @FXML
    public void helloWorldButtonAction(ActionEvent actionEvent) {
        logger.info(String.format("actionEventType: %s", actionEvent.getEventType().getName()));
        helloWorldText.setText(helloWorldText.getText()
                .replaceAll("(\\d+)$", String.valueOf(newRandomInt())));

//        changeButtonColor();

//        changePaneBackground();
    }

//    private void changePaneBackground() {
//        int red = newRandomInt();
//        int green = newRandomInt();
//        int blue = newRandomInt();
//
//        rootAnchorPane.setStyle(String.format("-fx-background-color: #%02x%02x%02x", red, green, blue));
//
//        if (red < 128 && green < 128 && blue < 128) {
//            helloWorldLabel.getStyleClass().add("labelWhiteText");
//        } else {
//            helloWorldLabel.getStyleClass().removeIf(f -> f.equals("labelWhiteText"));
//        }
//
//    }

//    private void changeButtonColor() {
//        int red = newRandomInt();
//        int green = newRandomInt();
//        int blue = newRandomInt();
//
//        if (red < 128 && green < 128 && blue < 128) {
//            helloWorldButton.getStyleClass().add("buttonWhiteText");
//        } else {
//            helloWorldButton.getStyleClass().removeIf(f -> f.equals("buttonWhiteText"));
//        }
//
//        logger.info(String.format("setting button background color to #%s%s%s (%d,%d,%d)",
//                Integer.toHexString(red),
//                Integer.toHexString(green),
//                Integer.toHexString(blue),
//                red, green, blue
//        ));
//
//        helloWorldButton.setStyle(String.format("-fx-background-color: #%02x%02x%02x", red, green, blue));
//    }

    private int newRandomInt() {
        return new Random().nextInt(255);
    }
}
