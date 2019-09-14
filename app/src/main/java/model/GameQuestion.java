package model;

import java.util.Random;

public class GameQuestion extends Question {

    private boolean rightAnswer;
    private Integer currentAnswer;

    public GameQuestion() {
        super();
        Random rndRightAnswer = new Random();
        Integer randomInteger = rndRightAnswer.nextInt(2);

        if (randomInteger == 0) {
            currentAnswer = questionAnswer;
            rightAnswer = true;
        } else {
            createWrongAnswer();
            rightAnswer = false;
        }


    }

    public void createWrongAnswer() {
        Random randomPosition = new Random();
        Integer position = randomPosition.nextInt(2);
        if (position == 0) {
            currentAnswer = questionAnswer - 1;
        } if (position == 1) {
            currentAnswer = questionAnswer + 1;
        } else {
            currentAnswer = questionAnswer + 2;
        }

    }

    public boolean getRightAnswer() {
        return rightAnswer;
    }

    public Integer getCurrentAnswer() {
        return currentAnswer;
    }

    @Override
    public String getQuestionString() {
        return super.getQuestionString().substring(0, super.getQuestionString().length() -1);
    }


}
