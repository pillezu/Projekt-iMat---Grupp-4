package sample.screens.checkout;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.IMat;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    Label iMatLabel;
    @FXML
    ImageView homeImage;
    @FXML
    VBox mainScreen;
    @FXML
    Button btn1;

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
        nameTextField.focusedProperty().addListener(new TextFieldListener(nameTextField));
        lastnameTextField.focusedProperty().addListener(new TextFieldListener(lastnameTextField));
        epostTextField.focusedProperty().addListener(new TextFieldListener(epostTextField));
        addressTextField.focusedProperty().addListener(new TextFieldListener(addressTextField));
        postnrTextField.focusedProperty().addListener(new TextFieldListener(postnrTextField));
        telephonenrTextField.focusedProperty().addListener(new TextFieldListener(telephonenrTextField));
        accountTextField.focusedProperty().addListener(new TextFieldListener(accountTextField));
        cvcTextField.focusedProperty().addListener(new TextFieldListener(cvcTextField));



    }


    private class TextFieldListener implements ChangeListener<Boolean> {
        private TextField textField;

        public TextFieldListener(TextField textField) {
            this.textField = textField;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                System.out.println();
                System.out.println(textField);


            }

        }
    }
}



