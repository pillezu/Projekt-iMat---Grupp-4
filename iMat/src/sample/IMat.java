package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IMat extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage = primaryStage;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void changeRoot(String fxmlPath) {
        System.out.println("Changing root to: " + fxmlPath);
        try {
            Parent root = FXMLLoader.load(IMat.class.getResource(fxmlPath));
            IMat.stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println("Could not open fxmlPath: " + fxmlPath);
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
