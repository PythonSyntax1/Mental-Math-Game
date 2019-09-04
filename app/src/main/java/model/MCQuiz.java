package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MCQuiz implements Iterable<MCQuestion> {

    private int quizLength;
    private List<MCQuestion> questionList = new LinkedList<>();


    public MCQuiz(int quizLength) {
        this.quizLength = quizLength;
        addMCQuestions();
    }



    // Populates Quiz with Multiple Choice Questions
    public void addMCQuestions() {

        for (int j = 0; j < quizLength; j++) {
            MCQuestion newMCQuestion = new MCQuestion();
            questionList.add(newMCQuestion);
        }
    }

    public Iterator<MCQuestion> iterator() {
        return questionList.iterator();
    }
}
