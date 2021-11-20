package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;

public class Teacher
{
    // By AQSA AYAZ
    private int Teacher_Id;
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
    private String gender;
    String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    Connection con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
    Statement stmt;
    ResultSet rs;

    public Teacher() throws SQLException {
    }

    public Teacher(int Teacher_Id,String Name,String Department,String FatherName,String CNIC,Date DOB,String Weight_Qual,String Majors,String ContactNumber,String Email,String gender) throws SQLException
    {
        this.Teacher_Id=Teacher_Id;
        this.Name=Name;
        this.Department=Department;
        this.FatherName=FatherName;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.Weight_Qual=Weight_Qual;
        this.Majors=Majors;
        this.ContactNumber=ContactNumber;
        this.Email= Email;
        this.gender=gender;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public String getGender() {
        return gender;
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
    public void setGender(String gender){this.gender=gender;}
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
    public int getTeacher_Id() {
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
    public void setTeacher_Id(int teacher_Id) {
        Teacher_Id = teacher_Id;
    }
    public void setWeight_Qual(String weight_Qual) {
        Weight_Qual = weight_Qual;
    }

    // By SUNDAS NOREEN
    public boolean Login(String Email, String Password) throws SQLException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM teachers ");
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

    ArrayList<Teacher> Teachers = new ArrayList<Teacher>();

    public ArrayList<Teacher> GetTeachersList() throws SQLException
    {
        Teachers.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `teachers` WHERE `Active`= 1");
            while (rs.next())
            {
                Teacher_Id=rs.getInt(1);
                Department=rs.getString(2);
                Name=rs.getString(3);
                FatherName=rs.getString(4);
                CNIC=rs.getString(5);
                DOB=rs.getDate(6);
                Weight_Qual=rs.getString(7);
                Majors=rs.getString(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                gender=rs.getString(14);
                System.out.println(Teacher_Id+Department+Name);
                Teachers.add(new Teacher(Teacher_Id,Name,Department,FatherName,CNIC,DOB,Weight_Qual,Majors,ContactNumber,Email,gender));
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
        return Teachers;
    }
    public ArrayList<Teacher> Teacher_Details(int Teacher_Id) throws SQLException
    {
        this.Teacher_Id=Teacher_Id;
        Teachers.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `teachers` WHERE `Teacher_Id`='"+Teacher_Id+"'");
            while (rs.next())
            {
                Department=rs.getString(2);
                Name=rs.getString(3);
                FatherName=rs.getString(4);
                CNIC=rs.getString(5);
                DOB=rs.getDate(6);
                Weight_Qual=rs.getString(7);
                Majors=rs.getString(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                gender=rs.getString(14);
                Teachers.add(new Teacher(Teacher_Id,Name,Department,FatherName,CNIC,DOB,Weight_Qual,Majors,ContactNumber,Email,gender));
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
        return Teachers;
    }

}


