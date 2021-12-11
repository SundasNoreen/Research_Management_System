// By SUNDAS NOREEN
package com.sundas.blogs;

import java.sql.*;

public class Home
{
    public static int Students;
    public static int teachers;
    public static int on;
    public static int com;

    public Home() throws SQLException {
    }

    public void setStudents(int Students) {this.Students=Students;}
    public void setTeachers(int Teachers){this.teachers=Teachers;}
    public void seton(int on){this.on=on;}
    public void setcom(int com){this.com=com;}

    public static int getStudents() throws SQLException
    {
        String url ="jdbc:mysql://localhost/rms";
        Connection con =  DriverManager.getConnection(url, "root", "");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students ");
            Students=0;
            while (rs.next())
            {
                Students++;
                }
        }
        catch (Exception ex)
        {
            return 200;
        }
        finally
        {
            con.close();
        }
        return Students;
    }

    public static int getTeachers() throws SQLException
    {
        String url ="jdbc:mysql://localhost/rms";
        Connection con =  DriverManager.getConnection(url, "root", "");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers ");
            teachers=0;
            while (rs.next())
            {
                teachers++;
            }
        }
        catch (Exception ex)
        {
            return 200;
        }
        finally
        {
            con.close();
        }
        return teachers;
    }

    public static int getOn() throws SQLException
    {
        String url ="jdbc:mysql://localhost/rms";
        Connection con =  DriverManager.getConnection(url, "root", "");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM open_research ");
            on=0;
            while (rs.next())
            {
                on++;
            }
        }
        catch (Exception ex)
        {
            return 200;
        }
        finally
        {
            con.close();
        }
        return on;
    }

    public static int getCom() throws SQLException
    {
        String url ="jdbc:mysql://localhost/rms";
        Connection con =  DriverManager.getConnection(url, "root", "");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM closed_research ");
            com=0;
            while (rs.next())
            {
                com++;
            }
        }
        catch (Exception ex)
        {
            return 200;
        }
        finally
        {
            con.close();
        }
        return com;
    }
}


