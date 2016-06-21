package com.factbz.notenverwaltung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Data.TestData;
import com.factbz.notenverwaltung.Model.Semester;

import java.util.ArrayList;

public class SemesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        TestData testData = new TestData();

        SemesterAdapter adapter = new SemesterAdapter(this, (ArrayList<Semester>) testData.getSemesters());


        ListView listView = (ListView) findViewById(R.id.lvSemester);
        listView.setAdapter(adapter);
    }
}
