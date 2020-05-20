package sample.components.details;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;


public class ProductDetail extends AnchorPane {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML ImageView productImageView;
    @FXML ImageView favoriteImageView;
    @FXML Label priceLabel;
    @FXML Label productNameLabel;
    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML SplitPane detailSplitePane;
    @FXML AnchorPane detailShadowAnchorpane;
    @FXML TextField nrProductsTextField;
    @FXML  ImageView closeImageView;

    public void populateDetailView(Product product) {

        detailSplitePane.lookupAll(".split-pane-divider").stream().forEach(div -> div.setMouseTransparent(true));

        detailShadowAnchorpane.setOnMouseClicked(mouseEvent -> detailShadowAnchorpane.toBack());

        closeImageView.setOnMouseClicked(mouseEvent -> detailShadowAnchorpane.toBack());

        productImageView.setImage(dataHandler.getFXImage(product));
        if(dataHandler.isFavorite(product)) {
            favoriteImageView.setImage(getFavoriteImage());
        }
        else {
            favoriteImageView.setImage(getEmptyFavoriteImage());
        }

        favoriteImageView.setOnMouseClicked(mouseEvent -> toggleFavorite(product));

        priceLabel.setText(product.getPrice() + product.getUnit());
        productNameLabel.setText(product.getName());
    }

    private Image getFavoriteImage() {
        return new Image(getClass().getClassLoader().getResourceAsStream("sample/resources/favorit.png"));
    }

    private Image getEmptyFavoriteImage() {
        return new Image(getClass().getClassLoader().getResourceAsStream("sample/resources/favorit_tom.png"));
    }

    public void toggleFavorite(Product product) {
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
