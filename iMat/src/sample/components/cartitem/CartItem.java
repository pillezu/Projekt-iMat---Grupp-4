package sample.components.cartitem;

import javafx.fxml.FXMLLoader;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CartItem extends AbstractProductItem {

    public CartItem(Product product) {
        super(product);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        setup();
    }
}
