package com.ppro.projekt.web;


import com.ppro.projekt.ProjektTools;
import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Upominka;
import com.ppro.projekt.entity.Vypujcka;
import com.ppro.projekt.service.SpravaDb;
import com.ppro.projekt.service.UzivatelDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class LoginController {

    private SpravaDb spravaDb;
    private UzivatelDb uzivatelDb;

    public LoginController(@Autowired SpravaDb spravaDb, @Autowired UzivatelDb uzivatelDb) {
        this.spravaDb = spravaDb;
        this.uzivatelDb = uzivatelDb;
    }

    @RequestMapping(value = "/overlogin", method=RequestMethod.POST)
    protected String login(HttpSession session, @RequestParam String email, @RequestParam String heslo, RedirectAttributes redirectAttributes) {
        String hesloHash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(heslo.getBytes());

            hesloHash = ProjektTools.bytesToHex(md.digest());
        } catch ( NoSuchAlgorithmException e ) {
            System.out.println("SHA-256 neni k dispozici");
            hesloHash = heslo;
        }


        if(uzivatelDb.overlogin(email,hesloHash))
        {
            int priv;
            if(uzivatelDb.privilegium(email))
            {priv = 1;} else { priv=0;}
            session.setAttribute("email", email);
            session.setAttribute("privilegium",priv);

            return "redirect:/login";
        }
        else
        {
            redirectAttributes.addFlashAttribute("message", "Špatný email nebo heslo, pokud vše zadáváte správně, váš účet je blokován");
            return "redirect:/login";
        }
    }

    @RequestMapping("/logout")
    protected String logout(HttpSession session)
    {
        session.removeAttribute("email");
        session.removeAttribute("privilegium");
        return "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String showForm(Model model,HttpSession session)
    {
        if(session.getAttribute("email") != null) {
            String email=session.getAttribute("email").toString();
            List<Vypujcka> vypujcky = spravaDb.najdiVypujcky(email);
            model.addAttribute("vypujcky", vypujcky);
            List<Upominka> upominky = uzivatelDb.vypisUpominkyProUzivatele(email);
            model.addAttribute("upominky", upominky);
            List<Rezervace> rezervace = uzivatelDb.vypisRezervaceProUzivatele(email);
            model.addAttribute("rezervace", rezervace);
        }
        return "login";
    }

}
