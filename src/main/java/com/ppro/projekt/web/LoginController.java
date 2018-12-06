package com.ppro.projekt.web;


import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private SpravaDb spravaDb;

    public LoginController(@Autowired SpravaDb spravaDb) {
        this.spravaDb = spravaDb;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showForm()
    {
        return "login";
    }


    //TODO PASS HASHING
    @RequestMapping(value = "/overlogin", method=RequestMethod.POST)
    @ResponseBody
    protected String login(HttpSession session,@RequestParam String email, @RequestParam String heslo) {

            if(spravaDb.overlogin(email,heslo) == true)
            {
                session.setAttribute("email", email);
                return "<script>window.location.replace('/login')</script>";
            }
            else
            {
                return "<script>alert('Špatný email nebo heslo');window.history.back();</script>";
            }
    }

    @RequestMapping("/logout")
    protected String logout(HttpSession session)
    {
        session.removeAttribute("email");
        return "/login";
    }

}
