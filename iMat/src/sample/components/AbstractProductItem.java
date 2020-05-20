package sample.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class AbstractProductItem extends AnchorPane {

    protected IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private ShoppingCart cart = dataHandler.getShoppingCart();

    protected Product product;

    @FXML
    protected ImageView productImageView;
    @FXML
    protected Label productNameLabel;
    @FXML
    protected Label priceLabel;
    @FXML
    protected TextField nrProductsTextField;
    @FXML
    protected Button addButton;
    @FXML
    protected Button removeButton;
    @FXML
    protected ImageView favoriteImageView;

    public AbstractProductItem(Product product) {
        this.product = product;
    }

    protected void setup() {
        productNameLabel.setText(product.getName());
        if (priceLabel != null) {
            priceLabel.setText(product.getPrice() + " " + product.getUnit());
        }
        productImageView.setImage(dataHandler.getFXImage(product));
        ShoppingItem item = getCartItemIfExists();
        setNrProductsTextField(item);
        setRemoveButtonDisabled(item);
        setupButtons();

    }

    private void setupButtons() {
        addButton.setOnMouseClicked(mouseEvent -> {
            ShoppingItem item = getCartItemIfExists();
            if (item == null) {
                item = new ShoppingItem(product);
                dataHandler.getShoppingCart().addProduct(product);
            } else {
                item.setAmount(item.getAmount() + 1);
            }
            cart.fireShoppingCartChanged(item, true);
        });
        removeButton.setOnMouseClicked(mouseEvent -> {
            ShoppingItem item = getCartItemIfExists();
            if (item == null) {
                return;
            } else {
                if (item.getAmount() <= 1) {
                    cart.removeItem(item);
                } else {
                    item.setAmount(item.getAmount() - 1);
                }
            }
            cart.fireShoppingCartChanged(item, false);
        });
        nrProductsTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nrProductsTextField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });
        nrProductsTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            setNrProductsToTextField();
        });
        nrProductsTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                setNrProductsToTextField();
            }
        });
    }

    private void setNrProductsToTextField() {
        double nrProducts = 0;
        try {
            nrProducts = Double.valueOf(nrProductsTextField.getText());
        } catch (NumberFormatException e) {
        }
        ShoppingItem item = getCartItemIfExists();
        if (nrProducts == 0) {
            if (item != null) {
                cart.removeItem(item);
            }
        } else {
            if (item == null) {
                item = new ShoppingItem(product);
                dataHandler.getShoppingCart().addProduct(product, nrProducts);
            } else {
                item.setAmount(nrProducts);
            }
        }

        cart.fireShoppingCartChanged(item, true);
    }

    protected void setRemoveButtonDisabled(ShoppingItem item) {
        boolean disabled = item == null;
        // setDisable wont update the color the button status here for some reason
        // so we always set it to true and then change the color manually.
        removeButton.setDisable(false);
        if (disabled) {
            removeButton.setStyle("-fx-opacity: 0.5");
        } else {
            removeButton.setStyle("-fx-opacity: 1");
        }
    }

    protected void setNrProductsTextField(ShoppingItem item) {
        double nrProducts = 0;
        if (item != null) {
            nrProducts = item.getAmount();
        }
        nrProductsTextField.setText("" + nrProducts);
    }

    protected ShoppingItem getCartItemIfExists() {
        List<ShoppingItem> items = dataHandler.getShoppingCart().getItems();
        for (ShoppingItem item : items) {
            if (item.getProduct() == product) {
                return item;
            }
        }
        return null;
    }

}
