package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;

public class Domains {
    // By SUNDAS NOREEN
    public String Name;
    public String About;
    public int No;
    String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    public Connection con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
    Statement stmt;
    ResultSet rs;

    Domains() throws SQLException {}
    Domains(String Name, String About, int No) throws SQLException {
        this.Name = Name;
        this.About = About;
        this.No = No;
    }

    ArrayList < Domains > Domain = new ArrayList < Domains > ();

    // By SUNDAS NOREEN
    public ArrayList < Domains > Domains_List() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Domain.clear();
            String query = "SELECT * FROM `domains` ORDER  BY `Name` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Name = rs.getString(1);
                About = rs.getString(2);
                No = rs.getInt(3);
                Domain.add(new Domains(Name, About, No));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Domains.");
        } finally {
            con.close();
        }
        return Domain;
    }
}