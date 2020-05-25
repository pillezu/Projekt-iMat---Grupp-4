package sample.components.cart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshShoppingCartList();
        cart.addShoppingCartListener(cartEvent -> {
            refreshShoppingCartList();
        });

        checkoutButton.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/checkout/Screen.fxml"));
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
