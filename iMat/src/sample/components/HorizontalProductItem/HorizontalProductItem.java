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


public class HorizontalProductItem extends AbstractProductItem {


    @FXML private Label totalPriceLabel;

    public HorizontalProductItem(Product product){
        super(product);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HorizontalProductItem.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try{
            fxmlLoader.load();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        setup();
    }
}
