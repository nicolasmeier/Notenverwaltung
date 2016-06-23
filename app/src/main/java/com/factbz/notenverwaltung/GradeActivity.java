package com.factbz.notenverwaltung;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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

import com.factbz.notenverwaltung.Adapter.GradeAdapter;
import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Data.DBAdapter;
import com.factbz.notenverwaltung.Dialog.AddGradeDialogFragment;
import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.Model.Semester;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        subjectID = (int) intent.getExtras().get("SubjectID");
        String subjectName = dbAdapter.getSubject(subjectID).getString(1);

        this.setTitle(subjectName + " - Noten");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Grade> mArrayList = new ArrayList<Grade>();
        try {
            Cursor mCursor = dbAdapter.getAllGrades();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (mCursor.getInt(3) == subjectID) {
                    Date finDate = format.parse(mCursor.getString(2));
                    mArrayList.add(new Grade(mCursor.getInt(0),finDate,mCursor.getFloat(1)));
                }
            }
        }catch (Exception e){
            // ignore
            // new
        }

        adapter = new GradeAdapter(this, mArrayList);


        ListView listView = (ListView) findViewById(R.id.lvGrade);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                final Grade g = adapter.getItem(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Löschen?")
                        .setMessage("Möchten sie die Note vom " + g.date + " endgültig löschen?")
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.remove(g);
                                adapter.notifyDataSetChanged();
                                dbAdapter.deleteGrade(g.id);
                            }
                        })
                        .setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = alert.create();
                dialog.show();
                return true;
            }
        });

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
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        dbAdapter.insertGrade(df.format(date), grade, subjectID);
        try {
            Cursor mCursor = dbAdapter.getAllGrades();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (mCursor.getInt(3) == subjectID) {
                    Boolean isNew = true;
                    for (int i = 0; i < adapter.getCount() ; i++){
                        if (mCursor.getInt(0) == adapter.getItem(i).id) isNew = false;
                    }
                    if (isNew) adapter.add(new Grade(mCursor.getInt(0),date,grade));
                }
            }
        }catch (Exception e){
            // ignore
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
