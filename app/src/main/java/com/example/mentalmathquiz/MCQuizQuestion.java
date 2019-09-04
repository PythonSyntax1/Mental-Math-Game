package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;



import java.util.Iterator;

import model.MCQuestion;
import model.MCQuiz;

import static com.example.mentalmathquiz.MainMenu.user;


public class MCQuizQuestion extends AppCompatActivity {

    private Integer livesLeft = 3;
    private Integer currentCorrectAnswerPosition;
    private Iterator<MCQuestion> currentIterator;

    private TextView livesLeftView;
    private TextView mcQuestionView;

    private Button mcButtonOne;
    private Button mcButtonTwo;
    private Button mcButtonThree;
    private Button mcButtonFour;

    private Integer questionNumber;

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
        MCQuiz currentQuiz = new MCQuiz(questionNumber);
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
                setNextQuestion();
                return true;

                // If there are no questions left the quiz ends, the timer will stop.
            } else {
                timer.stop();
                String time = timer.getText().toString();
                Intent intent = new Intent(this, MainMenu.class);
                user.insertIntoMC(time, questionNumber);

                startActivity(intent);
                return true;
            }

        // If the answer is incorrect and there are no lives left after deduction, end game
        } else if (livesLeft == 1) {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
            return false;


        // If the answer is incorrect, deduct a life.
        } else {
            livesLeft -= 1;
            livesLeftView.setText("Lives Left" + livesLeft);
            return false;
        }
    }

    public void setNextQuestion() {
        MCQuestion currentQuestion = currentIterator.next();
        mcQuestionView.setText(currentQuestion.getQuestionString());
        currentCorrectAnswerPosition = currentQuestion.getPosition();
        mcButtonOne.setText(String.valueOf(currentQuestion.getOptionOne()));
        mcButtonTwo.setText(String.valueOf(currentQuestion.getOptionTwo()));
        mcButtonThree.setText(String.valueOf(currentQuestion.getOptionThree()));
        mcButtonFour.setText(String.valueOf(currentQuestion.getOptionFour()));

    }

    @Override
    public void onBackPressed() {
        //Disable
    }




}
