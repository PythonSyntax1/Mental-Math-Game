package model;

import java.util.ArrayList;

public class StockStrategy implements QuizTypeStrategy {

    @Override
    public ArrayList<QuestionInterface> createQuiz(int quizLength) {
        ArrayList<QuestionInterface> retList = new ArrayList<>();
        for (int j = 0; j < quizLength; j++) {
            QuestionInterface newMCQuestion = new StockQuestion();
            retList.add(newMCQuestion);
        }
        return retList;
    }
}
