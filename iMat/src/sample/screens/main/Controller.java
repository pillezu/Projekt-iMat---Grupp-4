package sample.screens.main;

import javafx.fxml.Initializable;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
