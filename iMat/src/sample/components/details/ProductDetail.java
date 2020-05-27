package sample.components.details;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.CategoryManager;
import sample.IMat;
import sample.components.AbstractProductItem;
import sample.screens.main.Controller;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;


public class ProductDetail extends AbstractProductItem {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML ImageView productImageView;
    @FXML ImageView favoriteImageView;
    @FXML Label priceLabel;
    @FXML Label productNameLabel;
    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML Button categoryButton;
    @FXML SplitPane detailSplitPane;
    @FXML AnchorPane detailShadowAnchorPane;
    @FXML TextField nrProductsTextField;
    @FXML  ImageView closeImageView;

    public ProductDetail(Product product) {
        super(product);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        populateDetailView(product);
        canOpenDetailsView = false;
        setup();
        setupShoppingCartListener();
    }

    private void setupShoppingCartListener() {
        dataHandler.getShoppingCart().addShoppingCartListener(cartEvent -> {
            if (cartEvent.getShoppingItem() == null || cartEvent.getShoppingItem().getProduct() == product) {
                ShoppingItem item = getCartItemIfExists();
                setNrProductsTextField(item);
                setRemoveButtonDisabled(item);
            }
        });
    }

    public void populateDetailView(Product product) {

        showCategory();

        categoryButton.setOnMouseClicked(mouseEvent -> goToCategory());

        detailShadowAnchorPane.setOnMouseClicked(mouseEvent -> detailShadowAnchorPane.toBack());

        closeImageView.setOnMouseClicked(mouseEvent -> detailShadowAnchorPane.toBack());
        Tooltip.install(closeImageView, new Tooltip("Stäng detaltjvyn"));
        Tooltip.install(categoryButton, new Tooltip("Gå till kategori"));

        productImageView.setImage(dataHandler.getFXImage(product));
        if(dataHandler.isFavorite(product)) {
            favoriteImageView.setImage(getFavoriteImage());
        }
        else {
            favoriteImageView.setImage(getEmptyFavoriteImage());
        }

        favoriteImageView.setOnMouseClicked(mouseEvent -> toggleFavorite(product));
        Tooltip.install(favoriteImageView, new Tooltip("Lägg till/ta bort som favorit"));

        priceLabel.setText(product.getPrice() + product.getUnit());
        productNameLabel.setText(product.getName());

        Tooltip.install(addButton, new Tooltip("Lägg till vara i kundvagnen"));
        Tooltip.install(removeButton, new Tooltip("Ta bort vara från kundvagnen"));
    }

    private void goToCategory() {
        detailShadowAnchorPane.toBack();
        for (CategoryManager.FrontendCategory category : CategoryManager.FrontendCategory.values()) {
            for (ProductCategory productCategory : category.productCategories) {
                if (product.getCategory() == productCategory) {
                    CategoryManager.currentCategory = category;
                    return;
                }
            }
        }
    }

    private void showCategory() {
        CategoryManager.FrontendCategory[] categories = CategoryManager.FrontendCategory.values();
        for (int i = 1; i < categories.length; i++) {
            CategoryManager.FrontendCategory category = categories[i];
            ProductCategory[] productCategories = category.productCategories;
            for (int j = 0; j < productCategories.length; j++) {
                if (product.getCategory() == productCategories[j]) {
                    categoryButton.setText(categories[i].toString().replace("_", " "));
                    return;
                }
            }
        }
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
