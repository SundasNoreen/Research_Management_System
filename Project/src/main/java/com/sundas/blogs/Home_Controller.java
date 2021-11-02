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
//    String Error_Page="RL/Register-1.html";
    @RequestMapping("/")
public String Home_Page (Model model) throws SQLException
{
    Page="Home/Home.html";
    return Data.Connection(Page,Page);
}

    @RequestMapping("/Research_Management_System_Overview")
    public String OverView (Model model) throws SQLException
    {
        Page="Home/Overview.html";
        return Data.Connection(Page,Page);
    }



}
