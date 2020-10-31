package dev.pinter.rsaapp.gui;

import dev.pinter.rsaapp.core.Cryptography;
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
import java.security.KeyPair;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    public AnchorPane rootAnchorPane;
    public TextField messageField;
    public Button encDecButton;
    public TextField resultField;
    public Label resultLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changePaneBackground();
    }

    public void encryptDecryptAction(ActionEvent actionEvent) throws Exception {
        String msg = messageField.getText();
        Cryptography crypto = new Cryptography();
        KeyPair keys = crypto.keyPairGen();
        String encryptedMsg = crypto.encrypt(msg, keys.getPublic());
        resultField.setText(encryptedMsg);
        String decryptedMsg = crypto.decrypt(encryptedMsg, keys.getPrivate());
        resultLabel.setText(decryptedMsg);
    }


    private void changePaneBackground() {
        rootAnchorPane.setStyle(String.format("-fx-background-color: #%02x%02x%02x", 2, 5, 99));
    }

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

//    private int newRandomInt() {
//        return new Random().nextInt(255);
//    }
}
