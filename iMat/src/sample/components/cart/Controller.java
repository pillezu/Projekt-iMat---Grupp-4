package sample.components.cart;

import javafx.fxml.Initializable;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private ShoppingCart cart = dataHandler.getShoppingCart();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cart.addShoppingCartListener(cartEvent -> System.out.println(cart.getItems()));
    }
}
