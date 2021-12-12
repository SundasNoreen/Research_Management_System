
package com.sundas.blogs;

import java.sql.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
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
    public void setName(String Name) {this.Name=Name;}
    public void setField(String field) {
        this.Field = field;
    }
    public void setDegree(String degree) {
        this.Degree = degree;
    }
    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }
    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
    public void setContactNumber(String contactNumber) {
        this.ContactNumber = contactNumber;
    }
    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setFatherName(String fatherName) {
        this.FatherName = fatherName;
    }
    public void setLoginId(String loginId) {
        this.LoginId = loginId;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public void setReg_No(String reg_No) {
        this.Reg_No = reg_No;
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
    Student(String Reg_No,String Name,String Degree,String Field,String FatherName,String CNIC,Date DOB,String ContactNumber,String Email, String gender) throws SQLException {
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
    Student(String Reg_No,String Name,String Degree,String ClassName,String Field,String FatherName,String CNIC,Date DOB,String ContactNumber,String Email, String gender) throws SQLException {
        this.Reg_No=Reg_No;
        this.Name=Name;
        this.Email=Email;
        this.ClassName=ClassName;
        this.Degree=Degree;
        this.FatherName=FatherName;
        this.Field=Field;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.gender=gender;
    }

    Student(String Reg_No,String Name,String Degree,String ClassName,String Field,String FatherName,String CNIC,Date DOB,String New,String ContactNumber,String Email, String gender,String LoginId,String Password) throws SQLException {
        this.Reg_No=Reg_No;
        this.Name=Name;
        this.Email=Email;
        this.ClassName=ClassName;
        this.Degree=Degree;
        this.DOB=DOB;
        this.FatherName=FatherName;
        this.Field=Field;
        this.CNIC=CNIC;
        this.New=New;
        this.LoginId=LoginId;
        this.Password=Password;
        this.ContactNumber=ContactNumber;
        this.gender=gender;
    }

    Student() throws SQLException {}

    // By SUNDAS NOREEN
    public boolean Login(String Email, String Password) throws SQLException
    {
        this.Email=Email;
        this.Password=Password;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
            try
            {
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

    // By AQSA AYAZ
    public ArrayList<Student> GetData(String Reg_No) throws SQLException
    {
        this.Reg_No=Reg_No;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        Students.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='"+Reg_No+"'");
            while (rs.next())
            {
                Name=rs.getString(2);
                Degree=rs.getString(3);
                Field=rs.getString(5);
                ClassName=rs.getString(4);
                FatherName=rs.getString(6);
                CNIC=rs.getString(7);
                DOB=rs.getDate(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                gender=rs.getString(13);
                Students.add(new Student(Reg_No,Name,Degree,ClassName,Field,FatherName,CNIC,DOB,ContactNumber,Email,gender));
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

    // By SUNDAS NOREEN
    public boolean Change_Password(String Reg_No, String Password, String New) throws SQLException
    {
        this.Reg_No=Reg_No;
        this.New=New;
        this.Password=Password;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students  WHERE `Reg_No`='"+Reg_No+"'");
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

    // By AQSA AYAZ
    public ArrayList<Student> GetStudentsList() throws SQLException
    {
        Students.clear();
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `students` WHERE `Active`=1");
            while (rs.next())
            {
                Name=rs.getString(2);
                Reg_No=rs.getString(1);
                Degree=rs.getString(3);
                Field=rs.getString(5);
                ClassName=rs.getString(4);
                FatherName=rs.getString(6);
                CNIC=rs.getString(7);
                DOB=rs.getDate(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                gender=rs.getString(13);
                Students.add(new Student(Reg_No,Name,Degree,ClassName,Field,FatherName,CNIC,DOB,ContactNumber,Email,gender));
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

    // By AQSA AYAZ
    public ArrayList<Student> Student_Details(String Reg_No) throws SQLException
    {
        this.Reg_No=Reg_No;
        Students.clear();
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `students` WHERE `Reg_No`='"+Reg_No+"'");
            while (rs.next())
            {
                Name=rs.getString(2);
                Degree=rs.getString(3);
                Field=rs.getString(5);
                ClassName=rs.getString(4);
                FatherName=rs.getString(6);
                CNIC=rs.getString(7);
                DOB=rs.getDate(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                gender=rs.getString(13);
                LoginId=rs.getString(11);
                Password=rs.getString(12);
                Students.add(new Student(Reg_No,Name,Degree,ClassName,Field,FatherName,CNIC,DOB,"",ContactNumber,Email,gender,LoginId,Password));
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

    // By AQSA AYAZ
    public boolean Add_Student_Ind(String Reg_No,String Name,String Degree,String ClassName,String Field,String FatherName,String CNIC,String D,String ContactNumber,String Email, String gender,String LoginId,String Password) throws SQLException, ParseException {
            this.ClassName=ClassName;
            this.Reg_No=Reg_No;
            this.Name=Name;
            this.ContactNumber=ContactNumber;
            this.Degree=Degree;
            this.Field=Field;
            this.Email=Email;
            this.LoginId=LoginId;
            this.Password=Password;
            this.FatherName=FatherName;
            Date date1=Date.valueOf(D);
            this.gender=gender;
            this.CNIC=CNIC;
            boolean flag=false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String query = "INSERT INTO `students`(`Reg_No`, `Name`, `Degree`, `Class`, `Field`, `FatherName`, `CNIC`, `DOB`, `ContactNumber`, `Email`, `LoginId`, `Password`, `Gender`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1, Reg_No);
                st.setString(2, Name);
                st.setString(3, Degree);
                st.setString(4, ClassName);
                st.setString(5, Field);
                st.setString(6, FatherName);
                st.setString(7, CNIC);
                st.setDate(8, date1);
                st.setString(9, ContactNumber);
                st.setString(10, Email);
                st.setString(11, LoginId);
                st.setString(12, Password);
                st.setString(13, gender);
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
    public boolean Update_Student_Ind(String Reg_No,String Degree,String Field,String ContactNumber,String Email,String Password) throws SQLException, ParseException {
        this.Reg_No=Reg_No;
        this.ContactNumber=ContactNumber;
        this.Degree=Degree;
        this.Field=Field;
        this.Email=Email;
        this.Password=Password;
        boolean flag=false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `students` SET `Degree`=?,`Field`=?,`ContactNumber`=?,`Email`=?,`Password`=? WHERE `Reg_No`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Degree);
            st.setString(2, Field);
            st.setString(3, ContactNumber);
            st.setString(4, Email);
            st.setString(5, Password);
            st.setString(6, Reg_No);
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
    public boolean Delete_Student(String Reg_No) throws SQLException
    {
        this.Reg_No=Reg_No;
        boolean flag=false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `students` SET `Active`=0 WHERE `Reg_No`='"+Reg_No+"'");
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
}


