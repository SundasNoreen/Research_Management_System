package com.sundas.blogs;
// AQSA AYAZ

import java.sql.*;
import java.util.Date;

public class Teacher extends Person
{
    private String father;
    private String cnic;
    private String DOB;
    private String contact;
    private String city;
    private String heighestedu;
    private String field;
    private String institute;
    private String cityinstitue;
    private String name;

    public String getCnic() {
        return cnic;
    }

    public String getName() {
        return name;
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



    public String getField() {
        return field;
    }

    public String getInstitute() {
        return institute;
    }

    public String getCityinstitue() {
        return cityinstitue;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCityinstitue(String cityinstitue) {
        this.cityinstitue = cityinstitue;
    }

    public void setHeighestedu(String heighestedu) {
        this.heighestedu = heighestedu;
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
            String query = "UPDATE `teachers` SET `approval`=?,`Name`=?,`FatherName`=?,`CNIC`=?,`DOB`=?,`Contact`=?,`CityRes`=? WHERE `Email`=?";
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

    public String RegisterTeacher(String HeighestEdu,String Inst,String CityIns,String Field,String Email) throws SQLException
    {
        Connection con=null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `teachers` SET `HeighestEdu`=?,`Field`=?,`institute`=?,`Citylns`=? WHERE `Email`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,HeighestEdu);
            st.setString(2, Field);
            st.setString(3, Inst);
            st.setString(4, CityIns);
            st.setString(5,Email);
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM teachers ");
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


