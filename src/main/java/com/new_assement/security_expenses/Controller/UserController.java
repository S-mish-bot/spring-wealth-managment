package com.new_assement.security_expenses.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.new_assement.security_expenses.Models.Category;
import com.new_assement.security_expenses.Models.Expense;
import com.new_assement.security_expenses.Models.UserModel;
import com.new_assement.security_expenses.Service.CategoryService;
import com.new_assement.security_expenses.Service.ExpenseService;
import com.new_assement.security_expenses.Service.UserService;
// hihihih

@Controller
public class UserController {
    private String login_value;
    private UserService userService;
    private ExpenseService expenseService;
    private CategoryService categoryService;
    public UserController(UserService userService,CategoryService categoryService,ExpenseService expenseService) {
        this.userService = userService;
        this.categoryService=categoryService;
        this.expenseService=expenseService;
        this.login_value="0";
    }

    @GetMapping("//")
    public String home(){
        return "index";
    }
    @GetMapping("/register")
    public  String registeruser(Model model){
        model.addAttribute("registerRequest",new UserModel());
        return "register_page";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginRequest",new UserModel());
        return "login";
    }

    @PostMapping("/register")
    public String registeruser(@ModelAttribute UserModel userModel){
        try{
        UserModel newu =userService.registeruser(userModel.getLogin(), userModel.getPassword(), userModel.getUser_email());
        if (newu==null){
            return "error_page_register";
        }
        return "login";}
        catch(Exception e){
            return "error_page_register";

        }
    }
    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel){
        UserModel newu= userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (newu==null){
            return "error_page_login";

        }
        else{
            this.login_value="1";
            return "show_expenses";
        }
        
    }

    @GetMapping("/addcategory")
    public  String addCategory(Model model){
        if (this.login_value=="1"){
        model.addAttribute("categoryadd",new Category());
        return "add_category";}
        else{
            return "login";
        }

    }

    @PostMapping("/addcategory")
    public  String addCategory(@ModelAttribute Category  category,Model model){
        if (this.login_value=="1"){
        try{
        Category catu= categoryService.saveCategory(category.getCategory_name(),category.getCategory_id());
        if (catu==null){
            model.addAttribute("anyerror","id and categoty name should be unique");
        }
        else{
            model.addAttribute("anyerror","Category Added Succesfully");
        }
        return "add_category";}
        catch(Exception e){
            model.addAttribute("anyerror","category or id is already present in the list");
            return "add_category";
        }}
        else{
            return "login";
        }

    }
    @GetMapping("/showcategories")
    public String listCategory(Model model){
        if(this.login_value=="1"){
        model.addAttribute("list",categoryService.allcategories());
        return "show_category";}
        else{
            return "login";
        }
    }
    @GetMapping("/addexpense")
    public String addexpense(Model model){
        if(this.login_value=="1"){
            model.addAttribute("newexpense", new Expense());
            return "add_expense";
            
        }
        else{return "login";}
       

    }
    @PostMapping("/addexpense")
    public String addexpense(@ModelAttribute  Expense expense,Model model){
            try{
             Expense es=expenseService.addexpense(expense.getExpense_id(), expense.getDate(), expense.getDescription(), expense.getLocation(), expense.getCategoryid());
             if (es==null){

                model.addAttribute("anyerror"," Exepnse not added! please fill all the blanks or make sure category with respective id exists");
            }
            else{
                model.addAttribute("anyerror","Expense  Added Succesfully");
            }
            return "add_expense";
        }
        catch (Exception e){
            model.addAttribute("anyerror","expense id already present ");
            return "add_expense";

        }
    }
    @GetMapping("/showexpenses")
    public String listexpenses(Model model){
    
        model.addAttribute("list",expenseService.allexpenses());
        return "show_expenses";}
       
    }
    

    

