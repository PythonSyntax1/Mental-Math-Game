 package com.example.mentalmathquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.GameQuestion;

import static com.example.mentalmathquiz.MainMenu.user;


 public class GameActivity extends AppCompatActivity {

     //Note :
     //Certain aspects of game physics and logic based off of Coding with Sara's simple Android game.

    private GameQuestion currentQuestion;
    private Integer livesleft;
    private Integer score;

    private TextView scoreLabel;
    private TextView livesLeftLabel;
    private TextView questionLabel;
    private ImageView cow;
    private ImageView thor;
    private ImageView checkMark;
    private ImageView wrongCross;

    private Integer cowY;
    private Integer maxCowY;
    private Integer checkY;
    private Integer checkX;
    private Integer crossX;
    private Integer crossY;
    private Integer thorY;
    private Integer thorX;

    private Integer screenWidth;
    private Integer screenHeight;
    private Integer frameHeight;


    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private boolean touching;
    private boolean started;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoreLabel = findViewById(R.id.scoreTextView);
        livesLeftLabel = findViewById(R.id.gamelivesLeftText);
        questionLabel = findViewById(R.id.gamequestionTextView);
        cow = findViewById(R.id.cow);
        thor = findViewById(R.id.thor);
        checkMark = findViewById(R.id.checkmark);
        wrongCross = findViewById(R.id.wrongcross);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;


        thor.setX(-80);
        thor.setY(-80);
        checkMark.setX(-80);
        checkMark.setY(-80);
        wrongCross.setX(-80);
        wrongCross.setY(-80);

        cowY = 400;
        livesleft = 6;
        started = false;
        livesLeftLabel.setText("Left Left: " + 5);

        score = 0;
        scoreLabel.setText("Score: " + score);
        currentQuestion = new GameQuestion();


    }

    public void setUpQuestion() {
        questionLabel.setText(currentQuestion.getQuestionString().get(0) + " = " + currentQuestion.getCurrentAnswer());
        resetCheck();
        resetCross();
    }

    public void changePos() {

        hitAction();

        //Cow Movement
        if (touching == true) {
            if (cowY < maxCowY - 15) {
                cowY += 15;
            } else {
                cowY = maxCowY;
            }
        } else {
            if (cowY < 20) {
                cowY = 0;
            }
            cowY -= 20;
        }
        cow.setY(cowY);

        //CheckMark Movement
        checkX -= 6;
        if (checkX < 0) {
            Boolean notMissed = checkMissedAnswer(true);
            if (notMissed) {
                resetCheck();
            }
        }

        //wrongCross Movement
        crossX -= 5;
        if (crossX < 0) {
            Boolean notMissed = checkMissedAnswer(false);
            if (notMissed) {
                resetCross();
            }
        }

        //Thor's Hammer
        thorX -= 25;
        if (thorX < 0) {
            resetThor();
        }
        thor.setX(thorX);
        thor.setY(thorY);
        checkMark.setX(checkX);
        checkMark.setY(checkY);
        wrongCross.setX(crossX);
        wrongCross.setY(crossY);
    }

    public void resetThor() {
        Random rand1 = new Random();
        Random rand2 = new Random();
        Integer randomHeight = rand1.nextInt(frameHeight - 50);
        Integer thorDistance = rand2.nextInt(500);
        thorX = screenWidth + thorDistance;
        thorY = randomHeight;
    }

    public void resetCross() {
        Random rand1 = new Random();
        Integer randomHeight = rand1.nextInt(frameHeight - 50);
        Random rand2 = new Random();
        Integer randomDistance = rand2.nextInt(50);
        crossX = screenWidth + randomDistance;
        crossY = randomHeight;
    }

    public void resetCheck() {
        Random rand1 = new Random();
        Integer randomHeight = rand1.nextInt(frameHeight - 50);
        Random rand2 = new Random();
        Integer randomDistance = rand2.nextInt(50);
        checkX = screenWidth + randomDistance;
        checkY = randomHeight;
    }


    public Boolean checkMissedAnswer(Boolean missed) {
        if (missed != currentQuestion.getRightAnswer()) {
            return true;
        } else {
            checkLivesLeft();
            livesleft -= 1;
            livesLeftLabel.setText("Left Left: " + livesleft);
            currentQuestion = new GameQuestion();
            setUpQuestion();
            return false;
        }
    }

    public void checkRightAnswer(Boolean correct) {
        if (correct == currentQuestion.getRightAnswer()) {
            score += 10;
            scoreLabel.setText("Score: " + score);
            currentQuestion = new GameQuestion();
            setUpQuestion();
        } else {
            checkLivesLeft();
            livesleft -= 1;
            livesLeftLabel.setText("Left Left: " + livesleft);
            currentQuestion = new GameQuestion();
            setUpQuestion();
        }
    }


    public void hitAction() {


        if (0 <= checkX && checkX <= cow.getWidth() && checkY >=
                (cowY - checkMark.getHeight()) && checkY <= cowY+checkMark.getHeight()) {
            checkRightAnswer(true);
        }

        if (0 <= crossX && crossX <= cow.getWidth() && crossY >=
                (cowY - wrongCross.getHeight()) && crossY <= cowY+wrongCross.getHeight()) {
            checkRightAnswer(false);
        }

        if (0 <= thorX && thorX <= cow.getWidth() && thorY >=
                (cowY - thor.getHeight()) && thorY <= cowY+thor.getHeight()) {
            checkLivesLeft();
            livesleft -= 1;
            resetThor();
            livesLeftLabel.setText("Left Left: " + livesleft);
        }
    }

    public void checkLivesLeft(){
        if (livesleft == 1) {
            user.insertIntoGameResults(score);
            finish();
        }
    }

    public boolean onTouchEvent(MotionEvent me) {
        //Game doesn't start until user touches screen
        if (started == false) {
            started = true;

            FrameLayout frame = findViewById(R.id.framelayout);
            frameHeight = frame.getHeight();
            maxCowY = frameHeight - cow.getHeight();

            setUpQuestion();
            checkX = -5;
            thorX = -5;
            crossX = -5;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                touching = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP){
                touching = false;
            }

        }
        return true;
    }


}
