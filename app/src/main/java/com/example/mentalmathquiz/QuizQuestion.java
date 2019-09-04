package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;

import model.Question;
import model.Quiz;
import model.User;

import static com.example.mentalmathquiz.MainMenu.user;

public class QuizQuestion extends AppCompatActivity {

    private Integer currentQuestionAnswer;
    private Iterator<Question> currentIterator;

    private Integer questionNumber;


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
        textView.setText(currentQuestion.getQuestionString());
        currentQuestionAnswer = currentQuestion.getQuestionAnswer();

        //Starts the timer
        timer = findViewById(R.id.Timer);
        timer.start();


    }

    public void tryAnswer(View view) {

        EditText editText = findViewById(R.id.editText2);

        try {
            int inputedAnswer = Integer.parseInt(editText.getText().toString());

            //If the inputted answer is correct
            if (currentQuestionAnswer == inputedAnswer) {

                if (currentIterator.hasNext()) {
                    Question currentQuestion = currentIterator.next();
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(currentQuestion.getQuestionString());
                    currentQuestionAnswer = currentQuestion.getQuestionAnswer();

                    // If there are no questions left the quiz ends, the timer will stop.
                } else {
                    timer.stop();
                    String time = timer.getText().toString();
                    user.insertIntoOE(time, questionNumber);

                    Intent intent = new Intent(this, MainMenu.class);

                    startActivity(intent);
                }
            }
        } catch (Exception e) {
            //Do Nothing
        }
    }
}
