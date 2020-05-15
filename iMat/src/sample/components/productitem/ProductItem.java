package sample.components.productitem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AnchorPane {

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    private Product product;

    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private TextField nrProductsTextField;
    @FXML private ImageView favoriteImageView;
    @FXML private Button addButton;
    @FXML private Button removeButton;

    public ProductItem(Product product) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.product = product;

        productNameLabel.setText(product.getName());
        productImageView.setImage(dataHandler.getFXImage(product));
        if (dataHandler.isFavorite(product)) {
            favoriteImageView.setImage(getFavoriteImage());
        }

        addButton.setOnMouseClicked(mouseEvent -> {
            System.out.println("Adding item");
            dataHandler.getShoppingCart().addProduct(product);
        });


    }

    private Image getFavoriteImage() {
        return new Image(getClass().getClassLoader().getResourceAsStream("sample/resources/favorit.png"));
    }

    private Image getEmptyFavoriteImage() {
        return new Image(getClass().getClassLoader().getResourceAsStream("sample/resources/favorit_tom.png"));
    }

    @FXML
    public void toggleFavorite() {
        if (dataHandler.isFavorite(product)) {
            favoriteImageView.setImage(getEmptyFavoriteImage());
            dataHandler.removeFavorite(product);
        }
        else {
            favoriteImageView.setImage(getFavoriteImage());
            dataHandler.addFavorite(product);
        }
    }
}
