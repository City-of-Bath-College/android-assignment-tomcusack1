package questionapp.frank.fca.com.questionapp;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //variables go here
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private TextView lblScore;

    private List<QuestionObject> questions;

    private QuestionObject currentQuestion;
    private int index;

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //connect variables to interface items
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        lblScore = (TextView)findViewById(R.id.lblScore);


// set questionnaire text
        lblQuestion.setText("Is this Spain's official flag?");

// set image picture
        imgPicture.setImageResource(R.drawable.spainflag);

//on click listeners
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

//onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        index = 0;
        score = 0;
        generateQuestions();

        setupQuestion();

    }

    private void generateQuestions(){

        questions = new ArrayList<>();


        questions.add(new QuestionObject("Is this Spain's official flag?", true, R.drawable.spainflag));
        questions.add(new QuestionObject("Is this monument in Rome?", false, R.drawable.paris));
        questions.add(new QuestionObject("Is this Canada's flag?", false, R.drawable.usa));
        questions.add(new QuestionObject("Is this England's flag?", true, R.drawable.england));
        questions.add(new QuestionObject("His name is Luke Skywalker", false, R.drawable.vader));
        questions.add(new QuestionObject("This is a giraffe", true, R.drawable.giraffe));
        questions.add(new QuestionObject("1 pound equals 1.20 Euros", false, R.drawable.pound));
        questions.add(new QuestionObject("This is Australia", false, R.drawable.africa));
        questions.add(new QuestionObject("His name is Denzel Washington", false, R.drawable.cage));
        questions.add(new QuestionObject("Is this Jamaica's flag?", true, R.drawable.jamaica));

    }

    private void setupQuestion(){
        if (index == questions.size()) {
            // They've used all their questions - time to end the game!
            Log.d("TOM_APP", "Ended all the questions.");
        } else {
            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());

            index++;
        }
    }

    private void determineButtonPress(boolean answer){

        boolean expectedAnwer = currentQuestion.isAnswer();

        if (answer == expectedAnwer){
            // You were right!
            score++;
            Toast.makeText(MainActivity.this, "Well done!", Toast.LENGTH_SHORT).show();
        } else {
            // You were wrong!

            Toast.makeText(MainActivity.this, "Wrong answer!", Toast.LENGTH_SHORT).show();

        }


        lblScore.setText("Score = " + score);
        setupQuestion();

    }

    public void endGame() {
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations!")
                .setMessage("You scored" + score + " points this round!")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        alertDialog.show();
    }
}
