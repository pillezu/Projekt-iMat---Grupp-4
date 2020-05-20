package sample.components.HorizontalProductItem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.components.AbstractProductItem;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class HorizontalProductItem extends AbstractProductItem {


    @FXML private Label totalPriceLabel;

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

        dataHandler.getShoppingCart().addShoppingCartListener(cartEvent -> {
            if (cartEvent.getShoppingItem().getProduct() == product.getProduct()) {
                setNrProductsTextField(product);
            }
        });

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        totalPriceLabel.setText(df.format(product.getTotal()) + " kr");
    }
}
