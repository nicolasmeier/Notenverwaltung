package com.factbz.notenverwaltung.Model;

import java.util.Date;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Grade {
    public int id;
    public Date date;
    public float grade;

    public Grade(int id, Date date, float grade) {
        this.id = id;
        this.date = date;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return this.date.toString() + " - " + this.grade;
    }


}
