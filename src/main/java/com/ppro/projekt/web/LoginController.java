package com.ppro.projekt.web;


import com.ppro.projekt.ProjektTools;
import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Upominky;
import com.ppro.projekt.entity.Vypujcky;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    @RequestMapping(value = "/overlogin", method=RequestMethod.POST)
    @ResponseBody
    protected String login(HttpSession session,@RequestParam String email, @RequestParam String heslo) {
        String hesloHash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(heslo.getBytes());

            hesloHash = ProjektTools.bytesToHex(md.digest());
        } catch ( NoSuchAlgorithmException e ) {
            System.out.println("SHA-256 neni k dispozici");
            hesloHash = heslo;
        }


        if(spravaDb.overlogin(email,hesloHash))
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
