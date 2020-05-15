package sample.screens.accountdetails;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField nameTextField;
    @FXML
    TextField lastnameTextField;
    @FXML
    TextField epostTextField;
    @FXML
    TextField addressTextField;
    @FXML
    TextField postnrTextField;
    @FXML
    TextField telephonenrTextField;
    @FXML
    TextField accountTextField;
    @FXML
    TextField cvcTextField;
    @FXML
    Label kontouppgiftLabel;
    @FXML
    Label nameLabel;
    @FXML
    Label lastnameLabel;
    @FXML
    Label epostaddressLabel;
    @FXML
    Label addresslLabel;
    @FXML
    Label postnrLabel;
    @FXML
    Label telephonenrLabel;
    @FXML
    Label accountLabel;
    @FXML
    Label cvcLabel;
    @FXML
    Label validLabel;
    @FXML
    ComboBox monthComboBox;
    @FXML
    ComboBox yearComboBox;
    @FXML
    Button backButton;
    @FXML
    Button saveButton;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    CreditCard creditCard= dataHandler.getCreditCard();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        monthComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        monthComboBox.getSelectionModel().select("Månad");
        monthComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                //creditCard.setValidMonth(Integer.parseInt(newValue));
                creditCard.setValidMonth(Integer.parseInt(newValue));

            }
        });

        yearComboBox.getItems().addAll(2021,2022,2023);
        yearComboBox.getSelectionModel().select("År");
        yearComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                creditCard.setValidYear(Integer.parseInt(newValue));

            }
        });

    }

    private class TextFieldListener implements ChangeListener<Boolean> {

        private javafx.scene.control.TextField textField;


        public TextFieldListener(javafx.scene.control.TextField textField){
            this.textField = textField;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue){
                System.out.println(dataHandler);
                System.out.println(textField);

            }

        }
    }
}








