package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Research_Opportunities {
    // By AQSA AYAZ
    public int Application_Id;
    public String Title;
    public int Teacher_Id;
    public String Domain;
    public String Teacher;
    public String No_of_Student;
    public String Min_Edu;
    public Date Last_date_to_apply;
    public Date Starting_Date;
    public String Eligibility;
    public String About;
    public String CGPA;
    public Connection con;
    public Statement stmt, stmt2;
    public static ResultSet rs, ru;

    ArrayList < Research_Opportunities > Research = new ArrayList < Research_Opportunities > ();

    // By SUNDAS NOREEN
    Research_Opportunities() {

    }

    Research_Opportunities(int Application_Id, String Title, int Teacher_Id, String Teacher, String Domain, String No_of_Student, String Min_Edu, Date Last_date_to_apply, Date Starting_Date, String About, String Eligibility, String CGPA) {
        this.Application_Id = Application_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Last_date_to_apply = Last_date_to_apply;
        this.Starting_Date = Starting_Date;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
    }

    // By SUNDAS NOREEN
    public ArrayList < Research_Opportunities > Student_List() throws SQLException {
        try {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` WHERE  `Status`= 'O' ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);;
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                Research.add(new Research_Opportunities(Application_Id, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By SUNDAS NOREEN
    public ArrayList < Research_Opportunities > Student_Individual(int application_Id) throws SQLException {
        try {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` WHERE  `Application_Id`= '" + application_Id + "' ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);;
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                Research.add(new Research_Opportunities(Application_Id, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }
}