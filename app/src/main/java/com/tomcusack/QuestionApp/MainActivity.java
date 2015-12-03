// Copyright 2015 Tom Cusack
package com.tomcusack.QuestionApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import com.tomcusack.QuestionApp.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import io.paperdb.Paper;

public class MainActivity extends Activity {

    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private TextView lblQuestionNumber;
    private ImageView imgPicture;
    private TextView lblScore;
    private ArrayList<QuestionObject> questions;
    private int score;
    private int numQuestions;
    private int currentQuestion;
    private Boolean answer;
    private String playerName;
    private static final int MAX_QUESTIONS = 10;
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFalse = (Button)findViewById(R.id.btnFalse);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        lblQuestionNumber = (TextView)findViewById(R.id.lblQuestionNumber);
        imgPicture = (ImageView)findViewById(R.id.imgPicture);
        lblScore = (TextView)findViewById(R.id.lblScore);
        score = 0;
        numQuestions = 0;
        currentQuestion = 0;
        questions = new ArrayList<QuestionObject>();

        // False button listener
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer
                if(currentQuestion <  numQuestions){
                    checkAnswer(false);
                }
            }
        });

        // True button listener
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer
                if(currentQuestion <  numQuestions){
                    checkAnswer(true);
                }
            }
        });

        correctSound = MediaPlayer.create(MainActivity.this, R.raw.correct );
        wrongSound = MediaPlayer.create(MainActivity.this, R.raw.wrong);

        // I think now I'm using Parse I don't need to connect to paper anymore ...
        Paper.init(this);

        // Get the questions
        loadQuestions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadQuestions()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParseQuestionObject");
        query.findInBackground(new FindCallback<ParseObject>()
        {
            public void done(List<ParseObject> objects, ParseException e)
            {
                if (e == null)
                {
                    int len = objects.size();

                    // We need to loop through the parse dataset to ensure we have loaded
                    // all the questions to the question array

                    for (int i=0;i<len;i++)
                    {
                        ParseObject temp = objects.get(i);

                        questions.add(new QuestionObject(

                                // 'Question' Column
                                temp.getString("Question"),

                                // 'Answer' Column
                                temp.getBoolean("Answer"),

                                // 'ImageURL' Column
                                temp.getString("ImageURL")
                        ));
                    }
                }

                // Let's get the questions and answers ready to display
                setupQuestions();

            }

        });
    }

    private void setupQuestions()
    {
        // This function loads of a maximum of 10 random questions
        Collections.shuffle(questions);
        numQuestions = questions.size();

        if (numQuestions > MAX_QUESTIONS)
        {
            numQuestions = MAX_QUESTIONS;
        }

        // Once the questions are generated we can fire off a question..
        nextQuestion();
    }

    private void nextQuestion()
    {
        if (currentQuestion < numQuestions)
        {
            lblQuestion.setText(questions.get(currentQuestion).getQuestion());
            lblQuestionNumber.setText("Question " + (currentQuestion + 1));

            String imageURL = questions.get(currentQuestion).getImageURL();

            Picasso.with(this)
                    .load(imageURL)
                    .placeholder(R.drawable.question)
                    .error(R.drawable.question)
                    .into(imgPicture);

        }
        else
        {
            // We've run out of questions, and so that means the game has finished.
            // Let's calculate the score.
            endGame();
        }

    }

    private void checkAnswer(boolean answer)
    {
        if (answer == questions.get(currentQuestion).getAnswer())
        {
            // We want to positively reinforce getting a question right with a 'ding' (and give them a point!)
            correctSound.start();
            score++;
            lblScore.setText("Score:" + Integer.toString(score));
        }
        else
        {
            // Oops! They got it wrong. They don't get any extra points. But they do get a bad sound.
            wrongSound.start();
        }
        currentQuestion++;
        nextQuestion();
    }

    private void endGame()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations!");
        builder.setMessage("You scored " + score + " points! Please enter your name:");
        final EditText input = new EditText(this);

        // We need a little bit of input validation..
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                playerName = input.getText().toString();
                HighScoreObject highScore = new HighScoreObject();
                highScore.score = score;
                highScore.name = playerName;
                highScore.timestamp = new Date().getTime();

                List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());
                highScores.add(highScore);

                Collections.sort(highScores, new Comparator<HighScoreObject>() {
                    @Override
                    public int compare(HighScoreObject lhs, HighScoreObject rhs) {
                        if (lhs.score > rhs.score) {
                            return 1;
                        } else if (lhs.score < rhs.score) {
                            return -1;
                        } else if (lhs.timestamp > rhs.timestamp) {
                            return 1;
                        } else if (lhs.timestamp < rhs.timestamp) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });

                // Write the high scores to paper
                    Collections.reverse(highScores);
                Paper.book().write("highscores", highScores);
                finish();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        builder.show();

    }

}
