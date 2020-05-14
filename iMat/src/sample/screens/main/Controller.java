package sample.screens.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    ProductCategory[] categories = ProductCategory.values();

    @FXML
    ListView<String> categoriesListView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    }
}
