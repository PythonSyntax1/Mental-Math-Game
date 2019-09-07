package model;

import java.util.Random;

public class Question {

    protected String questionString;
    protected Integer questionAnswer;
    private Integer questionType;
    private Integer numOne;
    private Integer numTwo;


    public Question() {
        Random rand1 = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();

        numOne = rand1.nextInt(30);
        numTwo = rand2.nextInt(30);

        questionType = rand3.nextInt(4);

        createQuestionStringAndAnswer();
    }

    public void createQuestionStringAndAnswer() {

        if (questionType == 0) {
            questionAnswer = numOne + numTwo;
            questionString = numOne.toString() + "+" + numTwo.toString() + "?";
        }
        else if (questionType == 1) {

            //Prevents Question with a negative answer
            if (numOne > numTwo) {
            } else {
                int temp = numTwo;
                numTwo = numOne;
                numOne = temp;
            }
            questionAnswer = numOne - numTwo;
            questionString = numOne.toString() + "-" + numTwo.toString() + "?";
        }

        else if (questionType == 2) {
            questionAnswer = numOne * numTwo;
            questionString = numOne.toString() + "*" + numTwo.toString() + "?";
        }
        else if (questionType == 3) {

            Integer numFour = (numTwo/questionType) + 1;
            numOne = numOne * numFour;

            questionAnswer = numOne / numFour;
            questionString = numOne.toString() + "/" + numFour.toString() + "?";
        }

    }

    public String getQuestionString() {
        return questionString;
    }

    public Integer getQuestionAnswer() {
        return questionAnswer;
    }
}
