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
    public String Temp;
    String url = "jdbc:mysql://localhost/rms";
    public Connection con = DriverManager.getConnection(url, "root", "");
    public String Students = "";
    Statement stmt1, stmt2, stmt3, stmt4, stmt5;
    ResultSet rs, rt, ru;
    public String Teacher;

    public String getTitle() {
        return Title;
    }
    public int getTeacher_id() {
        return Teacher_id;
    }
    public String getDomain() {
        return Domain;
    }
    public Date getStarting_Date() {
        return Starting_Date;
    }
    public int getResearch_Id() {
        return Research_Id;
    }
    public String getAbout() {
        return About;
    }
    public String getTemp() {
        return Temp;
    }
    public String getStudents() {
        return Students;
    }
    public String getTeacher() {
        return Teacher;
    }
    public void setTeacher_id(int teacher_id) {
        Teacher_id = teacher_id;
    }
    public void setResearch_Id(int research_Id) {
        Research_Id = research_Id;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public void setStarting_Date(Date starting_Date) {
        Starting_Date = starting_Date;
    }
    public void setTeacher(String teacher) {
        Teacher = teacher;
    }
    public void setDomain(String domain) {
        Domain = domain;
    }
    public void setAbout(String about) {
        About = about;
    }
    public void setTemp(String temp) {
        Temp = temp;
    }

    private ArrayList<OpenResearch> Open = new ArrayList<OpenResearch>();

    OpenResearch() throws SQLException {
    }

    OpenResearch(int Research_Id, String Teacher, int Teacher_id, String Title, String Domain, Date Starting_Date, String About) throws SQLException {
        this.Research_Id = Research_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Teacher_id = Teacher_id;
        this.Domain = Domain;
        this.Starting_Date = Starting_Date;
        this.About = About;
    }

    OpenResearch(int Research_Id, String Teacher, int Teacher_id, String Students, String Title, String Domain, Date Starting_Date, String About) throws SQLException {
        this.Research_Id = Research_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Students = Students;
        this.Teacher_id = Teacher_id;
        this.Domain = Domain;
        this.Starting_Date = Starting_Date;
        this.About = About;
    }

    // By SUNDAS NOREEN
    public ArrayList<OpenResearch> Student_List(String Reg_No) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Open.clear();
            System.out.println(Reg_No);
            stmt1 = con.createStatement();
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
                    stmt3 = con.createStatement();
                    ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                    while (ru.next()) {
                        if (ru.getInt(1) == Teacher_id) {
                            Teacher = ru.getString(3);
                        }
                    }
                    Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Title, Domain, Starting_Date, About));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }

    // By SUNDAS NOREEN
    public ArrayList<OpenResearch> Student_Individual(int Research_Id) throws SQLException {
        try {
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
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Students, Title, Domain, Starting_Date, About));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }

    // By HIRA ASLAM
    public ArrayList<OpenResearch> Admin_List() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Open.clear();
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `open_research`");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_id = rs.getInt(3);
                Domain = rs.getString(4);
                Starting_Date = rs.getDate(5);
                About = rs.getString(6);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Title, Domain, Starting_Date, About));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }

    // By HIRA ASLAM
    public ArrayList<OpenResearch> Admin_Individual(int Research_Id) throws SQLException {
        try {
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
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Students, Title, Domain, Starting_Date, About));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }

    // By AAIZA NAEEM
    public ArrayList<OpenResearch> Teacher_List(int Teacher_Id) throws SQLException {
        this.Teacher_id = Teacher_Id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Open.clear();
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `open_research` WHERE  `Teacher_Id`='" + Teacher_id + "'");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_id = rs.getInt(3);
                Domain = rs.getString(4);
                Starting_Date = rs.getDate(5);
                About = rs.getString(6);
                stmt3 = con.createStatement();
                ru = stmt3.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_id) {
                        Teacher = ru.getString(3);
                    }
                }
                Open.add(new OpenResearch(Research_Id, Teacher, Teacher_id, Title, Domain, Starting_Date, About));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Open;
    }

    // By SUNDAS NOREEN
    public boolean Mark_Closed(int id, String Status, String Ending, String Report, String Abstract, String Conclusion) throws SQLException {
        this.Research_Id = id;
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT * FROM `open_research` WHERE  `Research_Id`='" + Research_Id + "'");
            while (rs.next()) {
                Research_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_id = rs.getInt(3);
                Domain = rs.getString(4);
                Starting_Date = rs.getDate(5);
                About = rs.getString(6);
                System.out.println("Data Fetched");
            }
            java.sql.Date date2 = java.sql.Date.valueOf(Ending);
            MarkClosed MyObj = new MarkClosed();
            if(MyObj.Mark_Closed(Research_Id,Title,Teacher_id,Domain,Starting_Date,Status,date2,Abstract,Conclusion,Report))
            {
                flag=true;
            }
        } catch (Exception ex) {
            flag = false;
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By LAIBA AASHIQ
    public boolean Add_Research(String Title,int Teacher_Id,String Domain,String Starting_Date, String About) throws SQLException {
        boolean flag=false;
        try{
        String query2 = "INSERT INTO `open_research`(`Title`, `Teacher_Id`, `Domain`, `Starting_Date`, `About`) VALUES (?,?,?,?,?)";
        PreparedStatement st2 = con.prepareStatement(query2);
        st2.setString(1, Title);
        st2.setInt(2, Teacher_Id);
        st2.setString(3, Domain);
        java.sql.Date date2 = java.sql.Date.valueOf(Starting_Date);
        st2.setDate(4, date2);
        st2.setString(5, About);
        System.out.println("Data Inserted");
        st2.executeUpdate();
        flag = true;}
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean Edit_Research(int Research_Id,String Domain,String Starting_Date, String About) throws SQLException {
        boolean flag=false;
        try{
            String query2 = "UPDATE `open_research` SET `Domain`=?,`Starting_Date`=?,`About`=? WHERE  `Research_Id`='" + Research_Id + "'";
            PreparedStatement st2 = con.prepareStatement(query2);
            st2.setString(1, Domain);
            java.sql.Date date2 = java.sql.Date.valueOf(Starting_Date);
            st2.setDate(2, date2);
            st2.setString(3, About);
            System.out.println("Data Updated");
            st2.executeUpdate();
            flag = true;}
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean Edit_Research(int Research_Id,String Domain, String About) throws SQLException {
        boolean flag=false;
        try{
            String query2 = "UPDATE `open_research` SET `Domain`=?,`About`=? WHERE  `Research_Id`='" + Research_Id + "'";
            PreparedStatement st2 = con.prepareStatement(query2);
            st2.setString(1, Domain);
            st2.setString(2, About);
            System.out.println("Data Updated");
            st2.executeUpdate();
            flag = true;}
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            con.close();
        }
        return flag;
    }

}
