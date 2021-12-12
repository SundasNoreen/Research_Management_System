package com.sundas.blogs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
public class Research_Opportunities {
    // By AQSA AYAZ
    public int Application_Id;
    public String Title;
    public int Teacher_Id;
    public String Domain;
    public String Teacher;
    public String No_of_Student;
    public String last;
    public String starting;
    public String Min_Edu;
    public Date Last_date_to_apply;
    public Date Starting_Date;
    public String Eligibility;
    public String About;
    public String CGPA;
    public int count;
    Date now = new Date();
    public Statement stmt, stmt2, stmt3;
    public static ResultSet rs, ru, rt;

    public String getLast() {
        return last;
    }
    public String getStarting() {
        return starting;
    }
    public void setLast(String last) {
        this.last = last;
    }
    public void setStarting(String starting) {
        this.starting = starting;
    }
    public String getTitle() {
        return Title;
    }
    public String getDomain() {
        return Domain;
    }
    public int getApplication_Id() {
        return Application_Id;
    }
    public int getTeacher_Id() {
        return Teacher_Id;
    }
    public Date getLast_date_to_apply() {
        return Last_date_to_apply;
    }
    public String getMin_Edu() {
        return Min_Edu;
    }
    public Date getStarting_Date() {
        return Starting_Date;
    }
    public String getEligibility() {
        return Eligibility;
    }
    public String getNo_of_Student() {
        return No_of_Student;
    }
    public String getTeacher() {
        return Teacher;
    }
    public String getCGPA() {
        return CGPA;
    }
    public String getAbout() {
        return About;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public void setDomain(String domain) {
        Domain = domain;
    }
    public void setApplication_Id(int application_Id) {
        Application_Id = application_Id;
    }
    public void setLast_date_to_apply(Date last_date_to_apply) {
        Last_date_to_apply = last_date_to_apply;
    }
    public void setMin_Edu(String min_Edu) {
        Min_Edu = min_Edu;
    }
    public void setNo_of_Student(String no_of_Student) {
        No_of_Student = no_of_Student;
    }
    public void setStarting_Date(Date starting_Date) {
        Starting_Date = starting_Date;
    }
    public void setTeacher(String teacher) {
        Teacher = teacher;
    }
    public void setTeacher_Id(int teacher_Id) {
        Teacher_Id = teacher_Id;
    }
    public void setCGPA(String CGPA) {
        this.CGPA = CGPA;
    }
    public void setAbout(String about) {
        About = about;
    }
    public void setEligibility(String eligibility) {
        Eligibility = eligibility;
    }

    ArrayList < Research_Opportunities > Research = new ArrayList < Research_Opportunities > ();

    // By SUNDAS NOREEN
    Research_Opportunities() throws SQLException {

    }

    Research_Opportunities(int Application_Id, String Title, int Teacher_Id, String Teacher, String Domain, String No_of_Student, String Min_Edu, Date Last_date_to_apply, Date Starting_Date, String About, String Eligibility, String CGPA) throws SQLException {
        this.Application_Id = Application_Id;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Last_date_to_apply = Last_date_to_apply;
        this.Starting_Date = Starting_Date;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
    }

    Research_Opportunities(int Application_Id,int count, String Title, int Teacher_Id, String Teacher, String Domain, String No_of_Student, String Min_Edu, Date Last_date_to_apply, Date Starting_Date, String About, String Eligibility, String CGPA) throws SQLException {
        this.Application_Id = Application_Id;
        this.count=count;
        this.Teacher = Teacher;
        this.Title = Title;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Last_date_to_apply = Last_date_to_apply;
        this.Starting_Date = Starting_Date;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
    }

    // By SUNDAS NOREEN
    public ArrayList < Research_Opportunities > Student_List() throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                if (Last_date_to_apply.after(now))
                {
                    System.out.println("PPP");
                    Research.add(new Research_Opportunities(Application_Id, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
                }
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By AAIZA NAEEM
    public ArrayList < Research_Opportunities > Teacher_List(int Teacher_Id) throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            this.Teacher_Id=Teacher_Id;
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` WHERE `Teacher_Id`='" + Teacher_Id + "'  ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                count=0;
                stmt3=con.createStatement();
                rt=stmt3.executeQuery("SELECT * FROM `application` WHERE  `Opportunity_Id`='"+Application_Id+"'");
                while (rt.next())
                {
                    count++;
                }
                Research.add(new Research_Opportunities(Application_Id,count, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By SUNDAS NOREEN
    public ArrayList < Research_Opportunities > Student_Individual(int application_Id) throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` WHERE  `Application_Id`= '" + application_Id + "' ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                Research.add(new Research_Opportunities(Application_Id, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By AYESHA NADEEM
    public ArrayList < Research_Opportunities > Admin_List() throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);;
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                count=0;
                stmt3=con.createStatement();
                rt=stmt3.executeQuery("SELECT * FROM `application` WHERE  `Opportunity_Id`='"+Application_Id+"'");
                while (rt.next())
                {
                    count++;
                }
                if (Last_date_to_apply.after(now))
                {
                    Research.add(new Research_Opportunities(Application_Id, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By AYESHA NADEEM
    public ArrayList < Research_Opportunities > Admin_Individual(int application_Id) throws SQLException {
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            Research.clear();
            String query = "SELECT * FROM `research_opportunities` WHERE  `Application_Id`= '" + application_Id + "' ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Application_Id = rs.getInt(1);
                Title = rs.getString(2);
                Teacher_Id = rs.getInt(3);
                Domain = rs.getString(4);
                Eligibility = rs.getString(5);
                About = rs.getString(6);
                No_of_Student = rs.getString(7);
                Min_Edu = rs.getString(8);
                Last_date_to_apply = rs.getDate(9);
                Starting_Date = rs.getDate(10);
                CGPA = rs.getString(12);
                stmt2 = con.createStatement();
                ru = stmt2.executeQuery("SELECT * FROM `teachers`");
                while (ru.next()) {
                    if (ru.getInt(1) == Teacher_Id) {
                        Teacher = ru.getString(3);
                    }
                }
                count=0;
                stmt3=con.createStatement();
                rt=stmt3.executeQuery("SELECT * FROM `application` WHERE  `Opportunity_Id`='"+Application_Id+"'");
                while (rt.next())
                {
                    count++;
                }
                Research.add(new Research_Opportunities(Application_Id,count, Title, Teacher_Id, Teacher, Domain, No_of_Student, Min_Edu, Last_date_to_apply, Starting_Date, About, Eligibility, CGPA));
            }
        } catch (Exception ex) {
            System.out.println("Failed to Load opportunities.");
        } finally {
            con.close();
        }
        return Research;
    }

    // By AQSA AYAZ
    public boolean Add_Research_Opportunities(String Title, int Teacher_Id,String Domain, String No_of_Student, String Min_Edu, String last, String starting, String About, String Eligibility, String CGPA) throws SQLException, ParseException {
        this.Title = Title;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
        this.last=last;
        this.starting=starting;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        java.sql.Date date1= java.sql.Date.valueOf(last);
        java.sql.Date date2= java.sql.Date.valueOf(starting);
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "INSERT INTO `research_opportunities`(`Title`, `Teacher_Id`, `Domain`, `Eligibility`, `About`, `No of Student`, `Min_Edu`, `Last_date_to_apply`, `Starting date`, `Status`, `CGPA`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Title);
            st.setInt(2, Teacher_Id);
            st.setString(3, Domain );
            st.setString(4, Eligibility);
            st.setString(5, About);
            st.setString(6, No_of_Student);
            st.setString(7, Min_Edu);
            st.setDate(8, date1);
            st.setDate(9,  date2);
            st.setString(10, "O");
            st.setString(11, CGPA);
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

    // By LAIBA AASHIQ
    public boolean Edit_Research_Opportunities(int Application_Id, int Teacher_Id,String Domain, String No_of_Student, String Min_Edu, String last, String starting, String About, String Eligibility, String CGPA) throws SQLException, ParseException {
        this.Application_Id=Application_Id;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
        this.last=last;
        this.starting=starting;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        boolean flag=false;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                java.sql.Date date1= java.sql.Date.valueOf(last);
                java.sql.Date date2= java.sql.Date.valueOf(starting);
                String query = "UPDATE `research_opportunities` SET `Domain`=?,`Eligibility`=?,`About`=?,`No of Student`=?,`Min_Edu`=?,`Last_date_to_apply`=?,`Starting date`=?,`CGPA`=? WHERE `Application_id`=?";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1, Domain );
                st.setString(2, Eligibility);
                st.setString(3, About);
                st.setString(4, No_of_Student);
                st.setString(5, Min_Edu);
                st.setDate(6, date1);
                st.setDate(7,  date2);
                st.setString(8, CGPA);
                st.setInt(9, Application_Id);
                st.executeUpdate();
                flag = true;
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            } finally {
                con.close();
            }
        return flag;
    }

    // By LAIBA AASHIQ
    public boolean Edit_Research_Opportunities(String starting,int Application_Id, int Teacher_Id,String Domain, String No_of_Student, String Min_Edu, String About, String Eligibility, String CGPA) throws SQLException, ParseException {
        this.Application_Id=Application_Id;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
        this.starting=starting;
        boolean flag=false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Date date2= java.sql.Date.valueOf(starting);
            String query = "UPDATE `research_opportunities` SET `Domain`=?,`Eligibility`=?,`About`=?,`No of Student`=?,`Min_Edu`=?,`Starting date`=?,`CGPA`=? WHERE `Application_id`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Domain );
            st.setString(2, Eligibility);
            st.setString(3, About);
            st.setString(4, No_of_Student);
            st.setString(5, Min_Edu);
            st.setDate(6,  date2);
            st.setString(7, CGPA);
            st.setInt(8, Application_Id);
            st.executeUpdate();
            flag = true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By LAIBA AASHIQ
    public boolean Edit_Research_Opportunities(int Application_Id, int Teacher_Id,String Domain, String No_of_Student, String Min_Edu, String last, String About, String Eligibility, String CGPA) throws SQLException, ParseException {
        this.Application_Id=Application_Id;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
        this.last=last;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        boolean flag=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Date date1= java.sql.Date.valueOf(last);
            String query = "UPDATE `research_opportunities` SET `Domain`=?,`Eligibility`=?,`About`=?,`No of Student`=?,`Min_Edu`=?,`Last_date_to_apply`=?,`CGPA`=? WHERE `Application_id`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Domain );
            st.setString(2, Eligibility);
            st.setString(3, About);
            st.setString(4, No_of_Student);
            st.setString(5, Min_Edu);
            st.setDate(6, date1);
            st.setString(7, CGPA);
            st.setInt(8, Application_Id);
            st.executeUpdate();
            flag = true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }

    // By LAIBA AASHIQ
    public boolean Edit_Research_Opportunities(int Application_Id, int Teacher_Id,String Domain, String No_of_Student, String Min_Edu,String About, String Eligibility, String CGPA) throws SQLException, ParseException {
        this.Application_Id=Application_Id;
        this.Teacher_Id = Teacher_Id;
        this.Domain = Domain;
        this.No_of_Student = No_of_Student;
        this.Min_Edu = Min_Edu;
        this.Eligibility = Eligibility;
        this.CGPA = CGPA;
        this.About = About;
        boolean flag=false;
        String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String query = "UPDATE `research_opportunities` SET `Domain`=?,`Eligibility`=?,`About`=?,`No of Student`=?,`Min_Edu`=?,`CGPA`=? WHERE `Application_id`=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, Domain );
            st.setString(2, Eligibility);
            st.setString(3, About);
            st.setString(4, No_of_Student);
            st.setString(5, Min_Edu);
            st.setString(6, CGPA);
            st.setInt(7, Application_Id);
            st.executeUpdate();
            flag = true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return flag;
    }



}
