package sample.screens.checkout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.IMat;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button toConfirmationButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        toConfirmationButton.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/confirmation/Screen.fxml"));


    }
}



