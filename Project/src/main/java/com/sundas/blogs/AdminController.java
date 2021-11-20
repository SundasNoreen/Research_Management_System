// By SUNDAS NOREEN

package com.sundas.blogs;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.sql.SQLException;

@Controller
public class AdminController
{
    String Role;
    String Name;
    String Email;
    int Admin_ID;
    DatabaseConnection Data = new DatabaseConnection();
    String Page;
    String Password;
    String Error_Page="Error.html";
    boolean logged_in=false;

    @GetMapping ("/Administrator_Login")
    public String Administrator_Login (Model model)
    {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Admin/Welcome.html";
        }
        else
        {
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/Administrator_Login")
    public String Administrators_Login (Model model,@ModelAttribute Admin MyObj) throws SQLException
    {
        Page="Login/Admin.html";
        String ID = MyObj.getEmail();
        Password = MyObj.getPassword();
        boolean result = MyObj.Login(ID,Password);
        if (result)
        {
            Name=MyObj.getName();
            logged_in=true;
            Role=MyObj.getRole();
            Email=MyObj.getEmail();
            Admin_ID=MyObj.getAdmin_Id();
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            return Data.Connection("Admin/Welcome.html",Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_Log_Out")
    public String LogOut()
    {

        logged_in=false;
       Name=null;
        Admin_ID=0;
        Role=null;
        Email=null;
        Page = "Home/Home.html";
        return Data.Connection(Page,Error_Page);

    }

    @RequestMapping("/Administrator_Current_Research_List")
    public String Admin_Current_Research_List(Model model, OpenResearch MyObj) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name", Name);
            model.addAttribute("Picture", "/img/Student_Male.png");
            ArrayList<OpenResearch> b;
            b = MyObj.Admin_List();
            model.addAttribute("b", b);
            Page = "Admin/Current_List.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page, Error_Page);
    }

    @RequestMapping("/Admin_Current_Research_Individual_{id}")
    public String Student_Current_Research_Individual(Model model,@PathVariable("id") int id, OpenResearch MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<OpenResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
            Page="Admin/Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_Past_Research_List")
    public String Admin_Past_Research_List(Model model, ClosedResearch MyObj) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<ClosedResearch> b;
            b=MyObj.Admin_List();
            model.addAttribute("b",b);
            Page="Admin/Past_List.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_Past_Research_Individual_{id}")
    public String Admin_Past_Research_Individual(Model model,@PathVariable("id") int id, ClosedResearch MyObj) throws SQLException {
        if(logged_in){
        model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<ClosedResearch> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
            Page="Admin/Past_Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_Research_Opportunities")
    public String Student_Research_Opportunities(Model model, Research_Opportunities MyObj, Domains dom) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Admin_List();
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            model.addAttribute("b",b);
            Page="Admin/Research_Opoortunities.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_Research_Opportunity_Individual_{id}")
    public String Research_Opportunity_Individual(Model model,@PathVariable("id") int id, Research_Opportunities MyObj) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Research_Opportunities> b;
            b=MyObj.Admin_Individual(id);
            model.addAttribute("b",b);
            Page="Admin/Opportunity_Individual.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_Profile")
    public String Admin_Profile(Model model,Admin MyObj) throws SQLException, ClassNotFoundException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Admin> fields;
            fields=MyObj.GetData(Admin_ID);
            model.addAttribute("b",fields);
            Page="Admin/Profile.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Admin_Change_Password_{id}")
    public String Admin_Change_Password(Model model,@PathVariable("id") String id) throws SQLException, ClassNotFoundException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Admin/Password.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Admin_Change_Password_{id}")
    public String Admin_Change_Password_Here(Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException, ClassNotFoundException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Admin/Password.html";
            String Password=MyObj.getPassword();
            String New=MyObj.getNew();
            if(MyObj.Change_Password(id,Password,New))
            {
                Admin_ID=0;
                Name=null;
                Role=null;
                Email=null;
                logged_in=false;
                Page="Home/Home.html";
            }
            else
            {
                Page="Admin/PWSFail.html";
            }
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_View_Admins_List")
    public String Admin_View_Admins_List(Model model, Admin MyObj) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Admin> b;
            b=MyObj.GetAdminsList();
            model.addAttribute("b",b);
            Page="Admin/Admins.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_View_Students_List")
    public String Admin_View_Students_List(Model model, Student MyObj) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Student> b;
            b=MyObj.GetStudentsList();
            model.addAttribute("b",b);
            Page="Admin/Students.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Details_{id}")
    public String Student_Details_Individual(Model model,@PathVariable("id") String id, Student MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Student> b;
            b=MyObj.Student_Details(id);
            model.addAttribute("b",b);
            Page="Admin/IndividualStudent.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Admin_View_Teachers_List")
    public String Admin_View_Teachers_List(Model model, Teacher MyObj) throws SQLException {
        if (logged_in) {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Teacher> b;
            b=MyObj.GetTeachersList();
            model.addAttribute("b",b);
            Page="Admin/Teachers.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Teacher_Details_{id}")
    public String Teacher_Details_Individual(Model model,@PathVariable("id") int id, Teacher MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Teacher> b;
            b=MyObj.Teacher_Details(id);
            model.addAttribute("b",b);
            Page="Admin/IndividualTeacher.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }
    }
