package sample.screens.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import sample.components.productitem.ProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    ProductCategory[] categories = ProductCategory.values();

    @FXML
    private ListView<String> categoriesListView;
    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private ScrollPane productsScrollPane;

    Map<String, ProductItem> productItemMap = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupCategories();


        for (Product product : dataHandler.getProducts()) {
            ProductItem productItem = new ProductItem(product);
            productItemMap.put(product.getName(), productItem);
        }
        productsFlowPane.toFront();
        updateProducts();
        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(cartEvent -> updateProducts());
    }

    private void setupCategories() {
        for (int i = 0; i < categories.length; i++) {
            ProductCategory category = categories[i];
            String name = category.toString()
                    .replace("_", " ");
            name = name.substring(0, 1) + name.substring(1).toLowerCase();
            categoriesListView.getItems().add(name);
        }
        categoriesListView.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldNumber, newNumber) -> {
            int index = newNumber.intValue();
            System.out.println("index: " + index);
            System.out.println("category: " + categories[index]);
        });
        categoriesListView.getSelectionModel().select(0);
    }

    private void updateProducts() {
        productsFlowPane.getChildren().clear();

        for (Product product : dataHandler.getProducts()) {
            productsFlowPane.getChildren().add(productItemMap.get(product.getName()));
        }

    }
}
