package com.factbz.notenverwaltung.Data;

import com.factbz.notenverwaltung.Model.Grade;
import com.factbz.notenverwaltung.Model.Semester;
import com.factbz.notenverwaltung.Model.Subject;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class TestData {
    private List<Semester> semesters;
    private List<Subject> subjects;
    private List<Grade> grades;

    public TestData() {
        semesters = new ArrayList<>();
        subjects = new ArrayList<>();
        grades = new ArrayList<>();

        Semester semester1 = new Semester(1, "Herbst Semester 2016");
        Semester semester2 = new Semester(2, "Frühlings Semester 2016");
        semesters.add(semester1);
        semesters.add(semester2);

        Subject subject1 = new Subject(1, "Englisch", semester1);
        Subject subject2 = new Subject(2, "Mathe", semester1);
        Subject subject3 = new Subject(3, "Geometrie", semester1);
        Subject subject4 = new Subject(4, "Franz", semester1);
        Subject subject5 = new Subject(5, "Geschichte", semester1);
        Subject subject6 = new Subject(6, "Englisch", semester2);
        Subject subject7 = new Subject(7, "Mathe", semester2);
        Subject subject8 = new Subject(8, "Schwimmen", semester2);
        Subject subject9 = new Subject(9, "Informatik", semester2);

        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);
        subjects.add(subject4);
        subjects.add(subject5);
        subjects.add(subject6);
        subjects.add(subject7);
        subjects.add(subject8);
        subjects.add(subject9);

        Grade grade01 = new Grade(1, new Date(), 5, subject1);
        Grade grade02 = new Grade(2, new Date(), 4, subject2);
        Grade grade03 = new Grade(3, new Date(), 5, subject3);
        Grade grade04 = new Grade(4, new Date(), 3, subject4);
        Grade grade05 = new Grade(5, new Date(), 6, subject5);
        Grade grade06 = new Grade(6, new Date(), 5, subject6);
        Grade grade07 = new Grade(7, new Date(), 4, subject7);
        Grade grade08 = new Grade(8, new Date(), 5, subject8);
        Grade grade09 = new Grade(9, new Date(), 3, subject9);
        Grade grade00 = new Grade(10, new Date(), 6, subject1);
        Grade grade11 = new Grade(11, new Date(), 4, subject2);
        Grade grade12 = new Grade(12, new Date(), 5, subject3);
        Grade grade13 = new Grade(13, new Date(), 3, subject4);
        Grade grade14 = new Grade(14, new Date(), 6, subject5);
        Grade grade15 = new Grade(15, new Date(), 5, subject6);
        Grade grade16 = new Grade(16, new Date(), 4, subject7);
        Grade grade17 = new Grade(17, new Date(), 5, subject8);
        Grade grade18 = new Grade(18, new Date(), 3, subject9);
        Grade grade19 = new Grade(19, new Date(), 6, subject1);

        grades.add(grade01);
        grades.add(grade02);
        grades.add(grade03);
        grades.add(grade04);
        grades.add(grade05);
        grades.add(grade06);
        grades.add(grade07);
        grades.add(grade08);
        grades.add(grade09);
        grades.add(grade00);
        grades.add(grade11);
        grades.add(grade12);
        grades.add(grade13);
        grades.add(grade14);
        grades.add(grade15);
        grades.add(grade16);
        grades.add(grade17);
        grades.add(grade18);
        grades.add(grade19);
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Grade> getGrades() {
        return grades;
    }

}
