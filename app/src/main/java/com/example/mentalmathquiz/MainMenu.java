package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import model.User;


public class MainMenu extends AppCompatActivity {

    public static final String quizType = "";
    public static User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }




    public void openNumberInPutOE(View view) {
        Intent intent = new Intent(this, NumberInputActivity.class);
        intent.putExtra(quizType, "OE");
        startActivity(intent);
    }



    public void openNumberInPutMC(View view) {
        Intent intent = new Intent(this, NumberInputActivity.class);
        intent.putExtra(quizType, "MC");
        startActivity(intent);
    }

    public void openNumberInPutResult(View view) {
        Intent intent = new Intent(this, NumberInputActivity.class);
        intent.putExtra(quizType, "Result");
        startActivity(intent);
    }

    public void openGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
