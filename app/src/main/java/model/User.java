package model;



import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class User {

    private Map<Integer, ArrayList<LocalTime>> mcResults = new HashMap<>();
    private Map<Integer, ArrayList<LocalTime>> oeResults = new HashMap<>();
    private ArrayList<Integer> gameResults = new ArrayList<>();



    //Inserts a new score into the MC results list
    public void insertIntoMC(String result, Integer num) {

        LocalTime newTime = LocalTime.parse(result);

        //Checks if there are current results with the same amount of questions
        //If there are, it inserts result into the correct position
        if (mcResults.containsKey(num)) {

            ArrayList<LocalTime> currentResultsList = mcResults.get(num);

            Integer i = 0;
            Integer currentsize = currentResultsList.size();

            while (i < currentsize)  {
                if (newTime.isBefore(currentResultsList.get(i))) {
                    currentResultsList.add(i, newTime);
                    break;
                }
                else {
                    i ++;
                }
                if (i == currentsize) {
                    currentResultsList.add(newTime);
                    break;
                }
            } checkListSize(currentResultsList);
        } else {
            ArrayList<LocalTime> newResultsList = new ArrayList<>();
            newResultsList.add(newTime);
            mcResults.put(num, newResultsList);
;        }
    }


    //Inserts a new score into the Open ended results list
    public void insertIntoOE(String result, Integer num) {

        LocalTime newTime = LocalTime.parse(result);

        //Checks if there are current results with the same amount of questions
        //If there are, it inserts result into the correct position
        if (oeResults.containsKey(num)) {

            ArrayList<LocalTime> currentResultsList = oeResults.get(num);

            Integer i = 0;
            Integer currentsize = currentResultsList.size();
            while (i < currentsize)  {
                if (newTime.isBefore(currentResultsList.get(i))) {
                    currentResultsList.add(i, newTime);
                    break;
                }
                else {
                    i ++;
                }
                if (i == currentsize) {
                    currentResultsList.add(newTime);
                    break;
                }
            } checkListSize(currentResultsList);

        //If there are no results with the same amount of questions, make new list
        } else {
            ArrayList<LocalTime> newResultsList = new ArrayList<>();
            newResultsList.add(newTime);
            oeResults.put(num, newResultsList);
        }
    }

    //Keeps result list's size under 15 results
    public void checkListSize(ArrayList<LocalTime> list) {
        if (list.size() > 15) {
            list.remove(15);
        }
    }

    //Inserts new score into the Game results list
    public void insertIntoGameResults(Integer score) {
        Integer i = 0;
        Integer currentsize = gameResults.size();
        if (currentsize == 0) {
            gameResults.add(score);
        }

        //Inserts score into gameResults list in the correct sorted position
        while (i < currentsize) {
            if (score > gameResults.get(i)) {
                gameResults.add(i, score);
                break;
            } else {
                i ++;

            } if (i == currentsize) {
                gameResults.add(score);
                break;
            }

          //Keeps results list size under 15 results
        } if (gameResults.size() > 15){
            gameResults.remove(15);
        }

    }

    //Returns if there are results for a specified amount of questions in the multiple Choice result list
    public Boolean mcContains(Integer num) {
        return mcResults.containsKey(num);
    }

    //Returns if there are results for a specified amount of questions in the Open ended results list
    public Boolean oeContains(Integer num) {
        return oeResults.containsKey(num);
    }

    //Returns a results list for multiple choice quizzes at a certain time
    //Used for inserting results into ListView
    public ArrayList<LocalTime> getMCList(Integer num) {
        return mcResults.get(num);
    }

    //Returns a results list for open ended quizzes at a certain time
    //Used for inserting results into ListView
    public ArrayList<LocalTime> getOEList(Integer num) {
        return oeResults.get(num);
    }

    //Returns the game results list.
    //Used for inserting results into ListView
    public ArrayList<Integer> getGameList() {
        return gameResults;
    }


    public void setGameResults(ArrayList<Integer> h) {
        gameResults = h;
    }

































    /*public void loadData() {
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("results.json"));
            mcResults = gson.fromJson(br, new TypeToken<Map<Integer, ArrayList<LocalTime>>>(){}.getType());
        } catch (IOException e) {
            //do nothing
        }
    }

    public void saveData() {

        try {
            Gson gson = new Gson();
            String jsonString = gson.toJson(mcResults);
            FileWriter fileWriter = new FileWriter("results.json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            //do nothing
        }

    }*/








}
