package reflex.reflex;


import animatefx.animation.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.stage.*;

import java.io.*;


public class Result {

    @FXML
    Label scoreResult;
    @FXML
    Label averageReflex;
    @FXML
    Label slowestReflex ;
    @FXML
    Label fastestReflex ;
    @FXML
    Label text3 ;
    @FXML
    Button eXbutton;


    @FXML
    public void initialize(){
        showUp();
    }
    public void setTextRes(String string1,String string2,String string3,String string4) { //displays the scores in the text field at the end of the game
       scoreResult.setText(string1);
       averageReflex.setText(string2);
       slowestReflex.setText(string3);
       fastestReflex.setText(string4);
    }
    @FXML
    public void close(ActionEvent e) throws IOException {   //closes the scene and returns to main menu
        ((Stage)(eXbutton.getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainClassController.class.getResource("RefMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }


    public void showUp(){  //a small glowing animation for the Exit button
        GlowText gbg = new GlowText(eXbutton,Color.DARKMAGENTA,Color.TRANSPARENT);
        gbg.setCycleCount(-1);
        gbg.setSpeed(0.5);
        gbg.play();
    }
}
