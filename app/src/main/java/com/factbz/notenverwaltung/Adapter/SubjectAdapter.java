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
        tvAvg.setText(String.format("%.2f", subject.avg));
        // Return the completed view to render on screen
        return convertView;
    }

}