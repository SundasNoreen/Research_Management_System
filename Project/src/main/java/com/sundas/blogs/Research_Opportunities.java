package com.sundas.blogs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
public class Research_Opportunities
{
    private int Application_Id;
    private String Title;
    private int  Teacher_Id;
    private String Domain;
    private String No_of_Student;
    private String Min_Edu;
    private Date Last_date_to_apply;
    private String Duration;
    private Date Starting_Date;
    private String Status;
    private Connection con;
    private Statement stmt;
    private static ResultSet rs;

   public int getApplication_Id()
   {
       return Application_Id;
   }

    public String getTitle() {
        return Title;
    }

    public String getDomain() {
        return Domain;
    }

    public String getNo_of_Student() {
        return No_of_Student;
    }

    public void setNo_of_Student(String no_of_Student) {
        No_of_Student = no_of_Student;
    }

    public String getMin_Edu() {
        return Min_Edu;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public int getTeacher_Id() {
        return Teacher_Id;
    }

    public Date getStarting_Date() {
        return Starting_Date;
    }

    public void setApplication_Id(int application_Id) {
        Application_Id = application_Id;
    }

    public void setTeacher_Id(int teacher_Id) {
        Teacher_Id = teacher_Id;
    }

    public String getDuration() {
        return Duration;
    }

    public Date getLast_date_to_apply() {
        return Last_date_to_apply;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setLast_date_to_apply(Date last_date_to_apply) {
        Last_date_to_apply = last_date_to_apply;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    Research_Opportunities()
    {

    }

    Research_Opportunities (int Application_Id, String Title, int Teacher_id, String No_of_Student, String Min_Edu, Date Last_date_to_apply, String Duration, Date Starting_Date)
    {
        this.Application_Id=Application_Id;
        this.Title=Title;
        this. Teacher_Id=Teacher_id;
        this. No_of_Student=No_of_Student;
        this. Min_Edu=Min_Edu;
        this. Last_date_to_apply=Last_date_to_apply;
        this. Duration=Duration;
        this. Starting_Date=Starting_Date;
    }

    public void setMin_Edu(String min_Edu) {
        Min_Edu = min_Edu;
    }

    public void setStarting_Date(Date starting_Date) {
        Starting_Date=Starting_Date;
   }


}
