package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MCQuiz implements Iterable<QuestionInterface> {

    private int quizLength;
    private ArrayList<QuestionInterface> questionList = new ArrayList<>();
    private QuizTypeStrategy strategy;



    public MCQuiz(int quizLength, QuizTypeStrategy strategy) {
        this.quizLength = quizLength;
        this.strategy = strategy;
        addMCQuestions();
    }



    // Populates Quiz with Multiple Choice Questions
    public void addMCQuestions() {
        questionList = strategy.createQuiz(quizLength);
    }

    public Iterator<QuestionInterface> iterator() {
        return questionList.iterator();
    }
}
