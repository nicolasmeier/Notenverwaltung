package com.factbz.notenverwaltung.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Subject {
    public String name;
    public float avg;

    public Subject(String name,float avg) {
        this.name = name;
        this.avg = avg;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
