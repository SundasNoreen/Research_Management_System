package com.sundas.blogs;
// AQSA AYAZ

import java.sql.*;
import java.util.Date;

public class Teacher extends Person
{
    private String Teacher_Id;
    private String Department;
    private String Name;
    private String FatherName;
    private String CNIC;
    private Date DOB;
    private String Weight_Qual;
    private String Majors;
    private String ContactNumber;
    private String Email;
    private String LoginId;
    private String Password;

    public void setPassword(String password) {
        Password = password;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getPassword() {
        return Password;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getEmail() {
        return Email;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getDepartment() {
        return Department;
    }

    public String getMajors() {
        return Majors;
    }

    public String getName() {
        return Name;
    }

    public String getTeacher_Id() {
        return Teacher_Id;
    }

    public String getWeight_Qual() {
        return Weight_Qual;
    }

    public void setMajors(String majors) {
        Majors = majors;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTeacher_Id(String teacher_Id) {
        Teacher_Id = teacher_Id;
    }

    public void setWeight_Qual(String weight_Qual) {
        Weight_Qual = weight_Qual;
    }

    public boolean Login(String Email, String Password) throws SQLException
    {
        Connection con=null;
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers ");
            while (rs.next())
            {
                String email = rs.getString(10);
                String PWS = rs.getString(12);
                if (email.equals(Email) && PWS.equals(Password))
                {
                    return true;
                }
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        finally
        {
            con.close();
        }
        return false;
    }
}


