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


