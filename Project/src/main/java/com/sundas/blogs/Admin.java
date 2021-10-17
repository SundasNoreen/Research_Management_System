package com.sundas.blogs;

import java.sql.*;

public class Admin extends Person
{
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
                String email = rs.getString(3);
                String PWS = rs.getString(4);
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
