package model;

import java.util.Random;

public class MCQuestion extends Question {

    //The options that appear on the screen for each question
    private Integer optionOne;
    private Integer optionTwo;
    private Integer optionThree;
    private Integer optionFour;

    //The position of the correct answer (0-3)
    private Integer position;


    public MCQuestion() {
        super();

        // Generates how where the actual answer is relative to the other options.
        Random randomPosition = new Random();
        position = randomPosition.nextInt(3);
        if (questionAnswer <= 2) {
            position = 0;
        }
        if (2 < questionAnswer && questionAnswer < 5) {
            position = randomPosition.nextInt(3);
        }
        generateOtherOptions();
    }


    //Based on the randomly generated positions, generates the position and values of each
    //Incorrect answer
    public void generateOtherOptions() {
        if (position == 0) {
            optionOne = questionAnswer;
            optionTwo = questionAnswer + 1;
            optionThree = questionAnswer + 2;
            optionFour = questionAnswer + 3;
        }
        else if (position == 1) {
            optionOne = questionAnswer - 1;
            optionTwo = questionAnswer;
            optionThree = questionAnswer + 1;
            optionFour = questionAnswer + 2;
        }
        //Varies the incorrect answers to reduce predictability
        else if (position == 2 && questionAnswer < 25) {
            optionOne = questionAnswer - 2;
            optionTwo = questionAnswer - 1;
            optionThree = questionAnswer;
            optionFour = questionAnswer + 1;
        }
        else if (position == 2) {
            optionOne = questionAnswer - 10;
            optionTwo = questionAnswer - 20;
            optionThree = questionAnswer;
            optionFour = questionAnswer + 3;
        }
        //Varies the incorrect answers to reduce predictability
        else if (position == 3 && questionAnswer < 25) {
            optionOne = questionAnswer - 3;
            optionTwo = questionAnswer - 2;
            optionThree = questionAnswer - 1;
            optionFour = questionAnswer;

        } else if (position == 3) {
            optionOne = questionAnswer - 10;
            optionTwo = questionAnswer - 20;
            optionThree = questionAnswer;
            optionFour = questionAnswer + 3;
        }

    }

    public Integer getPosition() {
        return position;
    }

    public Integer getOptionOne() {
        return optionOne;
    }

    public Integer getOptionTwo() {
        return optionTwo;
    }

    public Integer getOptionThree() {
        return optionThree;
    }

    public Integer getOptionFour() {
        return optionFour;
    }




}
