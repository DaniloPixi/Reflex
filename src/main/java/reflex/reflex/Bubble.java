package reflex.reflex;

import animatefx.animation.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;

import java.util.*;
public class Bubble extends Circle {
    public BounceInLeft enterAnim = new BounceInLeft(this);
    public BounceOutRight exitAnim = new BounceOutRight(this);
    public ZoomIn zoom = new ZoomIn(this);
    public  static String s;
    public static String reflexMessage;

    //please bring up suggestions with other elements of the string arrays, I gave up :)

    public static String[] messageLow = {"Awesome!","Great Job!","Speed Light!","Eye Blink!","Instant"};
    public static String[] messageMedium ={"Not bad!","Pretty good!","Well done!","Not bad at all!","Keep it up!"};
    public static String[] messageHigh = {"Focus!","Sleeping?","Wake up!","Could be better!","This is not chess!"};
    public Random ran = new Random();
    float x = ran.nextInt(750 - 150 + 1) + 150;
    float y = ran.nextInt(750 - 150 + 1) + 150;
    public long time1;
    public long time2;
    public long time3;

    public Bubble() {
        Grads g = new Grads(this); //getting the style for the circle
        enterAnim.setSpeed(3);
        setCenterX(x);    //positions the circle in a random position of the scene
        setCenterY(y);
        setRadius(40);
        addEventHandler(MouseEvent.MOUSE_CLICKED, eh);
        //disabling the circle after being clicked to avoid being clicked twice and mess with the entire list of circles and the score
        setOnMouseClicked(e -> setDisable(true));

    }
        //the event that gets triggered when the circle is clicked, such as reflex time, exit animation and reflex message
   private EventHandler<MouseEvent> eh = mouseEvent -> {
        if (mouseEvent.getSource() instanceof Bubble) {
            exitAnim.play();
            zoom.play();
            time2 = System.currentTimeMillis(); //upon being clicked , it sets a new float
            time3 = time2-time1; //reflex time, result of the time of appearance and the time when the circle is clicked
            s = String.valueOf(time2 - time1); //displaying the reflex time (float) as a string
            if(time3<500){  //based on the speed of the reflex the player gets a random message
                reflexMessage = messageLow[ran.nextInt(messageLow.length)];
            }else if(time3>500 && time3< 1000){
                reflexMessage = messageMedium[ran.nextInt(messageMedium.length)];
            }else if(time3 > 1000){
                reflexMessage = messageHigh[ran.nextInt(messageHigh.length)];
            }
        }
    };

}
