package app.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrintUserController {

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String printUser() {
        SecurityContext sc = SecurityContextHolder.getContext();
        System.out.println("Logged User: " + sc.getAuthentication().getName());
        System.out.println("authorities" + sc.getAuthentication().getAuthorities());
        return "print";
    }

    @RequestMapping("/welcome")
    @ResponseBody()
    public String helloUser() {
    	SecurityContext sc = SecurityContextHolder.getContext();
    	return "Witaj" + " " + sc.getAuthentication().getName();
    }
}