package com.capgemini.capstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.capstore.dao.AuthenticationDAO;
import com.capgemini.capstore.utils.WebUtils;
 
@Controller
public class MainController {
	
	
	@Autowired
	AuthenticationDAO dao;
 
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        System.out.println("user:" +loginedUser);
        Object obj = principal.getClass();
		System.out.println("principle: "+obj);
		
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
 
        return "userInfoPage";
    }
    
    @RequestMapping(value = "/merchantInfo", method = RequestMethod.GET)
    public String merchantInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String merchantInfo = WebUtils.toString(loginedUser);
        model.addAttribute("merchantInfo", merchantInfo);
 
        return "merchantInfoPage";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model, Principal principal) { 
    	
		return "changePassword";
    	
    }
    @RequestMapping(value="/PasswordChangedSuccessfully")
    public void updatePassword(@RequestParam("Newpassword") String newpwd,@RequestParam("Confirmpassword") String confirmpwd,@RequestParam("mobile_no") String mobile_num ) {
    	
    	dao.passwordChange(mobile_num,newpwd,confirmpwd);
    	
    }
 
}
