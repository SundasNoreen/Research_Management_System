package com.sundas.blogs;

import java.sql.*;
import java.util.ArrayList;

public class Admin
{
    // By SUNDAS NOREEN
    private int Admin_Id;
    private String Name;
    private String ContactNumber;
    private String Email;
    private String LoginId;
    private String Password;
    private String Role;
    private String Address;
    private String CNIC;
    private String New;
    String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    public Connection con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");

    public void setName(String Name) {Name=Name;}
    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setNew (String New){this.New=New;}
    public String getNew() {
        return New;
    }
    public void setLoginId(String loginId) {
        LoginId = loginId;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public int getAdmin_Id() {
        return Admin_Id;
    }
    public String getAddress() {
        return Address;
    }
    public String getRole() {
        return Role;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getCNIC() {
        return CNIC;
    }
    public String getContactNumber() {
        return ContactNumber;
    }
    public String getName(){return Name;}
    public String getEmail() {
        return Email;
    }
    public String getLoginId() {
        return LoginId;
    }
    public String getPassword() {
        return Password;
    }
    public void setAdmin_Id(int admin_Id) {
        Admin_Id = admin_Id;
    }
    public void setRole(String role) {
        Role = role;
    }

    // By SUNDAS NOREEN
    public Admin() throws SQLException
    {
    }
    public Admin(int Admin_Id,String Name,String Email,String Address,String CNIC,String Role,String ContactNumber) throws SQLException {
        this.Admin_Id=Admin_Id;
        this.Email=Email;
        this.Name=Name;
        this.Address=Address;
        this.CNIC=CNIC;
        this.Role=Role;
        this.ContactNumber=ContactNumber;
    }

    // By SUNDAS NOREEN
    public boolean Login(String Email, String Password) throws SQLException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin ");
            while (rs.next())
            {
                String email = rs.getString(4);
                String PWS = rs.getString(5);
                Name=rs.getString(2);
                Admin_Id=rs.getInt(1);
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

    ArrayList<Admin> AdminData = new ArrayList<Admin>();

    // By AYESHA NADEEM
    public ArrayList<Admin> GetData(int ID) throws SQLException
    {
        Admin_Id=ID;
        AdminData.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `admin` WHERE `Admin_id`='"+Admin_Id+"'");
            while (rs.next())
            {
                Name=rs.getString(2);
                Email=rs.getString(3);
                Address=rs.getString(6);
                CNIC=rs.getString(8);
                Role=rs.getString(9);
                ContactNumber=rs.getString(7);
                AdminData.add(new Admin(Admin_Id,Name,Email,Address,CNIC,Role,ContactNumber));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            con.close();
        }
        return AdminData;
    }

    // By AYESHA NADEEM
    public boolean Change_Password(int Admin_Id, String Password, String New) throws SQLException
    {
        this.Admin_Id=Admin_Id;
        this.Password=Password;
        this.New=New;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM admin ");
            while (rs.next())
            {
                String PWS = rs.getString(5);
                if (PWS.equals(Password))
                {
                    PreparedStatement stmt2=con.prepareStatement("UPDATE `admin` SET `Password`=? WHERE `Admin_Id`=?");
                    stmt2.setString(1,New);
                    stmt2.setInt(2,Admin_Id);
                    stmt2.executeUpdate();
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        finally
        {
            con.close();
        }
        return false;
    }

    // By HIRA ASLAM
    public ArrayList<Admin> GetAdminsList() throws SQLException
    {
        AdminData.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `admin`");
            while (rs.next())
            {
                Name=rs.getString(2);
                Email=rs.getString(3);
                Address=rs.getString(6);
                CNIC=rs.getString(8);
                Role=rs.getString(9);
                ContactNumber=rs.getString(7);
                AdminData.add(new Admin(Admin_Id,Name,Email,Address,CNIC,Role,ContactNumber));
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            con.close();
        }
        return AdminData;
    }

}
