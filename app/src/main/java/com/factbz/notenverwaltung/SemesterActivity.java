package com.factbz.notenverwaltung;

import android.content.Intent;
import android.net.LocalSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        final TestData testData = new TestData();

        SemesterAdapter adapter = new SemesterAdapter(this, (ArrayList<Semester>) testData.getSemesters());


        ListView listView = (ListView) findViewById(R.id.lvSemester);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(testData.getSemesters().get(i).name);

                Intent intent = new Intent(view.getContext(), SubjectActivity.class);
                intent.putExtra("Title", testData.getSemesters().get(i).name);
                intent.putExtra("SemesterID", i);
                startActivity(intent);
            }
        });
    }
}
