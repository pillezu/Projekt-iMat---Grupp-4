package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;
import java.net.URL;

public class IMat extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        IMatDataHandler.getInstance().getShoppingCart().clear();

        Parent root = FXMLLoader.load(getClass().getResource("screens/main/Screen.fxml"));
        stage = primaryStage;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        stage.setResizable(false);
        primaryStage.setTitle("iMat");
        primaryStage.show();
    }

    public static void changeRoot(String fxmlPath) {
        System.out.println("Changing root to: " + fxmlPath);
        try {
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
                IMatDataHandler.getInstance().shutDown();
            }
        }));
    }
}
