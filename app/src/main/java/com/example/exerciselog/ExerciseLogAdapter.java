package com.example.exerciselog;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.example.exerciselog.DatabaseHandler.COLUMN_CATEGORY;
import static com.example.exerciselog.DatabaseHandler.COLUMN_COMMENT;
import static com.example.exerciselog.DatabaseHandler.COLUMN_DATE;
import static com.example.exerciselog.DatabaseHandler.COLUMN_DISTANCE;
import static com.example.exerciselog.DatabaseHandler.COLUMN_ID;
import static com.example.exerciselog.DatabaseHandler.COLUMN_NAME;
import static com.example.exerciselog.DatabaseHandler.COLUMN_REPS;
import static com.example.exerciselog.DatabaseHandler.COLUMN_WEIGHT;

public class ExerciseLogAdapter extends RecyclerView.Adapter<ExerciseLogAdapter.ExerciseViewHolder> {


    private Context tcontext;
    private Cursor tcursor;

    public ExerciseLogAdapter(Context context, Cursor cursor) {
        tcontext = context;
        tcursor = cursor;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(tcontext);
        View view = inflater.inflate(R.layout.row_item, parent, false);

        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        // Make sure cursor can move to this position
        if (!tcursor.moveToPosition(position)) {
            return;
        }

        String name = tcursor.getString(tcursor.getColumnIndex(COLUMN_NAME));
        String cat = tcursor.getString(tcursor.getColumnIndex(COLUMN_CATEGORY));
        String comment = tcursor.getString(tcursor.getColumnIndex(COLUMN_COMMENT));
        String date = tcursor.getString(tcursor.getColumnIndex(COLUMN_DATE));
        //date is saved in UTC 24 Hr time, convert to local time

        SimpleDateFormat localTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date utcTime = null;
        try {
            utcTime = localTime.parse(date + " UTC");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        localTime.setTimeZone(TimeZone.getDefault());
        date = localTime.format(utcTime);


        int reps = tcursor.getInt(tcursor.getColumnIndex(COLUMN_REPS));
        String weight = tcursor.getString(tcursor.getColumnIndex(COLUMN_WEIGHT));
        String distance = tcursor.getString(tcursor.getColumnIndex(COLUMN_DISTANCE));

        long id = tcursor.getLong(tcursor.getColumnIndex(COLUMN_ID));

        holder.nameText.setText("Name: " + name);
        holder.dateText.setText("Date: " + date);
        holder.catText.setText("Category: " + cat);
        holder.commText.setText("Comment: " + comment);
        holder.repText.setText("Reps: " + String.valueOf(reps));
        holder.weightText.setText("Weight: " + weight);
        holder.distText.setText("Distance: " + distance);

        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return tcursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (tcursor != null) {
            tcursor.close();
        }
        tcursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText, catText, repText, commText, distText, weightText, dateText;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.row_item_name);
            catText = itemView.findViewById(R.id.row_item_category);
            repText = itemView.findViewById(R.id.row_item_reps);
            commText = itemView.findViewById(R.id.row_item_comment);
            distText = itemView.findViewById(R.id.row_item_distance);
            weightText = itemView.findViewById(R.id.row_item_weight);
            dateText = itemView.findViewById(R.id.row_item_date);
        }
    }
}
