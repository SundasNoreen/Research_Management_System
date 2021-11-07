// AQSA AYAZ(2019-CE-7)

package com.sundas.blogs;
import org.unbescape.css.CssIdentifierEscapeType;

import java.sql.*;
import java.sql.SQLException;
import java.util.Date;

public class Student extends Person
{
    private String Reg_No;
    private String Degree;
    private String ClassName;
    private String Field;
    private String FatherName;
    private String CNIC;
    private Date DOB;
    private String ContactNumber;
    private String Email;
    private String LoginId;
    private String Password;

    public void setField(String field) {
        Field = field;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getField() {
        return Field;
    }

    public String getDegree() {
        return Degree;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getEmail() {
        return Email;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getPassword() {
        return Password;
    }

    public String getReg_No() {
        return Reg_No;
    }

    public void setClass(String ClassName) {
        ClassName = ClassName;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setReg_No(String reg_No) {
        Reg_No = reg_No;
    }



    public boolean Login(String Email, String Password) throws SQLException
    {
        Connection con=null;
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students ");
                while (rs.next())
                {
                    String email = rs.getString(9);
                    String PWS = rs.getString(11);
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


