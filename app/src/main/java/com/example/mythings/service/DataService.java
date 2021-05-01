package com.example.mythings.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataService {

    private static DataService instance;

    private DataService() {}

    public static DataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        instance.generateData();
        return instance;
    }

    private JSONArray tasks = new JSONArray();

    private void generateData() {
        try {
            JSONObject task1 = new JSONObject();
            task1.put("id", "1");
            task1.put("date_start", "1620183600");
            task1.put("date_finish", "1620187200");
            task1.put("name", "Clean dishes");
            task1.put("description", "Take all dirty dished and clean those in a sink");

            JSONObject task2 = new JSONObject();
            task2.put("id", "2");
            task2.put("date_start", "1620216000");
            task2.put("date_finish", "1620219600");
            task2.put("name", "Home work");
            task2.put("description", "Call the teacher, ask excercises and complete them");

            JSONObject task3 = new JSONObject();
            task3.put("id", "3");
            task3.put("date_start", "1620244800");
            task3.put("date_finish", "1620248400");
            task3.put("name", "Gym");
            task3.put("description", "Today is a leg day. Make sure!");

            JSONObject task4 = new JSONObject();
            task4.put("id", "4");
            task4.put("date_start", "1620396000");
            task4.put("date_finish", "1620399600");
            task4.put("name", "Movie time");
            task4.put("description", "Download the latest Lost series and make tv series marathon");

            JSONObject task5 = new JSONObject();
            task5.put("id", "5");
            task5.put("date_start", "1620025200");
            task5.put("date_finish", "1620028800");
            task5.put("name", "Job");
            task5.put("description", "Get up and go make some money");

            tasks.put(task1);
            tasks.put(task2);
            tasks.put(task3);
            tasks.put(task4);
            tasks.put(task5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getPreparedData() {
        return tasks;
    }

    public JSONObject getByName(String name) {
        try {
            for (int i = 0; i < tasks.length(); i++) {
                JSONObject o = tasks.getJSONObject(i);
                if (name.equals(o.getString("name"))) {
                    return o;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
