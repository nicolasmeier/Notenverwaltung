package com.factbz.notenverwaltung;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.factbz.notenverwaltung.Adapter.GradeAdapter;
import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Data.DBAdapter;
import com.factbz.notenverwaltung.Model.Grade;

import java.util.ArrayList;

public class GradeActivity extends AppCompatActivity {

    public DBAdapter dbAdapter;
    private GradeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        int subjectID = (int) intent.getExtras().get("SubjectID");

        this.setTitle(testData.getSubjects().get(subjectID).name + " - Noten");

        adapter = new GradeAdapter(this, (ArrayList<Grade>) testData.getSubjects().get(subjectID).grades);


        ListView listView = (ListView) findViewById(R.id.lvGrade);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
