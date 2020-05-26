package sample.components.toolbar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import sample.IMat;

import javax.tools.Tool;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ToolBar toolbar;
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
        //toolbar.setPickOnBounds(false);
        mainLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
        favoritesLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/favorites/Screen.fxml"));
        historyLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/history/Screen.fxml"));
        accountDetailsLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/accountdetails/Screen.fxml"));

        Tooltip.install(mainLink, new Tooltip("Gå till sidan för alla varor"));
        Tooltip.install(favoritesLink, new Tooltip("Gå till sidan för dina favoritvaror"));
        Tooltip.install(historyLink, new Tooltip("Gå till sidan för dina tidigare köp"));
        Tooltip.install(accountDetailsLink, new Tooltip("Gå till sidan för att se och ändra din uppgifter"));
    }
}
