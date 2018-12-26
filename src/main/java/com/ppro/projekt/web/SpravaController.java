package com.ppro.projekt.web;

import com.ppro.projekt.entity.*;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                List<Vypujcka> vypujcky = spravaDb.najdiVypujckyProSpravu();
                model.addAttribute("vypujcky", vypujcky);
                List<Upominka> upominky = spravaDb.vypisUpominky();
                model.addAttribute("upominky", upominky);
                List<Uzivatel> uzivatele = spravaDb.vypisUzivatele();
                model.addAttribute("uzivatele", uzivatele);
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


    @RequestMapping(value = "/smazatuzivatele", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatuzivatele(@RequestParam int idecko)
    {
        spravaDb.odstranUzivatele(idecko);
        return "<script>alert('Uživatel smazán');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/smazatvypujcku", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatvypujcku(@RequestParam int idecko)
    {
        spravaDb.odstranVypujcku(idecko);
        return "<script>alert('Výpůjčka smazána');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/smazatupominku", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatupominku(@RequestParam int idecko)
    {
        spravaDb.odstranUpominku(idecko);
        return "<script>alert('Upomínka smazána');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/vlozitknihu", method=RequestMethod.POST)
    @ResponseBody
    protected String editace(@RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            ,@RequestParam int pocet_kusu, @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis)
    {
        Kniha kniha = new Kniha(nazev,popis,zanr,datum_vydani,pocet_stran,nakladatelstvi,isbn,pocet_kusu,jazyk);
        spravaDb.vlozKnihu(kniha);
        return "<script>alert('Kniha vložena');window.location.replace('/');</script>";
    }

    @RequestMapping(value = "/blokovatuzivatele", method=RequestMethod.GET)
    @ResponseBody
    protected String blokovatuzivatele(@RequestParam int idecko)
    {
        spravaDb.blokovatuzivatele(idecko);
        return "<script>alert('Uživatel blokován');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/odblokovatuzivatele", method=RequestMethod.GET)
    @ResponseBody
    protected String odblokovatuzivatele(@RequestParam int idecko)
    {
        spravaDb.odblokovatuzivatele(idecko);
        return "<script>alert('Uživatel odblokován');window.location.replace('/sprava');</script>";
    }

    @RequestMapping(value = "/editacerezervace", method=RequestMethod.GET)
    protected String editrezer(@RequestParam int idecko, Model model) {
        List<Rezervace> rezervace = spravaDb.najdiRezPodleId(idecko);
        model.addAttribute("rezervace", rezervace);
        return "editace-rezervace";
    }

    @RequestMapping(value = "/editacevypujcky", method=RequestMethod.GET)
    protected String editvypujcky(@RequestParam int idecko, Model model) {
        List<Vypujcka> vypujcky = spravaDb.najdiVypPodleId(idecko);
        model.addAttribute("vypujcky", vypujcky);
        return "editace-vypujcky";
    }

    @RequestMapping(value = "/editaceupominky", method=RequestMethod.GET)
    protected String editupominku(@RequestParam int idecko, Model model) {
        List<Upominka> upominky = spravaDb.najdiUpoPodleId(idecko);
        model.addAttribute("upominky", upominky);
        return "editace-upominky";
    }


    @RequestMapping(value = "/editovatknihu", method=RequestMethod.GET)
    protected String editovatknihu(@RequestParam int idecko, Model model) {
        List<Kniha> kniha = spravaDb.najdiKniPodleId(idecko);
        model.addAttribute("kniha", kniha);
        return "editace-knihy";
    }

    @RequestMapping(value = "/upravupominku", method=RequestMethod.GET)
    @ResponseBody
    protected String editupo(@RequestParam int idecko,@RequestParam int pokuta,@RequestParam String popis) {

        spravaDb.upravUpominku(idecko,pokuta,popis);
        return "<script>alert('Upominka upravena');window.location.replace('/sprava')</script>";

    }



    @RequestMapping(value = "/upravknihu", method=RequestMethod.GET)
    @ResponseBody
    protected String editknihy(@RequestParam int idecko,@RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            ,@RequestParam int pocet_kusu, @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis) {

        spravaDb.upravKnihu(idecko,nazev,jazyk,zanr,nakladatelstvi,datum_vydani,isbn,pocet_kusu,pocet_stran,popis);
        return "<script>alert('Kniha upravena');window.location.replace('/')</script>";

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
