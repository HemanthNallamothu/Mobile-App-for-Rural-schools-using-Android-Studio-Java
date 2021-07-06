package com.smallacademy.userroles;

public class Member {
    private String Subject;
    private String Date;
    private String Timeslot;
    private String Name;

    public Member() { }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTimeslot() { return Timeslot; }

    public void setTimeslot(String Timeslot) { this.Timeslot = Timeslot; }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }
}
