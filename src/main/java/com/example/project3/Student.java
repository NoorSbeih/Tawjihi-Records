package com.example.project3;

public class Student {
    protected int ID;
    protected String branch;
    protected double grade;

    public Student(int ID, String branch, double grade) {
        this.ID = ID;
        this.branch = branch;
        this.grade = grade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", branch='" + branch + '\'' +
                ", grade=" + grade +
                '}';
    }
}

