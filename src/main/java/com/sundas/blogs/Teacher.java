package com.sundas.blogs;
// By AAIZA NAEEM

import java.sql.*;
import java.util.Date;

public class Teacher extends Person
{
    private String Teacher_Id;
    private String Department;
    private String Name;
    private String FatherName;
    private String CNIC;
    private Date DOB;
    private String Weight_Qual;
    private String Majors;
    private String ContactNumber;
    private String Email;
    private String LoginId;
    private String Password;

    public void setPassword(String password) {
        Password = password;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getPassword() {
        return Password;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getFatherName() {
        return FatherName;
    }

    public String getEmail() {
        return Email;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getDepartment() {
        return Department;
    }

    public String getMajors() {
        return Majors;
    }

    public String getName() {
        return Name;
    }

    public String getTeacher_Id() {
        return Teacher_Id;
    }

    public String getWeight_Qual() {
        return Weight_Qual;
    }

    public void setMajors(String majors) {
        Majors = majors;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTeacher_Id(String teacher_Id) {
        Teacher_Id = teacher_Id;
    }

    public void setWeight_Qual(String weight_Qual) {
        Weight_Qual = weight_Qual;
    }
    public void Add_Application(String Teacher_Id, String Department, String Name, String FatherName, String CNIC, Date DOB, String Weight_Qual, String Majors, String ContactNumber, String Email, String LoginId, String Password)
    {
        this. Teacher_Id=Teacher_Id;
        this. Department= Department;
        this. Name= Name;
        this. FatherName=FatherName;
        this. CNIC=CNIC;
        this. DOB=DOB;
        this. Weight_Qual=Weight_Qual;
        this. Majors=Majors;
        this. ContactNumber=ContactNumber;
        this. Email=Email;
        this. LoginId=LoginId;
        this. Password=Password;
    }
    public boolean Login(String Email, String Password) throws SQLException
    {
    	this.Email=Email;
        this.Password=Password;
        Connection con=null;
            try
            {
            	String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM teachers ");
                while (rs.next())
                {
                    String email = rs.getString(10);
                    String PWS = rs.getString(12);
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

    //METHOD  (View_All_Applications)
    public void view_All_Applications(int Opportunity_Id){
        this. Opportunity_Id= Opportunity_Id;
    }
    //SQL QUERIES
    Connection con=null;
        try
    {
        	String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select FROM Applications WHERE Opportunity_Id ");
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

    public boolean Change_Password(String Teacher_Id, String Password, String New) throws SQLException
    {
    	this.Teacher_Id=Teacher_Id;
        this.New=New;
        this.Password=Password;
        Connection con=null;
            try{
            	String url ="jdbc:mysql://rms2021.mysql.database.azure.com:3306/rms?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con =  DriverManager.getConnection(url, "rms2021@rms2021", "2019ce3@rms");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM teachers ");
                while (rs.next())
                {
                    String PWS = rs.getString(12);
                    if (PWS.equals(Password))
                    {
                        PreparedStatement stmt2=con.prepareStatement("UPDATE `teachers` SET `Password`=? WHERE `Teacher_Id`=?");
                        stmt2.setString(1,New);
                        stmt2.setString(2,Teacher_Id);
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
                return false;
            }
            finally
            {
                con.close();
            }
            return false;
        }
            	
    //METHOD  (View_All_Research_Opportunites)
    public void view_All_Research_Opportunites(String Teacher_Id){
        this. Teacher_Id= Teacher_Id;
    }  
    //SQL QUERIES

        //METHOD  (view_Individual_Application)
    public void view_Individual_Application(int Application_ID){
        this. Application_ID= Application_ID;
    }    //SQL QUERIES
       
    
        //Method (APPROVE / REJECT)
    public void Approve_Reject(int Opportunity_Id){
        this. Opportunity_Id= Opportunity_Id;
    }   //SQL QUERIES

        //Method (Current Researches)
    public void current_researches(int Teacher_Id){
        this. Teacher_Id= Teacher_Id;
        }
        //SQL QUERIES

         //Method (Individual Research)
    public void Individual_Research(int Research_Id){
        this. Research_Id= Research_Id;
        }

          //Method (Update Researches)
    public void Update_Research(int Research_Id){
        this. Research_Id= Research_Id;
        }
        //SQL QUERIES
        Connection con=null;

           //Method (Application Close)
    public void Application_Close(int Application_Id){
        this. Application_Id= Application_Id;
        }
        //SQL QUERIES
        Connection con=null;

        }

