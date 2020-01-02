package com.example.mentalmathquiz;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import model.User;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class UserTests {

    private User testUser;

    @Before
    public void createTestUser() {
        testUser = new User();
        testUser.insertIntoMC("00:03", 5);
        testUser.insertIntoMC("00:06", 5);
        testUser.insertIntoMC("00:07", 5);
    }

    @Test
    public void testMCListAtRightIndex() {
        assertTrue(testUser.mcContains(5));
    }

    @Test
    public void testMCListNotAtRightIndex() {
        assertTrue(!testUser.mcContains(4));
    }

    @Test
    public void testHighScorePositions() {
        if (!testUser.getMCList(5).get(1).equals(LocalTime.parse("00:06"))) {
            fail();
        }
        if (!testUser.getMCList(5).get(0).equals(LocalTime.parse("00:03"))) {
            fail();
        }
        if (!testUser.getMCList(5).get(2).equals(LocalTime.parse("00:07"))) {
            fail();
        }
    }

    @Test
    public void testHighScoreLimit() {
        for (int i = 0; i <= 16; i++) {
            testUser.insertIntoMC("00:08", 5);
        }
        assertTrue(testUser.getMCList(5).size() == 15);
    }



}
