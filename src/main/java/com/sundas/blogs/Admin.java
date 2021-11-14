package com.sundas.blogs;

import java.sql.*;

public class Admin extends Person
{
    public ArrayList view () throws SQLException
    {
        ArrayList<Student> Students = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
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
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO  students(Reg_No, Degree, Class, Field, FatherName,CNIC, DOB, ContactNumber, Email, LoginId ,Password) VALUES (?,?,?,?,?,?,?,? ,?),?),?))";
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
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
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
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
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
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
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
