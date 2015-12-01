package com.tomcusack.QuestionApp;

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
<<<<<<< HEAD
=======

>>>>>>> origin/master
import com.tomcusack.QuestionApp.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;
<<<<<<< HEAD
=======

>>>>>>> origin/master
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
<<<<<<< HEAD
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

=======

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    /* Variables */
>>>>>>> origin/master
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
    private static final String IMAGE_ROOT_URL = "http://kvsk.org/dev/quiz/images/";
    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;
<<<<<<< HEAD

=======
>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Assign variables to interface items */
        btnFalse = (Button)findViewById(R.id.btnFalse);
        btnTrue = (Button)findViewById(R.id.btnTrue);
        lblQuestion = (TextView)findViewById(R.id.lblQuestion);
        lblQuestionNumber = (TextView)findViewById(R.id.lblQuestionNumber);
        imgPicture = (ImageView)findViewById(R.id.imgPicture);
        lblScore = (TextView)findViewById(R.id.lblScore);

        /* Init vars */
        score = 0;
        numQuestions = 0;
        currentQuestion = 0;

        // Init question array list
        questions = new ArrayList<QuestionObject>();

        /* Add listener for false button */
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer
                if(currentQuestion <  numQuestions){
                    checkAnswer(false);
                }
            }
        });

        /* Add listener for true button */
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check answer
                if(currentQuestion <  numQuestions){
                    checkAnswer(true);
                }
            }
        });

<<<<<<< HEAD
        correctSound = MediaPlayer.create(MainActivity.this, R.raw.ping );
        wrongSound = MediaPlayer.create(MainActivity.this, R.raw.boo);
        Paper.init(this);
        loadQuestions();
=======
        /* Init sounds */
        correctSound = MediaPlayer.create(MainActivity.this, R.raw.ping );
        wrongSound = MediaPlayer.create(MainActivity.this, R.raw.boo);

        /* Init Paper*/
        Paper.init(this);

        Log.d("TCUSACKLOG", "Main Activity Create");

        // Load Questions
        loadQuestionsFromParseAPI();
>>>>>>> origin/master
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

<<<<<<< HEAD
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
                    for(int i=0;i<len;i++)
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
                else

                // The app is probably unable to connect to the internet.
                // In this situation we'll roll back to locally stored Q&A
                {
                    loadFallbackQuestions();
                }

                // Let's get the questions and answers ready to display
=======
    /* Attempt to load question from Parse.com DB */
    private void loadQuestionsFromParseAPI(){

        // Query Parse.com DB to retrieve list of questions
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParseQuestionObject");
        query.findInBackground(new FindCallback<ParseObject>(){

            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    Log.d("TCUSACKLOG", "DATA LOADED OK!");
                    int len = objects.size();

                    // Add each object to questions
                    for(int i=0; i<len; i++)
                    {
                       ParseObject temp = objects.get(i);

                        /* Add qestion to array */
                        questions.add(new QuestionObject(
                                temp.getString("Question"),
                                temp.getBoolean("Answer"),
                                temp.getString("ImageURL")
                        ));
                    }

                } else {
                    /* Oh dear, something went wrong */
                    Log.d("MHISSDEV", "ERROR NO DATA LOADED");

                    /* Load fallback questions */
                    loadFallbackQuestions();
                }

>>>>>>> origin/master
                setupQuestions();
            }

        });
    }


    /* If Parse.com fails to load question use these! */
    private void loadFallbackQuestions(){

         /* Question 1 */
        questions.add(new QuestionObject(
                "Paris is the capital of Spain??",
                false,
                "http://goo.gl/wKOqbX"
        ));

        /* Question 2 */
        questions.add(new QuestionObject(
                "Rome is the capital of Italy??",
                true,
                "http://www.telegraph.co.uk/travel/hotel/article129671.ece/ALTERNATES/w620/rometravelguide5.jpg"
        ));

        /* Question 3 */
        questions.add(new QuestionObject(
                "London is the capital of England??",
                true,
                "http://cdn.londonandpartners.com/assets/73295-640x360-london-skyline-ns.jpg"
        ));

        /* Question 4 */
        questions.add(new QuestionObject(
                "Dublin is the capital of Ireland??",
                true,
                "http://www.aeroport-perpignan.com/sites/default/files/dublin_1830978b.jpg"
        ));

        /* Question 5 */
        questions.add(new QuestionObject(
                "Addis Ababa is the capital of Sudan??",
                false,
                "http://buzzkenya.com/wp-content/uploads/2015/01/Addis-Ababa-1.jpg"
        ));

        /* Question 6 */
        questions.add(new QuestionObject(
                "Canberra is the capital of Australia??",
                true,
                "http://95077ae8547a1b239f3b-5d2534a656ffdee95a27431d367b21fa.r54.cf1.rackcdn.com/27/1/large.jpg"
        ));

        /* Question 7 */
        questions.add(new QuestionObject(
                "Stockholm is the capital of Denmark??",
                false,
                "http://cache-graphicslib.viator.com/graphicslib/thumbs674x446/3904/SITours/stockholm-grand-tour-by-coach-and-boat-in-stockholm-142840.jpg"
        ));

        /* Question 8 */
        questions.add(new QuestionObject(
                "Helsinki is the capital of Finland??",
                true,
                "https://s-media-cache-ak0.pinimg.com/originals/00/c9/0d/00c90d43127c8617f689e72f73f0e976.jpg"
        ));

        /* Question 9 */
        questions.add(new QuestionObject(
                "Madrid is the capital of Portugal??",
                false,
                "http://anastasia.llobe.com/wp-content/uploads/2014/10/madrid4.jpg"
        ));

        /* Question 10 */
        questions.add(new QuestionObject(
                "Zagreb is the capital of Croatia??",
                true,
                "http://www.letstravelradio.com/thisweek/2008/05-08/croatia_main.jpg"
        ));

        /* Question 11 */
        questions.add(new QuestionObject(
                "Tokyo is the capital of China??",
                false,
                "http://www.telegraph.co.uk/incoming/article115762.ece/ALTERNATES/w460/tokyo.jpg"
        ));
    }

    /* Setup questions array */
    private void setupQuestions(){

        // Randomise order of questions
        Collections.shuffle(questions);

        // Set number of questions
        numQuestions = questions.size();

        // Ensure Maximum of 10 questions
        if(numQuestions > MAX_QUESTIONS){
            numQuestions = MAX_QUESTIONS;
        }

        // Do next question
        nextQuestion();
    }

<<<<<<< HEAD
    private void nextQuestion()
    {
        if (currentQuestion < numQuestions)
        {
            lblQuestion.setText(questions.get(currentQuestion).getQuestion());
            lblQuestionNumber.setText("Question " + (currentQuestion + 1));

            String imageURL = IMAGE_ROOT_URL + questions.get(currentQuestion).getImageURL();

=======
    // Initiate next question
    private void nextQuestion(){

        if(currentQuestion <  numQuestions) {
            // Set question text
            lblQuestion.setText(questions.get(currentQuestion).getQuestion());

            // Set Question number
            lblQuestionNumber.setText("Question " + (currentQuestion + 1));

            // Set Image
            String imageURL = IMAGE_ROOT_URL + questions.get(currentQuestion).getImageURL();

            // Load Image
>>>>>>> origin/master
            Picasso.with(this)
                    .load(imageURL)
                    .placeholder(R.drawable.question)
                    .error(R.drawable.question)
                    .into(imgPicture);

<<<<<<< HEAD
        }
        else

        // We've run out of questions, and so that means the game has finished.
        // Let's calculate the score.
        {
=======
        } else{
            // Quiz has finished
>>>>>>> origin/master
            endGame();
        }

    }

<<<<<<< HEAD
    private void checkAnswer(boolean answer)
    {
        if (answer == questions.get(currentQuestion).getAnswer())
        {
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

            // We want to positively reinforce getting a question right!
            // Let's also update the score
            correctSound.start();
            score++;
            lblScore.setText("Score:" + Integer.toString(score));
        }
        else
        {
            // Oops! They got it wrong. They don't get any extra points. But they do get a bad sound.
            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
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
=======
    /* Check answer and adjust score accordingly */
    private void checkAnswer(boolean answer){

        // Check Answer
        if(answer == questions.get(currentQuestion).getAnswer()){
            // Answer is correct
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();

            // Play Sound
            correctSound.start();

            // Update score
            score++;
            lblScore.setText("Score:" + Integer.toString(score));
        }
        else{
            // Answer is wrong
            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
            wrongSound.start();

        }

        // Do next question
        currentQuestion++;
        nextQuestion();

    }

    /* Quiz has finished */
    private void endGame(){

        // Build dialogue
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations!");
        builder.setMessage("You scored " + score + " points! Please enter your name:");

        // See http://stackoverflow.com/questions/10903754/input-text-dialog-android
        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

        // Set up the buttons
        // Ok button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playerName = input.getText().toString();

                // Create new highscore
                // Is this line screwing up the saving????????
                //HighScoreObject highScore = new HighScoreObject(score,  playerName, new Date().getTime());
>>>>>>> origin/master
                HighScoreObject highScore = new HighScoreObject();
                highScore.score = score;
                highScore.name = playerName;
                highScore.timestamp = new Date().getTime();

<<<<<<< HEAD
                List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());
                highScores.add(highScore);

                Collections.sort(highScores, new Comparator<HighScoreObject>()
                {
                    @Override
                    public int compare(HighScoreObject lhs, HighScoreObject rhs) {
                        if (lhs.score > rhs.score)
                        {
                            return 1;
                        }
                        else if (lhs.score < rhs.score)
                        {
                            return -1;
                        }
                        else if (lhs.timestamp > rhs.timestamp)
                        {
                            return 1;
                        }
                        else if (lhs.timestamp < rhs.timestamp)
                        {
                            return -1;
                        }
                        else
                        {
=======
                // Load highscores using paper
                List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                // Add item
                highScores.add(highScore);

                // Sort highscores by score / timestamp
                /* http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property*/
                Collections.sort(highScores, new Comparator<HighScoreObject>() {
                    @Override
                    public int compare(HighScoreObject lhs, HighScoreObject rhs) {
                        // First compare scores
                        if(lhs.score > rhs.score){
                            return 1;
                        }
                        else if(lhs.score < rhs.score){
                            return -1;
                        }
                        // Scores must be equal if we get here, lets compare timestamp
                        else if(lhs.timestamp > rhs.timestamp){
                            return 1;
                        }
                        else if(lhs.timestamp < rhs.timestamp){
                            return -1;
                        }
                        else{
                            // Scores and timestamp eaqual
>>>>>>> origin/master
                            return 0;
                        }
                    }
                });

<<<<<<< HEAD
                    Collections.reverse(highScores);
                    Paper.book().write("highscores", highScores);
                    Log.d("MHISSDEBUG", "Saving high scores!");
                    finish();

            }
        });

=======
                // Reverse highscores
                Collections.reverse(highScores);

                // Write using paper
                Paper.book().write("highscores", highScores);
                Log.d("MHISSDEBUG", "Saving high scores!");

                finish();
            }
        });

        // Cancel button
>>>>>>> origin/master
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });

        builder.show();

    }

<<<<<<< HEAD
=======

>>>>>>> origin/master
}
