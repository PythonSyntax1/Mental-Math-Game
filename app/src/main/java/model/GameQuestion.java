package model;

import java.util.Random;

public class GameQuestion extends Question {

    private boolean rightAnswer;

    //The answer that appears in the question string. It can be either wrong or right.
    private Integer currentAnswer;

    private Integer questionAnswerInt;

    //Chooses either 0 or 1 randomly. This determine whether the answer will be wrong or right.
    public GameQuestion() {
        super();
        Random rndRightAnswer = new Random();
        Integer randomInteger = rndRightAnswer.nextInt(2);

        questionAnswerInt = Integer.valueOf(questionAnswer);
        if (randomInteger == 0) {
            currentAnswer = questionAnswerInt;
            rightAnswer = true;
        } else {
            createWrongAnswer();
            rightAnswer = false;
        }


    }

    //If answer is wrong, it generates an incorrect answer.
    public void createWrongAnswer() {
        Random randomPosition = new Random();
        Integer position = randomPosition.nextInt(2);
        if (position == 0) {
            currentAnswer = questionAnswerInt - 1;
        } if (position == 1) {
            currentAnswer = questionAnswerInt + 1;
        } else {
            currentAnswer = questionAnswerInt + 2;
        }

    }

    //Returns whether or not the current question and answer is wrong or right
    public boolean getRightAnswer() {
        return rightAnswer;
    }

    //Returns the current answer in the question string, which can either be wrong or right
    public Integer getCurrentAnswer() {
        return currentAnswer;
    }

    @Override
    //Returns a string for the question, which includes the question and the current answer
    public String getQuestionString() {
        return super.getQuestionString().substring(0, super.getQuestionString().length() -1);
    }


}
