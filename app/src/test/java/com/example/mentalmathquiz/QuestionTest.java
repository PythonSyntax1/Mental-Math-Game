package com.example.mentalmathquiz;

import androidx.core.widget.TextViewCompat;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.GameQuestion;
import model.MCQuestion;
import model.Question;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class QuestionTest {

    private MCQuestion testMCQuestion;
    private Question testQuestion;

    @Before
    public void createNewQuestion() {
        testMCQuestion = new MCQuestion();
        testQuestion = new Question();
    }

    @Test
    public void mcQuestionPositive() {
        assertTrue(testMCQuestion.getQuestionAnswer() > 0);
        assertTrue(testMCQuestion.getOptionOne() > 0);
        assertTrue(testMCQuestion.getOptionTwo() > 0);
        assertTrue(testMCQuestion.getOptionThree() > 0);
        assertTrue(testMCQuestion.getOptionFour() > 0);
    }

    @Test
    public void mcQuestionOneCorrect() {
        Integer counter = 0;
        if (testMCQuestion.getOptionOne() == testMCQuestion.getQuestionAnswer()) {
            counter += 1;
        }
        if (testMCQuestion.getOptionTwo() == testMCQuestion.getQuestionAnswer()) {
            counter += 1;
        }
        if (testMCQuestion.getOptionThree() == testMCQuestion.getQuestionAnswer()) {
            counter += 1;
        }
        if (testMCQuestion.getOptionFour() == testMCQuestion.getQuestionAnswer()) {
            counter += 1;
        }
        assertTrue(1 == counter);
    }

    @Test
    public void typeCorrect() {
        if (testQuestion.getQuestionType() == 0) {
            assertTrue(testQuestion.getQuestionString().contains("+"));
        }
        else if (testQuestion.getQuestionType() == 1) {
            assertTrue(testQuestion.getQuestionString().contains("-"));
        }
        else if (testQuestion.getQuestionType() == 2) {
            assertTrue(testQuestion.getQuestionString().contains("*"));
        }
        else if (testQuestion.getQuestionType() == 3) {
            assertTrue(testQuestion.getQuestionString().contains("/"));
        }
        else {
            fail("Invalid question type");
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
            if (q.getRightAnswer() == true) {
                assert(q.getCurrentAnswer() == q.getQuestionAnswer());
            } else {
                assert(q.getCurrentAnswer() != q.getQuestionAnswer());
            }
        }
    }




}