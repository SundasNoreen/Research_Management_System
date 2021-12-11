package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ClosedResearch {
    // By AQSA AYAZ
    public int Research_Id;
    public int Teacher_id;
    public String Title;
    public String Remarks;
    public String Success_Level;
    public String Domain;
    public Date Starting_Date;
    public Date Ending_Date;
    public String Abstract;
    public String Conclusion;
    String url ="jdbc:mysql://localhost/rms";
    public Connection con =  DriverManager.getConnection(url, "root", "");public Blob Report;
    public String Status;
    public String Link;
    public String Students = "";
    Statement stmt1, stmt2, stmt3;
    ResultSet rs, rt, ru;
    public String Teacher;

    public int getResearch_Id() {
        return Research_Id;
    }
    public String getRemarks() {
        return Remarks;
    }
    public String getDomain() {
        return Domain;
    }
    public String getTitle() {
        return Title;
    }
    public String getTeacher() {
        return Teacher;
    }
    public Date getStarting_Date() {
        return Starting_Date;
    }
    public int getTeacher_id() {
        return Teacher_id;
    }
    public String getConclusion() {
        return Conclusion;
    }
    public String getAbstract() {
        return Abstract;
    }
    public String getStatus() {
        return Status;
    }
    public Date getEnding_Date() {
        return Ending_Date;
    }
    public void setDomain(String domain) {
        Domain = domain;
    }
    public void setStarting_Date(Date starting_Date) {
        Starting_Date = starting_Date;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public void setRemarks(String remarks) {
        this.Remarks = remarks;
    }
    public void setResearch_Id(int research_Id) {
        Research_Id = research_Id;
    }
    public void setTeacher_id(int teacher_id) {
        Teacher_id = teacher_id;
    }
    public void setTeacher(String teacher) {
        Teacher = teacher;
    }
    public void setConclusion(String conclusion) {
        Conclusion = conclusion;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }
    public void setLink(String link) {
        Link = link;
    }
    public String getLink() {
        return Link;
    }
    public String getStudents() {
        return Students;
    }
    public void setEnding_Date(Date ending_Date) {
        Ending_Date = ending_Date;
    }

    private ArrayList < ClosedResearch > Closed = new ArrayList < ClosedResearch > ();

    // By SUNDAS NOREEN
    ClosedResearch() throws SQLException {}

    ClosedResearch(int Research_Id, String Status, String Title, String Teacher, int Teacher_id, String Domain, Date Starting_Date, Date Ending_Date, String Success_Level, String Abstract, String Conclusion, Blob Report) throws SQLException {
        this.Research_Id = Research_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Teacher_id = Teacher_id;
        this.Domain = Domain;
        this.Starting_Date = Starting_Date;
        this.Ending_Date = Ending_Date;
        this.Conclusion = Conclusion;
        this.Abstract = Abstract;
        this.Report = Report;
        this.Status = Status;
        this.Success_Level = Success_Level;

    }

    ClosedResearch(int Research_Id, String Status, String Title, String Teacher, int Teacher_id, String Students, String Domain, Date Starting_Date, Date Ending_Date, String Success_Level, String Abstract, String Conclusion, Blob Report, String Link, String Remarks) throws SQLException {
        this.Research_Id = Research_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Students = Students;
        this.Teacher_id = Teacher_id;
        this.Domain = Domain;
        this.Starting_Date = Starting_Date;
        this.Ending_Date = Ending_Date;
        this.Conclusion = Conclusion;
        this.Abstract = Abstract;
        this.Report = Report;
        this.Status = Status;
        this.Remarks = Remarks;
        this.Success_Level = Success_Level;
        this.Link = Link;
    }

    // By SUNDAS NOREEN
    public ArrayList < ClosedResearch > Student_List(String Reg_No) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt1 = con.createStatement();
            Closed.clear();
            String query = "SELECT * FROM `student_research_closed` WHERE  `Student_Id`='" + Reg_No + "'";
            rt = stmt1.executeQuery(query);
            while (rt.next()) {
                stmt2 = con.createStatement();
                int Value = rt.getInt(2);
                rs = stmt2.executeQuery("SELECT * FROM `closed_research` WHERE `Research_Id`='" + Value + "'ORDER BY `Ending_Date` DESC");
                while (rs.next()) {
                    Research_Id = rs.getInt(1);
                    Status = rs.getString(2);
                    Title = rs.getString(3);
                    Teacher_id = rs.getInt(4);
                    Domain = rs.getString(5);
                    Starting_Date = rs.getDate(6);
                    Ending_Date = rs.getDate(7);
                    Success_Level = rs.getString(8);
                    Abstract = rs.getString(9);
                    Conclusion = rs.getString(10);
                    Report = rs.getBlob(11);
                    stmt3 = con.createStatement();
                    ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                    while (ru.next()) {
                        if (ru.getInt(1) == Teacher_id) {
                            Teacher = ru.getString(3);
                        }
                    }
                    Closed.add(new ClosedResearch(Research_Id, Status, Title, Teacher, Teacher_id, Domain, Starting_Date, Ending_Date, Success_Level, Abstract, Conclusion, Report));
                }
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Closed;
    }

    // By SUNDAS NOREEN
    public ArrayList < ClosedResearch > Student_Individual(int Research_Id, String Reg_Number) throws SQLException {
        this.Research_Id = Research_Id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt1 = con.createStatement();
            Closed.clear();
            System.out.println(Research_Id);
            String query = "SELECT * FROM `student_research_closed` WHERE  `Research_Id`='" + Research_Id + "'";
            rt = stmt1.executeQuery(query);
            while (rt.next()) {
                String Reg_No = rt.getString(1);
                if (Reg_No.equals(Reg_Number)) {
                    Remarks = rt.getString(3);
                }
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='" + Reg_No + "'");
                while (ru.next()) {
                    String Name = ru.getString(2);
                    Students = Students + Reg_No + " (" + Name + ")" + "\n";
                }

            }
            System.out.println(Students);
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `closed_research` WHERE `Research_Id`='" + Research_Id + "'ORDER BY `Starting_Date` DESC");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Status = rs.getString(2);
                Title = rs.getString(3);
                Teacher_id = rs.getInt(4);
                Domain = rs.getString(5);
                Starting_Date = rs.getDate(6);
                Ending_Date = rs.getDate(7);
                Success_Level = rs.getString(8);
                Abstract = rs.getString(9);
                Conclusion = rs.getString(10);
                Report = rs.getBlob(11);
                Link = rs.getString(12);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Closed.add(new ClosedResearch(Research_Id, Status, Title, Teacher, Teacher_id, Students, Domain, Starting_Date, Ending_Date, Success_Level, Abstract, Conclusion, Report, Link, Remarks));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Closed;
    }

    // By HIRA ASLAM
    public ArrayList <ClosedResearch> Admin_List() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt1 = con.createStatement();
            Closed.clear();
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `closed_research`");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Status = rs.getString(2);
                Title = rs.getString(3);
                Teacher_id = rs.getInt(4);
                Domain = rs.getString(5);
                Starting_Date = rs.getDate(6);
                Ending_Date = rs.getDate(7);
                Success_Level = rs.getString(8);
                Abstract = rs.getString(9);
                Conclusion = rs.getString(10);
                Report = rs.getBlob(11);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                    Closed.add(new ClosedResearch(Research_Id, Status, Title, Teacher, Teacher_id, Domain, Starting_Date, Ending_Date, Success_Level, Abstract, Conclusion, Report));

            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Closed;
    }

    // By HIRA ASLAM
    public ArrayList < ClosedResearch > Admin_Individual(int Research_Id) throws SQLException {
        this.Research_Id = Research_Id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt1 = con.createStatement();
            Closed.clear();
            System.out.println(Research_Id);
            String query = "SELECT * FROM `student_research_closed` WHERE  `Research_Id`='" + Research_Id + "'";
            rt = stmt1.executeQuery(query);
            while (rt.next()) {
                String Reg_No = rt.getString(1);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='" + Reg_No + "'");
                while (ru.next()) {
                    String Name = ru.getString(2);
                    Students = Students + Reg_No + " (" + Name + ")" + "\n";
                }
            }
            System.out.println(Students);
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `closed_research` WHERE `Research_Id`='" + Research_Id + "'ORDER BY `Starting_Date` DESC");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Status = rs.getString(2);
                Title = rs.getString(3);
                Teacher_id = rs.getInt(4);
                Domain = rs.getString(5);
                Starting_Date = rs.getDate(6);
                Ending_Date = rs.getDate(7);
                Success_Level = rs.getString(8);
                Abstract = rs.getString(9);
                Conclusion = rs.getString(10);
                Report = rs.getBlob(11);
                Link = rs.getString(12);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Closed.add(new ClosedResearch(Research_Id, Status, Title, Teacher, Teacher_id, Students, Domain, Starting_Date, Ending_Date, Success_Level, Abstract, Conclusion, Report, Link, Remarks));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Closed;
    }

    // By LAIBA AASHIQ
    public ArrayList <ClosedResearch> Teacher_List(int Teacher_Id) throws SQLException {
        this.Teacher_id=Teacher_Id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt1 = con.createStatement();
            Closed.clear();
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `closed_research` WHERE  `Teacher_Id`='"+Teacher_id+"'");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Status = rs.getString(2);
                Title = rs.getString(3);
                Teacher_id = rs.getInt(4);
                Domain = rs.getString(5);
                Starting_Date = rs.getDate(6);
                Ending_Date = rs.getDate(7);
                Success_Level = rs.getString(8);
                Abstract = rs.getString(9);
                Conclusion = rs.getString(10);
                Report = rs.getBlob(11);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Closed.add(new ClosedResearch(Research_Id, Status, Title, Teacher, Teacher_id, Domain, Starting_Date, Ending_Date, Success_Level, Abstract, Conclusion, Report));

            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Closed;
    }

    // By LAIBA AASHIQ
    public boolean Edit_Research(int Research_Id, String Status, String Link, String Abstract, String Conclusion) throws SQLException {
       boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query="UPDATE `closed_research` SET `Status`=?,`Abstract`=?,`Conclusion`=?,`Link`=? WHERE  `Research_Id`='"+Research_Id+"'";
            PreparedStatement st2 = con.prepareStatement(query);
            st2.setString(1, Status);
            st2.setString(2, Abstract);
            st2.setString(3, Conclusion);
            st2.setString(4, Link);
            System.out.println("Data Updated");
            st2.executeUpdate();
            flag=true;
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return flag;
    }
}