package com.divyanshi.garg.attendance_system;

public class StudentAddition {

    public String Studentid;
    public String Name;
    public String Course;
    public String Department;
    public String Roll_Number;
    public String Batch;

    public StudentAddition() {
    }

    public StudentAddition(String studentid, String name, String course, String department, String roll_Number, String batch) {
        this.Studentid = studentid;
        this.Name = name;
        this.Course = course;
        this.Department = department;
        this.Roll_Number = roll_Number;
        this.Batch = batch;
    }
}


