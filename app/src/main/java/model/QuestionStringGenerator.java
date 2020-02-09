package model;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionStringGenerator {

    private String stringQuestionAnswer;
    private String questionString;

    QuestionStringGenerator(int questionType, Integer numOne, Integer numTwo) {
        Integer questionAnswer = 0;
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
        stringQuestionAnswer = questionAnswer.toString();
    }

    public ArrayList<String> returnAnswerandString() {
        ArrayList<String> retList = new ArrayList<>();
        retList.add(stringQuestionAnswer);
        retList.add(questionString);
        return retList;
    }
}
