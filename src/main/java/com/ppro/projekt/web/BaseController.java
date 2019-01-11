package com.ppro.projekt.web;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import com.ppro.projekt.service.UzivatelDb;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BaseController implements ErrorController {

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

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return "<h1>Na stránce nastala chyba</h1><img src=\"img/error.png\"\n" +
                "         width=\"128" +
                "\" height=\"128\"> <br>Omlouváme se, pracujeme na odstranění<br> Status code: "+statusCode;
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

        spravaDb.vytvorNoveUpominky();
        spravaDb.smazStareRezervace();

        return "view";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
