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
import android.widget.ListView;

import com.factbz.notenverwaltung.Adapter.GradeAdapter;
import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Data.DBAdapter;
import com.factbz.notenverwaltung.Dialog.AddGradeDialogFragment;
import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.Model.Subject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GradeActivity extends AppCompatActivity implements AddGradeDialogFragment.GradeDialogListener{

    public DBAdapter dbAdapter;
    private GradeAdapter adapter;
    private int subjectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        subjectID = (int) intent.getExtras().get("SubjectID");
        String subjectName = dbAdapter.getSubject(subjectID).getString(1);

        this.setTitle(subjectName + " - Noten");

        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        ArrayList<Grade> mArrayList = new ArrayList<Grade>();
        try {
            Cursor mCursor = dbAdapter.getAllGrades();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (mCursor.getInt(4) == subjectID) {
                    Date finDate = format.parse(mCursor.getString(1));
                    mArrayList.add(new Grade(finDate,mCursor.getFloat(2)));
                }
            }
        }catch (Exception e){
            // ignore
            // new
        }

        adapter = new GradeAdapter(this, mArrayList);


        ListView listView = (ListView) findViewById(R.id.lvGrade);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabGrade);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment d = new AddGradeDialogFragment();
                d.show(getFragmentManager(),"Grade");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Date date, Float grade) {
        dbAdapter.insertGrade(date.toString(), grade, subjectID);
        adapter.add(new Grade(date, grade));
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
