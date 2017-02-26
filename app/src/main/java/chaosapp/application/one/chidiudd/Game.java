package chaosapp.application.one.chidiudd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;

import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static chaosapp.application.one.chidiudd.R.id.rel_layout;
import static chaosapp.application.one.chidiudd.R.id.vol_full;


public class Game extends AppCompatActivity {

    static TextView nameText;
    static TextView scoreText;
    Button start;
    boolean flagBackPress;
    static String name;
    Thread nextItem = null;
    Gamer Gamer=new Gamer();
    private Handler customHandler = new Handler();
    int scoreboard;
    static MediaPlayer wrongSound;
    static MediaPlayer rightSound;
    boolean volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        volume = pref.getBoolean("volume", true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        flagBackPress=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        rightSound=MediaPlayer.create(Game.this, R.raw.click3);
        wrongSound=MediaPlayer.create(Game.this, R.raw.wrong);
        RelativeLayout layout = (RelativeLayout) findViewById(rel_layout);
        nameText = (TextView) findViewById(R.id.name);
        nameText.setVisibility(View.INVISIBLE);
        scoreText = (TextView) findViewById(R.id.liveScore);
        scoreText.setText("0");
        scoreboard=0;
        //Toast.makeText(Game.this,"new-new", Toast.LENGTH_SHORT).show();
        start = (Button) findViewById(R.id.start);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nextItem==null) {
                    nameText.setVisibility(View.VISIBLE);
                    start.setVisibility(View.GONE);
                    scoreboard=0;
                    nextItem=new Thread(Gamer,"updateName");
                    nextItem.setPriority(Thread.MAX_PRIORITY);
                    nextItem.start();
                    customHandler.postDelayed(updateTimerThread, 0);
                }
            }
        });



        layout.setOnTouchListener(new OnSwipeTouchListener(Game.this) {

            public void onSwipeTop() {// fly

             //   Toast.makeText(Game.this,"top", Toast.LENGTH_SHORT).show();
                if(!Gamer.isOver()){
                    Gamer.incCount();
                    int index=Gamer.getIndex();

                    if((index%3)==2){//if wrong answer
                        gameover("why fly?");
                        nextItem=null;
                    }else{
                        	//update live score
                        if(volume) {
                            rightSound.start();
                        }
                        //mediaPlayer.setRightSound(Game.this);
                        scoreboard++;
                        nextItem.interrupt();
                        Thread.yield();
                    }
                }
            }

            public void onSwipeBottom() {// not fly

             //   Toast.makeText(Game.this,"bottom", Toast.LENGTH_SHORT).show();
                if(!Gamer.isOver()){
                   Gamer.incCount();
                    int index=Gamer.getIndex();

                    if((index%3)!=2){//wrong answer
                        gameover("why no fly?");
                        nextItem=null;
                    }else{
                        //update live score
                        if(volume) {
                            rightSound.start();
                        }//mediaPlayer.setRightSound(Game.this);
                        scoreboard++;
                        nextItem.interrupt();
                        Thread.yield();
                    }
                }
            }



            public boolean onTouch(View v, MotionEvent event) {

                GestureDetector gestureDetector=getGestureDetector();
                return gestureDetector.onTouchEvent(event);
            }

        });




    }

    @Override
    public void onBackPressed() {
        flagBackPress=true;
        nextItem=null;
        Gamer.stop();

        super.onBackPressed();

    }

    private Runnable updateTimerThread = new Runnable(){
        @Override
        public void run() {
            name=Gamer.getName();
            nameText.setText(name);
            if (Gamer.isOverT()) {
              //  scoreText.setText("0");
                gameover("too slow!\nanswer within 3 seconds");
                nextItem = null;
              Gamer.start();      //why???
            }

            if(!Gamer.isOver()) {
                //ToDo        this thread is running infinitely!!!!!
               // Toast.makeText(Game.this,"old", Toast.LENGTH_SHORT).show();
                scoreText.setText(Integer.toString(scoreboard));
            }else {
               // scoreboard=0;
                scoreText.setText("0");
            }



            customHandler.postDelayed(this, 0);
        }
    };

    public void gameover(String st) {
        if (!flagBackPress) {
            scoreText.setText("0");
            //    int score=Gamer.getScore();
            Gamer.stop();
            //gameover.setVisibility(View.VISIBLE);

            //    if(timeout==false)
            //        score--;
            //    if(score==-1)
            //        score=0;
            // customHandler.removeCallbacks(updateTimerThread);


            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int currScore = preferences.getInt("highscore", 0);

            if (scoreboard > currScore) {
                showMessage("Game Over", st + "\n" + "New High Score : " + scoreboard);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("highscore", scoreboard);
                editor.commit();
                //   Toast.makeText(Game.this,String.valueOf(currScore), Toast.LENGTH_SHORT).show();


            } else
                showMessage("Game Over", st + "\n" + "Score : " + scoreboard);

            if(volume) {
                wrongSound.start();
            } //  mediaPlayer.setWrongSound(Game.this);
            Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v1.vibrate(400);
            nameText.setVisibility(View.INVISIBLE);
            //nextItem.interrupt();
            //nextItem=null;
        }
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder .setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent_activity= new Intent(Game.this, Main_page.class);
                startActivity(intent_activity);
                Game.super.onBackPressed();
            }
        });
        builder.show();

    }



}

