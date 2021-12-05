// By SUNDAS NOREEN

package com.sundas.blogs;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController
{

    String Page;

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

}
