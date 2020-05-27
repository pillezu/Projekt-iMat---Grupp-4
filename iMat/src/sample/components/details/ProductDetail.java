package sample.components.details;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.CategoryManager;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;


public class ProductDetail extends AbstractProductItem {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @FXML ImageView productImageView;
    @FXML Label priceLabel;
    @FXML Label productNameLabel;
    @FXML Label ecoLabel;
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
        priceLabel.setText(product.getPrice() + product.getUnit());
        productNameLabel.setText(product.getName());
        if (product.isEcological()) {
            ecoLabel.setText("Ja");
        }

        Tooltip.install(addButton, new Tooltip("Lägg till vara i kundvagnen"));
        Tooltip.install(removeButton, new Tooltip("Ta bort vara från kundvagnen"));
    }

    private void goToCategory() {
        detailShadowAnchorPane.toBack();
        CategoryManager.FrontendCategory category = getFrontEndCategory();
        CategoryManager.goToCategory(category);
    }

    private CategoryManager.FrontendCategory getFrontEndCategory() {
        CategoryManager.FrontendCategory[] categories = CategoryManager.FrontendCategory.values();
        for (int i = 1; i < categories.length; i++) {
            CategoryManager.FrontendCategory category = categories[i];
            ProductCategory[] productCategories = category.productCategories;
            for (int j = 0; j < productCategories.length; j++) {
                if (product.getCategory() == productCategories[j]) {
                    return categories[i];
                }
            }
        }
        throw new RuntimeException("Category does not exist");
    }

    private void showCategory() {
        categoryButton.setText(getFrontEndCategory().toString().replace("_", " "));
    }

}
