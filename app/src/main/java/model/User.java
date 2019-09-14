package model;



import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class User {

    private Map<Integer, ArrayList<LocalTime>> mcResults = new HashMap<>();
    private Map<Integer, ArrayList<LocalTime>> oeResults = new HashMap<>();
    private ArrayList<Integer> gameResults = new ArrayList<>();



    public void insertIntoMC(String result, Integer num) {

        LocalTime newTime = LocalTime.parse(result);

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


    public void insertIntoOE(String result, Integer num) {

        LocalTime newTime = LocalTime.parse(result);

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
        } else {
            ArrayList<LocalTime> newResultsList = new ArrayList<>();
            newResultsList.add(newTime);
            oeResults.put(num, newResultsList);
        }
    }

    public void checkListSize(ArrayList<LocalTime> list) {
        if (list.size() > 15) {
            list.remove(15);
        }
    }

    public void insertIntoGameResults(Integer score) {
        Integer i = 0;
        Integer currentsize = gameResults.size();
        if (currentsize == 0) {
            gameResults.add(score);
        }
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
        } if (gameResults.size() > 15){
            gameResults.remove(15);
        }

    }


    public Boolean mcContains(Integer num) {
        return mcResults.containsKey(num);
    }

    public Boolean oeContains(Integer num) {
        return oeResults.containsKey(num);
    }

    public ArrayList<LocalTime> getMCList(Integer num) {
        return mcResults.get(num);
    }

    public ArrayList<LocalTime> getOEList(Integer num) {
        return oeResults.get(num);
    }

    public ArrayList<Integer> getGameList() {
        return gameResults;
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
