
package com.sundas.blogs;

import java.sql.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;

public class Student
{
    // By AQSA AYAZ
    private String Reg_No;
    private String Name;
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
    private String New;
    private String gender;

    public void setNew(String New){this.New=New;}
    public void setGender(String gender){this.gender=gender;}
    public void setName(String Name) {Name=Name;}
    public void setField(String field) {
        Field = field;
    }
    public void setDegree(String degree) {
        Degree = degree;
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
    public String getGender(){return gender;}
    public String getNew(){return New;}
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
    public String getName(){return Name;}
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

    public ArrayList<Student> Students = new ArrayList<Student>();
    // By SUNDAS NOREEN
    Student(String Reg_No,String Name,String Degree,String Field,String FatherName,String CNIC,Date DOB,String ContactNumber,String Email, String gender)
    {
        this.Reg_No=Reg_No;
        this.Name=Name;
        this.Email=Email;
        this.Degree=Degree;
        this.FatherName=FatherName;
        this.Field=Field;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.gender=gender;
    }

    Student(String reg_No, String degree, String aClass, String field, String fatherName, String CNIC, String DOB, String contactNumber, String email, String loginId, String password){}
    public boolean Login(String Email, String Password) throws SQLException
    {
        this.Email=Email;
        this.Password=Password;
        Connection con=null;
            try
            {
                String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students ");
                while (rs.next())
                {
                    String email = rs.getString(11);
                    String PWS = rs.getString(12);
                    if (email.equals(Email) && PWS.equals(Password))
                    {
                        Name=rs.getString(2);
                        Reg_No=rs.getString(1);
                        gender=rs.getString(13);
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

    public ArrayList<Student> GetData(String Reg_No) throws SQLException
    {
        this.Reg_No=Reg_No;
        Connection con=null;
        Students.clear();
        try
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='"+Reg_No+"'");
            while (rs.next())
            {
                Name=rs.getString(2);
                Degree=rs.getString(3);
                Field=rs.getString(5);
                FatherName=rs.getString(6);
                CNIC=rs.getString(7);
                DOB=rs.getDate(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                gender=rs.getString(13);
                Students.add(new Student(Reg_No,Name,Degree,Field,FatherName,CNIC,DOB,ContactNumber,Email,gender));
                }
            }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            con.close();
        }
        return Students;
    }

    public boolean Change_Password(String Reg_No, String Password, String New) throws SQLException
    {
        this.Reg_No=Reg_No;
        this.New=New;
        this.Password=Password;
        Connection con=null;
        try
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students ");
            while (rs.next())
            {
                String PWS = rs.getString(12);
                if (PWS.equals(Password))
                {
                    PreparedStatement stmt2=con.prepareStatement("UPDATE `students` SET `Password`=? WHERE `Reg_No`=?");
                    stmt2.setString(1,New);
                    stmt2.setString(2,Reg_No);
                    stmt2.executeUpdate();
                    return true;
                }
                else{
                    return false;
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


