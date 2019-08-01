package com.divyanshi.garg.attendance_system.Model;

public class User {

    private String EmpID;
    private String Password;
    private String Name;

    public User(String empID, String password, String name) {
        EmpID = empID;
        Password = password;
        Name = name;
    }

    public User() {
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
