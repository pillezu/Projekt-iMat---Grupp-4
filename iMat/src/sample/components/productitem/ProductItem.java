package sample.components.productitem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductItem extends AbstractProductItem {

    @FXML private ImageView favoriteImageView;

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

        if (dataHandler.isFavorite(product)) {
            favoriteImageView.setImage(getFavoriteImage());
        }
        favoriteImageView.setOnMouseClicked(mouseEvent -> toggleFavorite());
    }

    private Image getFavoriteImage() {
        return new Image(getClass().getClassLoader().getResourceAsStream("sample/resources/favorit.png"));
    }

    private Image getEmptyFavoriteImage() {
        return new Image(getClass().getClassLoader().getResourceAsStream("sample/resources/favorit_tom.png"));
    }

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
