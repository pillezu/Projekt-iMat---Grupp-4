package sample.screens.history;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.IMat;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML Label mainLink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainLink.setOnMouseClicked(mouseEvent -> {
            IMat.changeRoot("screens/main/Screen.fxml");
        });
    }
}
