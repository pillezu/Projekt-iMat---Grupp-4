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
        mainLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("sample/screens/main/Screen.fxml"));
        favoritesLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("sample/screens/favorites/Screen.fxml"));
        historyLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("sample/screens/main/Screen.fxml"));
        accountDetailsLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("sample/screens/main/Screen.fxml"));
    }
}
