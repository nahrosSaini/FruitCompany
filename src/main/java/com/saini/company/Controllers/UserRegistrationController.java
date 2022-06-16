package com.saini.company.Controllers;

import com.saini.company.DTO.UserRegistrationDTO;
import com.saini.company.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registerForm", method = RequestMethod.GET)
    public String showRegisterForm(Model model){
        UserRegistrationDTO userRegistrationDTO=new UserRegistrationDTO();
        model.addAttribute("userDao",userRegistrationDTO);
        return "registration";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO userRegistrationDTO){
        userService.saveUser(userRegistrationDTO);
        return "redirect:/registration/registerForm?success";
    }


}
