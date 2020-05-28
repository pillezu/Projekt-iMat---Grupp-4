package sample.screens.confirmation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {


    @FXML
    VBox mainScreen;
    @FXML
    ComboBox monthComboBox;
    @FXML
    ComboBox yearComboBox;
    @FXML
    RadioButton masterCardRadioButton;
    @FXML
    RadioButton visaCardRadioButton;
    @FXML
    Button ToFinish;
    @FXML
    Button toCheckout;
    @FXML
    TextField nameTextField;
    @FXML
    TextField lastnameTextField;
    @FXML
    TextField epostAdressTextField;
    @FXML
    TextField telTextField;
    @FXML
    TextField adressTextField;
    @FXML
    TextField postNrTextField;
    @FXML
    TextField cardTextField;
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
    @FXML
    Label savedMessageLabel;


    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    CreditCard creditCard = dataHandler.getCreditCard();
    Customer customer = dataHandler.getCustomer();
    ToggleGroup cardToggleGroup = new ToggleGroup();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateReceiptTextArea();
        confirmation.toFront();
        savedMessageLabel.setVisible(false);


        cvcTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (!newValue.matches("\\d*")) {
                cvcTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cardTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (!newValue.matches("\\d*")) {
                cardTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        postNrTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (!newValue.matches("\\d*")) {
                postNrTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        monthComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        yearComboBox.getItems().addAll("2020", "2021", "2022", "2023", "2024");

        masterCardRadioButton.setToggleGroup(cardToggleGroup);
        visaCardRadioButton.setToggleGroup(cardToggleGroup);

        cardToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {

            if (cardToggleGroup.getSelectedToggle() != null) {
                RadioButton selected = (RadioButton) cardToggleGroup.getSelectedToggle();
                creditCard.setCardType(selected.getText());
            }
        });

        ToFinish.setOnMouseClicked(mouseEvent -> {
            dataHandler.placeOrder();
            dataHandler.getShoppingCart().clear();
            IMat.changeRoot("screens/finish/Screen.fxml");
        });
        toCheckout.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/checkout/Screen.fxml"));

        Tooltip.install(ToFinish, new Tooltip("Genomför köp"));
        Tooltip.install(toCheckout, new Tooltip("Gå tillbaka till kassan"));

        summaryLabel.setText(DeliverySummaryManager.getDeliverySummary());

        showSavedContactInfo();
        makeIrrelevantInfoDisabled();
        checkMissingInformation();
        addCheckMissingInformationListeners();
    }

    private void addCheckMissingInformationListeners() {
        for (TextField field : new TextField[]{nameTextField, lastnameTextField, epostAdressTextField, telTextField,
                adressTextField, postNrTextField, cardTextField, cvcTextField}) {
            field.textProperty().addListener(observable -> checkMissingInformation());
        }
        cardToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> checkMissingInformation());
    }

    private void checkMissingInformation() {
        boolean hasAllInfo = true;
        List<TextField> textFields = new ArrayList<>();
        textFields.add(nameTextField);
        textFields.add(lastnameTextField);
        textFields.add(epostAdressTextField);
        textFields.add(telTextField);
        if (DeliverySummaryManager.deliveryType == DeliverySummaryManager.DeliveryType.DELIVERY) {
            textFields.add(adressTextField);
            textFields.add(postNrTextField);
        }
        if (DeliverySummaryManager.paymentType == DeliverySummaryManager.PaymentType.ONLINE) {
            textFields.add(cardTextField);
            textFields.add(cvcTextField);
            if (!masterCardRadioButton.isSelected() && !visaCardRadioButton.isSelected()) {
                if (!masterCardRadioButton.getStyleClass().contains("error")) {
                    masterCardRadioButton.getStyleClass().add("error");
                }
                if (!visaCardRadioButton.getStyleClass().contains("error")) {
                    visaCardRadioButton.getStyleClass().add("error");
                }
                hasAllInfo = false;
            } else {
                masterCardRadioButton.getStyleClass().remove("error");
                visaCardRadioButton.getStyleClass().remove("error");
            }
        }
        for (TextField field : textFields) {
            if (field.getText() == null || field.getText().length() == 0) {
                if (!field.getStyleClass().contains("error")) {
                    field.getStyleClass().add("error");
                }
                hasAllInfo = false;
            } else {
                field.getStyleClass().remove("error");
            }
        }
        ToFinish.setDisable(!hasAllInfo);
    }

    private void makeIrrelevantInfoDisabled() {
        if (DeliverySummaryManager.deliveryType == DeliverySummaryManager.DeliveryType.PICKUP) {
            adressTextField.setDisable(true);
            postNrTextField.setDisable(true);
        }
        if (DeliverySummaryManager.paymentType == DeliverySummaryManager.PaymentType.IN_PERSON) {
            cardTextField.setDisable(true);
            cvcTextField.setDisable(true);
            monthComboBox.setDisable(true);
            yearComboBox.setDisable(true);
            masterCardRadioButton.setDisable(true);
            visaCardRadioButton.setDisable(true);
        }
    }

    private void showSavedContactInfo() {
        nameTextField.setText(customer.getFirstName());
        lastnameTextField.setText(customer.getLastName());
        adressTextField.setText(customer.getAddress());
        epostAdressTextField.setText(customer.getEmail());
        telTextField.setText(customer.getPhoneNumber());
        postNrTextField.setText(customer.getPostCode());
        cardTextField.setText(creditCard.getCardNumber());
        if (creditCard.getVerificationCode() == 0) {
            cvcTextField.setText(null);
        } else {
            cvcTextField.setText(String.valueOf(creditCard.getVerificationCode()));
        }

        if (creditCard.getCardType() != null) {
            if (creditCard.getCardType().equals(masterCardRadioButton.getText())) {
                masterCardRadioButton.setSelected(true);
            } else if (creditCard.getCardType().equals(visaCardRadioButton.getText())) {
                visaCardRadioButton.setSelected(true);
            }
        } else {
            cardToggleGroup.selectToggle(null);
        }

        if (creditCard.getValidMonth() == 0) {
            monthComboBox.getSelectionModel().select(null);
        } else {
            monthComboBox.getSelectionModel().select(String.valueOf(creditCard.getValidMonth()));
        }

        if (creditCard.getValidYear() == 2011) {
            yearComboBox.getSelectionModel().select("2021");
        } else {
            yearComboBox.getSelectionModel().select(String.valueOf(creditCard.getValidYear()));
        }
    }

    public void saveConfirmationAction() {

        customer.setFirstName(nameTextField.getText());
        customer.setLastName(lastnameTextField.getText());
        customer.setAddress(adressTextField.getText());
        customer.setEmail(epostAdressTextField.getText());
        customer.setPhoneNumber(telTextField.getText());
        customer.setPostCode(postNrTextField.getText());

        creditCard.setValidYear(Integer.parseInt((String) yearComboBox.getSelectionModel().getSelectedItem()));
        creditCard.setValidMonth(Integer.parseInt((String) monthComboBox.getSelectionModel().getSelectedItem()));
        creditCard.setCardNumber(cardTextField.getText());

        try {
            creditCard.setVerificationCode(Integer.parseInt(cvcTextField.getText()));
        } catch (NumberFormatException e) {
            creditCard.setVerificationCode(0);
        }
        savedMessageLabel.setVisible(true);
        CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
            savedMessageLabel.setVisible(false);
        });
    }

    private void populateReceiptTextArea() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        for (ShoppingItem shoppingItem : dataHandler.getShoppingCart().getItems()) {
            recieptTextArea.appendText((shoppingItem.getAmount() + " st" + "  " + shoppingItem.getProduct().getName() + "   " + " " + "  " + "  " + shoppingItem.getTotal() + "    kr "));
            recieptTextArea.appendText("\n");

            int deliveryAmount;
            if (DeliverySummaryManager.deliveryType == DeliverySummaryManager.DeliveryType.DELIVERY) {
                deliveryAmount = 75;
            } else {
                deliveryAmount = 0;
            }
            deliveryLabel.setText(deliveryAmount + " kr");

            totalLabel.setText(df.format(dataHandler.getShoppingCart().getTotal() + deliveryAmount) + " kr");
        }
    }
}
