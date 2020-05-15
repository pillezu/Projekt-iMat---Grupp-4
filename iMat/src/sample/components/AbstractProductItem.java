package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class AbstractProductItem extends AnchorPane {

    protected IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    protected Product product;

    @FXML protected ImageView productImageView;
    @FXML protected Label productNameLabel;
    @FXML protected Label priceLabel;
    @FXML protected TextField nrProductsTextField;
    @FXML protected Button addButton;
    @FXML protected Button removeButton;
    @FXML protected ImageView favoriteImageView;

    public AbstractProductItem(Product product) {
        this.product = product;
    }

    protected void setup() {
        productNameLabel.setText(product.getName());
        priceLabel.setText(product.getPrice() + " " + product.getUnit());
        productImageView.setImage(dataHandler.getFXImage(product));

        addButton.setOnMouseClicked(mouseEvent -> {
            System.out.println("Adding item");
            ShoppingItem item = getCartItemIfExists();
            if (item == null) {
                dataHandler.getShoppingCart().addProduct(product);
            } else {
                item.setAmount(item.getAmount()+1);
            }
            nrProductsTextField.setText(""+item.getAmount());
        });
    }

    private ShoppingItem getCartItemIfExists() {
        List<ShoppingItem> items = dataHandler.getShoppingCart().getItems();
        for (ShoppingItem item : items) {
            if (item.getProduct() == product) {
                return item;
            }
        }
        return null;
    }

}
