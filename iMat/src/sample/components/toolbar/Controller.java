package sample.components.toolbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import sample.IMat;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private ToolBar toolbar;
    @FXML private Hyperlink mainLink;
    @FXML private Hyperlink favoritesLink;
    @FXML private Hyperlink historyLink;
    @FXML private Hyperlink accountDetailsLink;
    @FXML private Label iMatLabel;
    @FXML private ImageView homeImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //toolbar.setPickOnBounds(false);
        mainLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
        favoritesLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/favorites/Screen.fxml"));
        historyLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/history/Screen.fxml"));
        accountDetailsLink.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/accountdetails/Screen.fxml"));
        iMatLabel.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));
        homeImage.setOnMouseClicked(mouseEvent -> IMat.changeRoot("screens/main/Screen.fxml"));

        makeSelectedTabBold();

        Tooltip.install(mainLink, new Tooltip("Gå till sidan för alla varor"));
        Tooltip.install(favoritesLink, new Tooltip("Gå till sidan för dina favoritvaror"));
        Tooltip.install(historyLink, new Tooltip("Gå till sidan för dina tidigare köp"));
        Tooltip.install(accountDetailsLink, new Tooltip("Gå till sidan för att se och ändra din uppgifter"));
    }

    private void makeSelectedTabBold() {
        switch (IMat.openScreenPath) {
            case "screens/main/Screen.fxml":
                setSelected(mainLink);
                break;
            case "screens/favorites/Screen.fxml":
                setSelected(favoritesLink);
                break;
            case "screens/history/Screen.fxml":
                setSelected(historyLink);
                break;
            case "screens/accountdetails/Screen.fxml":
                setSelected(accountDetailsLink);
                break;
        }
    }

    private void setSelected(Hyperlink selectedTab) {
        selectedTab.setStyle("-fx-font-weight: bold; -fx-underline: true;");
    }
}
