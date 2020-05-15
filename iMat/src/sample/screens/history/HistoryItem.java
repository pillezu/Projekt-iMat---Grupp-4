package sample.screens.history;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import sample.components.HorizontalProductItem.HorizontalProductItem;
import se.chalmers.cse.dat216.project.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class HistoryItem extends TitledPane {

    IMatDataHandler datahandler = IMatDataHandler.getInstance();

    private Controller parentController;
    private List<Product> historyProducts;
    private String blankSpace = "\t\t";
    @FXML private FlowPane historyFlowPane;
    @FXML private TitledPane historyItemTitledPane;

    public HistoryItem(List<Product> products, String date, String day, String amountOfItems, String amountOfCost, Controller parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.setText("\t Datum: " + date + blankSpace + "Dag: " + day + blankSpace + "Antal varor: " +
                amountOfItems + blankSpace + "Totalkostnad: " + amountOfCost);

        this.parentController = parentController;
        historyProducts = products;

        historyFlowPane.getChildren().add(new HorizontalProductItem(datahandler.getProduct(4)));
        historyFlowPane.getChildren().add(new HorizontalProductItem(datahandler.getProduct(5)));
    }
}
