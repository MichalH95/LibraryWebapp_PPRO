package com.ppro.projekt.web;

import com.ppro.projekt.entity.*;
import com.ppro.projekt.service.SpravaDb;
import com.ppro.projekt.service.UzivatelDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpravaController {

    private SpravaDb spravaDb;

    private UzivatelDb uzivatelDb;

    public SpravaController(@Autowired SpravaDb spravaDb, @Autowired UzivatelDb uzivatelDb) {
        this.spravaDb = spravaDb;
        this.uzivatelDb = uzivatelDb;
    }

    @RequestMapping(value = "/sprava",method = RequestMethod.GET)
    public String showForm(RedirectAttributes redirectAttributes,Model model,HttpSession session)
    {
        if(null==session.getAttribute("privilegium"))
        {
            return "login";
        }else
        {
            int priv = Integer.parseInt(session.getAttribute("privilegium").toString());
            if(priv==1)
            {
                List<Rezervace> rezervace = spravaDb.vypisRezervace();
                model.addAttribute("rezervace", rezervace);
                List<Vypujcka> vypujcky = spravaDb.najdiVypujckyProSpravu();
                model.addAttribute("vypujcky", vypujcky);
                List<Upominka> upominky = spravaDb.vypisUpominky();
                model.addAttribute("upominky", upominky);
                List<Uzivatel> uzivatele = uzivatelDb.vypisUzivatele();
                model.addAttribute("uzivatele", uzivatele);
                List<Recenze> recenze = spravaDb.vypisRecenze();
                model.addAttribute("recenze", recenze);
                List<Kniha> nazvyknih = spravaDb.najdiVsechnyKnihy();
                model.addAttribute("nazvyK",nazvyknih);
                return "/sprava";
            }
            {
                redirectAttributes.addFlashAttribute("message", "Nemáte přístupová práva");
                return "redirect:/login";
            }
        }
    }


    @RequestMapping(value = "/smazatrecenzi", method=RequestMethod.GET)
    protected String smazatrecenzi(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.odstranRecenzi(idecko);
        redirectAttributes.addFlashAttribute("message", "Recenze smazána");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatrezervaci", method=RequestMethod.GET)
    protected String smazatrezervaci(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.odstranRezervaci(idecko);
        redirectAttributes.addFlashAttribute("message", "Rezervace smazána");
        return "redirect:/sprava";
    }


    @RequestMapping(value = "/smazatuzivatele", method=RequestMethod.GET)
    protected String smazatuzivatele(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        uzivatelDb.odstranUzivatele(idecko);
        redirectAttributes.addFlashAttribute("message", "Uživatel smazán");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatvypujcku", method=RequestMethod.GET)
    protected String smazatvypujcku(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.vratVypujcku(idecko);
        redirectAttributes.addFlashAttribute("message", "Výpůjčka vrácena");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatupominku", method=RequestMethod.GET)
    protected String smazatupominku(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.odstranUpominku(idecko);
        redirectAttributes.addFlashAttribute("message", "Upomínka smazána");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/vlozitknihu", method=RequestMethod.POST)
    protected String editace(RedirectAttributes redirectAttributes, @RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            ,@RequestParam int pocet_kusu, @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis)
    {
        Kniha kniha = new Kniha(nazev,popis,zanr,datum_vydani,pocet_stran,nakladatelstvi,isbn,pocet_kusu,jazyk);
        spravaDb.vlozKnihu(kniha);
        redirectAttributes.addFlashAttribute("message", "Kniha vložena");
        return "redirect:/";
    }

    @RequestMapping(value = "/vlozautora", method=RequestMethod.POST)
    protected String vlozautora(RedirectAttributes redirectAttributes, @RequestParam int knihaID, @RequestParam String jmeno,@RequestParam String vztah)
    {
        spravaDb.vlozAutora(knihaID,jmeno,vztah);
        redirectAttributes.addFlashAttribute("message", "Autor vložen");
        return "redirect:/";
    }


    @RequestMapping(value = "/blokovatuzivatele", method=RequestMethod.GET)
    protected String blokovatuzivatele(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        uzivatelDb.blokovatUzivatele(idecko);
        redirectAttributes.addFlashAttribute("message", "Uživatel blokován");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/odblokovatuzivatele", method=RequestMethod.GET)
    protected String odblokovatuzivatele(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        uzivatelDb.odblokovatUzivatele(idecko);
        redirectAttributes.addFlashAttribute("message", "Uživatel odblokován");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/rezervacinavypujcku", method=RequestMethod.GET)
    protected String rezervacinavypujcku(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.prevedRezervaciNaVypujcku(idecko);
        redirectAttributes.addFlashAttribute("message", "Rezervace převedena na výpůjčku");
        return "redirect:/sprava";
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
    protected String editupo(RedirectAttributes redirectAttributes, @RequestParam int idecko,@RequestParam int pokuta,@RequestParam String popis) {
        spravaDb.upravUpominku(idecko,pokuta,popis);
        redirectAttributes.addFlashAttribute("message", "Upomínka upravena");
        return "redirect:/sprava";
    }



    @RequestMapping(value = "/upravknihu", method=RequestMethod.GET)
    @ResponseBody
    protected String editknihy(RedirectAttributes redirectAttributes, @RequestParam int idecko,@RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            ,@RequestParam int pocet_kusu, @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis) {

        spravaDb.upravKnihu(idecko,nazev,jazyk,zanr,nakladatelstvi,datum_vydani,isbn,pocet_kusu,pocet_stran,popis);
        redirectAttributes.addFlashAttribute("message", "Kniha upravena");
        return "redirect:/";
    }


    @RequestMapping(value = "/upravvypujcku", method=RequestMethod.GET)
    @ResponseBody
    protected String editvyp(RedirectAttributes redirectAttributes, @RequestParam(value = "vraceno", defaultValue = "false") final String vraceno,@RequestParam int idecko
    ,@RequestParam String datum_vypujceni,@RequestParam String vypujceno_do) {

        if(vraceno.endsWith("on"))
        {
            spravaDb.upravVypujcku(idecko,datum_vypujceni,vypujceno_do,true);
        }else
            {
                spravaDb.upravVypujcku(idecko,datum_vypujceni,vypujceno_do,false);
            }
        redirectAttributes.addFlashAttribute("message", "Výpůjčka upravena");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/upravrezervaci", method=RequestMethod.GET)
    @ResponseBody
    protected String editrezer1(RedirectAttributes redirectAttributes, @RequestParam int idecko, @RequestParam String rezervace_od, @RequestParam String rezervace_do) {
        spravaDb.upravRezervaci(idecko,rezervace_od,rezervace_do);
        redirectAttributes.addFlashAttribute("message", "Výpůjčka upravena");
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatknihu", method=RequestMethod.GET)
    @ResponseBody
    protected String smazatknihu(RedirectAttributes redirectAttributes, @RequestParam int idknihy) {
        spravaDb.odstranKnihu(idknihy);
        redirectAttributes.addFlashAttribute("message", "Kniha smazána");
        return "redirect:/";
    }


}
