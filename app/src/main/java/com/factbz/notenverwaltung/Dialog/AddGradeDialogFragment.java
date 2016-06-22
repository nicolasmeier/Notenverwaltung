package com.factbz.notenverwaltung.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View v_iew =LayoutInflater.from(getContext()).inflate(R.layout.dialog_grade, null, false);

        builder.setView(v_iew)
                .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editGrade = (EditText) v_iew.findViewById(R.id.etGrade);
                        EditText editDate = (EditText) v_iew.findViewById(R.id.etDate);
                        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            Date finDate = format.parse(editDate.getText().toString());
                            mListener.onDialogPositiveClick(AddGradeDialogFragment.this, finDate, Float.parseFloat(editGrade.getText().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
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
}

