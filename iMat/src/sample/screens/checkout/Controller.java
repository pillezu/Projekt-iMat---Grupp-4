package sample.screens.checkout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.IMat;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML Label iMatLabel;
    @FXML ImageView homeImage;
    @FXML VBox mainScreen;
    @FXML Button btn1;

    @FXML TextArea nameTextField;
    @FXML TextArea lastnameTextField;
    @FXML TextArea epostTextField;
    @FXML TextArea addressTextField;
    @FXML TextArea postnrTextField;
    @FXML TextArea telephonenrTextField;
    @FXML TextArea accountTextField;
    @FXML TextArea cvcTextField;
    @FXML Label kontouppgiftLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn1.setOnMouseClicked(mouseEvent -> { IMat.changeRoot("History.fxml");
        });
    }
}
