package sample.screens.checkout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import sample.IMat;
import sample.components.HorizontalProductItem.HorizontalProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button toConfirmationButton;
    @FXML
    private FlowPane checkoutFlowPane;
    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (ShoppingItem item : dataHandler.getShoppingCart().getItems()){
            checkoutFlowPane.getChildren().add(new HorizontalProductItem(item));
        }

        toConfirmationButton.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/confirmation/Screen.fxml"));

    }
}



