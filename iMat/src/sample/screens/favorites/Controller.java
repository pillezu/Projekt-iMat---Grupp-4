package sample.screens.favorites;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import sample.IMat;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    Map<String, ProductItem> productItemMap = new HashMap<>();

    @FXML FlowPane productsFlowPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (Product product : dataHandler.getProducts()) {
            ProductItem productItem = new ProductItem(product, this);
            productItemMap.put(product.getName(), productItem);
        }

        updateProducts();
    }

    private void updateProducts() {
        productsFlowPane.getChildren().clear();

        for(Product product : dataHandler.getProducts()) {
            productsFlowPane.getChildren().add(productItemMap.get(product.getName()));
        }
    }
}
