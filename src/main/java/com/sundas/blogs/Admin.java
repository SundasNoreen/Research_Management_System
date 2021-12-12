package com.sundas.blogs;

import java.sql.*;
import java.text.ParseException;
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
    public void setName(String Name) {this.Name=Name;}
    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setNew (String New){this.New=New;}
    public String getNew() {
        return New;
    }
    public void setLoginId(String loginId) {
        this.LoginId = loginId;
    }
    public void setPassword(String password) {
        this.Password = password;
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
        this.Admin_Id = admin_Id;
    }
    public void setRole(String role) {
        this.Role = role;
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
    public Admin(int Admin_Id,String Name,String Email,String Address,String CNIC,String Role,String ContactNumber,String LoginId, String Password) throws SQLException {
        this.Admin_Id=Admin_Id;
        this.Email=Email;
        this.Name=Name;
        this.Address=Address;
        this.CNIC=CNIC;
        this.Role=Role;
        this.LoginId=LoginId;
        this.Password=Password;
        this.ContactNumber=ContactNumber;
    }


    // By SUNDAS NOREEN
    public boolean Login(String Email, String Password) throws SQLException
    {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
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
                Role=rs.getString(9);
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
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
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
                LoginId=rs.getString(4);
                Password=rs.getString(5);
                AdminData.add(new Admin(Admin_Id,Name,Email,Address,CNIC,Role,ContactNumber,LoginId,Password));
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
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
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
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        AdminData.clear();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `admin`");
            while (rs.next())
            {
                Admin_Id=rs.getInt(1);
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

    // By HIRA ASLAM
    public boolean Add_Admin_Ind(String Name,String Email,String Address,String CNIC,String Role,String ContactNumber,String LoginId, String Password) throws SQLException, ParseException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        this.Email=Email;
        this.Name=Name;
        this.Address=Address;
        this.CNIC=CNIC;
        this.Role=Role;
        this.ContactNumber=ContactNumber;
        this.LoginId=LoginId;
        this.Password=Password;
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `admin`(`Name`, `Email`, `LoginId`, `Password`, `Address`, `ContactNumber`, `CNIC`, `Role`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Name);
            st.setString(2, Email);
            st.setString(3, LoginId);
            st.setString(4, Password);
            st.setString(5, Address);
            st.setString(6, ContactNumber);
            st.setString(7, CNIC);
            st.setString(8, Role);
            st.executeUpdate();
            System.out.println("Added Successfully");
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By HIRA ASLAM
    public boolean Delete_Admin(int Admin_Id) throws SQLException
    {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        this.Admin_Id=Admin_Id;
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `admin`  WHERE `Admin_Id`='"+Admin_Id+"'");
            flag=true;
            System.out.println("Deleted");
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        finally
        {
            con.close();
        }
        return flag;
    }

    // By HIRA ASLAM
    public boolean Update_Admin_Ind(int Admin_Id,String ContactNumber,String Email,String Password,String Role, String Address) throws SQLException, ParseException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        this.Admin_Id=Admin_Id;
        this.Role=Role;
        this.Address=Address;
        this.ContactNumber=ContactNumber;
        this.Email=Email;
        this.Password=Password;
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `admin` SET `ContactNumber`=?,`Email`=?,`Password`=?,`Address`=?,`Role`=? WHERE `Admin_Id`='"+Admin_Id+"'";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, ContactNumber);
            st.setString(2, Email);
            st.setString(3, Password);
            st.setString(4, Address);
            st.setString(5, Role);
            st.executeUpdate();
            System.out.println("Updated Successfully");
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

}
