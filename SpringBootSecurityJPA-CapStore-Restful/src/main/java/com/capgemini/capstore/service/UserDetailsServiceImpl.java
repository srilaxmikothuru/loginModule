package com.capgemini.capstore.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.capgemini.capstore.dao.AuthenticationDAO;
import com.capgemini.capstore.entities.Authentication;
import com.capgemini.capstore.utils.WebUtils;
 

@Component(value="userService")
public  class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AuthenticationDAO authenticationDAO;
 
    @Override
    public UserDetails loadUserByUsername(String mobile_no) throws UsernameNotFoundException {
        Authentication authenticate = this.authenticationDAO.findUserAccount(mobile_no);
 
        if (authenticate == null) {
            System.out.println("User not found! " + mobile_no);
            throw new UsernameNotFoundException("User " + mobile_no + " was not found in the database");
        }
 
        System.out.println("Found User: " +authenticate);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.authenticationDAO.getRoleNames(authenticate.getMobileNo());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(authenticate.getMobileNo(),authenticate.getPassword(), grantList);
 
        return userDetails;
    }

	
	public  String welcomeMethod(Model model) {
		model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";	
	}

	public  String adminPage(Model model, Principal principal) {
		//User loginedUser = (User) ((Authentication) principal.
		Object obj = principal.getClass();
		System.out.println(obj);
		 
        //String userInfo = WebUtils.toString(loginedUser);
        //model.addAttribute("userInfo", userInfo);
         
        return "adminPage";
	}
	
	public static String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
	}

	public static String loginPage(Model model) {
		// TODO Auto-generated method stub
		return "loginPage";
	}
	
       
	
	
	
}