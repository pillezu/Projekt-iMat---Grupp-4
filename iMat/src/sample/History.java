package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class History implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML Label iMatLabel;
    @FXML ImageView homeImage;
    @FXML VBox mainScreen;
    @FXML Button btn1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn1.setOnMouseClicked(mouseEvent -> System.out.println("test"));
    }
}
