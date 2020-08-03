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
import java.util.ArrayList;

import model.User;


public class MainMenu extends AppCompatActivity {

    public static final String quizType = "";
    public static User user;
    private Boolean updated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        user = new User();
        if (updated == null) {
            loadData();
        } else {
            saveData();
        }
        updated = true;

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user.getGameList());
        editor.putString("gamelist", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("gamelist", null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        ArrayList<Integer> temp = gson.fromJson(json, type);
        if (temp != null) {
            user.setGameResults(temp);
        }
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

    public void openStockQuiz(View view) {
        Intent intent = new Intent(this, NumberInputActivity.class);
        intent.putExtra(quizType, "Stock");
        startActivity(intent);
    }
}
