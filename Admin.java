package com.sundas.blogs;


import java.sql.*;

//AQSA AYAZ


public class Admin extends Person
{
    Admin()
    {

    }
    Admin(String LoginId, String Name, String Email, String Password,int Admin_Id,String Address,String ContactNumber,String CNIC,String Role)
    {
        this.LoginId = LoginId;
        this.Name = Name;
        this.Email = Email;
        this.PassWord = Password;
        this.Admin_Id=Admin_Id;
        this.Role=Role;
        this.ContactNumber=ContactNumber;
        this.Address=Address;
        this.CNIC=CNIC;
    }
    
    public ArrayList view () throws SQLException
    {
        ArrayList<Student> Students = new ArrayList<Student>();
        try {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students ");
            while (rs.next()) {
                Reg_No = rs.getString(1);
                Degree = rs.getString(2);
                Class=rs.getString(3);
                Field=rs.getString(4);
                FatherName=rs.getString(5);
                CNIC=rs.getString(6);
                DOB=rs.getString(7);
                ContactNumber=rs.getString(8);
                Email=rs.getString(9);
                LoginId=rs.getString(10);
                Password=rs.getString(11);
                Students.add(new Student(Reg_No, Degree, Class, Field, FatherName,CNIC, DOB, ContactNumber, Email, LoginId ,Password));
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            con.close();
        }
        return Students;
    }
    //Aqsa Ayaz

    public boolean Add_Students((Reg_No, Degree, Class, Field, FatherName,CNIC, DOB, ContactNumber, Email, LoginId ,Password)) throws SQLException
    {
        Connection con=null;
        this.Degree=Degree;
        this.Reg_No=Reg_No;
        this.Class=Class;
        this.FatherName=FatherName;
        this.Field=Field;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        this.LoginId=LoginId;
        try
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO  students(Reg_No, Degree, Class, Field, FatherName,CNIC, DOB, ContactNumber, Email, LoginId ,Password) VALUES (?,?,?,?,?,?,?,? ,?,?,?))";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1,Reg_No);
            st.setString(2,Degree);
            st.setString(3,Class);
            st.setString(4, Field);
            st.setString(5,FatherName);
            st.setString(6,CNIC);
            st.setString(7,DOB);
            st.setString(8,ContactNumber);
            st.setString(9,Email);
            st.setint(10,LoginId);
            st.setString(11,Password);
            st.executeUpdate();
            return true;

        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }
    }
    //Aqsa Ayaz
    public boolean Edit_Students(int Reg_No,String Degree,String Class,String Field,String FatherName,String CNIC,String DOB,String ContactNumber,String Email,int LoginId ,String Password) throws SQLException
    {
        Connection con=null;
        this.Degree=Degree;
        this.Reg_No=Reg_No;
        this.Class=Class;
        this.FatherName=FatherName;
        this.Field=Field;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        this.LoginId=id;
        try
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `students` SET`Reg_No`=?, `Degree`=?, `Class`=?, `Field`=?,   `FatherName`=?,  `CNIC`=?,   'DOB'=?   ,   'ContactNumber'=? ,' Email'=? , 'LoginId'=? , 'Password'=?  WHERE LoginId  ='" + id + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt. setInt(1,Reg_No);
            preparedStmt.setString(2,Degree);
            preparedStmt.setString(3,Class);
            preparedStmt.setString(4, Field);
            preparedStmt.setString(5,FatherName);
            preparedStmt.setString(6,CNIC);
            preparedStmt.setString(7,DOB);
            preparedStmt.setString(8,ContactNumber);
            preparedStmt.setString(9,Email);
            preparedStmt.setint(10,LoginId);
            preparedStmt.setString(11,Password);
            preparedStmt.executeUpdate();
            con.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally
        {
            con.close();
        }
    }
    //Aqsa Ayaz
    public boolean Delete_Students(int Reg_No,String Degree,String Class,String Field,String FatherName,String CNIC,String DOB,String ContactNumber,String Email,int LoginId ,String Password) throws SQLException
    {
        Connection con=null;
        this.Degree=Degree;
        this.Reg_No=Reg_No;
        this.Class=Class;
        this.FatherName=FatherName;
        this.Field=Field;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        this.LoginId=LoginId;
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("Delete FROM students WHERE Reg_No = ?,Degree= ? Class=?,FatherName=?,Field=?,CNIC=?,DOB=?,ContactNumber=?,Email=?,LoginId=?,Paaword=?" );
            st.setInt(1,Reg_No);
            st.setString(2,Degree);
            st.setString(3,Class);
            st.setString(4, Field);
            st.setString(5,FatherName);
            st.setString(6,CNIC);
            st.setString(7,DOB);
            st.setString(8,ContactNumber);
            st.setString(9,Email);
            st.setint(10,LoginId);
            st.setString(11,Password);
            st.executeUpdate();
            return true;
        }
        catch (Exception x)
        {
            return false;
        }
        finally
        {
            con.close();
        }
    }
    //Aqsa Ayaz
    public ArrayList view () throws SQLException
    {
        ArrayList<Teacher> Teachers = new ArrayList<Teachers>();
        try {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers ");
            while (rs.next()) {
                Teacher_Id = rs.getString(1);
                Department = rs.getString(2);
                Name=rs.getString(3);
                FatherName=rs.getString(4);
                CNIC=rs.getString(5);
                DOB=rs.getString(6);
                Weight_Qual==rs.getString(7);
                Majors=rs.getString(8);
                ContactNumber=rs.getString(9);
                Email=rs.getString(10);
                LoginId=rs.getString(11);
                Password=rs.getString(12);
                Teachers.add(new Teacher( Teacher_Id, Department, Name, FatherName,CNIC, DOB, Weight_Qual,Majors,ContactNumber,Email, LoginId ,Password));
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            con.close();
        }
        return Teachers;
    }
    //Aqsa Ayaz
    public boolean Add_Teachers((Teacher_Id, Department, Name, FatherName,CNIC, DOB, Weight_Qual,Majors,ContactNumber,Email, LoginId ,Password)) throws SQLException
{
    Connection con=null;
    this.Majors=Majors;
    this.Teacher_Id=Teacher_Id;
    this.Name=Name;
    this.Weight_Qual=Weight_Qual
    this.FatherName=FatherName;
    this.Department=Department;
    this.CNIC=CNIC;
    this.DOB=DOB;
    this.ContactNumber=ContactNumber;
    this.Email=Email;
    this.Password=Password;
    this.LoginId=LoginId;
    try
    {
        String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String query = "INSERT INTO  teachers(Teacher_Id, Department, Name, FatherName,CNIC, DOB, Weight_Qual,Majors,ContactNumber,Email, LoginId ,Password) VALUES (?,?,?,?,?,?,?,? ,?,?,?,?))";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,Teacher_Id);
        st.setString(2,Department);
        st.setString(3,Name);
        st.setString(4,FatherName);
        st.setString(5,CNIC);
        st.setString(6,DOB);
        st.setString(7,Weight_Qual);
        st.setString(8,Majors);
        st.setString(9,ContactNumber);
        st.setString(10,Email);
        st.setint(11,LoginId);
        st.setString(12,Password);
        st.executeUpdate();
        return true;

    } catch (Exception ex)
    {
        System.out.println(ex.getMessage());
        return false;
    }
    finally
    {
        con.close();
    }
}
  //Aqsa Ayaz
    public boolean Edit_Teachers(int Teacher_Id, String Department, String Name, String FatherName,String CNIC, String DOB, String Weight_Qual,String Majors,String ContactNumber,String Email, String LoginId ,String Password) throws SQLException
    {
        Connection con=null;
        this.Majors=Majors;
        this.Teacher_Id=id;
        this.Name=Name;
        this.Weight_Qual=Weight_Qual
        this.FatherName=FatherName;
        this.Department=Department;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        this.LoginId=LoginId;
        try
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `teachers` SET`Teacher_Id`=?, `Department`=?, `Name`=?,    `FatherName`=?,  `CNIC`=?,   'DOB'=?   , `Weight_Qual`=?, `Majors`=?,   'ContactNumber'=? ,' Email'=? , 'LoginId'=? , 'Password'=?  WHERE Teacher_Id  ='" + id + "'";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1,Teacher_Id);
            preparedStmt.setString(2,Department);
            preparedStmt.setString(3,Name);
            preparedStmt.setString(4,FatherName);
            preparedStmt.setString(5,CNIC);
            preparedStmt.setString(6,DOB);
            preparedStmt.setString(7,Weight_Qual);
            preparedStmt.setString(8,Majors);
            preparedStmt.setString(9,ContactNumber);
            preparedStmt.setString(10,Email);
            preparedStmt.setint(11,LoginId);
            preparedStmt.setString(12,Password);
            preparedStmt.executeUpdate();
            con.close();
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally
        {
            con.close();
        }
    }
    //Aqsa Ayaz
    public boolean Delete_Teachers(int Teacher_Id, String Department, String Name, String FatherName,String CNIC, String DOB, String Weight_Qual,String Majors,String ContactNumber,String Email, String LoginId ,String Password) throws SQLException
    {
        Connection con=null;
        this.Majors=Majors;
        this.Teacher_Id=id;
        this.Name=Name;
        this.Weight_Qual=Weight_Qual
        this.FatherName=FatherName;
        this.Department=Department;
        this.CNIC=CNIC;
        this.DOB=DOB;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        this.LoginId=LoginId;
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement st = con.prepareStatement("Delete FROM teachers WHERE Weight_Qual = ?,Teacher_Id= ? Name=?,FatherName=?, Majors = ?,Department=?,CNIC=?,DOB=?,ContactNumber=?,Email=?,LoginId=?,Paaword=?" );
            st.setInt(1,Teacher_Id);
            st.setString(2,Department);
            st.setString(3,Name);
            st.setString(4,FatherName);
            st.setString(5,CNIC);
            st.setString(6,DOB);
            st.setString(7,Weight_Qual);
            st.setString(8,Majors);
            st.setString(9,ContactNumber);
            st.setString(10,Email);
            st.setint(11,LoginId);
            st.setString(12,Password);
            st.executeUpdate();
            return true;
        }
        catch (Exception x)
        {
            return false;
        }
        finally
        {
            con.close();
        }
    }


    public boolean Login(String Email, String Password) throws SQLException
    {
        Connection con=null;
        try
        {
            String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin ");
            while (rs.next())
            {
                String email = rs.getString(4);
                String PWS = rs.getString(5);
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
