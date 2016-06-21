package com.factbz.notenverwaltung.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.Model.Subject;
import com.factbz.notenverwaltung.R;

import java.util.ArrayList;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class GradeAdapter extends ArrayAdapter<Grade> {
    public GradeAdapter(Context context, ArrayList<Grade> grades) {
        super(context, 0, grades);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Grade grade = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grade, parent, false);
        }
        // Lookup view for data population
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvGrade = (TextView) convertView.findViewById(R.id.tvGrade);
        // Populate the data into the template view using the data object
        tvDate.setText(grade.date.toString());
        tvGrade.setText(String.format("%.2f", grade.grade));
        // Return the completed view to render on screen
        return convertView;
    }

}