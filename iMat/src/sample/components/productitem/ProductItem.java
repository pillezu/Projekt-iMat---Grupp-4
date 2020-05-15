package sample.components.productitem;

import javafx.fxml.FXMLLoader;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AbstractProductItem {

    public ProductItem(Product product) {
        super(product);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductItem.fxml"));
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
