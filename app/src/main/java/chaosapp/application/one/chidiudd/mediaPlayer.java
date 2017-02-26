package chaosapp.application.one.chidiudd;

import android.content.Context;
import android.media.MediaPlayer;


/**
 * Created by nishtha jain on 15-05-2016.
 */
public class mediaPlayer {
    static MediaPlayer wrongSound;
    static MediaPlayer rightSound;

    public static void setWrongSound(Context c){
        wrongSound=MediaPlayer.create(c, R.raw.wrong);
        wrongSound.start();
    }
    public static void setRightSound(Context c){
        rightSound=MediaPlayer.create(c, R.raw.click3);
        rightSound.start();
    }

    public static void setVolume(boolean val) {
        if (val == true) {
            wrongSound.setVolume(1, 1);
            rightSound.setVolume(1, 1);
        } else {
            wrongSound.setVolume(0, 0);
            rightSound.setVolume(0, 0);
        }
    }
}
