package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Iterator;

import model.Question;
import model.Quiz;


import static com.example.mentalmathquiz.MainMenu.user;

public class QuizQuestion extends AppCompatActivity {

    private String currentQuestionAnswer;
    private Iterator<Question> currentIterator;

    private Integer questionNumber;

    private Integer progressStatus = 0;
    private ProgressBar progressBar;



    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        Intent intent = getIntent();
        String quizlength = intent.getStringExtra(NumberInputActivity.quizlength);
        questionNumber = Integer.parseInt(quizlength);

        // Creates a new Quiz of the inputted number's length
        Quiz currentQuiz = new Quiz(questionNumber);
        currentQuiz.addQuestions();
        currentIterator = currentQuiz.iterator();

        // Sets the current question to be the first question of the Quiz
        Question currentQuestion = currentIterator.next();
        TextView textView = findViewById(R.id.textView);
        textView.setText(currentQuestion.getQuestionString().get(0));
        currentQuestionAnswer = currentQuestion.getQuestionAnswer().get(0);

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setMax(questionNumber);

        //Starts the timer
        timer = findViewById(R.id.Timer);
        timer.start();


    }

    public void tryAnswer(View view) {

        EditText editText = findViewById(R.id.editText2);

        try {
            int inputedAnswer = Integer.parseInt(editText.getText().toString());

            //If the inputted answer is correct
            if (Integer.valueOf(currentQuestionAnswer) == inputedAnswer) {

                if (currentIterator.hasNext()) {
                    progressStatus += 1;
                    progressBar.setProgress(progressStatus);
                    Question currentQuestion = currentIterator.next();
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(currentQuestion.getQuestionString().get(0));
                    currentQuestionAnswer = currentQuestion.getQuestionAnswer().get(0);

                    // If there are no questions left the quiz ends, the timer will stop.
                } else {
                    timer.stop();
                    String time = timer.getText().toString();
                    user.insertIntoOE(time, questionNumber);

                    finish();
                }
            }
        } catch (Exception e) {
            //Do Nothing
        }
    }

    @Override
    public void onBackPressed() {
        //Disable
    }


}
