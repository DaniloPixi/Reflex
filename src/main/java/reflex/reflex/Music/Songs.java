package reflex.reflex.Music;


import javafx.scene.media.*;
import javafx.util.*;

import java.util.*;


public class Songs {

    private static final Media song1 = new Media(Songs.class.getResource("/reflex/reflex/TULE, Chris Linton - Fearless Pt. II (Original Mix) [sickworldmusic].mp3").toExternalForm());
    private static final Media song2 = new Media(Songs.class.getResource("/reflex/reflex/Cartoon - On & On (feat. Daniel Levi) [NCS Release].mp3").toExternalForm());
    private static final Media song3 = new Media(Songs.class.getResource("/reflex/reflex/Warriyo  - Mortals (feat. Laura Brehm) [NCS Release].mp3").toExternalForm());
    public static final Media[] allSongs ={song1,song2,song3} ;
    private static Random rand3 = new Random();
    private static MediaPlayer player;

    public Songs() {
        player = new MediaPlayer(allSongs[rand3.nextInt(allSongs.length)]);

    }

    public void playSong() {
        Thread t = new Thread(() -> {
            player.play();
        });
        t.start();
    }

    public static MediaPlayer getPlayer() {
        return player;
    }


    public static void shortSSong(){
        player.stopTimeProperty().setValue(Duration.seconds(30));
    }
}
