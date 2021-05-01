package com.example.mythings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mythings.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private static ClickListener clickListener;

    private final LayoutInflater inflater;
    private Map<String, String> values;

    public TaskListAdapter(Context context, Map<String, String> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.time_table_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.timeView.setText(values.keySet().toArray()[position].toString());
        holder.taskView.setText(values.get(values.keySet().toArray()[position].toString()));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView timeView, taskView;
        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            timeView = (TextView) view.findViewById(R.id.timeText);
            taskView = (TextView) view.findViewById(R.id.taskText);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        TaskListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
