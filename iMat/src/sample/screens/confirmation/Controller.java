package sample.screens.confirmation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import sample.IMat;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML Label iMatLabel;
    @FXML ImageView homeImage;
    @FXML VBox mainScreen;
    @FXML Button btn1;
    @FXML
    ComboBox monthComboBox;
    @FXML
    ComboBox yearComboBox;
    @FXML
    RadioButton mastercardRadioButton;
    @FXML
    RadioButton visacardRadioButton;
    @FXML
    private Button ToFinish;
    @FXML
    private Button toCheckout;
    @FXML
     private Button saveButton;
    @FXML
    TextField NameTextField;
    @FXML
    TextField LastnameTextField;
    @FXML
    TextField epostadressTextField;
    @FXML
    TextField TelTextField;
    @FXML
    TextField AdressTextField;
    @FXML
    TextField PostkodTextField;
    @FXML
    TextField kortTextField;
    @FXML
    TextField cvcTextField;
    @FXML
    Rectangle accountSquare;
    @FXML
    AnchorPane confirmation;


    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    CreditCard creditCard= dataHandler.getCreditCard();
    Customer customer=dataHandler.getCustomer();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        //monthComboBox.getSelectionModel().select("Månad");

        monthComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                creditCard.setValidMonth(Integer.parseInt(newValue));
                creditCard.setValidMonth(Integer.parseInt(newValue));

            }
        });

        yearComboBox.getItems().addAll(2020,2021,2022,2023,2024);
        //yearComboBox.getSelectionModel().select("År");
        yearComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                creditCard.setValidYear(Integer.parseInt(newValue));

            }
        });
        ToggleGroup difficultyToggleGroup = new ToggleGroup();

        mastercardRadioButton.setToggleGroup(difficultyToggleGroup);
        visacardRadioButton.setToggleGroup(difficultyToggleGroup);
        ;


        difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (difficultyToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                    creditCard.setCardType(selected.getTypeSelector());

                }
            }
        });
        confirmation.toFront();

        ToFinish.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/finish/Screen.fxml"));
        toCheckout.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/checkout/Screen.fxml"));

    }
  public void saveConfirmationtAction(){
      customer.setFirstName(NameTextField.getText());
      customer.setLastName(LastnameTextField.getText());
      customer.setAddress(AdressTextField.getText());
      customer.setEmail(epostadressTextField.getText());
      customer.setPhoneNumber(TelTextField.getText());
      customer.setPostCode(PostkodTextField.getText());
      creditCard.setValidYear(Integer.parseInt((String) yearComboBox.getSelectionModel().getSelectedItem()));
      creditCard.setValidMonth(Integer.parseInt((String) monthComboBox.getSelectionModel().getSelectedItem()));
      creditCard.setCardNumber(kortTextField.getText());
      creditCard.setVerificationCode(Integer.parseInt(cvcTextField.getText()));
      creditCard.setCardType(mastercardRadioButton.getTypeSelector());
      creditCard.setCardType(visacardRadioButton.getTypeSelector());

    }
}





    /*private class TextFieldListener implements ChangeListener<Boolean> {

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
    /*
     */






