package com.factbz.notenverwaltung.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Semester {
    public int id;
    public String name;

    public Semester(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
