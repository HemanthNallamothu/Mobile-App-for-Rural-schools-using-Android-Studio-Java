package com.smallacademy.userroles;

public class AppointedUserDetails {
    private String Date;
    private String Name;
    private String Qualification;
    private String Subject;
    private String Timeslot;


    public AppointedUserDetails() {
    }

    public AppointedUserDetails(String date, String name, String qualification, String subject, String timeslot) {
        Date = date;
        Name = name;
        Qualification = qualification;
        Subject = subject;
        Timeslot = timeslot;
    }

    public String getDate() { return Date; }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTimeslot() {
        return Timeslot;
    }

    public void setTimeslot(String timeslot) {
        Timeslot = timeslot;
    }
}