package sample.screens.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML
    ListView categoriesListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 60; i++) {
            categoriesListView.getItems().add("Beef");
        }
    }
}
