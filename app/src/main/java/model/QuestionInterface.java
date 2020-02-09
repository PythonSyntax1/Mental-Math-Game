package model;

import java.util.ArrayList;


public interface QuestionInterface {

    void createQuestionStringAndAnswer();

    String getQuestionString();

    ArrayList<String> getQuestionAnswer();

    Integer getQuestionType();

}
