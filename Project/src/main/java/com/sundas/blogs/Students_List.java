package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;

public class Students_List {
    public String Name;
    public String Reg;

    public String getReg() {
        return Reg;
    }

    public void setReg(String reg) {
        Reg = reg;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    String url ="jdbc:mysql://localhost/rms";
    public Connection con =  DriverManager.getConnection(url, "root", "");

    Students_List() throws SQLException {}
    Students_List(String Name,String Reg) throws SQLException {
        this.Name=Name;
        this.Reg=Reg;
    }


    ArrayList<Students_List> lis = new ArrayList<Students_List>();

    public ArrayList<Students_List> GetData() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            lis.clear();
            Statement stmt2 = con.createStatement();
            ResultSet rs = stmt2.executeQuery("SELECT * FROM `students`");
            while (rs.next()) {
                Reg = rs.getString(1);
                Name = rs.getString(2);
                Name = Reg + " (" + Name + ")";
                lis.add(new Students_List(Name,Reg));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return lis;
    }
}
