package com.factbz.notenverwaltung;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Adapter.SubjectAdapter;
import com.factbz.notenverwaltung.Data.DBAdapter;
import com.factbz.notenverwaltung.Dialog.AddSemesterDialogFragment;
import com.factbz.notenverwaltung.Model.Semester;
import com.factbz.notenverwaltung.Model.Subject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    public DBAdapter dbAdapter;
    private SubjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        int semesterID = (int) intent.getExtras().get("SemesterID");
        String semesterName = dbAdapter.getSemester(semesterID).getString(1);

        this.setTitle(semesterName + " - Fächer");


        ArrayList<Subject> mArrayList = new ArrayList<Subject>();
        try {
            Cursor mCursor = dbAdapter.getAllSubjects();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (mCursor.getInt(2) == semesterID) {
                    mArrayList.add(new Subject(mCursor.getString(1),5.5f));
                }
            }
        }catch (Exception e){
            // ignore
        }
        adapter = new SubjectAdapter(this,mArrayList);


        ListView listView = (ListView) findViewById(R.id.lvSubject);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), GradeActivity.class);
                intent.putExtra("SubjectID", i);
                startActivity(intent);
            }
        });


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
