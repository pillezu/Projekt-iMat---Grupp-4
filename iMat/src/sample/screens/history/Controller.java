package sample.screens.history;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private IMatDataHandler datahandler = IMatDataHandler.getInstance();

    @FXML private VBox historyVBox;
    @FXML private Accordion historyAccordion;
    @FXML private FlowPane historyFlowPane;
    @FXML private ScrollPane historyScrollPane;
    List<HistoryItem> historyItems = new ArrayList<>();  //innan vi avslutar och clearar ett köp lägg i ett nytt historyItem här med den shoppingcarten.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        historyScrollPane.setFitToWidth(true);

        historyAccordion.getPanes().add(new HistoryItem(datahandler.getShoppingCart(), this));
        for (HistoryItem item : historyItems) {
            historyAccordion.getPanes().add(item);
        }

        System.out.println(historyAccordion.getPanes().size());
    }
}
