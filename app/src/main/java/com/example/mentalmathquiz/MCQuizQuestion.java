package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.Iterator;

import model.MCQuestion;
import model.MCQuiz;
import model.MCStrategy;
import model.QuestionInterface;

import static com.example.mentalmathquiz.MainMenu.user;


public class MCQuizQuestion extends AppCompatActivity {

    private Integer livesLeft = 3;
    private Integer currentCorrectAnswerPosition;
    private Iterator<QuestionInterface> currentIterator;

    private TextView livesLeftView;
    private TextView mcQuestionView;

    private Button mcButtonOne;
    private Button mcButtonTwo;
    private Button mcButtonThree;
    private Button mcButtonFour;

    private Integer questionNumber;

    private Integer progressStatus = 0;
    private ProgressBar progressBar;


    public static final String result = "";

    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcquiz_question);

        Intent intent = getIntent();
        String quizlength = intent.getStringExtra(NumberInputActivity.quizlength);
        questionNumber = Integer.parseInt(quizlength);

        // Creates a new Quiz of the inputted number's length
        MCQuiz currentQuiz = new MCQuiz(questionNumber, new MCStrategy());
        currentIterator = currentQuiz.iterator();

        // Sets the current question to be the first question of the Quiz, and sets Buttons
        mcQuestionView = findViewById(R.id.MCQuestionView);
        mcButtonOne = findViewById(R.id.mcButtonOne);
        mcButtonTwo = findViewById(R.id.mcButtonTwo);
        mcButtonThree = findViewById(R.id.mcButtonThree);
        mcButtonFour = findViewById(R.id.mcButtonFour);
        setNextQuestion();


        //Starts the timer and sets Lives Left Indicator
        timer = findViewById(R.id.Timer);
        timer.start();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(questionNumber);


        livesLeftView = findViewById(R.id.LivesView);
        livesLeftView.setText("Lives Left: " + livesLeft);

    }




    public void buttonOneClicked(View view) {
        checkCorrectAnswer(0);
    }

    public void buttonTwoClicked(View view) {
        checkCorrectAnswer(1);
    }

    public void buttonThreeClicked(View view)  {
        checkCorrectAnswer(2);
    }

    public void buttonFourClicked(View view) {
        checkCorrectAnswer(3);
    }


    public Boolean checkCorrectAnswer(int chosenPosition) {
        if (chosenPosition == currentCorrectAnswerPosition) {

            if (currentIterator.hasNext()) {
                progressStatus += 1;
                progressBar.setProgress(progressStatus);
                setNextQuestion();
                return true;

                // If there are no questions left the quiz ends, the timer will stop.
            } else {
                timer.stop();
                String time = timer.getText().toString();
                user.insertIntoMC(time, questionNumber);
                finish();
                return true;
            }

        // If the answer is incorrect and there are no lives left after deduction, end game
        } else if (livesLeft == 1) {
            finish();
            return false;


        // If the answer is incorrect, deduct a life.
        } else {
            livesLeft -= 1;
            livesLeftView.setText("Lives Left: " + livesLeft);
            return false;
        }
    }

    public void setNextQuestion() {
        QuestionInterface currentQuestion = currentIterator.next();
        mcQuestionView.setText(currentQuestion.getQuestionString().get(0));
        currentCorrectAnswerPosition = Integer.valueOf(currentQuestion.getQuestionAnswer().get(4));
        mcButtonOne.setText(currentQuestion.getQuestionAnswer().get(0));
        mcButtonTwo.setText(currentQuestion.getQuestionAnswer().get(1));
        mcButtonThree.setText(currentQuestion.getQuestionAnswer().get(2));
        mcButtonFour.setText(currentQuestion.getQuestionAnswer().get(3));

    }

    @Override
    public void onBackPressed() {
        //Disable
    }



}
