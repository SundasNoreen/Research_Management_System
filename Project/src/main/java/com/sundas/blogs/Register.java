package com.sundas.blogs;
import java.sql.*;

public class Register extends Person
{
    Connection con;
    String Return_Value;
    Statement stmt;
    ResultSet rs;
    Boolean flag=true;

    public String General(String Email,String Password,String Role) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (Role.equals("Teacher"))
            {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM teachers");
                while (rs.next())
                {
                    String EmailID = rs.getString(5);
                    if (EmailID.equals(Email)) {
                        Return_Value = "This Email is already registered.";
                        flag=false;
                        break;
                    }
                }
                if (flag) {
                    Return_Value = Role;
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String query = "INSERT INTO `teachers`(`approval`, `Name`, `Password`, `Email`, `FatherName`,`DOB`, `CNIC`,  `Contact`, `CityRes`, `HeighestEdu`, `Ongoingedu`, `Field`, `institute`, `Citylns`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1,"");
                    st.setString(2,"");
                    st.setString(3, Password);
                    st.setString(4, Email);
                    st.setString(5,"");
                    st.setString(6,"");
                    st.setString(7, "");
                    st.setString(8,"");
                    st.setString(9,"");
                    st.setString(10,"");
                    st.setString(11,"");
                    st.setString(12,"");
                    st.setString(13,"");
                    st.setString(14,"");
                    st.executeUpdate();
                }
            }
            else if (Role.equals("Student"))
            {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM students");
                while (rs.next())
                {
                    String EmailID = rs.getString(5);
                    if (EmailID.equals(Email))
                    {
                        Return_Value="This Email is already registered.";
                        flag=false;
                        break;
                    }
                }
                if (flag)
                {
                Return_Value=Role;
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
                Class.forName("com.mysql.cj.jdbc.Driver");
                    String query = "INSERT INTO `students`(`approval`, `Name`, `Password`, `Email`, `FatherName`,`DOB`, `CNIC`,  `Contact`, `CityRes`, `HeighestEdu`, `Ongoingedu`, `Field`, `institute`, `Citylns`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1,"");
                    st.setString(2,"");
                    st.setString(3, Password);
                    st.setString(4, Email);
                    st.setString(5,"");
                    st.setString(6,"");
                    st.setString(7, "");
                    st.setString(8,"");
                    st.setString(9,"");
                    st.setString(10,"");
                    st.setString(11,"");
                    st.setString(12,"");
                    st.setString(13,"");
                    st.setString(14,"");
                    st.executeUpdate();
                }
            }
        } catch (Exception ex)
        {
            Return_Value="There seems to be some Problem. Please Try Again.";
        } finally {
            con.close();
        }
        return Return_Value;
    }
}
