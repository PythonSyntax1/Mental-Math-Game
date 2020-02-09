package com.example.mentalmathquiz;


import org.junit.Before;
import org.junit.Test;

import model.MCQuestion;
import model.MCQuiz;
import model.MCStrategy;
import model.Question;
import model.QuestionInterface;
import model.QuestionStrategy;
import model.Quiz;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class QuizTest {

    private MCQuiz testMCQuiz;
    private MCQuiz testOEQuiz;

    @Before
    public void createQuizTypes() {
        testMCQuiz = new MCQuiz(7, new MCStrategy());
        testOEQuiz = new MCQuiz(8, new QuestionStrategy());
    }

    @Test
    public void mcQuizCount() {
        int i = 0;
        for (QuestionInterface mcQuestion: testMCQuiz) {
            i += 1;
        }
        assertEquals(7, i);
    }

    @Test
    public void oeQuizCount() {
        int i = 0;
        for (QuestionInterface mcQuestion: testOEQuiz) {
            i += 1;
        }
        assertEquals(8, i);
    }

    @Test
    public void mcQuizType() {
        for (QuestionInterface mcQuestion: testMCQuiz) {
            if (mcQuestion == null) {
                fail();
            }
        }
    }

    @Test
    public void oeQuizType() {
        for (QuestionInterface oeQuestion: testOEQuiz) {
            if (oeQuestion == null) {
                fail();
            }
        }
    }




}
