package reflex.reflex;




import javafx.scene.*;

import java.util.Random;

public class Grads {
//this class just sets some random style to the circles

    String first = "-fx-fill: radial-gradient(focus-distance 0% , center 50% 50% , radius 70% , #11b3d1, #9100ff)";
    String second = "-fx-fill: radial-gradient(focus-distance 0%, center 50% 50% , radius 70% , #7b0977, #031986)";
    String third = "-fx-fill: radial-gradient(focus-distance 0%, center 50% 50% , radius 70% , #7b1609, #867303)";


    public Grads(Node node) {
        Random rand = new Random();
        int randGrad = rand.nextInt(4);
        if (node.getStyle().isEmpty()) {
            node.setStyle(first);
        }
        if (randGrad == 1) {
            node.setStyle(first);
        } else if (randGrad == 2) {
            node.setStyle(second);
        } else if (randGrad == 3) {
            node.setStyle(third);
        }
    }

}
