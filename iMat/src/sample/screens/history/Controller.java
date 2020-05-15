package sample.screens.history;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private VBox historyVBox;
    @FXML private Accordion historyAccordion;
    @FXML private FlowPane historyFlowPane;
    @FXML private ScrollPane historyScrollPane;
    List<Product> products = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        historyScrollPane.setFitToWidth(true);

        historyAccordion.getPanes().add(new HistoryItem(products, "12/04/2020", "onsdag", "5", "48 kr", this));
        historyAccordion.getPanes().add(new HistoryItem(products, "12/04/2020", "onsdag", "5", "48 kr", this));
        historyAccordion.getPanes().add(new HistoryItem(products, "12/04/2020", "onsdag", "5", "48 kr", this));
        historyAccordion.getPanes().add(new HistoryItem(products, "12/04/2020", "fredag", "5", "48 kr", this));

        System.out.println(historyAccordion.getPanes().size());
    }
}
