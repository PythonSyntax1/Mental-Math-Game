package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Quiz implements Iterable<Question> {

    private int quizLength;
    private List<Question> questionList = new LinkedList<>();


    public Quiz(int quizLength) {

        this.quizLength = quizLength;

    }

    // Populates Quiz with regular questions
    public void addQuestions() {

        for (int j = 0; j < quizLength; j++) {
            Question newQuestion = new Question();
            questionList.add(newQuestion);
        }
    }

    public Iterator<Question> iterator() {
        return questionList.iterator();
    }


}
