package com.factbz.notenverwaltung.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.factbz.notenverwaltung.Filter.InputFilterMinMax;
import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Nicolas on 22.06.2016
 */
public class AddGradeDialogFragment extends DialogFragment {

    public interface GradeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, Date date, Float grade);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    GradeDialogListener mListener;
    private Calendar myCalendar;
    private EditText editDate;
    private EditText editGrade;
    private DatePickerDialog.OnDateSetListener date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View v_iew =LayoutInflater.from(getContext()).inflate(R.layout.dialog_grade, null, false);


        editGrade = (EditText) v_iew.findViewById(R.id.etGrade);
        editDate = (EditText) v_iew.findViewById(R.id.etDate);

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };

        editDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddGradeDialogFragment.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editGrade.setFilters(new InputFilter[]{ new InputFilterMinMax(1.0f,6.0f)});

        builder.setView(v_iew)
                .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(AddGradeDialogFragment.this, myCalendar.getTime(), Float.parseFloat(editGrade.getText().toString()));
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(AddGradeDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity){
            a = (Activity) context;
            try {
                mListener = (GradeDialogListener) a;
            }catch (ClassCastException e) {
                throw new ClassCastException(a.toString() + " must implement NoticeDialogListener");
            }
        }
    }

    private void updateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MMMM.yyyy");
        editDate.setText(sdf.format(myCalendar.getTime()));
    }

}

