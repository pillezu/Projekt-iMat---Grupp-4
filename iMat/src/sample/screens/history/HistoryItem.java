package sample.screens.history;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import sample.components.HorizontalProductItem.HorizontalProductItem;
import se.chalmers.cse.dat216.project.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryItem extends TitledPane {

    IMatDataHandler datahandler = IMatDataHandler.getInstance();

    private Controller parentController;
    private List<Product> historyProducts;
    private String blankSpace = "\t\t";
    int amount = 0;
    @FXML private FlowPane historyFlowPane;
    @FXML private TitledPane historyItemTitledPane;

    public HistoryItem(ShoppingCart products, Controller parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        LocalDate localDate = LocalDate.now();
        String date = (localDate.toString());
        String day = getDay(localDate.getDayOfWeek());


        for (ShoppingItem item : products.getItems()){
            amount = amount + (int)item.getAmount();
        }

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String totalCost = df.format(products.getTotal());

        this.setText("\t Datum: " + date + blankSpace + "Dag: " + day + blankSpace + "Antal varor: " +
                amount + blankSpace + "Totalkostnad: " + totalCost + " kr");

        this.parentController = parentController;

        for (ShoppingItem item : products.getItems()) {
            historyFlowPane.getChildren().add(new HorizontalProductItem(item));
        }


    }

    String getDay(DayOfWeek day){
        switch(day){
            case MONDAY:
                return "Måndag";
            case TUESDAY:
                return "Tisdag";
            case WEDNESDAY:
                return "Onsdag";
            case THURSDAY:
                return "Torsdag";
            case FRIDAY:
                return "Fredag";
            case SATURDAY:
                return "Lördag";
            case SUNDAY:
                return "Söndag";
            default:
                return "Fel";
        }
    }
}
