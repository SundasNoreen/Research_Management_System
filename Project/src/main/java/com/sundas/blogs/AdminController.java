// By SUNDAS NOREEN

package com.sundas.blogs;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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
            Admin_ID=MyObj.getAdmin_Id();
            model.addAttribute("Name",Name);
            System.out.println(Role);
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
        if (logged_in)
        {
            model.addAttribute("Name", Name);
            model.addAttribute("Picture", "/img/Student_Male.png");
            if(Role.equals("Administrator"))
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

    @GetMapping("/Add_Student_Ind")
    public String Add_Students_Here(Model model) throws ClassNotFoundException {
        if (logged_in) {
            model.addAttribute("Name", Name);
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
    public String Add(Model model, Student MyObj) throws SQLException, ClassNotFoundException, ParseException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Add_Teacher(Model model,Domains dom) throws ClassNotFoundException, SQLException {
        if (logged_in) {
            model.addAttribute("Name", Name);
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
    public String AddTeacher(Model model, Teacher MyObj, Domains dom) throws SQLException, ClassNotFoundException, ParseException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Delete_Teacher(Model model,@PathVariable("id") int id, Teacher MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Delete_Student(Model model,@PathVariable("id") String id, Student MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Edit_Student(Model model,@PathVariable("id") String id, Student MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Edit_Student_Details(Model model,@PathVariable("id") String id, Student MyObj) throws SQLException, ParseException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Edit_Teacher(Model model,@PathVariable("id") int id, Teacher MyObj, Domains dom) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Edit_Teacher_Details(Model model,@PathVariable("id") int id, Teacher MyObj,Domains dom) throws SQLException, ParseException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Add_Admin(Model model) throws ClassNotFoundException {
        if (logged_in) {
            model.addAttribute("Name", Name);
            model.addAttribute("Picture", "/img/Student_Male.png");
            if(Role.equals("Administrator"))
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
    public String AddAdmin(Model model, Admin MyObj) throws SQLException, ClassNotFoundException, ParseException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Delete_Admin(Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Edit_Admin(Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String Edit_Admin_Details(Model model,@PathVariable("id") int id, Admin MyObj) throws SQLException, ParseException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String AddCSVs(Model model) throws SQLException {
        if (logged_in)
        {
            model.addAttribute("Name",Name);
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
    public String ADDSTUDENTSCSV(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) throws SQLException, ParseException, IOException {
        String UPLOADED_FOLDER = "src//main//files//";
        if (logged_in)
        {
            model.addAttribute("Name",Name);
            model.addAttribute("Picture","/img/Student_Male.png");
            if (file.isEmpty()) {
                model.addAttribute("Message","This File is Empty.");
            }
            else{
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                int value=Add_Via_CSV.insert(path.toString());
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
