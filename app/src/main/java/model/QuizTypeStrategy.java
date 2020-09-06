package model;

import java.util.ArrayList;

public interface QuizTypeStrategy { 
    
    //The current application does not dynamically chose a strategy at runtime because each gamemode has a unique view,
    //but this allows for easy extension if a view is to be re-used in the future with different question types with the same 
    //quiz. For example, MC quiz that asks algebra, or MC quiz with mixed questions.
    ArrayList<QuestionInterface> createQuiz(int quizLength);
}
