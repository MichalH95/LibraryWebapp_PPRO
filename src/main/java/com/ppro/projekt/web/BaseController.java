package com.ppro.projekt.web;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/")
    public String zobrazeni(Model model, Model model1) {
        initDbService.initDb();
        List<Kniha> knihy = spravaDb.najdiVsechnyKnihy();
        model.addAttribute("knihy", knihy);
//        List<Autori> autori=spravaDb.NajdiAutoraByKnihaId();
//        model1.addAttribute("autori", autori);
        return "view";
    }

}
