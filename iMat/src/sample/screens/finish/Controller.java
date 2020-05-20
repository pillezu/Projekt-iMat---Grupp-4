package sample.screens.finish;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.IMat;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML Button continueButton;
    @FXML Button quitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        continueButton.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
        quitButton.setOnMouseClicked(mouseEvent -> quit());
    }

    private void quit() {
        dataHandler.shutDown();
        System.exit(1);
    }
}
