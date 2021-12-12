// By SUNDAS NOREEN

package com.sundas.blogs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class TeacherController
{
    DatabaseConnection Data = new DatabaseConnection();
    String Page;
    String Error_Page="Error.html";

    @GetMapping("/Teacher_Login")
    public String Teacher_Login (HttpServletRequest request, Model model)
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("logged_in_teacher")==null)
        {
            Page = "Login/Teacher.html";
        }
        else
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Welcome.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Teacher_Login")
    public String Teacher_Login (HttpServletRequest request,Model model,@ModelAttribute Teacher MyObj) throws SQLException
    {
        Page="Login/Teacher.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            HttpSession session = request.getSession();
            session.setAttribute("Teacher_Id",MyObj.getTeacher_Id());
            session.setAttribute("Teacher_Name",MyObj.getName());
            session.setAttribute("logged_in_teacher","true");
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            return Data.Connection("Teacher/Welcome.html",Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Research_Opportunities")
    public String Teacher_Research_Opportunities(HttpServletRequest request,Model model, Research_Opportunities MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Teacher_List((Integer) session.getAttribute("Teacher_Id"));
            model.addAttribute("b",b);
            Page="Teacher/ResearchOpportunities.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Research_Opportunity_Individual_{id}")
    public String Teacher_Research_Opportunity_Individual(HttpServletRequest request,Model model, @PathVariable("id") int id, Research_Opportunities MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
            Page="Teacher/Opportunity_Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Applications")
    public String Teacher_Research_Applications(HttpServletRequest request,Model model,Research_Opportunities MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Teacher_List((Integer) session.getAttribute("Teacher_Id"));
            model.addAttribute("b",b);
            Page="Teacher/ResearchApplications.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Applications_{id}")
    public String Research_Applications_List(HttpServletRequest request,Model model, @PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
            Statement stmt;
            ResultSet rs;
            String FName=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            String query = "SELECT * FROM `research_opportunities` WHERE  `Application_Id`= '" + id + "' ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                FName= rs.getString(2);}
            ArrayList<Application> b;
            b=MyObj.View_Applications_Particular(id);
            model.addAttribute("b",b);
            model.addAttribute("TName",FName);
            Page="Teacher/Application_Lists.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Teacher_Full_{id}")
    public String Applications_Student_Full(HttpServletRequest request,Model model,@PathVariable("id") int id, Application MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Application> b;
            b=MyObj.View_Applications(id);
            model.addAttribute( "b",b);
            Page="Teacher/Full_Application.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Current_Research_List")
    public String Teacher_Current_Research_List(HttpServletRequest request,Model model, OpenResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<OpenResearch> b;
            b = MyObj.Teacher_List((Integer) session.getAttribute("Teacher_Id"));
            model.addAttribute("b", b);
            Page = "Teacher/Current_List.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page, Error_Page);
    }

    @RequestMapping("/Teacher_Completed_Research_List")
    public String Teacher_Past_Research_List(HttpServletRequest request,Model model, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<ClosedResearch> b;
            b = MyObj.Teacher_List((Integer) session.getAttribute("Teacher_Id"));
            model.addAttribute("b", b);
            Page = "Teacher/Completed_List.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page, Error_Page);
    }

    @RequestMapping("/Teacher_Current_Research_Individual_{id}")
    public String Student_Current_Research_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, OpenResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<OpenResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
            Page="Teacher/Individual.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Completed_Research_Individual_{id}")
    public String Teacher_Completed_Research_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<ClosedResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
            Page="Teacher/Past_Individual.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Research_Paper_Reviews")
    public String Student_Research_Paper_Reviews(HttpServletRequest request,Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Tracker> fields;
            fields=MyObj.All_Track();
            model.addAttribute("b",fields);
            Page="Teacher/PeerTracker.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Research_Paper_Review_{id}")
    public String Student_Research_Paper_Review(HttpServletRequest request,Model model,@PathVariable("id") int id, Research_Tracker MyObj) throws SQLException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Tracker> b;
            b=MyObj.Paper_Review(id);
            model.addAttribute( "b",b);
            Page="Teacher/Full_Review.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Profile")
    public String Teacher_Profile(HttpServletRequest request,Model model,Teacher MyObj) throws SQLException, ClassNotFoundException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Teacher> fields;
            fields=MyObj.GetData((Integer) session.getAttribute("Teacher_Id"));
            model.addAttribute("b",fields);
            Page="Teacher/Profile.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Teacher_Change_Password_{id}")
    public String Teacher_Change_Password(HttpServletRequest request,Model model,@PathVariable("id") String id) throws SQLException, ClassNotFoundException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Apply.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Teacher_Change_Password_{id}")
    public String Teacher_Change_Password_Here(HttpServletRequest request,Model model,@PathVariable("id") String id, Teacher MyObj) throws SQLException, ClassNotFoundException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Apply.html";
            String Password=MyObj.getPassword();
            String New=MyObj.getNew();
            if(MyObj.Change_Password((Integer) session.getAttribute("Teacher_Id"),Password,New))
            {
                session.invalidate();
                Page="Home/Home.html";
            }
            else
            {
                Page="Teacher/PWSFail.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Add_Research_Opportunity")
    public String Add_Research_Opportunity(HttpServletRequest request,Model model,Domains dom, Research_Opportunities MyObj) throws SQLException, ClassNotFoundException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Add_R_O.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Research_Opportunity")
    public String Add_Research_Opportunity_Here(HttpServletRequest request,Model model,Research_Opportunities MyObj) throws SQLException, ClassNotFoundException, ParseException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Add_R_O.html";
            String Title=MyObj.getTitle();
            String Domain=MyObj.getDomain();
            String No_of_Student=MyObj.getNo_of_Student();
            String Min_Edu=MyObj.getMin_Edu();
            String Last_date_to_apply=MyObj.getLast();
            String Starting_Date=MyObj.getStarting();
            String Eligibility=MyObj.getEligibility();
            String About=MyObj.getAbout();
            String CGPA=MyObj.getCGPA();
            System.out.println(Starting_Date);
            if(MyObj.Add_Research_Opportunities(Title,(Integer) session.getAttribute("Teacher_Id"),Domain,No_of_Student,Min_Edu,Last_date_to_apply, Starting_Date, About,Eligibility,CGPA))
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Log_Out")
    public String LogOut(HttpServletRequest request,Model model) throws SQLException {
        HttpSession session = request.getSession();
        session.invalidate();
        int s=Home.getStudents();
        int t=Home.getTeachers();
        int o=Home.getOn();
        int c=Home.getCom();
        model.addAttribute("s",s);
        model.addAttribute("t",t);
        model.addAttribute("o",o);
        model.addAttribute("c",c);
        Page = "Home/Home.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Approved_Applications_{id}")
    public String Approved_Applications_List(HttpServletRequest request,Model model, @PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
            Statement stmt, stmt2, stmt3;
            ResultSet rs, ru, rt;
            String Name=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            String query = "SELECT * FROM `research_opportunities` WHERE  `Application_Id`= '" + id + "' ORDER BY `Last_date_to_apply` ASC";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Name= rs.getString(2);}
                ArrayList<Application> b;
                b=MyObj.View_Approved(id);
                model.addAttribute("b",b);
                model.addAttribute("Name",Name);
                Page="Teacher/Application_Lists.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Approve_{id}")
    public String Applications_Approve(HttpServletRequest request,Model model, @PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            if(MyObj.Approve_Reject(id,"Approved"))
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
            }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Reject_{id}")
    public String Applications_Rejected(HttpServletRequest request,Model model, @PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            if(MyObj.Approve_Reject(id,"Rejected"))
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Edit_Research_Opportunity_{id}")
    public String Edit_Research_Opportunity(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, Research_Opportunities MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Edit_Opp.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<Research_Opportunities> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Research_Opportunity_{id}")
    public String Edit_Research_Opportunity_Here(HttpServletRequest request,Model model,@PathVariable("id") int id, Research_Opportunities MyObj) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Edit_Opp.html";
            String Domain=MyObj.getDomain();
            String No_of_Student=MyObj.getNo_of_Student();
            String Min_Edu=MyObj.getMin_Edu();
            String Last=MyObj.getLast();
            String Start=MyObj.getStarting();
            String Eligibility=MyObj.getEligibility();
            String About=MyObj.getAbout();
            String CGPA=MyObj.getCGPA();
            boolean tag=false;
            if(Start=="")
            {
                if(Last=="")
                {
                    tag = MyObj.Edit_Research_Opportunities(Start, id, (Integer) session.getAttribute("Teacher_Id"), Domain, No_of_Student, Min_Edu, About, Eligibility, CGPA);
                }
                else
                    {
                tag=MyObj.Edit_Research_Opportunities(id,(Integer) session.getAttribute("Teacher_Id"),Domain,No_of_Student,Min_Edu,Last,About,Eligibility,CGPA);
            }
            }
            else if(Last=="")
            {
                if(Start=="")
                {
                    tag = MyObj.Edit_Research_Opportunities(id, (Integer) session.getAttribute("Teacher_Id"), Domain, No_of_Student, Min_Edu, About, Eligibility, CGPA);
                }
                else
                    {
                    tag = MyObj.Edit_Research_Opportunities(Start, id, (Integer) session.getAttribute("Teacher_Id"), Domain, No_of_Student, Min_Edu, About, Eligibility, CGPA);
                }
            }
           else
            {
                tag = MyObj.Edit_Research_Opportunities(id, (Integer) session.getAttribute("Teacher_Id"), Domain, No_of_Student, Min_Edu, Last, Start, About, Eligibility, CGPA);
            }
            if (tag)

            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);

}

    @GetMapping("/Add_Open_Research")
    public String Add_Open_Research(HttpServletRequest request,Model model,Domains dom, OpenResearch MyObj) throws SQLException, ClassNotFoundException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Add_Open.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Open_Research")
    public String Add_Open_Researches(HttpServletRequest request,Model model,OpenResearch MyObj) throws SQLException, ClassNotFoundException, ParseException
    {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Add_R_O.html";
            String Title=MyObj.getTitle();
            String Domain=MyObj.getDomain();
            String Starting_Date=MyObj.getTemp();
            String About=MyObj.getAbout();
            if(MyObj.Add_Research(Title,(Integer) session.getAttribute("Teacher_Id"),Domain,Starting_Date,About))
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);}

    @GetMapping("/Mark_Research_Closed_{id}")
    public String Mark_Research_Closed(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, OpenResearch MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/CloseResearch.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<OpenResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Mark_Research_Closed_{id}")
    public String Mark_Research_Closed_Here(HttpServletRequest request,Model model,@PathVariable("id") int id, ClosedResearch MyObj,OpenResearch ABC) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/CloseResearch.html";
            String Status=MyObj.getStatus();
            String Ending=MyObj.getRemarks();
            String Report=MyObj.getLink();
            String Abstract=MyObj.getAbstract();
            String Conclusion=MyObj.getConclusion();
            if(ABC.Mark_Closed(id,Status,Ending,Report,Abstract,Conclusion))
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);

    }

    @RequestMapping("/Department_Research_Record")
    public String Department_Research_Record(HttpServletRequest request,Model model, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<ClosedResearch> b;
            b=MyObj.Admin_List();
            model.addAttribute("b",b);
            Page="Teacher/Past_List.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Delete_Research_{id}")
    public String Delete_Research(HttpServletRequest request,Model model, @PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
            Statement stmt;
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            boolean tag = false;
            try {
                String query = "DELETE FROM `open_research` WHERE  `Research_Id`= '" + id + "'";
                stmt.executeUpdate(query);
                String query1 = "DELETE FROM `closed_research` WHERE  `Research_Id`= '" + id + "'";
                stmt.executeUpdate(query1);
                String query2 = "DELETE FROM `student_research_open` WHERE  `Research_Id`= '" + id + "'";
                stmt.executeUpdate(query2);
                String query3= "DELETE FROM `student_research_closed` WHERE  `Research_Id`= '" + id + "'";
                stmt.executeUpdate(query3);
                tag=true;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                con.close();
            }
            if(tag)
            {Page="Teacher/Success.html";}
            else
            {Page="Teacher/Failure.html";}}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
}

    @GetMapping("/Edit_Research_Open_{id}")
    public String Edit_Research_Open(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, OpenResearch MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Edit_Open.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<OpenResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Research_Open_{id}")
    public String Edit_Research_Op(HttpServletRequest request,Model model,@PathVariable("id") int id, OpenResearch MyObj) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Edit_Open.html";
            String Domain=MyObj.getDomain();
            String Starting_Date=MyObj.getTemp();
            String About=MyObj.getAbout();
            boolean tag=false;
            if (Starting_Date=="") {
                tag = MyObj.Edit_Research(id, Domain, About);
            }
            else {
                tag = MyObj.Edit_Research(id, Domain, Starting_Date, About);
            }
            if(tag)
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);

    }

    @GetMapping("/Add_Students_{id}")
    public String Adds_St_Res(HttpServletRequest request,Model model,@PathVariable("id") int id,Students_List dom, OpenResearch MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Add_Students.html";
            ArrayList<Students_List> d;
            d=dom.GetData();
            model.addAttribute("d",d);
            ArrayList<OpenResearch> b;
            b=MyObj.Student_Individual(id);
            model.addAttribute("b",b);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Students_{id}")
    public String Add_St(HttpServletRequest request,Model model,@PathVariable("id") int id,Students_List dom, OpenResearch MyObj) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Students_List> d;
            d=dom.GetData();
            model.addAttribute("d",d);
            ArrayList<OpenResearch> b;
            b=MyObj.Student_Individual(id);
            model.addAttribute("b",b);
            Page="Teacher/Add_Students.html";
            String Reg=MyObj.getTemp();
            boolean tag=false;
            boolean f=true;
            try{
                String url = "jdbc:mysql://naam.mysql.database.azure.com:3306/rms?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                Connection con =  DriverManager.getConnection(url, "KchBhi@naam", "Daal1234");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM `student_research_open` WHERE  `Research_Id`= '" + id + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if(rs.getString(1).equals(Reg))
                {
                    f=false;
                }
            }
            if(f)
            {
            String query1 = "INSERT INTO  `student_research_open`(`Student_Id`, `Research_Id`) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(query1);
            st.setString(1, Reg);
            st.setInt(2, id);
            st.executeUpdate();
            tag=true;
            }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            if(tag)
            { model.addAttribute("Message","Student Added to the Research.");
            }
            else
            {
                model.addAttribute("Message","Student could't be added. Please Try Again.");
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);

    }

    @GetMapping("/Edit_Research_Closed_{id}")
    public String Edit_Research_Closed(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, ClosedResearch MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Edit_Closed.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<ClosedResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Research_Closed_{id}")
    public String Edit_Research_Cl(HttpServletRequest request,Model model,@PathVariable("id") int id, ClosedResearch MyObj) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_teacher")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Teacher_Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Teacher/Edit_Closed.html";
            String Status=MyObj.getStatus();
            String Link=MyObj.getLink();
            String Abstract=MyObj.getAbstract();
            String Conclusion=MyObj.getConclusion();
            if(MyObj.Edit_Research(id,Status,Link,Abstract,Conclusion))
            {
                Page="Teacher/Success.html";
            }
            else
            {
                Page="Teacher/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);

    }

}
