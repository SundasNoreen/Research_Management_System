// By SUNDAS NOREEN

package com.sundas.blogs;

import java.sql.*;
import java.util.Date;

public class MarkClosed
{

    public MarkClosed() throws SQLException {
    }

    public static boolean Mark_Closed(int Research_Id, String Title, int Teacher_id, String Domain, Date Starting_Date, String Status, java.sql.Date date2, String Abstract, String Conclusion, String Report) throws SQLException {
        boolean flag = false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        if(MarkClosed.Step_One(Research_Id,Title,Teacher_id,Domain,Starting_Date,Status,date2,Abstract,Conclusion,Report))
        {
            if(MarkClosed.Step_Two(Research_Id))
            {
                if(MarkClosed.Step_Three(Research_Id))
                {
                    if(MarkClosed.Step_Four(Research_Id))
                    {
                        flag=true;
                    }
                }

            }

        }
        return flag;
    }

    public static boolean Step_One(int Research_Id, String Title, int Teacher_id, String Domain, Date Starting_Date, String Status, java.sql.Date date2, String Abstract, String Conclusion, String Report) throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `closed_research`(`Status`, `Title`, `Teacher_Id`, `Domain`, `Starting_Date`, `Ending_Date`,`Abstract`, `Conclusion`, `Link`,`Research_Id`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Status);
            st.setString(2, Title);
            st.setInt(3, Teacher_id);
            st.setString(4, Domain);
            st.setDate(5, (java.sql.Date) Starting_Date);
            st.setDate(6, date2);
            st.setString(7, Abstract);
            st.setString(8, Conclusion);
            st.setString(9, Report);
            st.setInt(10, Research_Id);
            st.executeUpdate();
            flag=true;
        } catch (Exception ex) {
            flag = false;
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    public static boolean Step_Two(int Research_Id)throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "DELETE FROM `open_research` WHERE  `Research_Id`='" + Research_Id + "'";
            Statement stmt3 = con.createStatement();
            stmt3.executeUpdate(query);
            flag=true;
        } catch (Exception ex) {
            flag = false;
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    public static boolean Step_Three(int Research_Id)throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rt = stmt.executeQuery("SELECT * FROM `student_research_open` WHERE  `Research_Id`='" + Research_Id + "'");
            while (rt.next()) {
                String query2 = "INSERT INTO `student_research_closed`(`Student_Id`, `Research_Id`) VALUES (?,?)";
                PreparedStatement st2 = con.prepareStatement(query2);
                String name = rt.getString(1);
                st2.setString(1, name);
                st2.setInt(2, Research_Id);
                st2.executeUpdate();
                flag = true;
            }
            flag=true;
        } catch (Exception ex) {
            flag = false;
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    public static boolean Step_Four(int Research_Id)throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "DELETE FROM `student_research_open` WHERE  `Research_Id`='" + Research_Id + "'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            flag=true;
        } catch (Exception ex) {
            flag = false;
            System.out.println(ex.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }
}
