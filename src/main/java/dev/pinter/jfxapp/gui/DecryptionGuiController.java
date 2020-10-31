package dev.pinter.jfxapp.gui;

import dev.pinter.jfxapp.Main;
import dev.pinter.jfxapp.core.CryptoException;
import dev.pinter.jfxapp.core.Cryptography;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class DecryptionGuiController implements Initializable {



    @FXML
    public TextField decryptMessageField;

    @FXML
    public Button decryptButton;

    @FXML
    public Label decryptResultLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void decryptButton(ActionEvent actionEvent)  {
        String msg = decryptMessageField.getText();
        String decryptedMsg = null;
        try {
            decryptedMsg = Cryptography.getInstance().decrypt(msg);
        } catch (CryptoException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Error while Decrypting the message.");
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
        decryptResultLabel.setText(decryptedMsg);
    }
}
