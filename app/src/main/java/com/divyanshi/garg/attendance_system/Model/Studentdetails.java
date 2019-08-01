package com.divyanshi.garg.attendance_system.Model;

public class Studentdetails {

    private String StudentID;
    private String Name;
    private String Course;


    private String Department;
    private String Roll_Number;
    private String Batch;

    public Studentdetails(String studentID, String name, String course, String department, String rollNumber, String batch) {
        StudentID = studentID;
        Name = name;
        Course = course;
        Department = department;
        Roll_Number = rollNumber;
        Batch = batch;
    }

    public Studentdetails() {
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getRoll_Number() {
        return Roll_Number;
    }

    public void setRoll_Number(String roll_Number) {
        Roll_Number = roll_Number;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }
}
