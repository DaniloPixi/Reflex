package reflex.reflex;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;

import java.io.*;

public class MainClass extends Application {
    Stage stage = new Stage() ;
    Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        stage = this.stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RefMain.fxml"));
        scene = new Scene(fxmlLoader.load(), 1000, 800,true);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle(" ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}