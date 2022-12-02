package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.services.AuthenticationService;
import com.faisal.superduperdrive.models.User;
import com.faisal.superduperdrive.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/signup")
    public String getRegisterPage(User user){
        return "signup";
    }

    @PostMapping("/signup")
    public String submitRegisterForm(User user , RedirectAttributes attr) {
        if(user.getUsername().isEmpty()){
            attr.addFlashAttribute("error","Username cannot be empty");
            return "redirect:/signup";
        }
        if(!userService.isAvailableUsername(user.getUsername())){
            attr.addFlashAttribute("error","Username already registered");
            return "redirect:/signup";
        }
        userService.createUser(user);
        attr.addFlashAttribute("success","You successfully signed up!");
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }


}
