// Sundas Noreen

package com.sundas.blogs;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

@Controller
public class Home_Controller
{
    String Global_Email;
    String Global_Role;
    String Page;
    DatabaseConnection Data = new DatabaseConnection();
    String Error_Page="RL/Register-1.html";

    @RequestMapping("/")
    public String Home_Page (Model model) throws SQLException
    {
        Page="Front/Home_Page.html";
        Home_Page myobj = new Home_Page();
        ArrayList<Research_Opportunities> a = myobj.Closest_opportunities();
        ArrayList<Research_Opportunities> b = myobj.First_Four_opportunities();
        model.addAttribute("a",a);
        model.addAttribute("b",b);
        return Data.Connection(Page,Error_Page);
    }
    @GetMapping ("/student_login")
    public String Students_Login ()
    {
        Page="RL/StudentLogin.html";
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/student_login")
    public String Student_Login (Model model,@ModelAttribute Student MyObj) throws SQLException
    {
        Page="RL/StudentLogin.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            return Data.Connection("RL/Register-1.html",Error_Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping ("/teacher_login")
    public String Teachers_Login ()
    {
        Page="RL/TeacherLogin.html";
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/teacher_login")
    public String Teacher_Login (Model model,@ModelAttribute Teacher MyObj) throws SQLException
    {
        Page="RL/TeacherLogin.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            return Data.Connection("RL/Register-1.html",Error_Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping ("/admin_login")
    public String Admins_Login ()
    {
        Page="RL/AdminLogin.html";
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/admin_login")
    public String Admin_Login (Model model,@ModelAttribute Admin MyObj) throws SQLException
    {
        Page="RL/AdminLogin.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            return Data.Connection("RL/AdminLogin.html",Error_Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping ("/register")
    public String Register ()
    {
        Page="RL/Register-1.html";
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping ("/register")
    public String RegisterPage (Model model, @ModelAttribute Register MyObj) throws SQLException
    {
        Page="RL/Register-1.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        String Role = MyObj.getRole();
        String Message = MyObj.General(Email,Password,Role);
        if (Message.equals("Student")||Message.equals("Teacher"))
        {
            Global_Email=Email;
            Global_Role=Role;
            Register_Person();
        }
        else
        {
            model.addAttribute("error",Message);
        }
        return Data.Connection(Page,Error_Page);
    }
    @GetMapping ("/register_personal")
    public String Register_Person ()
    {
        Page="RL/Register-2.html";
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/register_personal")
    public String Register_Personal(Model model,@ModelAttribute Student Objects,@ModelAttribute Teacher MyObj) throws SQLException {
    Page="RL/Register-2.html";
    if (Global_Role.equals("Student"))
    {
        String Name = Objects.getName();
        String Father = Objects.getFather();
        String CNIC = Objects.getCnic();
        String DOB = Objects.getDOB();
        String Contact = Objects.getContact();
        String CityRes= Objects.getCity();
        String Mes = Objects.Register(Global_Email,Name,Father,CNIC,DOB,Contact,CityRes);
        if (Mes.equals("Done"))
        {
            Register_Students();
        }
        else
        {
            model.addAttribute("error",Mes);
        }
    }
        else if (Global_Role.equals("Teacher"))
        {
            String Name = MyObj.getName();
            String Father = MyObj.getFather();
            String CNIC = MyObj.getCnic();
            String DOB = MyObj.getDOB();
            String Contact = MyObj.getContact();
            String CityRes= MyObj.getCity();
            String Mes = MyObj.Register(Global_Email,Name,Father,CNIC,DOB,Contact,CityRes);
            if (Mes.equals("Done"))
            {
                Register_Teachers();
            }
            else
            {
                model.addAttribute("error",Mes);
            }
        }

    return Data.Connection(Page,Error_Page);
}
    @GetMapping ("/register_students")
    public String Register_Students ()
    {
        Page="RL/StudentRegister.html";
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping ("/register_students")
    public String RegisterStudents (Model model, @ModelAttribute Student MyObj) throws SQLException
    {
        Page="RL/StudentRegister.html";
        String HeighestEdu = MyObj.getHeighestedu();
        String Ongoing = MyObj.getOngoingedu();
        String Field = MyObj.getField();
        String Inst = MyObj.getInstitute();
        String City = MyObj.getCi();
        String Message = MyObj.RegisterStudent(HeighestEdu,Ongoing,Inst,City,Field,Global_Email);
        if (Message.equals("Done"))
        {
            model.addAttribute("error","Your Account is Registered Successfully!");

        }
        else
        {
            model.addAttribute("error",Message);
        }
        return Data.Connection(Page,Error_Page);
    }
    @GetMapping ("/register_teacher")
    public String Register_Teachers ()
    {
        Page="RL/TeacherRegister.html";
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/register_teacher")
    public String RegisterTeachers(Model model,@ModelAttribute Teacher MyObj) throws SQLException {
        Page="RL/TeacherRegister.html";
        String HeighestEdu = MyObj.getHeighestedu();
        String Field = MyObj.getField();
        String Inst = MyObj.getInstitute();
        String City = MyObj.getCity();
        String Message = MyObj.RegisterTeacher(HeighestEdu,Inst,City,Field,Global_Email);
        if (Message.equals("Done"))
        {
            model.addAttribute("error","Your Account is Registered Successfully!");

        }
        else
        {
            model.addAttribute("error",Message);
        }
        return Data.Connection(Page,Error_Page);
    }
}
