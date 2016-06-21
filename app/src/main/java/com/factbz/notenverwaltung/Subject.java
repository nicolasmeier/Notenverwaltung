package com.factbz.notenverwaltung;

import java.util.List;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Subject {
    public int id;
    public String name;
    public Semester semester;
    public List<Grade> grades;

    public Subject(int id, String name, Semester semester, List<Grade> grades) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.grades = grades;

        semester.subjects.add(this);
    }


    @Override
    public String toString() {
        return this.name;
    }
}
