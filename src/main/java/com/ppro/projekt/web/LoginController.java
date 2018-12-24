package com.ppro.projekt.web;


import com.ppro.projekt.entity.*;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {


       private SpravaDb spravaDb;

    public LoginController(@Autowired SpravaDb spravaDb) {
        this.spravaDb = spravaDb;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showForm(Model model,HttpSession session)
    {
        if(null==session.getAttribute("email"))
        {
            return "login";
        }else
        {
            String email=session.getAttribute("email").toString();
            List<Vypujcky> vypujcky = spravaDb.najdiVypujcky(email);
            model.addAttribute("vypujcky", vypujcky);
            List<Upominky> upominky = spravaDb.vypisUpominkyProUzivatele(email);
            model.addAttribute("upominky", upominky);
            List<Rezervace> rezervace = spravaDb.vypisRezervaceProUzivatele(email);
            model.addAttribute("rezervace", rezervace);
            return "login";
        }
    }


    //TODO PASS HASHING
    @RequestMapping(value = "/overlogin", method=RequestMethod.POST)
    @ResponseBody
    protected String login(HttpSession session,@RequestParam String email, @RequestParam String heslo) {

            if(spravaDb.overlogin(email,heslo))
            {

                int priv;
                if(spravaDb.privilegium(email))
                {priv = 1;} else { priv=0;}
                session.setAttribute("email", email);
                session.setAttribute("privilegium",priv);
                return "<script>window.location.replace('/login')</script>";
            }
            else
            {
                return "<script>alert('Špatný email nebo heslo, pokud vše zadáváte správně, váš účet je blokován');window.history.back();</script>";
            }
    }




    @RequestMapping("/logout")
    protected String logout(HttpSession session)
    {
        session.removeAttribute("email");
        session.removeAttribute("privilegium");
        return "/login";
    }

}
