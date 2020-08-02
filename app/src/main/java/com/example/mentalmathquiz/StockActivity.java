package com.example.mentalmathquiz;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import model.MCQuiz;
import model.MCStrategy;
import model.QuestionInterface;

import static com.example.mentalmathquiz.MainMenu.user;

public class StockActivity extends AppCompatActivity {

    private Integer questionNumber;
    private Iterator<QuestionInterface> currentIterator;
    private ArrayList<String> userAnswerStack = new ArrayList<>();
    private QuestionInterface currentQuestion;

    private TextView stockViewOneTop;
    private TextView stockViewOneBottom;
    private TextView stockViewTwoTop;
    private TextView stockViewTwoBottom;
    private TextView stockViewThreeTop;
    private TextView stockViewThreeBottom;
    private TextView stockViewFourTop;
    private TextView stockViewFourBottom;

    private Button stockButtonOne;
    private Button stockButtonTwo;
    private Button stockButtonThree;
    private Button stockButtonFour;

    private Chronometer timer;

    private Integer progressStatus = 0;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockquiz_question);
        Intent intent = getIntent();
        String quizlength = intent.getStringExtra(NumberInputActivity.quizlength);
        questionNumber = Integer.parseInt(quizlength);

        MCQuiz currentQuiz = new MCQuiz(questionNumber, new MCStrategy());
        currentIterator = currentQuiz.iterator();


        stockViewOneTop = findViewById(R.id.textView2);
        stockViewOneBottom = findViewById(R.id.textView6);
        stockViewTwoTop = findViewById(R.id.textView3);
        stockViewTwoBottom = findViewById(R.id.textView7);
        stockViewThreeTop = findViewById(R.id.textView4);
        stockViewThreeBottom = findViewById(R.id.textView8);
        stockViewFourTop = findViewById(R.id.textView5);
        stockViewFourBottom = findViewById(R.id.textView9);

        stockButtonOne = findViewById(R.id.mcButtonOne);
        stockButtonTwo = findViewById(R.id.mcButtonTwo);
        stockButtonThree = findViewById(R.id.mcButtonThree);
        stockButtonFour = findViewById(R.id.mcButtonFour);
        setNextQuestion();


        //Starts the timer and sets Lives Left Indicator
        timer = findViewById(R.id.Timer);
        timer.start();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(questionNumber);

    }

    public void handleAnswer() {
        if (userAnswerStack.size() == 4) {
            if (userAnswerStack.equals(currentQuestion.getQuestionAnswer())) {
                if (currentIterator.hasNext()) {
                    progressStatus += 1;
                    progressBar.setProgress(progressStatus);
                    setNextQuestion();
                } else {
                    timer.stop();
                    String time = timer.getText().toString();
                    user.insertIntoMC(time, questionNumber);
                    finish();
                }
            }
        }
    }

    public void setNextQuestion() {
        currentQuestion = currentIterator.next();
        ArrayList<String> questionStrings = currentQuestion.getQuestionString();

        stockViewOneTop.setText(questionStrings.get(0));
        stockViewOneBottom.setText(questionStrings.get(1) + " " + questionStrings.get(2));
        setTickerColour(Double.parseDouble(questionStrings.get(2)), stockViewOneBottom);

        stockViewTwoTop.setText(questionStrings.get(3));
        stockViewTwoBottom.setText(questionStrings.get(4) + " " + questionStrings.get(5));
        setTickerColour(Double.parseDouble(questionStrings.get(5)), stockViewTwoBottom);

        stockViewThreeTop.setText(questionStrings.get(7));
        stockViewThreeBottom.setText(questionStrings.get(8) + " " + questionStrings.get(9));
        setTickerColour(Double.parseDouble(questionStrings.get(9)), stockViewThreeBottom);

        stockViewFourTop.setText(questionStrings.get(10));
        stockViewFourBottom.setText(questionStrings.get(11) + " " + questionStrings.get(12));
        setTickerColour(Double.parseDouble(questionStrings.get(12)), stockViewFourBottom);



    }

    private void setTickerColour(double change, TextView textView) {
        if (change > 0) {
            textView.setColo
        }

    }

}
