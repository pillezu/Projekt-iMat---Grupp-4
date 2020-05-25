package sample.screens.checkout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import sample.IMat;
import sample.components.HorizontalProductItem.HorizontalProductItem;
import sample.components.checkoutProductItem.CheckoutProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.event.ItemListener;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button toConfirmationButton;
    @FXML
    private FlowPane checkoutFlowPane;
    @FXML private Label amountLabel;
    @FXML private Label itemLabel;
    @FXML private Label deliveryLabel;
    @FXML private Label totalLabel;
    @FXML private RadioButton deliveryButton;
    @FXML private RadioButton pickupButton;
    @FXML private RadioButton payWithCardButton;
    @FXML private RadioButton payWithCashButton;


    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    ShoppingCart listener = dataHandler.getShoppingCart();

    private ToggleGroup deliveryToggleGroup = new ToggleGroup();
    private ToggleGroup paymentToggleGroup = new ToggleGroup();

    private int deliveryAmount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listener.addShoppingCartListener(cartEvent -> {
            updateCartInfo();
        });

        for (ShoppingItem item : dataHandler.getShoppingCart().getItems()){
            checkoutFlowPane.getChildren().add(new CheckoutProductItem(item));
        }

        //RadioButton&ToggleGroup settings

        deliveryButton.setToggleGroup(deliveryToggleGroup);
        pickupButton.setToggleGroup(deliveryToggleGroup);
        payWithCardButton.setToggleGroup(paymentToggleGroup);
        payWithCashButton.setToggleGroup(paymentToggleGroup);

        deliveryToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                updateCartInfo();
            }
        });

        toConfirmationButton.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/confirmation/Screen.fxml"));

        updateCartInfo();

    }

    private int itemAmount(){
        int amount = 0;
        for (ShoppingItem item : dataHandler.getShoppingCart().getItems()){
            amount += (int)item.getAmount();
        }
        return amount;
    }

    private void updateCartInfo(){

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        itemLabel.setText(df.format(dataHandler.getShoppingCart().getTotal()) + " kr");

        amountLabel.setText(itemAmount() + " st");

        if (deliveryToggleGroup.getSelectedToggle() != null) {
            deliveryAmount = (deliveryToggleGroup.getSelectedToggle().equals(deliveryButton)) ? 75 : 0;
        }

        deliveryLabel.setText(deliveryAmount + " kr");


        totalLabel.setText(df.format(dataHandler.getShoppingCart().getTotal() + deliveryAmount) + " kr");

    }
}



