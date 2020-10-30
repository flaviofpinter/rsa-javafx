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
    public void decryptButton(ActionEvent actionEvent) throws Exception {
        String msg = decryptMessageField.getText();
        String decryptedMsg = new Cryptography().decrypt(msg, new Main().keys.getPrivate());
        decryptResultLabel.setText(decryptedMsg);
    }
}
