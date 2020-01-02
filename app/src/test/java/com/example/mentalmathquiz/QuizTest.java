package com.example.mentalmathquiz;


import org.junit.Before;
import org.junit.Test;

import model.MCQuestion;
import model.MCQuiz;
import model.Question;
import model.Quiz;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class QuizTest {

    private MCQuiz testMCQuiz;
    private Quiz testOEQuiz;

    @Before
    public void createQuizTypes() {
        testMCQuiz = new MCQuiz(7);
        testOEQuiz = new Quiz(8);
    }

    @Test
    public void mcQuizCount() {
        int i = 0;
        for (MCQuestion mcQuestion: testMCQuiz) {
            i += 1;
        }
        assertEquals(7, i);
    }

    @Test
    public void oeQuizCount() {
        int i = 0;
        for (Question mcQuestion: testOEQuiz) {
            i += 1;
        }
        assertEquals(8, i);
    }

    @Test
    public void mcQuizType() {
        for (MCQuestion mcQuestion: testMCQuiz) {
            if (mcQuestion == null) {
                fail();
            }
        }
    }

    @Test
    public void oeQuizType() {
        for (Question oeQuestion: testOEQuiz) {
            if (oeQuestion == null) {
                fail();
            }
        }
    }




}
