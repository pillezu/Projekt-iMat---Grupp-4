package sample.screens.favorites;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import sample.IMat;
import sample.components.productitem.ProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    Map<String, ProductItem> productItemMap = new HashMap<>();

    @FXML private FlowPane productsFlowPane;
    @FXML
    private StackPane detailsStackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Product product : dataHandler.getProducts()) {
            if (dataHandler.isFavorite(product)) {
                ProductItem productItem = new ProductItem(product);
                productItemMap.put(product.getName(), productItem);
            }
        }
        productsFlowPane.toFront();
        updateProducts();
        IMat.detailsStackPane = detailsStackPane;
    }

    private void updateProducts() {
        productsFlowPane.getChildren().clear();

        for (Product product : dataHandler.getProducts()) {
            if (dataHandler.isFavorite(product)) {
                productsFlowPane.getChildren().add(productItemMap.get(product.getName()));
            }
        }

    }
}
