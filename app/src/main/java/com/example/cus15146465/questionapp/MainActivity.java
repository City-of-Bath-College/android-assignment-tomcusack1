package com.example.cus15146465.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // Start variable declarations
    // Start variable declarations

    public Button btnFalse;
    public Button btnTrue;
    public TextView lblQuestion;
    public ImageView imgPicture;
    public int index;

    public List<QuestionObject> question;

    // End variable declarations


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        public void generateQuestions(){
            new questions = new ArrayList<>();

            QuestionObject one = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(one);
            QuestionObject two = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(two);
            QuestionObject three = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(three);
            QuestionObject four = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(four);
            QuestionObject five = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(five);
            QuestionObject six = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(six);
            QuestionObject seven = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(seven);
            QuestionObject eight = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(eight);
            QuestionObject nine = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(nine);
            QuestionObject ten = new QuestionObject("Is the capital of England London?", true, R.drawable.england);
            questions.add(ten);
        }

        public void setUpQuestion(){
            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());

            index++;
        }

        public void determineButtonPress(boolean answer){
            boolean expectedAnswer = currentQuestion.isAnswer();

            if (answer == expectedAnswer)
            {
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "False!", Toast.LENGTH_SHORT).show();
            }
            setUpQuestion();
        }

        //onclick listeners
        btnTrue.setOnClickListener(new View.onClickListener() {

            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        btnFalse.setOnClickListener(new View.onClickListener() {

            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        generateQuestion();

        setUpQuestion();

    }



}
