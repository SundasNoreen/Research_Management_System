package com.sundas.blogs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Research_Opportunities
{
    private int Research_id;
    private String Heading;
    private String field;
    private String no_of_students;
    private String discipline_of_students;
    private String degree;
    private float Cgpa;
    private String Description;
    private String last_date_to_apply;
    private String Starting_date;
    private int Teacher_id;
    private Connection con;
    private Statement stmt;
    private static ResultSet rs;
    public String getHeading()
    {
        return Heading;
    }

    public void setHeading(String heading) {
        this.Heading = heading;
    }

    public float getCgpa() {
        return Cgpa;
    }
    public int getTeacher_id() {
        return Teacher_id;
    }
    public String getDegree() {
        return degree;
    }
    public int getResearch_id() {
        return Research_id;
    }
    public String getLast_date_to_apply() {
        return last_date_to_apply;
    }
    public String getDiscipline_of_students() {
        return discipline_of_students;
    }
    public String getDescription() {
        return Description;
    }
    public String getField() {
        return field;
    }
    public String getNo_of_students() {
        return no_of_students;
    }
    public String getStarting_date() {
        return Starting_date;
    }
    public void setLast_date_to_apply(String last_date_to_apply) {
        this.last_date_to_apply = last_date_to_apply;
    }
    public void setCgpa(float cgpa) {
        Cgpa = cgpa;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void setDiscipline_of_students(String discipline_of_students) {
        this.discipline_of_students = discipline_of_students;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public void setField(String field) {
        this.field = field;
    }
    public void setNo_of_students(String no_of_students) {
        this.no_of_students = no_of_students;
    }
    public void setResearch_id(int Research_id) {
        this.Research_id = Research_id;
    }

    public void setStarting_date(String starting_date) {
        this.Starting_date = starting_date;
    }
    public void setTeacher_id(int teacher_id) {
        Teacher_id = teacher_id;
    }

    Research_Opportunities()
    {

    }
    Research_Opportunities(int Research_id, String Heading,String field,String no_of_students,String discipline_of_students, int Teacher_id, String degree,  float Cgpa, String Description,String  last_date_to_apply, String Starting_date)
    {
        this. Research_id=Research_id;
        this.Heading=Heading;
        this. field=field;
        this. no_of_students=no_of_students;
        this. discipline_of_students=discipline_of_students;
        this. Teacher_id=Teacher_id;
        this. degree=degree;
        this. Description=Description;
        this. last_date_to_apply=last_date_to_apply;
        this. Cgpa=Cgpa;
        this. Starting_date=Starting_date;
    }
}
