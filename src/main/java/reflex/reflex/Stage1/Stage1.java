package reflex.reflex.Stage1;

import animatefx.animation.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import reflex.reflex.*;
import reflex.reflex.Music.*;

import java.io.*;
import java.util.*;

public class Stage1 {
    @FXML
    private Pane messagePane = new Pane();
    @FXML
    private Text messageText = new Text();
    @FXML
    private Text scoreText = new Text();
    @FXML
    private Text reflexText = new Text();
    @FXML
    private AnchorPane ap = new AnchorPane();
    @FXML
    private Button backButton = new Button();
    @FXML
    private Text scoreTextField;
    @FXML
    private Text reflexTimeField;
    private  List<Bubble> bubbleList = new ArrayList<>();
    private  List<Long> averageReflex = new ArrayList<>();
    private int bubbleNr = 0;
    private double score = 0;
    private final Songs song = new Songs();
    private double x = 0.0112;
    private long averageSum;
    private Long slowest;
    private Long fastest;

    @FXML
    private void initialize() {

        scoreText.setVisible(false);
        reflexText.setVisible(false);
        for (int i = 0; i <2000; i++) { //adding circles to the scene, 2000 is an arbitrary number
            bubbleList.add(new Bubble());
        }
        Songs.getPlayer().setOnEndOfMedia(()-> {  //calling the result of the game at the end of the song when the game ends
            try {
                getResult();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addBubbleOnClick();
    }


    private void addBubbleOnClick() {
        scoreText.setVisible(true);
        reflexText.setVisible(true);

        if (reflexText.isVisible()) {
            song.playSong();
        }

        for (Bubble b : bubbleList) {
            b.addEventHandler(MouseEvent.MOUSE_CLICKED, eh2);
            b.exitAnim.setOnFinished(ee -> ap.getChildren().remove(b)); //removing the circle at the end of the animation after being clicked

        }
        ap.getChildren().add(bubbleList.get(bubbleNr)); //adding the next circle from the list

    }
    private void setMessage(){             //setting a flash message of the reflex before the circle was clicked
        messageText.setText(Bubble.reflexMessage);
        FadeInDownBig fi = new FadeInDownBig(messagePane);
        fi.setSpeed(5);
        fi.play();
        fi.setOnFinished(e->new BounceOut(messagePane).setSpeed(1).play());
    }
    private final EventHandler<MouseEvent> eh2 = mouseEvent -> {
        if (mouseEvent.getSource() instanceof Bubble) {
            averageReflex.add(bubbleList.get(bubbleNr).time3);  //measuring the reflex time
            bubbleNr = bubbleNr + 1;   //increasing the bubble number so the next one from the list gets added to the scene
            score = score + 5;     //a arbitrary score for each successfully clicked circle
            ap.getChildren().add(bubbleList.get(bubbleNr));    //adds the new circle to the scene
            bubbleList.get(bubbleNr).time1 = System.currentTimeMillis();  //sets the appearing time of the last circle in the scene
            bubbleList.get(bubbleNr).enterAnim.play();
            if (bubbleNr == 0) {                //the appearing time is being set upon stage initiation, so always removing the first element since is not accurate
                reflexTimeField.setText("0");
            } else {
                reflexTimeField.setText(Bubble.s);  //displaying the reflex timer
            }
            scoreTextField.setText(String.valueOf((int) score));  //displaying the score
            setMessage();
        }
    };
    private void calculateAverage() {       //method to calculate the average reflex timer from the game , based on all the elements except the first one
        long sum = 0;
        for (int i = 1; i< averageReflex.size(); i++) {
            sum += averageReflex.get(i);
        }
        averageReflex.remove(0);   //the first element of the list that is always inaccurate
        averageSum = sum/ averageReflex.size();
    }
    private void getSlowestAndFastest(){      //as the name says, calculating the slowest and fastest reflex that will be displayed at the end of the game
        averageReflex.remove(0);
        fastest = Collections.min(averageReflex);
        slowest = Collections.max(averageReflex);
    }
    private void getResult() throws IOException {
        closeScene(); //closing the game scene
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/reflex/reflex/Result.fxml"));
        Parent root = fxmlLoader.load();
        Result result = fxmlLoader.getController(); //getting the controller of the result class , so we can set the text in the appropriate text fields

            getSlowestAndFastest();

        if(averageReflex.size()>2){ //check if the list has more than 3 elements, so it can calculate the average
            calculateAverage();
        }else{
            result.setTextRes(scoreTextField.getText(), "too few clicks",String.valueOf(slowest),String.valueOf(fastest));
        }

        result.setTextRes(scoreTextField.getText(), String.valueOf(averageSum),String.valueOf(slowest),String.valueOf(fastest)); //sets the results of the game in another scene
        Scene scene = new Scene(root, 700, 550);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        ((Stage)(scoreTextField.getScene().getWindow())).close();
    }
    public void reopen(ActionEvent e) {   //in case the player wants to interrupt the game, can simply press back and go to the main menu
        Songs.getPlayer().stop();
        ((Stage)(scoreTextField.getScene().getWindow())).close();
        closeScene();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MainClassController.class.getResource("RefMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    private void closeScene (){  //stops the playing songs and removes all the circles from the list
        ap.getChildren().removeAll(bubbleList);
        Songs.getPlayer().stop();
    }
}
