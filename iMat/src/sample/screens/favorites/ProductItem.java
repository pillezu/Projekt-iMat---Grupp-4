package sample.screens.favorites;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AnchorPane {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    private Product product;
    private Controller parentController;

    @FXML ImageView productImageView;
    @FXML Label productNameLabel;

    public ProductItem(Product product, Controller parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductItem.fxml"));
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.product = product;
        this.parentController = parentController;

        productNameLabel.setText(product.getName());

    }
}
