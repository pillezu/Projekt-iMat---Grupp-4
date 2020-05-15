package sample.screens.favorites;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AnchorPane {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    private Product product;
    private Controller parentController;

    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private TextField nrProductsTextField;
    @FXML private Button removeButton;

    public ProductItem(Product product, Controller controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.product = product;
        this.parentController = controller;

        productNameLabel.setText(product.getName());
        productImageView.setImage(dataHandler.getFXImage(product));

    }
}
