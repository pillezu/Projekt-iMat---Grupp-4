package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

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
            dataHandler.getShoppingCart().addProduct(product);
        });
    }

}
