package com.factbz.notenverwaltung.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pascal Affentranger on 21.06.2016.
 */
public class DBAdapter {
    public static final String DB_NAME = "grades.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_SEMESTER = "semester";
    public static final String COLUMN_SEMESTER_ID = "id";
    public static final String COLUMN_SEMESTER_NAME = "name";

    public static final String TABLE_SUBJECT = "subject";
    public static final String COLUMN_SUBJECT_ID = "id";
    public static final String COLUMN_SUBJECT_NAME = "name";
    public static final String COLUMN_SUBJECT_SEMESTER_FK = "semesterFK";

    public static final String TABLE_GRADE = "grade";
    public static final String COLUMN_GRADE_ID = "id";
    public static final String COLUMN_GRADE_DATE = "date";
    public static final String COLUMN_GRADE_GRADE = "grade";
    public static final String COLUMN_GRADE_SUBJECT_FK = "subjectFK";

    // SQL for creation of semester table
    public static final String SQL_CREATE_SEMESTER =
            "create table " + TABLE_SEMESTER + "("
                    + COLUMN_SEMESTER_ID + " integer primary key autoincrement, "
                    + COLUMN_SEMESTER_NAME + " text not null);";

    // SQL for creation of subject table
    public static final String SQL_CREATE_SUBJECT =
            "create table " + TABLE_SUBJECT + "("
                    + COLUMN_SUBJECT_ID + " integer primary key autoincrement, "
                    + COLUMN_SUBJECT_NAME + " text not null, "
                    + COLUMN_SUBJECT_SEMESTER_FK + " integer, "
                    + "FOREIGN KEY(" + COLUMN_SUBJECT_SEMESTER_FK + ") REFERENCES " + TABLE_SEMESTER + "(" + COLUMN_SEMESTER_ID + "));";

    // SQL for creation of grade table
    public static final String SQL_CREATE_GRADE =
            "create table " + TABLE_GRADE + "("
                    + COLUMN_GRADE_ID + " integer primary key autoincrement, "
                    + COLUMN_GRADE_GRADE + " real not null, "
                    + COLUMN_GRADE_DATE + " text not null, "
                    + COLUMN_GRADE_SUBJECT_FK + " integer, "
                    + "FOREIGN KEY(" + COLUMN_GRADE_SUBJECT_FK + ") REFERENCES " + TABLE_SUBJECT + "(" + COLUMN_SUBJECT_ID + "));";

    final Context context;

    private GradesDatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new GradesDatabaseHelper(context);
    }


    private static class GradesDatabaseHelper extends SQLiteOpenHelper {

        public GradesDatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(SQL_CREATE_SEMESTER);
                db.execSQL(SQL_CREATE_SUBJECT);
                db.execSQL(SQL_CREATE_GRADE);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DB_NAME, "Upgrading database from version " + oldVersion + " to " + newVersion);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEMESTER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADE);
            onCreate(db);
        }
    }

    //-- opens the database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //-- closes the database
    public void close(){
        DBHelper.close();
    }

    //---------------------------------------------
    //----- Semester -----

    // insert a semester into the database
    public long insertSemester(String name){
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_SEMESTER_NAME, name);
        return db.insert(TABLE_SEMESTER, null, initialValues);
    }

    // delete a semester from the database
    public boolean deleteSemester(int rowId){
        return db.delete(TABLE_SEMESTER, COLUMN_SEMESTER_ID + "=" + rowId, null) > 0;
    }

    // retrieves all semesters from the database
    public Cursor getAllSemesters(){
        return db.query(TABLE_SEMESTER, new String[] {COLUMN_SEMESTER_ID, COLUMN_SEMESTER_NAME}, null, null, null, null, null);
    }

    // retrieves a particular semester from the database
    public Cursor getSemester(int rowId) throws SQLException {
        Cursor myCursor =
                db.query(true, TABLE_SEMESTER, new String[] {COLUMN_SEMESTER_ID, COLUMN_SEMESTER_NAME},
                        COLUMN_SEMESTER_ID + "=" + rowId, null, null, null, null, null);
        if(myCursor != null){
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    // update a semester
    public boolean updateSemester(int rowId, String name){
        ContentValues args = new ContentValues();
        args.put(COLUMN_SEMESTER_ID, rowId);
        args.put(COLUMN_SEMESTER_NAME, name);
        return db.update(TABLE_SEMESTER, args, COLUMN_SEMESTER_ID + "=" + rowId, null) > 0;
    }

    //---------------------------------------------
    //----- Subject -----

    // insert a subject into the database
    public long insertSubject(String name, int semester_fk){
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_SUBJECT_NAME, name);
        initialValues.put(COLUMN_SUBJECT_SEMESTER_FK, semester_fk);
        return db.insert(TABLE_SUBJECT, null, initialValues);
    }

    // delete a subject from the database
    public boolean deleteSubject(int rowId){
        return db.delete(TABLE_SUBJECT, COLUMN_SUBJECT_ID + "=" + rowId, null) > 0;
    }

    // retrieves all subjects from the database
    public Cursor getAllSubjects(){
        return db.query(TABLE_SUBJECT, new String[] {COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME, COLUMN_SUBJECT_SEMESTER_FK}
                , null, null, null, null, null);
    }

    // retrievs a particular subject from the database
    public Cursor getSubject(int rowId) throws SQLException {
        Cursor myCursor =
                db.query(true, TABLE_SUBJECT, new String[] {COLUMN_SUBJECT_ID, COLUMN_SUBJECT_NAME, COLUMN_SUBJECT_SEMESTER_FK},
                        COLUMN_SUBJECT_ID + "=" + rowId, null, null, null, null, null);
        if(myCursor != null){
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    // update a subject
    public boolean updateSubject(int rowId, String name, int semesterfk){
        ContentValues args = new ContentValues();
        args.put(COLUMN_SUBJECT_ID, rowId);
        args.put(COLUMN_SUBJECT_NAME, name);
        args.put(COLUMN_SUBJECT_SEMESTER_FK, semesterfk);
        return db.update(TABLE_SUBJECT, args, COLUMN_SUBJECT_ID + "=" + rowId, null) > 0;
    }

    //---------------------------------------------
    //----- Grade -----

    // inserts a grade into the database
    public long insertGrade(String date, float grade, int subject_fk){
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_GRADE_GRADE, grade);
        initialValues.put(COLUMN_GRADE_DATE, date);
        initialValues.put(COLUMN_GRADE_SUBJECT_FK, subject_fk);
        return db.insert(TABLE_GRADE, null, initialValues);
    }

    // deletes a grade from the database
    public boolean deleteGrade(int rowId){
        return db.delete(TABLE_GRADE, COLUMN_GRADE_ID + "=" + rowId, null) > 0;
    }

    // retrieves all grades from the database
    public Cursor getAllGrades(){
        return db.query(TABLE_GRADE, new String[] {COLUMN_GRADE_ID, COLUMN_GRADE_GRADE, COLUMN_GRADE_DATE, COLUMN_GRADE_SUBJECT_FK}
                , null, null, null, null, null);
    }

    // retreives a particular grade from the database
    public Cursor getGrade(int rowId) throws SQLException {
        Cursor myCursor =
                db.query(true, TABLE_GRADE, new String[] {COLUMN_GRADE_ID, COLUMN_GRADE_GRADE, COLUMN_GRADE_DATE, COLUMN_GRADE_SUBJECT_FK},
                        COLUMN_GRADE_ID + "=" + rowId, null, null, null, null, null);
        if(myCursor != null){
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    // update a grade
    public boolean updateGrade(int rowId, String date, float grade, int subjectfk){
        ContentValues args = new ContentValues();
        args.put(COLUMN_GRADE_ID, rowId);
        args.put(COLUMN_GRADE_GRADE, grade);
        args.put(COLUMN_GRADE_DATE, date);
        args.put(COLUMN_GRADE_SUBJECT_FK, subjectfk);
        return db.update(TABLE_GRADE, args, COLUMN_GRADE_ID + "=" + rowId, null) > 0;
    }
}
