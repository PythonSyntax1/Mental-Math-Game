package model;

import java.util.ArrayList;
import java.util.Random;

public class MCQuestion implements QuestionInterface{

    //The options that appear on the screen for each question
    private String questionString;
    private String questionAnswer;
    private Integer questionType;
    private Integer numOne;
    private Integer numTwo;
    private QuestionStringGenerator questionStringGenerator;
    //The position of the correct answer (0-3)
    private Integer position;

    private Integer optionOne;
    private Integer optionTwo;
    private Integer optionThree;
    private Integer optionFour;



    public MCQuestion() {
        Random rand1 = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();

        numOne = rand1.nextInt(30);
        numTwo = rand2.nextInt(30);

        //Specifies which operation the question should be
        //Operation is one of : *, + , -, /
        questionType = rand3.nextInt(4);
        createQuestionStringAndAnswer();
        // Generates how where the actual answer is relative to the other options.
        Random randomPosition = new Random();
        position = randomPosition.nextInt(3);
        if (Integer.valueOf(questionAnswer) <= 2) {
            position = 0;
        }
        generateOtherOptions();
    }

    public void createQuestionStringAndAnswer() {
        questionStringGenerator = new QuestionStringGenerator(questionType, numOne, numTwo);
        questionAnswer = questionStringGenerator.returnAnswerandString().get(0);
        questionString = questionStringGenerator.returnAnswerandString().get(1);
    }

    //Based on the randomly generated positions, generates the position and values of each
    //Incorrect answer
    private void generateOtherOptions() {
        Integer intquestionAnswer = Integer.valueOf(questionAnswer);
        if (position == 0) {
            optionOne = intquestionAnswer;
            optionTwo = intquestionAnswer + 1;
            optionThree = intquestionAnswer + 2;
            optionFour = intquestionAnswer + 3;
        }
        else if (position == 1) {
            optionOne = intquestionAnswer - 1;
            optionTwo = intquestionAnswer;
            optionThree = intquestionAnswer + 1;
            optionFour = intquestionAnswer + 2;
        }
        //Varies the incorrect answers to reduce predictability
        else if (position == 2 && intquestionAnswer < 25) {
            optionOne = intquestionAnswer - 2;
            optionTwo = intquestionAnswer - 1;
            optionThree = intquestionAnswer;
            optionFour = intquestionAnswer + 1;
        }
        else if (position == 2) {
            optionOne = intquestionAnswer - 10;
            optionTwo = intquestionAnswer - 20;
            optionThree = intquestionAnswer;
            optionFour = intquestionAnswer + 3;
        }
        //Varies the incorrect answers to reduce predictability
        else if (position == 3 && intquestionAnswer < 25) {
            optionOne = intquestionAnswer - 3;
            optionTwo = intquestionAnswer - 2;
            optionThree = intquestionAnswer - 1;
            optionFour = intquestionAnswer;

        } else if (position == 3) {
            optionOne = intquestionAnswer - 10;
            optionTwo = intquestionAnswer - 20;
            optionThree = intquestionAnswer;
            optionFour = intquestionAnswer + 3;
        }

    }

    public String getQuestionString() {
        return questionString;
    }

    public ArrayList<String> getQuestionAnswer() {
        ArrayList<String> retList = new ArrayList<>();
        retList.add(optionOne.toString());
        retList.add(optionTwo.toString());
        retList.add(optionThree.toString());
        retList.add(optionFour.toString());
        retList.add(position.toString());
        return retList;
    }

    public Integer getQuestionType() {return questionType;}


}
