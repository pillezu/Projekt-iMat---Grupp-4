package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.components.details.ProductDetail;
import sample.components.productitem.ProductItem;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IMat extends Application {
    public static Stage stage;
    public static Map<String, ProductItem> productItemMap = new HashMap<>();
    public static StackPane detailsStackPane;
    public static String openScreenPath = "screens/main/Screen.fxml";
    public static IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {


        //dataHandler.getShoppingCart().clear();

        Parent root = FXMLLoader.load(getClass().getResource("screens/main/Screen.fxml"));
        stage = primaryStage;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.setMaximized(true);
        stage.setResizable(false);
        primaryStage.setTitle("iMat");
        primaryStage.show();

    }

    public static void changeRoot(String fxmlPath) {
        System.out.println("Changing root to: " + fxmlPath);
        try {
            openScreenPath = fxmlPath;
            URL location = IMat.class.getResource(fxmlPath);
            Parent root = FXMLLoader.load(location);
            System.out.println(root);
            IMat.stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Could not open fxmlPath: " + fxmlPath);
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                dataHandler.shutDown();
            }
        }));
    }

    public static void setDetails(Product product) {
        if (detailsStackPane == null) {
            return;
        }

        Node detail = new ProductDetail(product);
        detailsStackPane.getChildren().add(detail);
    }
}
