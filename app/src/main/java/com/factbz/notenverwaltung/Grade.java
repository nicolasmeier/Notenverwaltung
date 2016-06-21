package com.factbz.notenverwaltung;

import java.util.Date;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Grade {
    public int id;
    public Date date;
    public float grade;
    public Subject subject;

    public Grade(int id, Date date, float grade, Subject subject) {
        this.id = id;
        this.date = date;
        this.grade = grade;
        this.subject = subject;

        subject.grades.add(this);
    }


    @Override
    public String toString() {
        return this.date.toString() + " - " + this.grade;
    }


}
