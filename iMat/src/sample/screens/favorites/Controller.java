package sample.screens.favorites;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Product product : dataHandler.getProducts()) {
            ProductItem productItem = new ProductItem(product, this);
            productItemMap.put(product.getName(), productItem);
        }
        productsFlowPane.toFront();
        updateProducts();
    }

    private void updateProducts() {
        productsFlowPane.getChildren().clear();

        for (Product product : dataHandler.getProducts()) {
            productsFlowPane.getChildren().add(productItemMap.get(product.getName()));
        }

    }
}
