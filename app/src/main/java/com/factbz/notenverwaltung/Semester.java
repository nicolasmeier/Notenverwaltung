package com.factbz.notenverwaltung;

import java.util.Date;
import java.util.List;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Semester {
    public int id;
    public String name;
    public List<Subject> subjects;

    public Semester(int id, String name, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
