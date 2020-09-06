package model;

import java.util.ArrayList;

public class QuestionStrategy implements QuizTypeStrategy {



    public QuestionStrategy() {
    }

    @Override
    public ArrayList<QuestionInterface> createQuiz(int quizLength) {
        ArrayList<QuestionInterface> retList = new ArrayList<>();
        for (int j = 0; j < quizLength; j++) {
            QuestionInterface newQuestion = new Question();
            retList.add(newQuestion);
        }
        return retList;
    }

}
