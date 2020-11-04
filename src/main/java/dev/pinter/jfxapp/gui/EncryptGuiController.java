package dev.pinter.jfxapp.gui;

import dev.pinter.jfxapp.core.CryptoException;
import dev.pinter.jfxapp.core.Cryptography;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class EncryptGuiController implements Initializable {
    @FXML
    public AnchorPane anchorEncrypt;

    @FXML
    public TextField messageField;

    @FXML
    public Button encryptButton;

    @FXML
    public TextField resultField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorEncrypt.setStyle(String.format("-fx-background-color: #%02x%02x%02x", 15, 0, 110));
        encryptButton.setStyle("-fx-background-color: #37a4ed");
    }

    @FXML
    public void encryptButton(ActionEvent actionEvent){
        String msg = messageField.getText();
        String encryptedMsg = null;
        try {
            encryptedMsg = Cryptography.getInstance().encrypt(msg);
        } catch (CryptoException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error while Encrypting the message.");
            error.setTitle("Error");
            error.showAndWait();
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Bug,se vira");
            error.setTitle("Bug");
            error.showAndWait();
            Platform.exit();
            System.exit(1);
        }
        resultField.setText(encryptedMsg);
    }
}
