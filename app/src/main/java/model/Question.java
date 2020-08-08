package model;

import java.util.ArrayList;
import java.util.Random;

public class Question implements QuestionInterface{

    protected ArrayList<String> questionStrings;
    protected String questionAnswer;
    protected Integer questionType;
    protected Integer numOne;
    protected Integer numTwo;
    private QuestionStringGenerator questionStringGenerator;


    public Question() {
        Random rand1 = new Random();
        Random rand2 = new Random();
        Random rand3 = new Random();

        numOne = rand1.nextInt(30);
        numTwo = rand2.nextInt(30);

        //Specifies which operation the question should be
        //Operation is one of : *, + , -, /
        questionType = rand3.nextInt(4);
        createQuestionStringAndAnswer();
    }

    //Creates the question string, by appending the two randomly generated number
    //Sets the question answer by taking the two numbers and applying the correct operation
    public void createQuestionStringAndAnswer() {
        questionStrings = new ArrayList<>();
        questionStringGenerator = new QuestionStringGenerator(questionType, numOne, numTwo);
        questionAnswer = questionStringGenerator.returnAnswerandString().get(0);
        String questionStringOne = questionStringGenerator.returnAnswerandString().get(1);
        questionStrings.add(questionStringOne);
    }

    public ArrayList<String> getQuestionString() {
        return questionStrings;
    }

    public ArrayList<String> getQuestionAnswer() {
        ArrayList<String> retList = new ArrayList<>();
        retList.add(questionAnswer);
        return retList;
    }

    public Integer getQuestionType() {return questionType;}
}
