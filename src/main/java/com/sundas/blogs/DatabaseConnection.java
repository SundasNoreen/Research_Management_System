// By SUNDAS NOREEN

package com.sundas.blogs;

import java.sql.*;

public class DatabaseConnection {

    public String Connection (String a, String b) {
        Connection con = null;
        try
        {
            String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");            Class.forName("com.mysql.cj.jdbc.Driver");
            return a;
        }
        catch (Exception ex)
        {
            return b;
        }
    }
}
