package com.capgemini.capstore.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capstore.service.UserDetailsServiceImpl;
import com.capgemini.capstore.utils.WebUtils;
 
@RestController
public class MainController {
	
	
	@Autowired
	UserDetailsServiceImpl user;
	
    @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
		return user.welcomeMethod(model);
    	
       
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        return user.adminPage(model, principal);
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return  UserDetailsServiceImpl.loginPage(model);
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
		return UserDetailsServiceImpl.logoutSuccessfulPage(model);
        
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
 
}
