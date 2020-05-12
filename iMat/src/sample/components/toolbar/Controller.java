package sample.components.toolbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import sample.IMat;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Hyperlink mainLink;
    @FXML
    Hyperlink favoritesLink;
    @FXML
    Hyperlink historyLink;
    @FXML
    Hyperlink accountDetailsLink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainLink.setOnAction(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
        favoritesLink.setOnAction(mouseEvent -> IMat.changeRoot("screens/favorites/Screen.fxml"));
        historyLink.setOnAction(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
        accountDetailsLink.setOnAction(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
    }
}
