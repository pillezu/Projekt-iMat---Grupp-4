package sample.screens.finish;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
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

        Tooltip.install(continueButton, new Tooltip("Gå till sidan för alla varor"));
        Tooltip.install(quitButton, new Tooltip("Stänger av programmet"));
    }

    private void quit() {
        dataHandler.shutDown();
        System.exit(1);
    }
}
