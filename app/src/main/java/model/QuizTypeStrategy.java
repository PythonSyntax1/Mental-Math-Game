package model;

import java.util.ArrayList;

public interface QuizTypeStrategy {

    ArrayList<QuestionInterface> createQuiz(int quizLength);
}
