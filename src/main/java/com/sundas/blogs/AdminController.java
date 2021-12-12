// By SUNDAS NOREEN

package com.sundas.blogs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class AdminController
{
    DatabaseConnection Data = new DatabaseConnection();
    String Page;
    String Error_Page="Error.html";

    @GetMapping ("/Administrator_Login")
    public String Administrator_Login (HttpServletRequest request,Model model)
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("logged_in_admin")==null)
        {
            Page = "Login/Admin.html";
        }
        else
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Admin/Welcome.html";
        }
        return Data.Connection(Page,Error_Page);
    }
    @PostMapping ("/Administrator_Login")
    public String Administrators_Login (HttpServletRequest request,Model model,@ModelAttribute Admin MyObj) throws SQLException
    {
        Page="Login/Admin.html";
        String ID = MyObj.getEmail();
        String Password = MyObj.getPassword();
        boolean result = MyObj.Login(ID,Password);
        if (result)
        {
            int id=MyObj.getAdmin_Id();
            HttpSession session = request.getSession();
            session.setAttribute("Name",MyObj.getName());
            session.setAttribute("Role",MyObj.getRole());
            session.setAttribute("logged_in_admin","true");
            session.setAttribute("Admin_Id",id);
            System.out.println(session.getAttribute("Admin_Id"));
            model.addAttribute("Name",(String)session.getAttribute("Student_Name"));
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

    @RequestMapping("/Administrator_Current_Research_List")
    public String Admin_Current_Research_List(HttpServletRequest request,Model model, OpenResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Student_Current_Research_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, OpenResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Admin_Past_Research_List(HttpServletRequest request,Model model, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Admin_Past_Research_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, ClosedResearch MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Student_Research_Opportunities(HttpServletRequest request,Model model, Research_Opportunities MyObj, Domains dom) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Research_Opportunity_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, Research_Opportunities MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Admin_Profile(HttpServletRequest request,Model model,Admin MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Admin> fields;
            System.out.println(session.getAttribute("Admin_Id"));
            fields=MyObj.GetData((Integer) session.getAttribute("Admin_Id"));
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
    public String Admin_Change_Password(HttpServletRequest request,Model model,@PathVariable("id") String id) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Admin_Change_Password_Here(HttpServletRequest request,Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException, ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Admin/Password.html";
            String Password=MyObj.getPassword();
            String New=MyObj.getNew();
            if(MyObj.Change_Password(id,Password,New))
            {
                session.invalidate();
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
    public String Admin_View_Admins_List(HttpServletRequest request,Model model, Admin MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture", "/img/Student_Male.png");
            if(session.getAttribute("Role").equals("Administrator"))
            {
                ArrayList<Admin> b;
                b = MyObj.GetAdminsList();
                model.addAttribute("b", b);
                Page = "Admin/Admins.html";
            }
            else
            {
                Page = "Admin/Access.html";
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

    @RequestMapping("/Admin_View_Students_List")
    public String Admin_View_Students_List(HttpServletRequest request,Model model, Student MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Student_Details_Individual(HttpServletRequest request,Model model,@PathVariable("id") String id, Student MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Admin_View_Teachers_List(HttpServletRequest request,Model model, Teacher MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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
    public String Teacher_Details_Individual(HttpServletRequest request,Model model,@PathVariable("id") int id, Teacher MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
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

    @GetMapping("/Add_Student_Ind")
    public String Add_Students_Here(HttpServletRequest request,Model model) throws ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture", "/img/Student_Male.png");
            Page = "Admin/Add_Student.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Student_Ind")
    public String Add(HttpServletRequest request,Model model, Student MyObj) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Message","");
            model.addAttribute("Picture","/img/Student_Male.png");
            String Reg_No=MyObj.getReg_No();
            String Name=MyObj.getName();
            String Degree=MyObj.getDegree();
            String ClassName=MyObj.getClassName();
            String Field=MyObj.getField();
            String FatherName=MyObj.getFatherName();
            String CNIC=MyObj.getCNIC();
            String DOB=MyObj.getNew();
            String ContactNumber=MyObj.getContactNumber();
            String Email=MyObj.getEmail();
            String LoginId=MyObj.getLoginId();
            String Password=MyObj.getPassword();
            String gender=MyObj.getGender();
            if(MyObj.Add_Student_Ind(Reg_No,Name,Degree,ClassName,Field,FatherName,CNIC,DOB,ContactNumber,Email,gender,LoginId,Password))
            {
                model.addAttribute("Message","Student Added Successfully!");
            }
            else
            {
                model.addAttribute("Message","Student could'nt be added!");
            }
            Page="Admin/Add_Student.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Add_Teacher_Ind")
    public String Add_Teacher(HttpServletRequest request,Model model,Domains dom) throws ClassNotFoundException, SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture", "/img/Student_Male.png");
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            Page = "Admin/Add_Teacher.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Teacher_Ind")
    public String AddTeacher(HttpServletRequest request,Model model, Teacher MyObj, Domains dom) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Message","");
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Domains> d;
            d=dom.Domains_List();
            model.addAttribute("d",d);
            String Name=MyObj.getName();
            String Department=MyObj.getDepartment();
            String FatherName=MyObj.getFatherName();
            String CNIC=MyObj.getCNIC();
            String DOB=MyObj.getExtra();
            String ContactNumber=MyObj.getContactNumber();
            String Email=MyObj.getEmail();
            String Majors=MyObj.getMajors();
            String LoginId=MyObj.getLoginId();
            String Password=MyObj.getPassword();
            String Weight_Qual=MyObj.getWeight_Qual();
            String gender=MyObj.getGender();
            if(MyObj.Add_Teacher_Ind(Name,FatherName,Department,Majors,Weight_Qual,CNIC,DOB,ContactNumber,Email,gender,LoginId,Password))
            {
                model.addAttribute("Message","Teacher Added Successfully!");
            }
            else
            {
                model.addAttribute("Message","Teacher could'nt be added!");
            }
            Page="Admin/Add_Teacher.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Delete_Teacher_{id}")
    public String Delete_Teacher(HttpServletRequest request,Model model,@PathVariable("id") int id, Teacher MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            MyObj.Delete_Teacher(id);
            Page="Admin/Welcome.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Delete_Student_{id}")
    public String Delete_Student(HttpServletRequest request,Model model,@PathVariable("id") String id, Student MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            MyObj.Delete_Student(id);
            Page="Admin/Welcome.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Edit_Student_{id}")
    public String Edit_Student(HttpServletRequest request,Model model,@PathVariable("id") String id, Student MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Student> d = MyObj.Student_Details(id);
            model.addAttribute("d",d);
            Page="Admin/EditStudent.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Student_{id}")
    public String Edit_Student_Details(HttpServletRequest request,Model model,@PathVariable("id") String id, Student MyObj) throws SQLException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            String Degree=MyObj.getDegree();
            String Field=MyObj.getField();
            String ContactNumber=MyObj.getContactNumber();
            String Email=MyObj.getEmail();
            String Password=MyObj.getPassword();
            Page="Admin/EditStudent.html";
            if(MyObj.Update_Student_Ind(id,Degree,Field,ContactNumber,Email,Password))
            {
                Page="Admin/Success.html";
            }
            else
            {
                Page="Admin/Failure.html";
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

    @GetMapping("/Edit_Teacher_{id}")
    public String Edit_Teacher(HttpServletRequest request,Model model,@PathVariable("id") int id, Teacher MyObj, Domains dom) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Teacher> d = MyObj.Teacher_Details(id);
            model.addAttribute("d",d);
            ArrayList<Domains> s = dom.Domains_List();
            model.addAttribute("s",s);
            Page="Admin/EditTeacher.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Teacher_{id}")
    public String Edit_Teacher_Details(HttpServletRequest request,Model model,@PathVariable("id") int id, Teacher MyObj,Domains dom) throws SQLException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Domains> s = dom.Domains_List();
            model.addAttribute("s",s);
            String ContactNumber=MyObj.getContactNumber();
            String Email=MyObj.getEmail();
            String Password=MyObj.getPassword();
            String Weight_Qual=MyObj.getWeight_Qual();
            String Majors = MyObj.getMajors();
            Page="Admin/EditStudent.html";
            if(MyObj.Update_Teacher_Ind(id,Weight_Qual,Majors,ContactNumber,Email,Password))
            {
                Page="Admin/Success.html";
            }
            else
            {
                Page="Admin/Failure.html";
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

    @GetMapping("/Add_Admin_Ind")
    public String Add_Admin(HttpServletRequest request,Model model) throws ClassNotFoundException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture", "/img/Student_Male.png");
            if(session.getAttribute("Role").equals("Administrator"))
            {
                Page = "Admin/Add_Admin.html";}
            else
            {
                Page = "Admin/Access.html";}
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Admin_Ind")
    public String AddAdmin(HttpServletRequest request,Model model, Admin MyObj) throws SQLException, ClassNotFoundException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Message","");
            model.addAttribute("Picture","/img/Student_Male.png");
            String Name=MyObj.getName();
            String CNIC=MyObj.getCNIC();
            String ContactNumber=MyObj.getContactNumber();
            String Email=MyObj.getEmail();
            String LoginId=MyObj.getLoginId();
            String Password=MyObj.getPassword();
            String Address=MyObj.getAddress();
            String Role=MyObj.getRole();
            if(MyObj.Add_Admin_Ind(Name,Email,Address,CNIC,Role,ContactNumber,LoginId,Password))
            {
                model.addAttribute("Message","Admin Added Successfully!");
            }
            else
            {
                model.addAttribute("Message","Admin could'nt be added!");
            }
            Page="Admin/Add_Admin.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @RequestMapping("/Delete_Admin_{id}")
    public String Delete_Admin(HttpServletRequest request,Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            MyObj.Delete_Admin(id);
            Page="Admin/Welcome.html";
        }
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @GetMapping("/Edit_Admin_{id}")
    public String Edit_Admin(HttpServletRequest request,Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            ArrayList<Admin> d = MyObj.GetData(id);
            model.addAttribute("d",d);
            Page="Admin/EditAdmin.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Edit_Admin_{id}")
    public String Edit_Admin_Details(HttpServletRequest request,Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException, ParseException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            String ContactNumber=MyObj.getContactNumber();
            String Email=MyObj.getEmail();
            String Password=MyObj.getPassword();
            String Address=MyObj.getAddress();
            String Role=MyObj.getRole();
            Page="Admin/EditAdmin.html";
            if(MyObj.Update_Admin_Ind(id,ContactNumber,Email,Password,Role,Address))
            {
                Page="Admin/Success.html";
            }
            else
            {
                Page="Admin/Failure.html";
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


    @GetMapping("/Add_Students_csv")
    public String AddCSVs(HttpServletRequest request,Model model) throws SQLException {
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            Page="Admin/AddStudents.html";}
        else
        {
            String Message = "You Need to Login First.";
            model.addAttribute("Message", Message);
            Page = "Login/Admin.html";
        }
        return Data.Connection(Page,Error_Page);
    }

    @PostMapping("/Add_Students_csv")
    public String ADDSTUDENTSCSV(@RequestParam("file") MultipartFile file,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws SQLException, ParseException, IOException {
        String UPLOADED_FOLDER = "src//main//files//";
        HttpSession session=request.getSession();
        if (session.getAttribute("logged_in_admin")!=null)
        {
            model.addAttribute("Name",(String)session.getAttribute("Name"));
            model.addAttribute("Picture","/img/Student_Male.png");
            if (file.isEmpty()) {
                model.addAttribute("Message","This File is Empty.");
            }
            else{
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                int value= Add_Via_CSV.insert(path.toString());
                if(value==0)
                {
                    String Message = "There seems to be some Problem. Please Check the Format of Data in your CSV File.";
                    model.addAttribute("Message",Message);
                }
                else
                {
                    String Message = value+" Students Added Successfully.";
                    model.addAttribute("Message",Message);
                }
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

}
