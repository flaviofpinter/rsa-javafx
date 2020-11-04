package dev.pinter.rsaapp.gui;

import dev.pinter.rsaapp.core.CryptoException;
import dev.pinter.rsaapp.core.Cryptography;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class DecryptionGuiController implements Initializable {
    @FXML
    public AnchorPane anchorDecrypt;

    @FXML
    public TextField decryptMessageField;

    @FXML
    public Button decryptButton;

    @FXML
    public Label decryptResultLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorDecrypt.setStyle(String.format("-fx-background-color: #%02x%02x%02x", 15, 0, 110));
        decryptButton.setStyle("-fx-background-color: #37a4ed");
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
