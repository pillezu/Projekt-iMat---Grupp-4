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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class HistoryItem extends TitledPane {


    private String blankSpace = "\t\t";
    int amount = 0;
    double totalPrice;
    @FXML private FlowPane historyFlowPane;
    @FXML private TitledPane historyItemTitledPane;

    public HistoryItem(Order order){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("historyItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        LocalDate localDate = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd  hh:mm");
        String date = (sdf.format(order.getDate()));
        String day = getDay(order.getDate().getDay());

        for (ShoppingItem item : order.getItems()){
            amount = amount + (int)item.getAmount();
            totalPrice += item.getTotal();
        }



        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String totalCost = df.format(totalPrice);

        this.setText("\t Datum: " + date + blankSpace + "Dag: " + day + blankSpace + "Antal varor: " +
                amount + blankSpace + "Totalkostnad: " + totalCost + " kr");

        for (ShoppingItem item : order.getItems()) {
            historyFlowPane.getChildren().add(new HorizontalProductItem(item));
        }


    }

    String getDay(int day){
        switch(day){
            case 1:
                return "Måndag";
            case 2:
                return "Tisdag";
            case 3:
                return "Onsdag";
            case 4:
                return "Torsdag";
            case 5:
                return "Fredag";
            case 6:
                return "Lördag";
            case 7:
                return "Söndag";
            default:
                return "Fel";
        }
    }
}
