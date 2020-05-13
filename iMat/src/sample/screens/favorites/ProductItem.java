package sample.screens.favorites;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Product;

import java.io.IOException;

public class ProductItem extends AnchorPane {

    private Product product;
    private Controller parentController;
    private int korv;

    @FXML ImageView productImageView;
    @FXML Label productNameLabel;

    public ProductItem(Product product, Controller parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.product = product;
        this.parentController = parentController;

        productImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(product.getImageName())));
        productNameLabel.setText(product.getName());
    }
}
