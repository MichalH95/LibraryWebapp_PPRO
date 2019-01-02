package com.ppro.projekt.web;


import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VyhledavacController {

    private SpravaDb spravaDb;

    public VyhledavacController(@Autowired SpravaDb spravaDb) {
        this.spravaDb = spravaDb;
    }

    @RequestMapping(value = "/vyhledavani", method = RequestMethod.POST)
    protected String vyhledavani(Model model, @RequestParam(value = "druhvyhledavani", required = true) int druhvyhledavani, @RequestParam(value = "zanr", required = false) String zanr, @RequestParam(value = "jazyk", required = false) String jazyk, @RequestParam(value = "nakladatelstvi", required = false) String nakladatelstvi
            , @RequestParam(value = "hledani", required = false) String hledani) {
        List<Kniha> knihy;
        if (druhvyhledavani == 0) {
            knihy = spravaDb.najdiVsechnyKnihy();
        } else {
            knihy = spravaDb.filtrace(zanr, jazyk, nakladatelstvi,hledani);
        }
        model.addAttribute("knihy", knihy);
        return "view";
    }

}
