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

    @RequestMapping("/")
    public String Home_Page ()
    {
        Page="Home/Home.html";
        return Page;
    }
    @RequestMapping("/Research_Management_System_Overview")
    public String OverView ()
    {
        Page="Home/Overview.html";
        return Page;
    }
    @RequestMapping("/before_you_start")
    public String Before_You_Start ()
    {
        Page="Home/Step_1.html";
        return Page;
    }
    @RequestMapping("/planning_and_design")
    public String Planning_and_Design ()
    {
        Page="Home/Step_2.html";
        return Page;
    }
    @RequestMapping("/project_setup")
    public String Project_Setup ()
    {
        Page="Home/Step_3.html";
        return Page;
    }
    @RequestMapping("/project_delivery")
    public String Project_Delivery ()
    {
        Page="Home/Step_4.html";
        return Page;
    }
    @RequestMapping("/project_outputs")
    public String Project_Outputs ()
    {
        Page="Home/Step_5.html";
        return Page;
    }
    @RequestMapping("/project_close")
    public String Project_Close ()
    {
        Page="Home/Step_6.html";
        return Page;
    }

    @GetMapping ("/Student_Login")
    public String Students_Login ()
    {
        Page="Login/Student.html";
        return Data.Connection(Page,Page);
    }
    @PostMapping ("/Student_Login")
    public String Student_Login (Model model,@ModelAttribute Student MyObj) throws SQLException
    {
        Page="Login/Student.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            return Data.Connection("RL/Register-1.html",Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Page);
    }

    @GetMapping ("/Teacher_Login")
    public String Teachers_Login ()
    {
        Page="Login/Teacher.html";
        return Data.Connection(Page,Page);
    }
    @PostMapping ("/Teacher_Login")
    public String Teacher_Login (Model model,@ModelAttribute Teacher MyObj) throws SQLException
    {
        Page="Login/Teacher.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            return Data.Connection("RL/Register-1.html",Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Page);
    }

    @GetMapping ("/Administrator_Login")
    public String Admins_Login ()
    {
        Page="Login/Admin.html";
        return Data.Connection(Page,Page);
    }
    @PostMapping ("/Administrator_Login")
    public String Admin_Login (Model model,@ModelAttribute Admin MyObj) throws SQLException
    {
        Page="Login/Admin.html";
        String Email = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(Email,Password);
        if (result)
        {
            return Data.Connection("RL/AdminLogin.html",Page);
        }
        else
        {
            String Message = "Invalid Credentials.";
            model.addAttribute("Message", Message);
        }
        return Data.Connection(Page,Page);
    }


}
