package model;

import java.util.ArrayList;


public interface QuestionInterface {

    void createQuestionStringAndAnswer();

    ArrayList<String> getQuestionString();

    ArrayList<String> getQuestionAnswer();

    Integer getQuestionType();

}
