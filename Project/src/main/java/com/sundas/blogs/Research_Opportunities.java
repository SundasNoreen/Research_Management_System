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
    public int count;
    String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    public Connection con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
    public Statement stmt, stmt2, stmt3;
    public static ResultSet rs, ru, rt;

    ArrayList < Research_Opportunities > Research = new ArrayList < Research_Opportunities > ();

    // By SUNDAS NOREEN
    Research_Opportunities() throws SQLException {

    }

    Research_Opportunities(int Application_Id, String Title, int Teacher_Id, String Teacher, String Domain, String No_of_Student, String Min_Edu, Date Last_date_to_apply, Date Starting_Date, String About, String Eligibility, String CGPA) throws SQLException {
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

    Research_Opportunities(int Application_Id,int count, String Title, int Teacher_Id, String Teacher, String Domain, String No_of_Student, String Min_Edu, Date Last_date_to_apply, Date Starting_Date, String About, String Eligibility, String CGPA) throws SQLException {
        this.Application_Id = Application_Id;
        this.count=count;
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

    // By AYESHA NADEEM
    public ArrayList < Research_Opportunities > Admin_List() throws SQLException {
        try {
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
                count=0;
                stmt3=con.createStatement();
                rt=stmt3.executeQuery("SELECT * FROM `application` WHERE  `Opportunity_Id`='"+Application_Id+"'");
                while (rt.next())
                {
                    count++;
                }
                Research.add(new Research_Opportunities(Application_Id,count, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By AYESHA NADEEM
    public ArrayList < Research_Opportunities > Admin_Individual(int application_Id) throws SQLException {
        try {
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
                count=0;
                stmt3=con.createStatement();
                rt=stmt3.executeQuery("SELECT * FROM `application` WHERE  `Opportunity_Id`='"+Application_Id+"'");
                while (rt.next())
                {
                    count++;
                }
                Research.add(new Research_Opportunities(Application_Id,count, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }
}