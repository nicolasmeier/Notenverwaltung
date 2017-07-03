package com.factbz.notenverwaltung.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.factbz.notenverwaltung.Data.DBAdapter;
import com.factbz.notenverwaltung.Model.Subject;
import com.factbz.notenverwaltung.R;

import java.util.ArrayList;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class SubjectAdapter extends ArrayAdapter<Subject> {
    public SubjectAdapter(Context context, ArrayList<Subject> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Subject subject = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_subject, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvAvg = (TextView) convertView.findViewById(R.id.tvAvg);
        // Populate the data into the template view using the data object
        tvName.setText(subject.name);

        float averageGrade = getAverageGrade(parent.getContext(),subject.id);
        tvAvg.setText(String.format("%.2f", averageGrade));
        // Return the completed view to render on screen
        return convertView;
    }

    public float getAverageGrade(Context context, int subjectId){
        DBAdapter dbAdapter = new DBAdapter(context);
        dbAdapter.open();
        Cursor gradeCursor = dbAdapter.getAllGrades();
        float sum = 0;
        int count = 0;
        try {

            for (gradeCursor.moveToFirst(); !gradeCursor.isAfterLast(); gradeCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (gradeCursor.getInt(3) == subjectId) {
                    sum += gradeCursor.getFloat(1);
                    count++;
                }
            }
        }catch (Exception e){
            // ignorieren da cursor leer
        }
        return (sum / count);
    }

}