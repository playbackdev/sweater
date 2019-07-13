package com.shvetsovg.sweater.controller;

import com.shvetsovg.sweater.Service.UserService;
import com.shvetsovg.sweater.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){

        if(user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are cannot be different!");
        }
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = ControlleUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }
        if(!userService.addUser(user)) {
            model.addAttribute("usernameError", "User Exists!");
            return "registration";
        }
        model.addAttribute("message", "You successfully registered!");
        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated) {
            model.addAttribute("message", "Your account successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }

}
