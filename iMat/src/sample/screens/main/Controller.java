package sample.screens.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import sample.CategoryManager;
import sample.IMat;
import sample.components.productitem.ProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    CategoryManager.FrontendCategory[] categories = CategoryManager.FrontendCategory.values();

    @FXML
    private ListView<String> categoriesListView;
    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private ScrollPane productsScrollPane;
    @FXML
    private TextField searchTextField;

    private boolean searching = false;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupProductItems();
        setupCategories();
        setupSearch();


        productsFlowPane.toFront();
        updateProducts();
    }

    private void setupSearch() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Text changed
            searching = newValue.length() != 0;
            updateProducts();
        });
        searchTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Lost focus
            }
        });
        searchTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                // Enter pressed
            }
        });
    }

    private void setupProductItems() {
        if (IMat.productItemMap.size() == 0) {
            for (Product product : dataHandler.getProducts()) {
                ProductItem productItem = new ProductItem(product);
                IMat.productItemMap.put(product.getName(), productItem);
            }
        }
    }

    private void setupCategories() {
        for (int i = 0; i < categories.length; i++) {
            CategoryManager.FrontendCategory category = categories[i];
            String name = category.toString()
                    .replace("_", " ");
            name = name.substring(0, 1) + name.substring(1).toLowerCase();
            categoriesListView.getItems().add(name);
        }
        categoriesListView.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldNumber, newNumber) -> {
            int index = newNumber.intValue();
            if (index == -1) {
                return;
            }
            CategoryManager.FrontendCategory category = categories[index];
            System.out.println("Selected category: " + category);
            CategoryManager.currentCategory = category;
            searching = false;
            searchTextField.setText("");
            updateProducts();
        });
        categoriesListView.getSelectionModel().select(0);
    }

    private void updateProducts() {
        productsFlowPane.getChildren().clear();

        List<Product> products = dataHandler.getProducts();
        if (searching) {
            products = dataHandler.findProducts(searchTextField.getText());
        }
        Set<ProductCategory> currentCategories = Set.of(CategoryManager.currentCategory.productCategories);
        for (Product product : products) {
            if (currentCategories.contains(product.getCategory())){
                ProductItem productItem = IMat.productItemMap.get(product.getName());
                productItem.update();
                productsFlowPane.getChildren().add(productItem);
            }
        }
    }
}
