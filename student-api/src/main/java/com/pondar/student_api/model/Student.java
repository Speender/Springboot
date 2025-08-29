package com.pondar.student_api.model;

public class Student {
    private Long pkStudentID;
    private String name;
    private String course;

    public Student() {}


    public Student(Long pkStudentID, String name, String course){
        this.pkStudentID = pkStudentID;
        this.name = name;
        this.course = course;
    }

    public Long getPkStudentID(){
        return pkStudentID;
    }

    public String getName(){
        return name;
    }

    public String getCourse(){
        return course;
    }

    public void setPkStudentID(Long pkStudentID){
        this.pkStudentID = pkStudentID;
    }

    public void setName(String name){
        this.name = name;
    } 

    public void setCourse(String course){
        this.course = course;
    }
}