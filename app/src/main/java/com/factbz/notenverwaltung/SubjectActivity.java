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

import com.factbz.notenverwaltung.Adapter.SemesterAdapter;
import com.factbz.notenverwaltung.Adapter.SubjectAdapter;
import com.factbz.notenverwaltung.Data.DBAdapter;
import com.factbz.notenverwaltung.Dialog.AddSemesterDialogFragment;
import com.factbz.notenverwaltung.Dialog.AddSubjectDialogFragment;
import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.Model.Semester;
import com.factbz.notenverwaltung.Model.Subject;

import java.util.ArrayList;
import java.util.Date;

public class SubjectActivity extends AppCompatActivity implements AddSubjectDialogFragment.SubjectDialogListener{

    public DBAdapter dbAdapter;
    private SubjectAdapter adapter;
    private int semesterID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        Intent intent = getIntent();
        semesterID = (int) intent.getExtras().get("SemesterID");
        String semesterName = dbAdapter.getSemester(semesterID).getString(1);

        this.setTitle(semesterName + " - Fächer");

        float avg;
        int count;

        ArrayList<Subject> mArrayList = new ArrayList<Subject>();
        try {
            Cursor mCursor = dbAdapter.getAllSubjects();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (mCursor.getInt(2) == semesterID) {

                    mArrayList.add(new Subject(mCursor.getInt(0),mCursor.getString(1)));
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
                intent.putExtra("SubjectID", adapter.getItem(i).id);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                final Subject s = adapter.getItem(position);
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Löschen?")
                        .setMessage("Möchten sie das Fach " + s.name + " endgültig löschen?")
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.remove(s);
                                adapter.notifyDataSetChanged();
                                dbAdapter.deleteSubject(dbAdapter.getSubjectByName(s.name).getInt(0));
                                Cursor gradeCursor = dbAdapter.getAllSubjects();
                                for (gradeCursor.moveToFirst(); !gradeCursor.isAfterLast(); gradeCursor.moveToNext()) {
                                    if (gradeCursor.getInt(3) == s.id) {
                                        dbAdapter.deleteGrade(gradeCursor.getInt(0));
                                    }
                                }
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSubject);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment d = new AddSubjectDialogFragment();
                d.show(getFragmentManager(),"Subject");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String name) {
        dbAdapter.insertSubject(name, semesterID);
        try {
            Cursor mCursor = dbAdapter.getAllSubjects();
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                // The Cursor is now set to the right position
                if (mCursor.getInt(2) == semesterID) {
                    Boolean isNew = true;
                    for (int i = 0; i < adapter.getCount(); i++){
                        if (mCursor.getInt(0) == adapter.getItem(i).id) isNew = false;
                    }
                    if (isNew) adapter.add(new Subject(mCursor.getInt(0),name));
                }
            }
        }catch (Exception e){
            // ignore
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //cancel
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
