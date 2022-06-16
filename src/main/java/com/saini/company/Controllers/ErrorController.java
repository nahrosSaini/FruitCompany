package com.saini.company.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showErrorPage(){
        return "error";
    }
}
