package com.sundas.blogs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

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
    private String extra;
    private String New;
    String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
    Statement stmt;
    ResultSet rs;

    public Teacher() throws SQLException {
    }

    public Teacher(int Teacher_Id,String Name,String Department,String FatherName,String CNIC,Date DOB,String Weight_Qual,String Majors,String ContactNumber,String Email,String gender,String LoginId, String Password) throws SQLException
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
        this.LoginId=LoginId;
        this.Password=Password;
    }

    public String getExtra() {
        return extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setLoginId(String loginId) {
        this.LoginId = loginId;
    }
    public void setFatherName(String fatherName) {
        this.FatherName = fatherName;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    public void setGender(String gender){this.gender=gender;}
    public void setContactNumber(String contactNumber) {
        this.ContactNumber = contactNumber;
    }
    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
    public void setNew(String New){this.New=New;}
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
    public String getNew(){return New;}
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
        this.Majors = majors;
    }
    public void setDepartment(String department) {
        this.Department = department;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void setTeacher_Id(int teacher_Id) {
        this.Teacher_Id = teacher_Id;
    }
    public void setWeight_Qual(String weight_Qual) {
        this.Weight_Qual = weight_Qual;
    }

    // By AAIZA NAEEM
    public boolean Login(String Email, String Password) throws SQLException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM teachers ");
            while (rs.next())
            {
                String email = rs.getString(11);
                String PWS = rs.getString(12);
                if (email.equals(Email) && PWS.equals(Password))
                {
                    gender=rs.getString(13);
                    Name=rs.getString(3);
                    Teacher_Id=rs.getInt(1);
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

    // By AYESHA NADEEM
    public ArrayList<Teacher> GetTeachersList() throws SQLException
    {
        Teachers.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `teachers` WHERE `Active`=1");
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
                gender=rs.getString(13);
                LoginId=rs.getString(11);
                Password=rs.getString(12);
                Teachers.add(new Teacher(Teacher_Id,Name,Department,FatherName,CNIC,DOB,Weight_Qual,Majors,ContactNumber,Email,gender,LoginId,Password));
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

    // By AYESHA NADEEM
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
                gender=rs.getString(13);
                LoginId=rs.getString(11);
                Password=rs.getString(12);
                Teachers.add(new Teacher(Teacher_Id,Name,Department,FatherName,CNIC,DOB,Weight_Qual,Majors,ContactNumber,Email,gender,LoginId,Password));
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

    // By SUNDAS NOREEN
    public boolean Add_Teacher_Ind(String Name,String FatherName,String Department,String Majors,String Weight_Qual,String CNIC,String D,String ContactNumber,String Email,String gender,String LoginId,String Password) throws SQLException, ParseException {
        this.Name=Name;
        this.Department=Department;
        this.FatherName=FatherName;
        this.CNIC=CNIC;
        this.Weight_Qual=Weight_Qual;
        this.Majors=Majors;
        this.ContactNumber=ContactNumber;
        this.Email= Email;
        this.gender=gender;
        java.sql.Date date1= java.sql.Date.valueOf(D);
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `teachers`(`Department`, `Name`, `FatherName`, `CNIC`, `DOB`, `Weight_Qual`, `Majors`, `ContactNumber`, `Email`, `LoginId`, `Password`, `Gender`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Department);
            st.setString(2, Name);
            st.setString(3, FatherName);
            st.setString(4, CNIC);
            st.setDate(5, date1);
            st.setString(6, Weight_Qual);
            st.setString(7, Majors);
            st.setString(8, ContactNumber);
            st.setString(9, Email);
            st.setString(10, LoginId);
            st.setString(11, Password);
            st.setString(12, gender);
            st.executeUpdate();
            System.out.println("Added Successfully");
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public boolean Delete_Teacher(int Teacher_Id) throws SQLException
    {
        this.Teacher_Id=Teacher_Id;
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `teachers` SET `Active`=0 WHERE `Teacher_Id`='"+Teacher_Id+"'");
            flag=true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            con.close();
        }
        return flag;
    }

    // By AYESHA NADEEM
    public boolean Update_Teacher_Ind(int Teacher_Id,String Weight_Qual, String Majors,String ContactNumber,String Email,String Password) throws SQLException, ParseException {
        this.Teacher_Id=Teacher_Id;
        this.Weight_Qual=Weight_Qual;
        this.Majors=Majors;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `teachers` SET `Weight_Qual`=?,`Majors`=?,`ContactNumber`=?,`Email`=?,`Password`=? WHERE `Teacher_Id`='"+Teacher_Id+"'";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Weight_Qual);
            st.setString(2, Majors);
            st.setString(3, ContactNumber);
            st.setString(4, Email);
            st.setString(5, Password);
            st.executeUpdate();
            System.out.println("Updated Successfully");
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By SUNDAS NOREEN
    public ArrayList<Teacher> GetData(int Teacher_Id) throws SQLException
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
                gender=rs.getString(13);
                LoginId=rs.getString(11);
                Password=rs.getString(12);
                Teachers.add(new Teacher(Teacher_Id,Name,Department,FatherName,CNIC,DOB,Weight_Qual,Majors,ContactNumber,Email,gender,LoginId,Password));
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

    // By AAIZA NAEEM
    public boolean Change_Password(int Teacher_Id, String Password, String New) throws SQLException
    {
        this.Teacher_Id=Teacher_Id;
        this.New=New;
        this.Password=Password;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers WHERE `Teacher_Id`='"+Teacher_Id+"'");
            while (rs.next())
            {
                String PWS = rs.getString(12);
                if (PWS.equals(Password))
                {
                    PreparedStatement stmt2=con.prepareStatement("UPDATE `teachers` SET `Password`=? WHERE `Teacher_Id`=?");
                    stmt2.setString(1,New);
                    stmt2.setInt(2,Teacher_Id);
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


