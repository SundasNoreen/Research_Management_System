// Sundas Noreen

package com.sundas.blogs;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;

@Controller
public class Home_Controller 
{
    String Admin_Email;
    String Admin_Role;
    String Student_Reg_No;
    String Student_Name;
    String Page;
    String Error_Page="Error.html";
    String Gender;
    int flag = 1;
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


    ////////////////////////////// STUDENT SECTION //////////////////////////////
    @GetMapping ("/Student_Login")
    public String Students_Login ()
    {
        Page="Login/Student.html";
        return Data.Connection(Page,Error_Page);
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
            Student_Reg_No=MyObj.getReg_No();
            Student_Name=MyObj.getName();
            model.addAttribute("Name",Student_Name);
            Gender=MyObj.getGender();
            if (Gender.equals("Male"))
            {
                model.addAttribute("Picture","/img/Student_Male.png");
            }
            else if(Gender.equals("Female"))
            {
                model.addAttribute("Picture","/img/Student_Female.png");
            }
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
    public String Student_Current_Research_List(Model model, OpenResearch MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<OpenResearch> b;
        b=MyObj.Student_List(Student_Reg_No);
        model.addAttribute("b",b);
        Page="Student/Current_List.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Current_Research_Individual_{id}")
    public String Student_Current_Research_Individual(Model model,@PathVariable("id") int id, OpenResearch MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<OpenResearch> b;
        b=MyObj.Student_Individual(id);
        model.addAttribute("b",b);
        Page="Student/Individual.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Completed_Research_List")
    public String Student_Completed_Research_List(Model model, ClosedResearch MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<ClosedResearch> b;
        b=MyObj.Student_List(Student_Reg_No);
        model.addAttribute("b",b);
        Page="Student/Past_List.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Completed_Research_Individual_{id}")
    public String Student_Completed_Research_Individual(Model model,@PathVariable("id") int id, ClosedResearch MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<ClosedResearch> b;
        b=MyObj.Student_Individual(id,Student_Reg_No);
        model.addAttribute("b",b);
        Page="Student/Past_Individual.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Research_Opportunities")
    public String Student_Research_Opportunities(Model model, Research_Opportunities MyObj, Domains dom) throws SQLException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Research_Opportunities> b;
        b=MyObj.Student_List();
        ArrayList<Domains> d;
        d=dom.Domains_List();
        model.addAttribute("d",d);
        model.addAttribute("b",b);
        Page="Student/Research_Opoortunities.html";
        return Data.Connection(Page,Error_Page);
    }
    
@RequestMapping("/Research_Opportunity_Individual_{id}")
public String Research_Opportunity_Individual(Model model,@PathVariable("id") int id, Research_Opportunities MyObj) throws SQLException {
    model.addAttribute("Name",Student_Name);
    if (Gender.equals("Male"))
    {
        model.addAttribute("Picture","/img/Student_Male.png");
    }
    else if(Gender.equals("Female"))
    {
        model.addAttribute("Picture","/img/Student_Female.png");
    }
    ArrayList<Research_Opportunities> b;
    b=MyObj.Student_Individual(id);
    model.addAttribute("b",b);
    Page="Student/Opportunity_Individual.html";
    return Data.Connection(Page,Error_Page);
}

    @GetMapping("/Research_Opportunity_Apply_{id}")
    public String Research_Opportunity_Apply(Model model,@PathVariable("id") int id,Domains dom, Application MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Domains> d;
        d=dom.Domains_List();
        model.addAttribute("d",d);
        ArrayList<Application> fields;
        fields=MyObj.SetData(id,Student_Reg_No);
        model.addAttribute("f",fields);
        Page="Student/Apply.html";
        if(MyObj.Check(id,Student_Reg_No))
        {
            Page="Student/Already_Applied.html";
        }
        else
        {
            Page="Student/Apply.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Research_Opportunity_Apply_{id}")
    public String Research_Opportunity_Apply_Here(Model model,@PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/Apply.html";
        String CGPA=MyObj.getCGPA();
        String Degree=MyObj.getDegree();
        String Field=MyObj.getField();
        String Reason=MyObj.getReason();
        String Semester=MyObj.getSemester();
        ArrayList<Application> fields;
        fields=MyObj.SetData(id,Student_Reg_No);
        model.addAttribute("f",fields);
        System.out.println(Semester);
        if(MyObj.Apply(id,Student_Reg_No,CGPA,Degree,Field,Reason,"Submitted",Semester))
        {
            Page="Student/Success.html";
        }
        else
        {
            Page="Student/Failure.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Student_{id}")
    public String Student_Research_Opportunities(Model model,@PathVariable("id") String id, Application MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        model.addAttribute("ids",Student_Reg_No);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Application> b;
        b=MyObj.View_Applications(Student_Reg_No);
        model.addAttribute("b",b);
        String VAlUES="";
        model.addAttribute("Alerts",VAlUES);
        Page="Student/Applications.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Applications_Student_Full_{id}")
    public String Applications_Student_Full(Model model,@PathVariable("id") int id, Application MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        model.addAttribute("ids",Student_Reg_No);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Application> b;
        b=MyObj.View_Applications(id);
        model.addAttribute( "b",b);
        Page="Student/Full_Application.html";
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Research_Opportunity_Application_Edit_{id}")
    public String Research_Opportunity_Edit(Model model,@PathVariable("id") int id,Domains dom, Application MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
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
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Research_Opportunity_Application_Edit_{id}")
    public String Research_Opportunity_Edit_Here(Model model,@PathVariable("id") int id, Application MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/Apply.html";
        String CGPA=MyObj.getCGPA();
        String Degree=MyObj.getDegree();
        String Field=MyObj.getField();
        String Reason=MyObj.getReason();
        String Semester=MyObj.getSemester();
        ArrayList<Application> fields;
        fields=MyObj.SetData(id,Student_Reg_No);
        model.addAttribute("f",fields);
        System.out.println(Semester);
        if(MyObj.Edit(id,Student_Reg_No,CGPA,Degree,Field,Reason,"Submitted",Semester))
        {
            Page="Student/Success.html";
        }
        else
        {
            Page="Student/Failure.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Student_Profile")
    public String Student_Profile(Model model,Student MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Student> fields;
        fields=MyObj.GetData(Student_Reg_No);
        model.addAttribute("b",fields);
        Page="Student/Profile.html";
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Change_Password_{id}")
    public String Change_Password(Model model,@PathVariable("id") String id) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/Password.html";
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Change_Password_{id}")
    public String Change_Password_Here(Model model,@PathVariable("id") String id, Student MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/Apply.html";
        String Password=MyObj.getPassword();
        String New=MyObj.getNew();
        if(MyObj.Change_Password(Student_Reg_No,Password,New))
        {
            Student_Reg_No="";
            Student_Name="";
            Gender="";
            Page="Home/Home.html";
        }
        else
        {
            Page="Student/PWSFail.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Paper_Tracker")
    public String Research_Paper_Tracker(Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Research_Tracker> fields;
        fields=MyObj.MyTrack(Student_Reg_No);
        model.addAttribute("b",fields);
        Page="Student/Tracker.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Peer_Directory")
    public String Peer_Directory(Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Research_Tracker> fields;
        fields=MyObj.All_Track();
        model.addAttribute("b",fields);
        Page="Student/PeerTracker.html";
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Research_Paper_Review_{id}")
    public String Research_Paper_Review(Model model,@PathVariable("id") int id, Research_Tracker MyObj) throws SQLException {
        model.addAttribute("Name",Student_Name);
        model.addAttribute("ids",Student_Reg_No);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        ArrayList<Research_Tracker> b;
        b=MyObj.Paper_Review(id);
        model.addAttribute( "b",b);
        Page="Student/Full_Review.html";
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Edit_Research_Paper_Review_{id}")
    public String Edit_Research_Paper_Review(Model model,@PathVariable("id") int id,Domains dom, Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/Edit_Paper.html";
        ArrayList<Domains> d;
        d=dom.Domains_List();
        model.addAttribute("d",d);
        ArrayList<Research_Tracker> b;
        b=MyObj.Paper_Review(id);
        model.addAttribute( "f",b);
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Research_Paper_Review_{id}")
    public String Edit_Research_Paper_Review_Here(Model model,@PathVariable("id") int id, Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
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
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Add_Research_Paper_Review")
    public String Add_Research_Paper_Review(Model model,Domains dom, Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/AddPaper.html";
        ArrayList<Domains> d;
        d=dom.Domains_List();
        model.addAttribute("d",d);
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Research_Paper_Review")
    public String Add_Research_Paper_Review_Here(Model model,Research_Tracker MyObj) throws SQLException, ClassNotFoundException {
        model.addAttribute("Name",Student_Name);
        if (Gender.equals("Male"))
        {
            model.addAttribute("Picture","/img/Student_Male.png");
        }
        else if(Gender.equals("Female"))
        {
            model.addAttribute("Picture","/img/Student_Female.png");
        }
        Page="Student/Edit_Paper.html";
        String Title=MyObj.getTitle();
        String Student_Id=Student_Reg_No;
        String Author=MyObj.getAuthor();
        String Domain=MyObj.getDomain();
        Date Publishing=MyObj.getPublishing();
        String Abstract=MyObj.getAbstract();
        String Notes=MyObj.getNotes();
        String Conclusion=MyObj.getConclusion();
        if(MyObj.AddPaper(Student_Id,Title,Author,Domain,Abstract,Notes,Conclusion,Publishing))
        {
            Page="Student/PaperSuccess.html";
        }
        else
        {
            Page="Student/PaperFailure.html";
        }
        return Data.Connection(Page,Error_Page);
    }
}
