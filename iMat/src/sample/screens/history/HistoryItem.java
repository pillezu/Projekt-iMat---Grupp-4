package sample.screens.history;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class HistoryItem extends AnchorPane {

    private Controller parentController;
    private List<Product> historyProducts;
    @FXML private TitledPane historyItemTitledPane;

    public HistoryItem(List<Product> products, String purchaseInfo, Controller parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyItem.fxml"));
        //fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        historyProducts = products;
        for(Product p : products ){
           // historyFlowPane.getChildren().add(p);
        }
    }
}
