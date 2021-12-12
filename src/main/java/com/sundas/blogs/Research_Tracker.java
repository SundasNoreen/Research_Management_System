package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;

public class Research_Tracker
{
    // By SUNDAS NOREEN
    private int Paper_Id;
    private String Student_Id;
    private String Title;
    private String Name;
    private String Author;
    private String Domain;
    private String Abstract;
    private String Notes;
    private String Conclusion;
    private Date Publishing;
    private Date Review;
    public Statement stmt,stmt2,stmt3;
    public ResultSet rs,rt,ru;
    String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
    public ArrayList<Research_Tracker> Tracks = new ArrayList<Research_Tracker>();

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setStudent_Id(String student_Id) {
        Student_Id = student_Id;
    }
    public String getStudent_Id() {
        return Student_Id;
    }
    public int getPaper_Id() {
        return Paper_Id;
    }
    public String getAbstract() {
        return Abstract;
    }
    public String getAuthor() {
        return Author;
    }
    public String getDomain() {
        return Domain;
    }
    public String getTitle() {
        return Title;
    }
    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }
    public String getNotes() {
        return Notes;
    }
    public String getConclusion() {
        return Conclusion;
    }
    public void setAuthor(String author) {
        Author = author;
    }
    public void setDomain(String domain) {
        Domain = domain;
    }
    public void setPaper_Id(int paper_Id) {
        Paper_Id = paper_Id;
    }
    public void setConclusion(String conclusion) {
        Conclusion = conclusion;
    }
    public void setNotes(String notes) {
        Notes = notes;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public Date getPublishing() {
        return Publishing;
    }
    public Date getReview() {
        return Review;
    }
    public void setPublishing(Date publishing) {
        Publishing = publishing;
    }
    public void setReview(Date review) {
        Review = review;
    }

    Research_Tracker() throws SQLException {}

    Research_Tracker(int Paper_Id,String Student_Id,String Name,String Title,String Author,String Domain,String Abstract,String Notes,String Conclusion,Date Publishing,Date Review) throws SQLException {
        this.Paper_Id=Paper_Id;
        this.Student_Id=Student_Id;
        this.Name=Name;
        this.Title=Title;
        this.Author=Author;
        this.Domain=Domain;
        this.Abstract=Abstract;
        this.Notes=Notes;
        this.Conclusion=Conclusion;
        this.Publishing=Publishing;
        this.Review=Review;
    }

    // By SUNDAS NOREEN
    public ArrayList<Research_Tracker> MyTrack(String Reg_No) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM `research_tracker` WHERE `Student_Id`='"+Reg_No+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Paper_Id=rs.getInt(1);
                Student_Id=rs.getString(2);
                Title=rs.getString(3);
                Author=rs.getString(4);
                Domain=rs.getString(5);
                Abstract=rs.getString(6);
                Notes=rs.getString(7);
                Conclusion=rs.getString(8);
                Publishing=rs.getDate(9);
                Review=rs.getDate(10);
                stmt2= con.createStatement();
                rt=stmt2.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='"+Reg_No+"'");
                while (rt.next())
                {
                    Name=null;
                    Name=Student_Id+" ("+rt.getString(2)+")";
                }
                Tracks.add(new Research_Tracker(Paper_Id,Student_Id,Name,Title,Author,Domain,Abstract,Notes,Conclusion,Publishing,Review));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return Tracks;
    }

    // By AQSA AYAZ
    public ArrayList<Research_Tracker> All_Track() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM `research_tracker`";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Paper_Id=rs.getInt(1);
                Student_Id=rs.getString(2);
                Title=rs.getString(3);
                Author=rs.getString(4);
                Domain=rs.getString(5);
                Abstract=rs.getString(6);
                Notes=rs.getString(7);
                Conclusion=rs.getString(8);
                Publishing=rs.getDate(9);
                Review=rs.getDate(10);
                stmt2= con.createStatement();
                rt=stmt2.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='"+Student_Id+"'");
                while (rt.next())
                {
                    Name=null;
                    Name=Student_Id+" ("+rt.getString(2)+")";
                }
                Tracks.add(new Research_Tracker(Paper_Id,Student_Id,Name,Title,Author,Domain,Abstract,Notes,Conclusion,Publishing,Review));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return Tracks;
    }

    // By SUNDAS NOREEN
    public ArrayList<Research_Tracker> Paper_Review(int Paper_Id) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM `research_tracker` WHERE `Paper_Id`='"+Paper_Id+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Student_Id=rs.getString(2);
                Title=rs.getString(3);
                Author=rs.getString(4);
                Domain=rs.getString(5);
                Abstract=rs.getString(6);
                Notes=rs.getString(7);
                Conclusion=rs.getString(8);
                Publishing=rs.getDate(9);
                Review=rs.getDate(10);
                stmt2= con.createStatement();
                rt=stmt2.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='"+Student_Id+"'");
                while (rt.next())
                {
                    Name=null;
                    Name=Student_Id+" ("+rt.getString(2)+")";

                }
                Tracks.add(new Research_Tracker(Paper_Id,Student_Id,Name,Title,Author,Domain,Abstract,Notes,Conclusion,Publishing,Review));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return Tracks;
    }

    // By SUNDAS NOREEN
    public boolean UpdatePaper(int Paper_Id,String Author,String Abstract,String Notes,String Conclusion) throws SQLException {
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `research_tracker` SET `Author`=?,`Abstract`=?,`Notes`=?,`Conclusion`=? WHERE `Paper_Id`=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,Author);
            stmt.setString(2,Abstract);
            stmt.setString(3,Notes);
            stmt.setString(4,Conclusion);
            stmt.setInt(5,Paper_Id);
            stmt.executeUpdate();
            System.out.println("Edited Successfully");
            flag = true;

        }
        catch (Exception e)
        {
            flag=false;
            System.out.println(e.getMessage());
        } finally
        {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean AddPaper(String Student_Id,String Title,String Author,String Domain,String Abstract,String Notes,String Conclusion,Date Publishing) throws SQLException {
        this.Student_Id=Student_Id;
        this.Title=Title;
        this.Author=Author;
        this.Domain=Domain;
        this.Abstract=Abstract;
        this.Notes=Notes;
        this.Conclusion=Conclusion;
        this.Publishing=Publishing;
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `research_tracker`(`Student_Id`, `Title`, `Author`, `Domain`, `Abstract`, `Notes`, `Conclusion`, `Date_of_Publishing`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,Student_Id);
            stmt.setString(2,Title);
            stmt.setString(3,Author);
            stmt.setString(4,Domain);
            stmt.setString(5,Abstract);
            stmt.setString(6,Notes);
            stmt.setString(7, Conclusion);
            stmt.setDate(8,Publishing);
            stmt.executeUpdate();
            System.out.println("Added Successfully");
            flag = true;

        }
        catch (Exception e)
        {
            flag=false;
            System.out.println(e.getMessage());
        } finally
        {
            con.close();
        }
        return flag;
    }
}
