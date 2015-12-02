// Copyright 2015 Tom Cusack

package com.tomcusack.QuestionApp;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.tomcusack.QuestionApp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends Activity
{
    private ListView listView;
    private List<com.tomcusack.QuestionApp.HighScoreObject> highScores;
    private Button btnReset;
    private TextView lblNoScores;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        // If there aren't any score
        lblNoScores = (TextView)findViewById(R.id.lblNoScores);
        lblNoScores.setVisibility(View.INVISIBLE);
        // .. and if there are ..
        Paper.init(this);
        displayScores();

        // Reset button
        btnReset = (Button)findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete all the high scores in paper
                Paper.book().delete("highscores");
                setContentView(R.layout.activity_high_score);
            }
        });
    }

    protected void displayScores()
    {
        // Let's get the high scores from paper, and display them if we have more than one
        highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        if (highScores.size() > 0)
        {
            HighscoreAdapter adapter = new HighscoreAdapter(highScores);
            listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(adapter);

            // Don't show people who get 0, because that's not good enough!
            lblNoScores.setVisibility(View.INVISIBLE);
        }
        else
        {
            // And we also want to hide it if there isn't anything in the database
            lblNoScores.setVisibility(View.VISIBLE);
        }

    }

    private class HighscoreAdapter extends ArrayAdapter<HighScoreObject>
    {

        public HighscoreAdapter(List<HighScoreObject> items)
        {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.row_highscore, null);
            }

            // Here we're going to get and return our high scores
            HighScoreObject highscore = highScores.get(position);
            Date date = new Date(highscore.getTimestamp());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            TextView lblTitle = (TextView)convertView.findViewById(R.id.lblTitle);
            String strOutput = (position + 1) + ". " + highscore.getName()  + ": " + + highscore.getScore() + " correct answers!";
            lblTitle.setText(strOutput);

            // And format the text colour
            if(position == 0)
            {
                lblTitle.setTextColor(Color.parseColor("#76EE00"));
            }

            return convertView;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_high_score, menu);
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

}
