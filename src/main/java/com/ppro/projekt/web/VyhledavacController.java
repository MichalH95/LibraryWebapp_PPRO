package com.ppro.projekt.web;


import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VyhledavacController {

    private SpravaDb spravaDb;

    public VyhledavacController(@Autowired SpravaDb spravaDb) {
        this.spravaDb = spravaDb;
    }



    @RequestMapping(value = "/vyhledavani", method = RequestMethod.POST)
    protected String vyhledavani(Model model, @RequestParam (value = "druhvyhledavani",required = true) int druhvyhledavani, @RequestParam (value = "zanr",required = false) String zanr, @RequestParam (value = "jazyk",required = false) String jazyk, @RequestParam (value = "nakladatelstvi",required = false) String nakladatelstvi)
    {
        if(druhvyhledavani==0) {
            List<Kniha> knihy = spravaDb.najdiVsechnyKnihy();
            model.addAttribute("knihy", knihy);

//        List<Autori> autori=spravaDb.NajdiAutoraByKnihaId();
//        model1.addAttribute("autori", autori);
            return "view";
        }else
            {

                List<Kniha> knihy = spravaDb.filtrace(zanr,jazyk,nakladatelstvi);
                model.addAttribute("knihy", knihy);
                return "view";

            //TODO dodělat autory
            }


    }

}
