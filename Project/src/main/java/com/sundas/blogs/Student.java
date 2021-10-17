// AQSA AYAZ

package com.sundas.blogs;
import org.unbescape.css.CssIdentifierEscapeType;

import java.sql.*;
import java.sql.SQLException;
import java.util.Date;

public class Student extends Person
{
    private String name;
    private String father;
    private String cnic;
    private String DOB;
    private String contact;
    private String city;
    private String heighestedu;
    private String ongoingedu;
    private String field;
    private String institute;
    private String ci;

    public String getName(){
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getFather() {
        return father;
    }

    public String getDOB() {
        return DOB;
    }

    public String getHeighestedu() {
        return heighestedu;
    }

    public String getCity() {
        return city;
    }

    public String getContact() {
        return contact;
    }

    public String getOngoingedu() {
        return ongoingedu;
    }

    public String getField() {
        return field;
    }

    public String getInstitute() {
        return institute;
    }

    public String getCi() {
        return ci;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setHeighestedu(String heighestedu) {
        this.heighestedu = heighestedu;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setOngoingedu(String ongoingedu) {
        this.ongoingedu = ongoingedu;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String Register(String Email,String Name,String Father,String CNIC,String DOB,String Contact,String cityres) throws SQLException
    {
        Connection con=null;
        try{
        con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String query = "UPDATE `students` SET `approval`=?,`Name`=?,`FatherName`=?,`CNIC`=?,`DOB`=?,`Contact`=?,`CityRes`=? WHERE `Email`=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,"No");
        st.setString(2,Name);
        st.setString(3, Father);
        st.setString(4, CNIC);
        st.setString(5, DOB);
        st.setString(6,Contact);
        st.setString(7, cityres);
        st.setString(8,Email);
        st.executeUpdate();
        return "Done";
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "There Seems to be some Problem.Please Try Again";
        }
        finally {
            con.close();
        }
    }

    public String RegisterStudent(String HeighestEdu,String OngoingEdu,String Inst,String CityIns,String Field,String Email) throws SQLException
    {
        Connection con=null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `students` SET `HeighestEdu`=?,`Ongoingedu`=?,`Field`=?,`institute`=?,`Citylns`=? WHERE `Email`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,HeighestEdu);
            st.setString(2,OngoingEdu);
            st.setString(3, Field);
            st.setString(4, Inst);
            st.setString(5, CityIns);
            st.setString(6,Email);
            st.executeUpdate();
            return "Done";
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "There Seems to be some Problem.Please Try Again";
        }
        finally {
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM students ");
                while (rs.next())
                {
                    String email = rs.getString(5);
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


