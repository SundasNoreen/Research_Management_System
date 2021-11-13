package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class OpenResearch {
    // By AQSA AYAZ
    public int Research_Id;
    public int Teacher_id;
    public String Title;
    public String Domain;
    public Date Starting_Date;
    public String About;
    public String Progress;
    public Connection con;
    public String Students = "";
    Statement stmt1, stmt2, stmt3;
    ResultSet rs, rt, ru;
    public String Teacher;
    private ArrayList < OpenResearch > Open = new ArrayList < OpenResearch > ();

    OpenResearch() {}

    OpenResearch(int Research_Id, String Teacher, int Teacher_id, String Title, String Domain, Date Starting_Date, String About, String Progress) {
        this.Research_Id = Research_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Teacher_id = Teacher_id;
        this.Domain = Domain;
        this.Starting_Date = Starting_Date;
        this.About = About;
        this.Progress = Progress;
    }

    OpenResearch(int Research_Id, String Teacher, int Teacher_id, String Students, String Title, String Domain, Date Starting_Date, String About, String Progress) {
        this.Research_Id = Research_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Students = Students;
        this.Teacher_id = Teacher_id;
        this.Domain = Domain;
        this.Starting_Date = Starting_Date;
        this.About = About;
        this.Progress = Progress;
    }

    // By SUNDAS NOREEN
    public ArrayList < OpenResearch > Student_List(String Reg_No) throws SQLException {
        try {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Open.clear();

            String query = "SELECT * FROM `student_research_open` WHERE  `Student_Id`='" + Reg_No + "'";
            rt = stmt1.executeQuery(query);
            while (rt.next()) {
                stmt2 = con.createStatement();
                int Value = rt.getInt(2);
                rs = stmt2.executeQuery("SELECT * FROM `open_research` WHERE `Research_Id`='" + Value + "'ORDER BY `Starting_Date` DESC");
                while (rs.next()) {
                    Research_Id = rs.getInt(1);
                    Title = rs.getString(2);
                    Teacher_id = rs.getInt(3);
                    Domain = rs.getString(4);
                    Starting_Date = rs.getDate(5);
                    About = rs.getString(6);
                    Progress = rs.getString(7);
                    stmt3 = con.createStatement();
                    ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                    while (ru.next()) {
                        if (ru.getInt(1) == Teacher_id) {
                            Teacher = ru.getString(3);
                        }
                    }
                    Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Title, Domain, Starting_Date, About, Progress));
                }
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }

    // By SUNDAS NOREEN
    public ArrayList < OpenResearch > Student_Individual(int Research_Id) throws SQLException {
        try {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt1 = con.createStatement();
            Open.clear();

            String query = "SELECT * FROM `student_research_open` WHERE  `Research_Id`='" + Research_Id + "'";
            rt = stmt1.executeQuery(query);
            while (rt.next()) {
                String Reg_No = rt.getString(1);
                stmt2 = con.createStatement();
                rs = stmt2.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='" + Reg_No + "'");
                while (rs.next()) {
                    String Name = rs.getString(2);
                    Students = Students + Reg_No + " (" + Name + ")" + "\n";
                }

            }
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `open_research` WHERE `Research_Id`='" + Research_Id + "'ORDER BY `Starting_Date` DESC");
            while (rs.next()) {
                Title = rs.getString(2);
                Teacher_id = rs.getInt(3);
                Domain = rs.getString(4);
                Starting_Date = rs.getDate(5);
                About = rs.getString(6);
                Progress = rs.getString(7);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Students, Title, Domain, Starting_Date, About, Progress));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }
}