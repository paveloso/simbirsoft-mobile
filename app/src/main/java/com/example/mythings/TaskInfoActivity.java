package com.example.mythings;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mythings.service.DataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskInfoActivity extends AppCompatActivity {

    private TextView nameText, dateText, descText;
    private DataService dataService = DataService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_info);

        nameText = (TextView) findViewById(R.id.infoName);
        dateText = (TextView) findViewById(R.id.infoStartDate);
        descText = (TextView) findViewById(R.id.infoDesc);

        JSONObject object = dataService.getByName(getIntent().getStringExtra("TASK_NAME"));

        try {
            nameText.setText(object.getString("name"));
            dateText.setText(new Date(Long.parseLong(object.getString("date_start")) * 1000).toString());
            descText.setText(object.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
