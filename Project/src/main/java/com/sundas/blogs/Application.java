package com.sundas.blogs;

import java.sql.*;

public class Application {
    private int Opportunity_Id;
    private int Student_Id;
    private String CGPA;
    private String Degree;
    private String Field;
    private String Reason;
    private String Status;
    private Connection con;
    private Statement stmt;
    private static ResultSet rs;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getOpportunity_Id() {
        return Opportunity_Id;
    }

    public void setOpportnity_Id(int opportunity_Id) {
        Opportunity_Id = opportunity_Id;
    }

    public String getCGPA() {
        return CGPA;
    }

    public String getReason() {
        return Reason;
    }

    public int getStudent_Id() {
        return Student_Id;
    }

    public void setStudent_Id(int student_Id) {
        Student_Id = student_Id;
    }

    public String getDegree() {
        return Degree;
    }

    public String getField() {
        return Field;
    }

    public void setCGPA(String CGPA) {
        this.CGPA = CGPA;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public void setField(String field) {
        Field = field;
    }
    public void Apply(int Opportunity_Id,int Student_Id,String CGPA,String Degree,String Field,String Reason,String Status) throws SQLException
    {
        Connection con=null;
        this.Opportunity_Id=Opportunity_Id;
        this.CGPA=CGPA;
        this.Degree=Degree;
        this.Field=Field;
        this.Student_Id=Student_Id;
        this.Status=Status;
        this.Reason=Reason;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO  application(Opportunity_Id,Student_Id,CGPA,Degree,Field,Reason,Status) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1,Opportunity_Id);
            st.setInt(2,Student_Id);
            st.setString(3,CGPA);
            st.setString(4, Degree);
            st.setString(5,Field);
            st.setString(6,Reason);
            st.setString(7,Status);
            st.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            con.close();
        }
    }
}
