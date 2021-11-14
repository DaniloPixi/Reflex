package reflex.reflex;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import reflex.reflex.Music.*;

import java.io.*;

public class MainClassController {
    @FXML
    Button longOption;
    @FXML
    Button closeButton;
    @FXML
    Button shortOption;

    public static Stage st;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    @FXML  //the game option that lasts 30 seconds
     public void openShort(ActionEvent e1) {
        longOption.setOnMouseClicked(e->{
             st = (Stage) longOption.getScene().getWindow();
             st.close();
        });
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Stage1Style.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 800, true);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML   //the game option that lasts as long as the song
    public void openLong(ActionEvent e1) {
        shortOption.setOnMouseClicked(e->{
            st = (Stage) longOption.getScene().getWindow();
            st.close();
        });
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Stage1Style.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 800, true);
            Stage stage = new Stage();
            Songs.shortSSong();
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}