package model;

import java.util.ArrayList;

public class MCStrategy implements QuizTypeStrategy {


    public MCStrategy() {
    }

    @Override
    public ArrayList<QuestionInterface> createQuiz(int quizLength) {
        ArrayList<QuestionInterface> retList = new ArrayList<>();
        for (int j = 0; j < quizLength; j++) {
            MCQuestion newMCQuestion = new MCQuestion();
            retList.add(newMCQuestion);
        }
        return retList;
    }
}
