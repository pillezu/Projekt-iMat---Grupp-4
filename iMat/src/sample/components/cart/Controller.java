package sample.components.cart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import sample.components.cartitem.CartItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private ShoppingCart cart = dataHandler.getShoppingCart();

    @FXML
    private FlowPane productsFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cart.addShoppingCartListener(cartEvent -> {
            System.out.println(cart.getItems());
            productsFlowPane.getChildren().clear();

            for (ShoppingItem item : cart.getItems()) {
                productsFlowPane.getChildren().add(new CartItem(item.getProduct()));
            }
        });
    }
}
