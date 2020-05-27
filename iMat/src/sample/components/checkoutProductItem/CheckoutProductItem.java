package sample.components.checkoutProductItem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class CheckoutProductItem extends AbstractProductItem {


    @FXML private Label totalPriceLabel;
    @FXML private Label itemPriceLabel;

    public CheckoutProductItem(ShoppingItem product){
        super(product.getProduct());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CheckoutProductItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        setup();

        setupShoppingCartListener();

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);


        totalPriceLabel.setText("Totalpris: " + df.format(product.getTotal()) + " kr");
        itemPriceLabel.setText("Styckpris: " + df.format(product.getProduct().getPrice()) + " kr");
    }

    private void setupShoppingCartListener() {
        dataHandler.getShoppingCart().addShoppingCartListener(cartEvent -> {
            if (cartEvent.getShoppingItem() == null || cartEvent.getShoppingItem().getProduct() == product) {
                ShoppingItem item = getCartItemIfExists();
                setNrProductsTextField(item);
                setRemoveButtonDisabled(item);
                updatePrices(item);
            }
        });
    }

    private void updatePrices(ShoppingItem item){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        totalPriceLabel.setText("Totalpris: " + df.format(item.getTotal()) + " kr");
        itemPriceLabel.setText("Styckpris: " + df.format(item.getProduct().getPrice()) + " kr");
    }
}