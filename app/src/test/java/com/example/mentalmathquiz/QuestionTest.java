package com.example.mentalmathquiz;

import androidx.core.widget.TextViewCompat;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import model.GameQuestion;
import model.MCQuestion;
import model.Question;
import model.StockTicker;

import static org.junit.Assert.*;


public class QuestionTest {

    private ArrayList<MCQuestion> mcQuestionTestList ;
    private ArrayList<Question> testQuestionList;

    @Before
    public void createNewQuestion() {
        //Because the questions are created with random numbers, 10 questions are tested at a time
        //so that there is a smaller chance where there is an uncaught case where the test fails.
        mcQuestionTestList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            MCQuestion singleMCquestion = new MCQuestion();
            mcQuestionTestList.add(singleMCquestion);
        }
        testQuestionList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            MCQuestion singleMCquestion = new MCQuestion();
            mcQuestionTestList.add(singleMCquestion);
        }
    }

    @Test
    public void gameQuestionTests() {
        ArrayList<GameQuestion> testArray = new ArrayList();
        Integer i = 0;
        while (i < 3) {
            GameQuestion tempTestQuestion = new GameQuestion();
            testArray.add(tempTestQuestion);
            i ++;
        }
        for (GameQuestion q: testArray) {
            Integer qAnswer = Integer.valueOf(q.getQuestionAnswer().get(4));
            qAnswer = Integer.valueOf(q.getQuestionAnswer().get(qAnswer));
            if (q.getRightAnswer() == true) {
                assert(q.getCurrentAnswer() == qAnswer);
            } else {
                assert(q.getCurrentAnswer() != qAnswer);
            }
        }
    }

    @Test
    public void codeTest() {
        StockTicker hello = new StockTicker();
        int hi = 1;
    }

    @Test
    public void mcQuestionPositive() {
        for (MCQuestion testMCQuestion : mcQuestionTestList) {
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(4)) < 0) {
                fail();
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(3))< 0) {
                fail();
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(2)) < 0) {
                fail();
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(1)) < 0) {
                fail();
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(0)) < 0) {
                fail();
            }
        }
    }


    @Test
    public void mcQuestionOneCorrect() {
        for (MCQuestion testMCQuestion : mcQuestionTestList) {
            Integer qAnswer = Integer.valueOf(testMCQuestion.getQuestionAnswer().get(4));
            qAnswer = Integer.valueOf(testMCQuestion.getQuestionAnswer().get(qAnswer));
            Integer counter = 0;
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(3)).equals(qAnswer)) {
                counter += 1;
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(2)).equals(qAnswer)) {
                counter += 1;
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(1)).equals(qAnswer)) {
                counter += 1;
            }
            if (Integer.valueOf(testMCQuestion.getQuestionAnswer().get(0)).equals(qAnswer)) {
                counter += 1;
            }
            if (counter != 1) {
                fail();
            }
        }
    }

    @Test
    public void typeCorrect() {
        for (Question testQuestion: testQuestionList) {
            if (testQuestion.getQuestionType() == 0) {
                if (!testQuestion.getQuestionString().contains("+")) {
                    fail();
                }
            }
            else if (testQuestion.getQuestionType() == 1) {
                if (!testQuestion.getQuestionString().contains("-")) {
                    fail();
                }
            }
            else if (testQuestion.getQuestionType() == 2) {
                if (!testQuestion.getQuestionString().contains("*")) {
                    fail();
                }
            }
            else if (testQuestion.getQuestionType() == 3) {
                if (!testQuestion.getQuestionString().contains("/")) {
                    fail();
                }
            }
            else {
                fail("Invalid question type");
            }
        }
    }

}