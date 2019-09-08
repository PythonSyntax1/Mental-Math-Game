package model;



import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class User {

    private Map<Integer, ArrayList<LocalTime>> mcResults = new HashMap<>();
    private Map<Integer, ArrayList<LocalTime>> oeResults = new HashMap<>();



    public void insertIntoMC(String result, Integer num) {

        LocalTime newTime = LocalTime.parse(result);

        if (mcResults.containsKey(num)) {

            ArrayList<LocalTime> currentResultsList = mcResults.get(num);

            Integer i = 0;
            Integer currentsize = currentResultsList.size();
            while (i < currentsize)  {
                if (newTime.isBefore(currentResultsList.get(i))) {
                    currentResultsList.add(i, newTime);
                    return;
                }
                else {
                    i ++;
                }
                if (i == currentsize) {
                    currentResultsList.add(newTime);
                    return;
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
                    return;
                }
                else {
                    i ++;
                }
                if (i == currentsize) {
                    currentResultsList.add(newTime);
                    return;
                }
            } checkListSize(currentResultsList);
        } else {
            ArrayList<LocalTime> newResultsList = new ArrayList<>();
            newResultsList.add(newTime);
            oeResults.put(num, newResultsList);
        }
    }

    public void checkListSize(ArrayList<LocalTime> list) {
        if (list.size() > 10) {
            list.remove(10);
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
