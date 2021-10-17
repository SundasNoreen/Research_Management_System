package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;

public class Home_Page extends Research_Opportunities{
    public int Research_id;
    public String field;
    public String no_of_students;
    public String discipline_of_students;
    public String degree;
    public float Cgpa;
    public String Description;
    public String last_date_to_apply;
    public String Starting_date;
    public int Teacher_id;
    public String Heading;
    Connection con;
    Statement stmt;
    ResultSet rs;
    private ArrayList<Research_Opportunities> Opportunities_Posts = new ArrayList<Research_Opportunities>();

    public ArrayList First_Four_opportunities() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `researchopportnities` ORDER BY `Reasearch id` DESC LIMIT 0,5");
            Opportunities_Posts.clear();
            while (rs.next()) {
                Research_id = rs.getInt(11);
                field = rs.getString(1);
                no_of_students = rs.getString(2);
                discipline_of_students = rs.getString(3);
                Teacher_id = rs.getInt(10);
                degree = rs.getString(4);
                Cgpa = rs.getFloat(5);
                Description = rs.getString(6);
                last_date_to_apply = rs.getString(7);
                Starting_date = rs.getString(9);
                Heading=rs.getString(12);
                Opportunities_Posts.add(new Research_Opportunities(Research_id,Heading,field, no_of_students, discipline_of_students, Teacher_id, degree, Cgpa, Description, last_date_to_apply, Starting_date));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        }
        finally
        {
            con.close();
        }
        return Opportunities_Posts;
    }

    public ArrayList Closest_opportunities() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `researchopportnities` ORDER BY `Last to apply` DESC LIMIT 0,5");
            Opportunities_Posts.clear();
            while (rs.next()) {
                Research_id = rs.getInt(11);
                field = rs.getString(1);
                no_of_students = rs.getString(2);
                discipline_of_students = rs.getString(3);
                Teacher_id = rs.getInt(10);
                degree = rs.getString(4);
                Cgpa = rs.getFloat(5);
                Description = rs.getString(6);
                last_date_to_apply = rs.getString(7);
                Starting_date = rs.getString(9);
                Heading=rs.getString(12);
                Opportunities_Posts.add(new Research_Opportunities(Research_id,Heading, field, no_of_students, discipline_of_students, Teacher_id, degree, Cgpa, Description, last_date_to_apply, Starting_date));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        }
        finally
        {
            con.close();
        }
        return Opportunities_Posts;
    }
}
