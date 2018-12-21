package com.ppro.projekt.web;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Uzivatele;
import com.ppro.projekt.entity.Vypujcky;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpravaController {

    private SpravaDb spravaDb;

    public SpravaController(@Autowired SpravaDb spravaDb) {
        this.spravaDb = spravaDb;
    }

    @RequestMapping(value = "/sprava",method = RequestMethod.GET)
    public String showForm(Model model,HttpSession session)
    {
        if(null==session.getAttribute("privilegium"))
        {
            return "login";
        }else
        {
            int priv = Integer.parseInt(session.getAttribute("privilegium").toString());
            if(priv==1)
            {

                List<Rezervace> rezervace = spravaDb.vypisrezervace();
                model.addAttribute("rezervace", rezervace);
                List<Vypujcky> vypujcky = spravaDb.najdiVypujckyProSpravu();
                model.addAttribute("vypujcky", vypujcky);
                return "/sprava";
            }else
            {
                return "/view";
            }
        }
    }

    @RequestMapping(value = "/smazatrezervaci", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatrezervaci(@RequestParam int idecko)
    {
        spravaDb.odstranRezervaci(idecko);
        return "<script>alert('Rezervace smazána');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/smazatvypujcku", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatvypujcku(@RequestParam int idecko)
    {
        spravaDb.odstranVypujcku(idecko);
        return "<script>alert('Výpůjčka smazána');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/vlozitknihu", method=RequestMethod.POST)
    @ResponseBody
    protected String editace(@RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            , @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis)
    {
        Kniha kniha = new Kniha(nazev,popis,zanr,datum_vydani,pocet_stran,nakladatelstvi,isbn,jazyk);
        spravaDb.vlozKnihu(kniha);
        return "<script>alert('Kniha vložena');window.location.replace('/');</script>";
    }

    @RequestMapping(value = "/editacerezervace", method=RequestMethod.GET)
    protected String editrezer(@RequestParam int idecko, Model model) {
        List<Rezervace> rezervace = spravaDb.najdiRezPodleId(idecko);
        model.addAttribute("rezervace", rezervace);
        return "editace-rezervace";
    }

    @RequestMapping(value = "/editacevypujcky", method=RequestMethod.GET)
    protected String editvypujcky(@RequestParam int idecko, Model model) {
        List<Vypujcky> vypujcky = spravaDb.najdiVypPodleId(idecko);
        model.addAttribute("vypujcky", vypujcky);
        return "editace-vypujcky";
    }



    @RequestMapping(value = "/upravvypujcku", method=RequestMethod.GET)
    @ResponseBody
    protected String editvyp(@RequestParam(value = "vraceno", defaultValue = "false") final String vraceno,@RequestParam int idecko
    ,@RequestParam String datum_vypujceni,@RequestParam String vypujceno_do) {

        if(vraceno.endsWith("on"))
        {
            spravaDb.upravVypujcku(idecko,datum_vypujceni,vypujceno_do,true);
        }else
            {
                spravaDb.upravVypujcku(idecko,datum_vypujceni,vypujceno_do,false);
            }
        return "<script>alert('Vypujcka upravena');window.location.replace('/sprava')</script>";

    }

    @RequestMapping(value = "/upravrezervaci", method=RequestMethod.GET)
    @ResponseBody
    protected String editrezer1(@RequestParam int idecko, @RequestParam String rezervace_od, @RequestParam String rezervace_do) {

        spravaDb.upravRezervaci(idecko,rezervace_od,rezervace_do);
        return "<script>alert('Rezervace upravena');window.location.replace('/sprava')</script>";
    }

    @RequestMapping(value = "/smazatknihu", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatknihu(@RequestParam int idknihy) {
        spravaDb.odstranKnihu(idknihy);
        return "<script>alert('Kniha smazána');window.location.replace('/')</script>";
    }


}
