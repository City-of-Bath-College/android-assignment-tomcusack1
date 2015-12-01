package com.tomcusack.QuestionApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tomcusack.QuestionApp.R;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {

    /* Declare vars */
    private Button btnPlay;
    private Button btnAbout;
    private Button btnHighScoreTable;
    private TextView lblHighScore;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Log.d("TCUSACKLOG", "***** APP START *****");

        /* Init vars */
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnPlay =  (Button)findViewById(R.id.btnPlay);
        btnHighScoreTable = (Button)findViewById(R.id.btnHigh);
        lblHighScore = (TextView)findViewById(R.id.highScoreMessage);

        // Initiate Paper
        Paper.init(this);

        /* Add listener for false button */
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Code goes here
                Log.d("TCUSACKLOG", "BUTTON About");
                Intent  i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        /* Add listener for play button */
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code goes here
                Log.d("TCUSACKLOG", "BUTTON PLAY");
                Intent  i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        /* Add listener for High score button */
        btnHighScoreTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code goes here
                Log.d("TCUSACKLOG", "BUTTON Highscore");
                Intent  i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TCUSACKLOG", "onStart Called!!");
        highScore = getHighScore();
        // Set High score text
        lblHighScore.setText("High Score: " + Integer.toString(highScore));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_introduction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private int getHighScore(){

        // Declare default high score result
        int result = 0;

        // Load highscores using paper
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        // Get length og list
        int len = highScores.size();

        if(len > 0){
            // Hightscore objects exist
            Log.d("TCUSACKLOG", "High scores found!");

            // Highest Score should be first as we sorted them
            HighScoreObject highScore = highScores.get(0);
            result = highScore.score;
        }
        else{
            // No High Score objects found
            Log.d("TCUSACKLOG", "NO high scores found!");
        }

        return result;
    }
}
