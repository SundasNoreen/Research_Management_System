// By SUNDAS NOREEN

package com.sundas.blogs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;

@Controller
public class StudentController
{
    
    DatabaseConnection Data = new DatabaseConnection();
    String Page;
    String Error_Page="Error.html";

    @GetMapping ("/Student_Login")
    public String Students_Login (HttpServletRequest request, Model model)
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("logged_in_student")==null)
        {
            Page = "Login/Student.html";
        }
        else
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Welcome.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping ("/Student_Login")
    public String Student_Login (HttpServletRequest request,Model model,@ModelAttribute Student MyObj) throws SQLException
    {
        Page="Login/Student.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            String a=MyObj.getReg_No();
            String b=MyObj.getName();
            String c="true";
            HttpSession session = request.getSession();
            session.setAttribute("Student_Reg_No",a);
            session.setAttribute("Student_Name",b);
            session.setAttribute("logged_in_student",c);
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            return Data.Connection("Student/Welcome.html",Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Current_Research_List")
    public String Student_Current_Research_List(HttpServletRequest request, Model model, OpenResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<OpenResearch> b;
            System.out.println(session.getAttribute("Student_Reg_No"));
            b = MyObj.Student_List((String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("b", b);
            Page = "Student/Current_List.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page, Error_Page);
    }

    @RequestMapping("/Student_Current_Research_Individual_{id}")
    public String Student_Current_Research_Individual(HttpServletRequest request, Model model, @PathVariable("id") int id, OpenResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<OpenResearch> b;
            b=MyObj.Student_Individual(id);
            model.addAttribute("b",b);
            Page="Student/Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Completed_Research_List")
    public String Student_Completed_Research_List(HttpServletRequest request,Model model, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<ClosedResearch> b;
            b=MyObj.Student_List((String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("b",b);
            Page="Student/Past_List.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Completed_Research_Individual_{id}")
    public String Student_Completed_Research_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<ClosedResearch> b;
            b=MyObj.Student_Individual(id,(String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("b",b);
            Page="Student/Past_Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Research_Opportunities")
    public String Student_Research_Opportunities(HttpServletRequest request,Model model, Research_Opportunities MyObj, Domains dom) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Student_List();
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            model.addAttribute("b",b);
            Page="Student/Research_Opoortunities.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Opportunity_Individual_{id}")
    public String Research_Opportunity_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, Research_Opportunities MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Student_Individual(id);
            model.addAttribute("b",b);
            Page="Student/Opportunity_Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Research_Opportunity_Apply_{id}")
    public String Research_Opportunity_Apply(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<Application> fields;
            fields=MyObj.SetData(id,(String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("f",fields);
            Page="Student/Apply.html";
            if(MyObj.Check(id,(String)session.getAttribute("Student_Reg_No")))
            {
                Page="Student/Already_Applied.html";
            }
            else
            {
                Page="Student/Apply.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Research_Opportunity_Apply_{id}")
    public String Research_Opportunity_Apply_Here(HttpServletRequest request, Model model,@PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Apply.html";
            String CGPA=MyObj.getCGPA();
            String Degree=MyObj.getDegree();
            String Field=MyObj.getField();
            String Reason=MyObj.getReason();
            String Semester=MyObj.getSemester();
            ArrayList<Application> fields;
            fields=MyObj.SetData(id,(String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("f",fields);
            System.out.println(Semester);
            if(MyObj.Apply(id,(String)session.getAttribute("Student_Reg_No"),CGPA,Degree,Field,Reason,"Submitted",Semester))
            {
                Page="Student/Success.html";
            }
            else
            {
                Page="Student/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Student_{id}")
    public String Student_Research_Opportunities(HttpServletRequest request,Model model,@PathVariable("id") String id, Application MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            model.addAttribute("ids",(String)session.getAttribute("Student_Reg_No"));
            ArrayList<Application> b;
            b=MyObj.View_Applications((String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("b",b);
            String VAlUES="";
            model.addAttribute("Alerts",VAlUES);
            Page="Student/Applications.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Student_Full_{id}")
    public String Applications_Student_Full(HttpServletRequest request,Model model,@PathVariable("id") int id, Application MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            model.addAttribute("ids",(String)session.getAttribute("Student_Reg_No"));
            ArrayList<Application> b;
            b=MyObj.View_Applications(id);
            model.addAttribute( "b",b);
            Page="Student/Full_Application.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Research_Opportunity_Application_Edit_{id}")
    public String Research_Opportunity_Edit(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Edit.html";
            if(MyObj.CheckStatus(id))
            {
                Page="Student/Edit.html";
            }
            else
            {
                Page="Student/Cant_Edit.html";
            }
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<Application> b;
            b=MyObj.View_Applications(id);
            model.addAttribute( "f",b);
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Research_Opportunity_Application_Edit_{id}")
    public String Research_Opportunity_Edit_Here(HttpServletRequest request,Model model,@PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Apply.html";
            String CGPA=MyObj.getCGPA();
            String Degree=MyObj.getDegree();
            String Field=MyObj.getField();
            String Reason=MyObj.getReason();
            String Semester=MyObj.getSemester();
            ArrayList<Application> fields;
            fields=MyObj.SetData(id,(String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("f",fields);
            System.out.println(Semester);
            if(MyObj.Edit(id,(String)session.getAttribute("Student_Reg_No"),CGPA,Degree,Field,Reason,"Submitted",Semester))
            {
                Page="Student/Success.html";
            }
            else
            {
                Page="Student/Failure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Profile")
    public String Student_Profile(HttpServletRequest request,Model model,Student MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<Student> fields;
            fields=MyObj.GetData((String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("b",fields);
            Page="Student/Profile.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Change_Password_{id}")
    public String Change_Password(HttpServletRequest request,Model model,@PathVariable("id") String id) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Password.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Change_Password_{id}")
    public String Change_Password_Here(HttpServletRequest request,Model model,@PathVariable("id") String id, Student MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Apply.html";
            String Password=MyObj.getPassword();
            String New=MyObj.getNew();
            if(MyObj.Change_Password((String)session.getAttribute("Student_Reg_No"),Password,New))
            {
                session.invalidate();
                Page="Home/Home.html";
            }
            else
            {
                Page="Student/PWSFail.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Paper_Tracker")
    public String Research_Paper_Tracker(HttpServletRequest request,Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<Research_Tracker> fields;
            fields=MyObj.MyTrack((String)session.getAttribute("Student_Reg_No"));
            model.addAttribute("b",fields);
            Page="Student/Tracker.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Peer_Directory")
    public String Peer_Directory(HttpServletRequest request,Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            ArrayList<Research_Tracker> fields;
            fields=MyObj.All_Track();
            model.addAttribute("b",fields);
            Page="Student/PeerTracker.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Paper_Review_{id}")
    public String Research_Paper_Review(HttpServletRequest request,Model model,@PathVariable("id") int id, Research_Tracker MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            model.addAttribute("ids",(String)session.getAttribute("Student_Reg_No"));
            ArrayList<Research_Tracker> b;
            b=MyObj.Paper_Review(id);
            model.addAttribute( "b",b);
            Page="Student/Full_Review.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Edit_Research_Paper_Review_{id}")
    public String Edit_Research_Paper_Review(HttpServletRequest request,Model model,@PathVariable("id") int id,Domains dom, Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Edit_Paper.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            ArrayList<Research_Tracker> b;
            b=MyObj.Paper_Review(id);
            model.addAttribute( "f",b);}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Research_Paper_Review_{id}")
    public String Edit_Research_Paper_Review_Here(HttpServletRequest request,Model model,@PathVariable("id") int id, Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Edit_Paper.html";
            String Author=MyObj.getAuthor();
            String Abstract=MyObj.getAbstract();
            String Notes=MyObj.getNotes();
            String Conclusion=MyObj.getConclusion();
            if(MyObj.UpdatePaper(id,Author,Abstract,Notes,Conclusion))
            {
                Page="Student/PaperSuccess.html";
            }
            else
            {
                Page="Student/PaperFailure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Add_Research_Paper_Review")
    public String Add_Research_Paper_Review(HttpServletRequest request,Model model,Domains dom, Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/AddPaper.html";
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);}
            else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Research_Paper_Review")
    public String Add_Research_Paper_Review_Here(HttpServletRequest request,Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            Page="Student/Edit_Paper.html";
            String Title=MyObj.getTitle();
            String Student_Id=(String)session.getAttribute("Student_Reg_No");
            String Author=MyObj.getAuthor();
            String Domain=MyObj.getDomain();
            Date Publishing=MyObj.getPublishing();
            String Abstract=MyObj.getAbstract();
            String Notes=MyObj.getNotes();
            String Conclusion=MyObj.getConclusion();
            if(MyObj.AddPaper(Student_Id,Title,Author,Domain,Abstract,Notes,Conclusion, (java.sql.Date) Publishing))
            {
                Page="Student/PaperSuccess.html";
            }
            else
            {
                Page="Student/PaperFailure.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Student.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Delete_Research_Paper_Review_{id}")
    public String Delete_Research_Paper_Review(HttpServletRequest request,Model model, @PathVariable("id") int id) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_student")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
            model.addAttribute("Picture","/img/Student_Female.png");
            String url = "jdbc:mysql://localhost/rms";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement stmt;
            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt = con.createStatement();
            boolean tag = false;
            try {
                String query = "DELETE FROM `research_tracker` WHERE  `Paper_Id`= '" + id + "'";
                stmt.executeUpdate(query);
                tag=true;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            finally {
                con.close();
            }
            if(tag)
            {Page="Student/Success.html";}
            else
            {Page="Student/Failure.html";}}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Teacher.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Log_Out")
    public String LogOut(HttpServletRequest request,Model model) throws SQLException {
        HttpSession session=request.getSession();
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
}
