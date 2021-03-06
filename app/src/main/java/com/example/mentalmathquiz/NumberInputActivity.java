package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class NumberInputActivity extends AppCompatActivity {

    public static final String quizlength = "";

    private String quiztype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        quiztype = intent.getStringExtra(MainMenu.quizType);
    }


    public void openQuiz(View view) {
        if (quiztype.equals("OE")) {
            openOEQuiz();
        } else if (quiztype.equals("Result")) {
            openResults();
        } else if (quiztype.equals("MC")){
            openMCQuiz();
        } else {
            openStockQuiz();
        }
    }


    public void openOEQuiz() {
        Intent intent = new Intent(this, QuizQuestion.class);
        prepareNewWindow(intent);
    }

    public void openMCQuiz() {
        Intent intent = new Intent(this, MCQuizQuestion.class);
        prepareNewWindow(intent);
    }

    public void openResults() {
        Intent intent = new Intent(this, ResultsActivity.class);
        prepareNewWindow(intent);
    }

    public void openStockQuiz() {
        Intent intent = new Intent(this, StockActivity.class);
        prepareNewWindow(intent);
    }

    public void prepareNewWindow(Intent intent) {
        EditText editText = findViewById(R.id.editText);
        String num = editText.getText().toString();
        try {
            Integer newnum = Integer.parseInt(num);
            intent.putExtra(quizlength, num);
            startActivity(intent);
            finish();
        } catch (NumberFormatException e) {
            System.out.println("Not number");
        }
    }
}

