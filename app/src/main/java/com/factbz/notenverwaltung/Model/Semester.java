package com.factbz.notenverwaltung.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Semester {
    public String name;
    public List<Subject> subjects;

    public Semester( String name) {
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
