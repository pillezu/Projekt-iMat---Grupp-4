package sample.screens.accountdetails;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private class TextFieldListener implements ChangeListener<Boolean> {

        private javafx.scene.control.TextField textField;


        public TextFieldListener(javafx.scene.control.TextField textField){
            this.textField = textField;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue){
                System.out.println();
                System.out.println(textField);

            }

        }
    }



}


