package com.ppro.projekt.web;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BaseController {

    private SpravaDb spravaDb;
    private InitDbService initDbService;

    public BaseController(@Autowired SpravaDb spravaDb,
                          @Autowired InitDbService initDbService) {
        this.spravaDb = spravaDb;
        this.initDbService = initDbService;
    }

    @RequestMapping("/nahratdata")
    @ResponseBody
    public String nahratdata(Model model, HttpSession session) {
        initDbService.initDb();
    return "<script>alert('Data úspěšně nahrána');window.location.replace('/');</script>";
    }

    @RequestMapping("/")
    public String zobrazeni(Model model) {

        //TODO výpis autora ke knize
        List<Kniha> knihy = spravaDb.najdiVsechnyKnihy();
        model.addAttribute("knihy", knihy);
        return "view";
    }

}
