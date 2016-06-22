package com.factbz.notenverwaltung.Model;

import java.util.Date;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Grade {
    public Date date;
    public float grade;

    public Grade(Date date, float grade) {
        this.date = date;
        this.grade = grade;
    }


    @Override
    public String toString() {
        return this.date.toString() + " - " + this.grade;
    }


}
