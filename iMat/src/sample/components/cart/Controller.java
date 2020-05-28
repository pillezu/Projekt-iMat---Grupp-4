package sample.components.cart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import sample.IMat;
import sample.components.cartitem.CartItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private ShoppingCart cart = dataHandler.getShoppingCart();
    private Map<String, CartItem> cartItemCacheMap = new HashMap<>();

    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button emptyCartButton;
    @FXML
    private Label numItemsLabel;
    @FXML
    private Label totalPriceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshShoppingCartList();
        setLabelTexts();
        cart.addShoppingCartListener(cartEvent -> {
            refreshShoppingCartList();
            setLabelTexts();
        });

        checkoutButton.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/checkout/Screen.fxml"));
        Tooltip.install(checkoutButton, new Tooltip("Gå vidare till kassan"));

        emptyCartButton.setOnMouseClicked(mouseEvent -> cart.clear());
        Tooltip.install(emptyCartButton, new Tooltip("Töm hela kundvagnen"));

    }

    private void setLabelTexts() {
        numItemsLabel.setText("Unika varor: " + getNumItems());
        totalPriceLabel.setText(getTotal() + " kr");
    }


    private Double getTotal() {
        Double total = cart.getTotal();
        total = Math.round(total*100.0)/100.0;
        return total;
    }

    private int getNumItems() {
        /*int numItems = 0;
        for (ShoppingItem item : cart.getItems()) {
            numItems += item.getAmount();
        }*/
        return dataHandler.getShoppingCart().getItems().size();
    }

    private void refreshShoppingCartList() {
        productsFlowPane.getChildren().clear();
        if (cart.getItems().size() == 0) {
            checkoutButton.setDisable(true);
        }
        else {
            checkoutButton.setDisable(false);
        }
        for (int i = cart.getItems().size()-1; i >= 0; i--) {
            ShoppingItem item = cart.getItems().get(i);
            CartItem cartItem;
            if (cartItemCacheMap.containsKey(item.getProduct().getName())) {
                cartItem = cartItemCacheMap.get(item.getProduct().getName());
                cartItem.update();
            } else {
                cartItem = new CartItem(item.getProduct());
            }
            productsFlowPane.getChildren().add(cartItem);
        }
    }
}
