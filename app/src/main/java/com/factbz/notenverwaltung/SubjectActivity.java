package com.factbz.notenverwaltung;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Adapter.SubjectAdapter;
import com.factbz.notenverwaltung.Data.TestData;
import com.factbz.notenverwaltung.Model.Semester;
import com.factbz.notenverwaltung.Model.Subject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String title = (String) intent.getExtras().get("Title");
        int semesterID = (int) intent.getExtras().get("SemesterID");

        this.setTitle(title);


        TestData testData = new TestData();

        SubjectAdapter adapter = new SubjectAdapter(this, (ArrayList<Subject>) testData.getSemesters().get(semesterID).subjects);


        ListView listView = (ListView) findViewById(R.id.lvSubject);
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
