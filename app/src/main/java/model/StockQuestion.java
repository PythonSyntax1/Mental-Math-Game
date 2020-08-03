package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StockQuestion implements QuestionInterface {

    private ArrayList<StockTicker> stockTickers;
    private ArrayList<String> answer;


    public StockQuestion() {
        stockTickers = new ArrayList<>();
        answer = new ArrayList<>();
        createQuestionStringAndAnswer();
    }

    public void createQuestionStringAndAnswer() {
        ArrayList<StockTicker> sortBuffer = new ArrayList<>();
        for (int i = 0; i < 4; i ++ ) {
            StockTicker curTicker = new StockTicker();
            stockTickers.add(curTicker);
            sortBuffer.add(curTicker);
        }
        Collections.sort(sortBuffer, new Comparator<StockTicker>(){
            public int compare(StockTicker p1, StockTicker p2) {
                if (p1.getFinishPrice() < p2.getFinishPrice()) {
                    return -1;
                } else if (p1.getFinishPrice() > p2.getFinishPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (StockTicker st: sortBuffer) {
            answer.add(st.getName());
        }
    }

    public ArrayList<String> getQuestionString() {
        ArrayList<String> questionStrings = new ArrayList<>();
        for (StockTicker st: stockTickers) {
            questionStrings.add(st.getName());
            questionStrings.add(Double.toString(st.getPrice()));
            questionStrings.add(Double.toString(st.getPct()));
        }
        return questionStrings;
    }


    public ArrayList<String> getQuestionAnswer() {
        return this.answer;
    }

    public Integer getQuestionType() {
        //Todo : Add modes where you chose highest stock price instead of order them
        return 1;
    }
}
