package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.services.AuthenticationService;
import com.faisal.superduperdrive.models.User;
import com.faisal.superduperdrive.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String submitRegisterForm(User user , Model model){
        if(!userService.isAvailableUsername(user.getUsername())){
            model.addAttribute("error",true);
            return "signup";
        }
        userService.createUser(user);
        model.addAttribute("success",true);
        return "signup";
    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
