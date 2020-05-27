package sample.screens.confirmation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.DeliverySummaryManager;
import sample.IMat;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
    RadioButton masterCardRadioButton;
    @FXML
    RadioButton visaCardRadioButton;
    @FXML
    private Button ToFinish;
    @FXML
    private Button toCheckout;
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
    AnchorPane confirmation;
    @FXML
    TextArea recieptTextArea;

    @FXML
    Label deliveryLabel;
    @FXML
    Label totalLabel;
    @FXML
    Label summaryLabel;



    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    CreditCard creditCard= dataHandler.getCreditCard();
    Customer customer=dataHandler.getCustomer();
    private List<String>shopList=new ArrayList<>();

    private int deliveryAmount=0;
    private ToggleGroup deliveryToggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateReceptTextArea();
        confirmation.toFront();


        cvcTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cvcTextField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        kortTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                kortTextField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        PostkodTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                PostkodTextField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        monthComboBox.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        if (creditCard.getValidMonth() == 0) {
            monthComboBox.getSelectionModel().select(null);
        }
        else {
            monthComboBox.getSelectionModel().select(String.valueOf(creditCard.getValidMonth()));
        }


        yearComboBox.getItems().addAll("2020","2021","2022","2023","2024");
        if  (creditCard.getValidYear() == 0) {
            yearComboBox.getSelectionModel().select(null);
        }
        else {
            yearComboBox.getSelectionModel().select(String.valueOf(creditCard.getValidYear()));
        }

        ToggleGroup difficultyToggleGroup = new ToggleGroup();
        masterCardRadioButton.setToggleGroup(difficultyToggleGroup);
        visaCardRadioButton.setToggleGroup(difficultyToggleGroup);

        if (creditCard.getCardType().equals(masterCardRadioButton.getText())) {
            masterCardRadioButton.setSelected(true);
        }
        else if (creditCard.getCardType().equals(visaCardRadioButton.getText())) {
            visaCardRadioButton.setSelected(true);
        }

        difficultyToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (difficultyToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) difficultyToggleGroup.getSelectedToggle();
                    creditCard.setCardType(selected.getText());
                }
            }
        });

        ToFinish.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/finish/Screen.fxml"));
        toCheckout.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/checkout/Screen.fxml"));

        Tooltip.install(ToFinish, new Tooltip("Genomför köp"));
        Tooltip.install(toCheckout, new Tooltip("Gå tillbaka till kassan"));

        summaryLabel.setText(DeliverySummaryManager.getDeliverySummary());
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


    }
    private void populateReceptTextArea(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        for (ShoppingItem shoppingItem : dataHandler.getShoppingCart().getItems()) {
            recieptTextArea.appendText((shoppingItem.getAmount() + " st"+"  " +shoppingItem.getProduct().getName() + "   "+" "  +"  " + "  "+shoppingItem.getTotal() + "    kr "));
            recieptTextArea.appendText( "\n");
        }
            if(deliveryToggleGroup.getSelectedToggle()!= null){
                deliveryAmount = (deliveryToggleGroup.getSelectedToggle().equals(deliveryLabel)) ? 75 : 0;
                deliveryLabel.setText(deliveryAmount+ " kr");

            }

            //deliveryLabel.setText(deliveryAmount+ "  kr");

            totalLabel.setText(df.format(dataHandler.getShoppingCart().getTotal()+ deliveryAmount)+ " kr");


    }
}


    /*public void format(String s){
        int from= recieptTextArea.getCaretPosition();
        int to= recieptTextArea.getLength()/2;
        recieptTextArea.selectRange(from,to);

    }
    /*
     */










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






