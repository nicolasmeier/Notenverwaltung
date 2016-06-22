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

import com.factbz.notenverwaltung.Model.Subject;
import com.factbz.notenverwaltung.R;

/**
 * Created by Nicolas on 22.06.2016
 */
public class AddSubjectDialogFragment extends DialogFragment {

    public interface SubjectDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String name);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    SubjectDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View v_iew =LayoutInflater.from(getContext()).inflate(R.layout.dialog_subject, null, false);

        builder.setView(v_iew)
                .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText editText = (EditText) v_iew.findViewById(R.id.etSubject);
                        mListener.onDialogPositiveClick(AddSubjectDialogFragment.this, editText.getText().toString());
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(AddSubjectDialogFragment.this);
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
                mListener = (SubjectDialogListener) a;
            }catch (ClassCastException e) {
                throw new ClassCastException(a.toString() + " must implement NoticeDialogListener");
            }
        }
    }
}

