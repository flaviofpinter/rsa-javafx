package dev.pinter.jfxapp.gui;

import dev.pinter.jfxapp.Main;
import dev.pinter.jfxapp.core.Cryptography;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class EncryptGuiController implements Initializable {

    @FXML
    public TextField messageField;

    @FXML
    public Button encryptButton;

    @FXML
    public TextField resultField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void encryptButton(ActionEvent actionEvent) throws Exception {
        String msg = messageField.getText();
        String encryptedMsg = new Cryptography().encrypt(msg, new Main().keys.getPublic());
        resultField.setText(encryptedMsg);
    }
}
