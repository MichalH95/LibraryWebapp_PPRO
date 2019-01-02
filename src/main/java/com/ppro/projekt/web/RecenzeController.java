package com.ppro.projekt.web;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Recenze;
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
import java.util.List;

@Controller
public class RecenzeController {

    private SpravaDb spravaDb;
    private UzivatelDb uzivatelDb;

    public RecenzeController(@Autowired SpravaDb spravaDb, @Autowired UzivatelDb uzivatelDb) {
        this.spravaDb = spravaDb;
        this.uzivatelDb = uzivatelDb;
    }


    @RequestMapping("/recenze")
    public String zobrazeni(Model model) {
        return "recenze";
    }

    @RequestMapping(value = "/zobrazrecenzi", method= RequestMethod.GET)
    protected String smazatupominku(RedirectAttributes redirectAttributes,Model model,@RequestParam String nazevknihy)
    {
        List<Recenze> recenze=spravaDb.najdiRecenzi(nazevknihy);
        model.addAttribute("recenze",recenze);
        if(recenze.isEmpty())
        {
            redirectAttributes.addFlashAttribute("message", "Název neodpovídá žádné knize v databázi, nebo neexistuje recenze");
            return "redirect:/recenze";
        }else
            {
            return "/recenze";
            }
        }

    @RequestMapping(value = "/ulozrecenzi", method= RequestMethod.GET)
    protected String ulozeni (HttpSession session,RedirectAttributes redirectAttributes,@RequestParam int idknihy,String emailuzivatele
    ,@RequestParam String popis,@RequestParam int hodnoceni,@RequestParam String jmeno)
    {
        redirectAttributes.addFlashAttribute("message", "Děkujeme Vám za recenzi");
        session.removeAttribute("recenze");
        spravaDb.ulozitRecenzi(idknihy,emailuzivatele,popis,hodnoceni,jmeno);
        return "redirect:/";
    }

    @RequestMapping(value = "/napsatrecenzi", method= RequestMethod.GET)
        protected String napsatrecenzi(Model model,RedirectAttributes redirectAttributes, @RequestParam int idknihy, HttpSession session)
        {
            if(uzivatelDb.zjistiRecenze(session.getAttribute("email").toString(),idknihy))
            {
                redirectAttributes.addFlashAttribute("message", "Na tuto knihu jste již recenzi napsal");
                return "redirect:/login";
            }
            {
                session.setAttribute("recenze",1);
                model.addAttribute("idkProRecen",idknihy);
                model.addAttribute("email",session.getAttribute("email").toString());
                return "/recenze";
            }
    }
}
