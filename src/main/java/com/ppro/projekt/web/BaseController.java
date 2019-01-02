package com.ppro.projekt.web;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import com.ppro.projekt.service.UzivatelDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BaseController {

    private SpravaDb spravaDb;
    private UzivatelDb uzivatelDb;
    private InitDbService initDbService;

    public BaseController(@Autowired SpravaDb spravaDb,
                          @Autowired UzivatelDb uzivatelDb,
                          @Autowired InitDbService initDbService) {
        this.spravaDb = spravaDb;
        this.uzivatelDb = uzivatelDb;
        this.initDbService = initDbService;
    }

    @RequestMapping("/vypujcit")
    public String vypujcka(@RequestParam int idecko, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("email") == null) {
            redirectAttributes.addFlashAttribute("message", "Pro vytvoření výpůjčky je nutné se přihlásit");
            redirectAttributes.addFlashAttribute("status",0);
            return "redirect:/login";
        } else {
            String email = session.getAttribute("email").toString();
            if (spravaDb.dostupnost(idecko)) {
                uzivatelDb.nastavitVypujcku(idecko, email);
                redirectAttributes.addFlashAttribute("message", "Právě jste si vypůjčil knihu");
                redirectAttributes.addFlashAttribute("status",1);
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("message", "Litujeme, knihu má někdo vypůjčenou");
                redirectAttributes.addFlashAttribute("status",0);
                return "redirect:/";
            }
        }
    }

    @RequestMapping("/rezervovat")
    public String rezervace(@RequestParam int idecko, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("email") == null) {
            redirectAttributes.addFlashAttribute("message", "Pro vytvoření rezervace je nutné se přihlásit");
            redirectAttributes.addFlashAttribute("status",0);
            return "redirect:/login";
        } else {
            String email = session.getAttribute("email").toString();
            uzivatelDb.nastavitRezervaci(idecko, email);
            redirectAttributes.addFlashAttribute("message", "Právě jste si rezervoval knihu");
            redirectAttributes.addFlashAttribute("status",1);
            return "redirect:/login";
        }
    }

    @RequestMapping("/nahratdata")
    public String nahratdata(RedirectAttributes redirectAttributes) {
        initDbService.initDb();
        redirectAttributes.addFlashAttribute("message", "Data úspěšně nahrána");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }

    @RequestMapping("/")
    public String zobrazeni(Model model) {
        List<Kniha> knihy = spravaDb.najdiVsechnyKnihy();
        model.addAttribute("knihy", knihy);
        return "view";
    }

}
