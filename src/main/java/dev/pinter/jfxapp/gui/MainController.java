package dev.pinter.jfxapp.gui;

import dev.pinter.jfxapp.Main;
import dev.pinter.jfxapp.core.Constants;
import dev.pinter.jfxapp.core.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    public Button encryptionDecisionButton;

    @FXML
    public Button decryptionDecisionButton;

    @FXML
    public AnchorPane rootAnchorPane;

    @FXML
    public Label mainDecisionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void encryptButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/encryptionGUI.fxml"), Language.get().getResourceBundleUI());
        Scene scene2 = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene2);
        stage.show();
    }

    @FXML
    public void decryptButtonAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/decryptionGUI.fxml"), Language.get().getResourceBundleUI());
        Scene scene3 = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene3);
        stage.show();
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

//    private int newRandomInt() {
//        return new Random().nextInt(255);
//    }
}
