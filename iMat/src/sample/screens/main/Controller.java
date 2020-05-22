package sample.screens.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import sample.CategoryManager;
import sample.IMat;
import sample.components.productitem.ProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupProductItems();
        setupCategories();


        productsFlowPane.toFront();
        updateProducts();
        //IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(cartEvent -> updateProducts());
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
            CategoryManager.FrontendCategory category = categories[index];
            System.out.println("Selected category: " + category);
            CategoryManager.currentCategory = category;
            updateProducts();
        });
        categoriesListView.getSelectionModel().select(0);
    }

    private void updateProducts() {
        productsFlowPane.getChildren().clear();
        Set<ProductCategory> currentCategories = Set.of(CategoryManager.currentCategory.productCategories);

        for (Product product : dataHandler.getProducts()) {
            if (currentCategories.contains(product.getCategory())){
                ProductItem productItem = IMat.productItemMap.get(product.getName());
                productItem.update();
                productsFlowPane.getChildren().add(productItem);
            }
        }

    }
}
