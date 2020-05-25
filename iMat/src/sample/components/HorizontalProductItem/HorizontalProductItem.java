package sample.components.HorizontalProductItem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class HorizontalProductItem extends AbstractProductItem {


    @FXML private Label totalPriceLabel;
    @FXML private Label amountBought;

    public HorizontalProductItem(ShoppingItem product){
        super(product.getProduct());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HorizontalProductItem.fxml"));
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

        amountBought.setText("Antal köpta: " + product.getAmount() + " " + product.getProduct().getUnitSuffix());

        totalPriceLabel.setText("Totalpris: " + df.format(product.getTotal()) + " kr");
    }

    private void setupShoppingCartListener() {
        dataHandler.getShoppingCart().addShoppingCartListener(cartEvent -> {
            if (cartEvent.getShoppingItem() == null || cartEvent.getShoppingItem().getProduct() == product) {
                ShoppingItem item = getCartItemIfExists();
                setNrProductsTextField(item);
                setRemoveButtonDisabled(item);
            }
        });
    }
}
