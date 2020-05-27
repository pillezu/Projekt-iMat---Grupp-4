package sample.screens.checkout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import sample.DeliverySummaryManager;
import sample.IMat;
import sample.components.checkoutProductItem.CheckoutProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
    @FXML private Label deliverySummaryLabel;
    @FXML private DatePicker datePicker;


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
        Tooltip.install(toConfirmationButton, new Tooltip("Gå vidare till bekräftelse av köp"));

        updateCartInfo();

        deliveryToggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            updateDeliverySummary();
        });
        paymentToggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            updateDeliverySummary();
        });
        datePicker.valueProperty().addListener((observableValue, textField, t1) -> {
            updateDeliverySummary();
        });


        //datepicker configs

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        datePicker.setValue(LocalDate.now().plusDays(1));

        updateDeliverySummary();

    }

    private void updateDeliverySummary() {
        DeliverySummaryManager.date = datePicker.getValue().toString();

        if (deliveryToggleGroup.getSelectedToggle() == null) {
            DeliverySummaryManager.deliveryType = null;
        } else {
            if (deliveryToggleGroup.getSelectedToggle().equals(deliveryButton)) {
                DeliverySummaryManager.deliveryType = DeliverySummaryManager.DeliveryType.DELIVERY;
            } else {
                DeliverySummaryManager.deliveryType = DeliverySummaryManager.DeliveryType.PICKUP;
            }
        }

        if (paymentToggleGroup.getSelectedToggle() == null) {
            DeliverySummaryManager.paymentType = null;
        } else {
            if (paymentToggleGroup.getSelectedToggle().equals(payWithCardButton)) {
                DeliverySummaryManager.paymentType = DeliverySummaryManager.PaymentType.ONLINE;
            } else {
                DeliverySummaryManager.paymentType = DeliverySummaryManager.PaymentType.IN_PERSON;
            }
        }
        deliverySummaryLabel.setText(DeliverySummaryManager.getDeliverySummary());
        toConfirmationButton.setDisable(!DeliverySummaryManager.allSelectionsMade());
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



