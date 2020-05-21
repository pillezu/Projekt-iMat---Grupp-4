package sample.screens.accountdetails;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML TextField nameTextField;
    @FXML TextField lastnameTextField;
    @FXML TextField epostTextField;
    @FXML TextField addressTextField;
    @FXML TextField postnrTextField;
    @FXML TextField telephonenrTextField;
    @FXML TextField accountTextField;
    @FXML TextField cvcTextField;
    @FXML Label kontouppgiftLabel;
    @FXML Label nameLabel;
    @FXML Label lastnameLabel;
    @FXML Label epostaddressLabel;
    @FXML Label addresslLabel;
    @FXML Label postnrLabel;
    @FXML Label telephonenrLabel;
    @FXML Label accountLabel;
    @FXML Label cvcLabel;
    @FXML Label validLabel;
    @FXML ComboBox monthComboBox;
    @FXML ComboBox yearComboBox;
    @FXML Button backButton;
    @FXML Button saveButton;
    @FXML RadioButton mastercardRadioButton;
    @FXML RadioButton visakortRadioButton;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    CreditCard creditCard = dataHandler.getCreditCard();
    Customer customer = dataHandler.getCustomer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showSavedContactInfo();

        monthComboBox.getItems().addAll("Månad","1","2","3","4","5","6","7","8","9","10","11","12");
        if (creditCard.getValidMonth() == 0) {
            monthComboBox.getSelectionModel().select("Månad");
        }
        else {
            monthComboBox.getSelectionModel().select(String.valueOf(creditCard.getValidMonth()));
        }

        monthComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                if (!newValue.equals("Månad")) {
                    creditCard.setValidMonth(Integer.parseInt(newValue));
                }
            }
        });

        yearComboBox.getItems().addAll("År","2020","2021","2022","2023","2024");
        if  (creditCard.getValidYear() == 0) {
            yearComboBox.getSelectionModel().select("År");
        }
        else {
            yearComboBox.getSelectionModel().select(String.valueOf(creditCard.getValidYear()));
        }
        yearComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                if (!newValue.equals("År")) {
                    creditCard.setValidYear(Integer.parseInt(newValue));
                }
            }
        });

        ToggleGroup difficultyToggleGroup = new ToggleGroup();
        mastercardRadioButton.setToggleGroup(difficultyToggleGroup);
        visakortRadioButton.setToggleGroup(difficultyToggleGroup);


        difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (difficultyToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                    creditCard.setCardType(selected.getText());
                }
            }
        });
    }

    private void showSavedContactInfo() {
        nameTextField.setText(customer.getFirstName());
        lastnameTextField.setText(customer.getLastName());
        addressTextField.setText(customer.getAddress());
        epostTextField.setText(customer.getEmail());
        telephonenrTextField.setText(customer.getPhoneNumber());
        postnrTextField.setText(customer.getPostCode());
        accountTextField.setText(creditCard.getCardNumber());
        cvcTextField.setText(String.valueOf(creditCard.getVerificationCode()));

    }

    public void saveContactAction() {
        customer.setFirstName(nameTextField.getText());
        customer.setLastName(lastnameTextField.getText());
        customer.setAddress(addressTextField.getText());
        customer.setEmail(epostTextField.getText());
        customer.setPhoneNumber(telephonenrTextField.getText());
        customer.setPostCode(postnrTextField.getText());
        creditCard.setValidYear(Integer.parseInt((String) yearComboBox.getSelectionModel().getSelectedItem()));
        creditCard.setValidMonth(Integer.parseInt((String) yearComboBox.getSelectionModel().getSelectedItem()));
        creditCard.setCardNumber(accountTextField.getText());
        creditCard.setVerificationCode(Integer.parseInt(cvcTextField.getText()));
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
}








