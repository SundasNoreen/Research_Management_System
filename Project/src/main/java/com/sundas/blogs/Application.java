package com.sundas.blogs;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Application {
    // By SUNDAS NOREEN
    public int Application_Id;
    public int Opportunity_Id;
    public String Student_Id;
    public String CGPA;
    public String Degree;
    public String Field;
    public String Reason;
    public String Semester;
    public String Status;
    public Statement stmt, stmt2, stmt3, stmt4;
    public String Name;
    public Date Submission;
    public String Teacher_Name;
    public int Teacher_Id;
    public String Title;
    public static ResultSet rs, ru, rt, rv;
    String url ="jdbc:mysql://localhost/rms";
    public Connection con =  DriverManager.getConnection(url, "root", "");
    public void setApplication_Id(int application_Id) {
        Application_Id = application_Id;
    }
    public int getApplication_Id() {
        return Application_Id;
    }
    public int getOpportunity_Id() {
        return Opportunity_Id;
    }
    public void setOpportunity_Id(int opportunity_Id) {
        Opportunity_Id = opportunity_Id;
    }
    public String getStudent_Id() {
        return Student_Id;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public String getStatus() {
        return Status;
    }
    public void setField(String field) {
        Field = field;
    }
    public void setReason(String reason) {
        Reason = reason;
    }
    public void setDegree(String degree) {
        Degree = degree;
    }
    public void setStudent_Id(String student_Id) {
        Student_Id = student_Id;
    }
    public void setCGPA(String CGPA) {
        this.CGPA = CGPA;
    }
    public String getField() {
        return Field;
    }
    public String getDegree() {
        return Degree;
    }
    public String getReason() {
        return Reason;
    }
    public String getCGPA() {
        return CGPA;
    }
    public String getSemester() {
        return Semester;
    }
    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public ArrayList < Application > App = new ArrayList < Application > ();

    Application() throws SQLException {

    }

    Application(int Application_Id, String Title, int Opportunity_Id, int Teacher_Id, String Teacher_Name, Date Submission, String Student_Id, String Name, String CGPA, String Degree, String Field, String Reason, String Status, String Semester) throws SQLException {
        this.Application_Id = Application_Id;
        this.Title = Title;
        this.Opportunity_Id = Opportunity_Id;
        this.CGPA = CGPA;
        this.Degree = Degree;
        this.Field = Field;
        this.Name = Name;
        this.Student_Id = Student_Id;
        this.Semester = Semester;
        this.Status = Status;
        this.Reason = Reason;
        this.Submission = Submission;
        this.Teacher_Name = Teacher_Name;
        this.Teacher_Id = Teacher_Id;
    }

    Application(String Student_Id, String Name, int Opportunity_Id, String Title, int Teacher_Id, String Teacher_Name) throws SQLException {
        this.Student_Id = Student_Id;
        this.Name = Name;
        this.Title = Title;
        this.Opportunity_Id = Opportunity_Id;
        this.Teacher_Id = Teacher_Id;
        this.Teacher_Name = Teacher_Name;
        this.Student_Id = Student_Id;
    }

    // By SUNDAS NOREEN
    public ArrayList < Application > SetData(int Opportunity_Id, String Student_Id) throws SQLException, ClassNotFoundException {
 Class.forName("com.mysql.cj.jdbc.Driver");
        stmt = con.createStatement();
        App.clear();
        this.Student_Id = Student_Id;
        this.Opportunity_Id = Opportunity_Id;
        try {
            String query = "SELECT * FROM `research_opportunities` WHERE  `Application_Id`= '" + Opportunity_Id + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher_Name = ru.getString(3);
                    }
                }
            }
            stmt3 = con.createStatement();
            rt = stmt3.executeQuery("SELECT * FROM `students` WHERE  `Reg_No`= '" + Student_Id + "'");
            System.out.println(Student_Id);
            while (rt.next()) {
                Name = rt.getString(2);
                App.add(new Application(Student_Id, Name, Opportunity_Id, Title, Teacher_Id, Teacher_Name));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load.");
        } finally {
            con.close();
        }
        return App;
    }

    // By SUNDAS NOREEN
    public boolean Apply(int Opportunity_Id, String Student_Id, String CGPA, String Degree, String Field, String Reason, String Status, String Semester) throws SQLException {
 boolean flag = false;
        this.Opportunity_Id = Opportunity_Id;
        this.CGPA = CGPA;
        this.Degree = Degree;
        this.Field = Field;
        this.Student_Id = Student_Id;
        this.Semester = Semester;
        this.Status = Status;
        this.Reason = Reason;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `application`(`Opportunity_Id`, `Student_Id`, `CGPA`, `Degree`, `Field`, `Reason`, `Semester`, `Status`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            System.out.println(Student_Id);
            st.setInt(1, Opportunity_Id);
            st.setString(2, Student_Id);
            st.setString(3, CGPA);
            st.setString(4, Degree);
            st.setString(5, Field);
            st.setString(6, Reason);
            st.setString(7, Semester);
            st.setString(8, Status);
            st.executeUpdate();
            System.out.println("Applied Successfully");
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean Edit(int Application_Id, String Student_Id, String CGPA, String Degree, String Field, String Reason, String Status, String Semester) throws SQLException {
  boolean flag = false;
        this.Application_Id = Application_Id;
        this.CGPA = CGPA;
        this.Degree = Degree;
        this.Field = Field;
        this.Student_Id = Student_Id;
        this.Semester = Semester;
        this.Status = Status;
        this.Reason = Reason;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `application` SET `CGPA`=?,`Degree`=?,`Field`=?,`Reason`=?,`Semester`=? WHERE `Application_Id`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, CGPA);
            st.setString(2, Degree);
            st.setString(3, Field);
            st.setString(4, Reason);
            st.setString(5, Semester);
            st.setInt(6, Application_Id);
            st.executeUpdate();
            System.out.println("Edited Successfully");
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean Check(int Opportunity_Id, String Student_Id) throws SQLException {
        boolean flag = false;
        this.Opportunity_Id = Opportunity_Id;
        this.Student_Id = Student_Id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM `application`";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt(2) == Opportunity_Id && rs.getString(3).equals(Student_Id)) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean CheckStatus(int Application_Id) throws SQLException {
     boolean flag = false;
        this.Application_Id = Application_Id;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "SELECT * FROM `application` WHERE `Application_Id`='" + Application_Id + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(9).equals("Submitted")) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public ArrayList < Application > View_Applications(int id) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `application` WHERE `Application_Id`='" + id + "'");
            App.clear();
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Opportunity_Id = rs.getInt(2);
                Student_Id = rs.getString(3);
                CGPA = rs.getString(4);
                Degree = rs.getString(5);
                Field = rs.getString(6);
                Reason = rs.getString(7);
                Semester = rs.getString(8);
                Status = rs.getString(9);
                Submission = rs.getDate(10);
                stmt2 = con.createStatement();
                rt = stmt2.executeQuery("SELECT * FROM `research_opportunities` WHERE `Application_id`='" + Opportunity_Id + "'");
                while (rt.next()) {
                    Title = rt.getString(2);
                    Teacher_Id = rt.getInt(3);
                    stmt3 = con.createStatement();
                    ru = stmt3.executeQuery("SELECT * FROM `teachers` WHERE `Teacher_Id`='" + Teacher_Id + "'");
                    while (ru.next()) {
                        Teacher_Name = ru.getString(3);
                    }
                    stmt4 = con.createStatement();
                    rv = stmt4.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='" + Student_Id + "'");
                    while (rv.next()) {
                        Name = rv.getString(2);
                    }
                }
                App.add(new Application(Application_Id, Title, Opportunity_Id, Teacher_Id, Teacher_Name, Submission, Student_Id, Name, CGPA, Degree, Field, Reason, Status, Semester));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return App;
    }

    // By SUNDAS NOREEN
    public ArrayList < Application > View_Applications(String id) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `application` WHERE `Student_Id`='" + id + "'");
            App.clear();
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Opportunity_Id = rs.getInt(2);
                Student_Id = rs.getString(3);
                CGPA = rs.getString(4);
                Degree = rs.getString(5);
                Field = rs.getString(6);
                Reason = rs.getString(7);
                Semester = rs.getString(8);
                Status = rs.getString(9);
                Submission = rs.getDate(10);
                stmt2 = con.createStatement();
                rt = stmt2.executeQuery("SELECT * FROM `research_opportunities` WHERE `Application_id`='" + Opportunity_Id + "'");
                while (rt.next()) {
                    Title = rt.getString(2);
                    Teacher_Id = rt.getInt(3);
                    stmt3 = con.createStatement();
                    ru = stmt3.executeQuery("SELECT * FROM `teachers` WHERE `Teacher_Id`='" + Teacher_Id + "'");
                    while (ru.next()) {
                        Teacher_Name = ru.getString(3);
                    }
                    stmt4 = con.createStatement();
                    rv = stmt4.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='" + Student_Id + "'");
                    while (rv.next()) {
                        Name = rv.getString(2);
                    }
                }
                App.add(new Application(Application_Id, Title, Opportunity_Id, Teacher_Id, Teacher_Name, Submission, Student_Id, Name, CGPA, Degree, Field, Reason, Status, Semester));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return App;
    }
}