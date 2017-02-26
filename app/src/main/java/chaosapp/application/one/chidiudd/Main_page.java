package chaosapp.application.one.chidiudd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);

     //   mediaPlayer mp=new mediaPlayer();
        Button play=(Button)findViewById(R.id.play);
        Button howToPlay=(Button)findViewById(R.id.howtoplay);
        final ImageView volumeFull=(ImageView)findViewById(R.id.vol_full);
        final ImageView volumeMute=(ImageView)findViewById(R.id.vol_mute);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if(pref.getBoolean("volume", true)){
            volumeFull.setVisibility(View.VISIBLE);
            volumeMute.setVisibility(View.INVISIBLE);

        }else{
            volumeFull.setVisibility(View.INVISIBLE);
            volumeMute.setVisibility(View.VISIBLE);
        }





        volumeFull.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                volumeFull.setVisibility(View.INVISIBLE);
                volumeMute.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editr = pref.edit();
                editr.putBoolean("volume", false);
                editr.commit();


               // mediaPlayer.volume(false);
            }
        });

        volumeMute.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                volumeFull.setVisibility(View.VISIBLE);
                volumeMute.setVisibility(View.INVISIBLE);
                SharedPreferences.Editor editr = pref.edit();
                editr.putBoolean("volume", true);
                editr.commit();
               // mediaPlayer.volume(true);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent_activity= new Intent(Main_page.this, Game.class);
                startActivity(intent_activity);
            }
        });

        howToPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent_activity= new Intent(Main_page.this, MainActivity.class);
                startActivity(intent_activity);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        TextView score=(TextView)findViewById(R.id.score);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int highScore = preferences.getInt("highscore", 0);
        score.setText(Integer.toString(highScore));
    }

    @Override
    public void onBackPressed() {
       moveTaskToBack(true);
        finish();


    }
}
