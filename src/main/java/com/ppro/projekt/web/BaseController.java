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
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String vypujcka(@RequestParam int idecko, HttpSession session) {
        if (null == session.getAttribute("email")) {
            return "<script>alert('Pro vytvoření výpůjčky je nutné se přihlásit');window.location.replace('/login');</script>";
        } else {
            String email = session.getAttribute("email").toString();
            if (spravaDb.dostupnost(idecko)) {
                uzivatelDb.nastavitVypujcku(idecko, email);
                return "<script>alert('Právě jste si vypůjčil knihu');window.location.replace('/login');</script>";
            } else {
                return "<script>alert('Litujeme, knihu si někdo právě vypůjčil');window.location.replace('/');</script>";
            }
        }
    }

    @RequestMapping("/rezervovat")
    @ResponseBody
    public String rezervace(@RequestParam int idecko, HttpSession session) {
        if (null == session.getAttribute("email")) {
            return "<script>alert('Pro vytvoření rezervace je nutné se přihlásit');window.location.replace('/login');</script>";
        } else {
            String email = session.getAttribute("email").toString();
            uzivatelDb.nastavitRezervaci(idecko, email);
            return "<script>alert('Právě jste si rezervoval knihu');window.location.replace('/login');</script>";
        }
    }

    @RequestMapping("/nahratdata")
    @ResponseBody
    public String nahratdata() {
        initDbService.initDb();
        return "<script>alert('Data úspěšně nahrána');window.location.replace('/');</script>";
    }

    @RequestMapping("/")
    public String zobrazeni(Model model) {
        List<Kniha> knihy = spravaDb.najdiVsechnyKnihy();
        model.addAttribute("knihy", knihy);
        return "view";
    }

}
