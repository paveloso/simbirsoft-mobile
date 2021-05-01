package com.example.mythings;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.mythings.adapter.TaskListAdapter;
import com.example.mythings.service.DataService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private Calendar dayClicked;
    private Map<String, String> times;

    private DataService dataService = DataService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateTimesTasks();

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                dayClicked = eventDay.getCalendar();
                setSelectedDay();
                generateTimesTasks();
                recyclerView.setAdapter(new TaskListAdapter(getApplicationContext(), times));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TaskListAdapter taskListAdapter = new TaskListAdapter(this, times);
        taskListAdapter.setOnItemClickListener(new TaskListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (dataService.getByName(times.get(times.keySet().toArray()[position].toString())) != null) {
                    Intent intent = new Intent(getBaseContext(), TaskInfoActivity.class);
                    intent.putExtra("TASK_NAME", times.get(times.keySet().toArray()[position].toString()));
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(new TaskListAdapter(this, times));
    }

    private void setSelectedDay() {
        calendarView.setHighlightedDays(new ArrayList<>(Arrays.asList(dayClicked)));
        calendarView.setSelectedDates(new ArrayList<>(Arrays.asList(dayClicked)));
    }

    private void generateTimesTasks() {
        times = new LinkedHashMap<>();
        for (int i = 0; i < 24; i++) {
            times.put((i < 10 ? "0" : "") + i + ":00 - " + (i < 9 ? "0" : "") + (i + 1) + ":00", "");
        }
        if (dayClicked != null) {
            try {
                JSONArray data = dataService.getPreparedData();
                for (int i = 0; i < data.length(); i++) {
                    JSONObject obj = data.getJSONObject(i);
                    Calendar objStartDate = Calendar.getInstance();
                    objStartDate.setTime(new Date(Long.parseLong(obj.get("date_start").toString()) * 1000));
                    if (dayClicked.get(Calendar.DAY_OF_YEAR) == objStartDate.get(Calendar.DAY_OF_YEAR)) {
                        times.put((objStartDate.get(Calendar.HOUR_OF_DAY) < 10 ? "0" : "") + objStartDate.get(Calendar.HOUR_OF_DAY) + ":00 - " + (objStartDate.get(Calendar.HOUR_OF_DAY) < 9 ? "0" : "") + (objStartDate.get(Calendar.HOUR_OF_DAY) + 1) + ":00", obj.getString("name"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}