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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                redirectAttributes.addFlashAttribute("status",0);
                return "redirect:/login";
            }
        }
    }


    @RequestMapping(value = "/smazatrecenzi", method=RequestMethod.GET)
    protected String smazatrecenzi(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.odstranRecenzi(idecko);
        redirectAttributes.addFlashAttribute("message", "Recenze smazána");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatrezervaci", method=RequestMethod.GET)
    protected String smazatrezervaci(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.odstranRezervaci(idecko);
        redirectAttributes.addFlashAttribute("message", "Rezervace smazána");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }


    @RequestMapping(value = "/smazatuzivatele", method=RequestMethod.GET)
    protected String smazatuzivatele(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        uzivatelDb.odstranUzivatele(idecko);
        redirectAttributes.addFlashAttribute("message", "Uživatel smazán");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatvypujcku", method=RequestMethod.GET)
    protected String smazatvypujcku(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.vratVypujcku(idecko);
        redirectAttributes.addFlashAttribute("message", "Výpůjčka vrácena");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatupominku", method=RequestMethod.GET)
    protected String smazatupominku(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.odstranUpominku(idecko);
        redirectAttributes.addFlashAttribute("message", "Upomínka smazána");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/vytvorupominku", method=RequestMethod.POST)
    protected String vytvorupominku(RedirectAttributes redirectAttributes,@RequestParam int idvypujcky,@RequestParam int iduzivatele, @RequestParam int idknihy,
                                  @RequestParam int pokuta, @RequestParam String popis)
    {
        uzivatelDb.vlozUpominku(iduzivatele,idknihy,pokuta,popis,idvypujcky);
        redirectAttributes.addFlashAttribute("message", "Upomínka vložena");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }

    @RequestMapping(value = "/smazatautora", method=RequestMethod.GET)
    protected String smazatautora(RedirectAttributes redirectAttributes,@RequestParam int idautora)
    {
        spravaDb.odstranAutora(idautora);
        redirectAttributes.addFlashAttribute("message", "Autor smazán");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }

    @RequestMapping(value = "/vlozupominku", method=RequestMethod.GET)
    protected String vlozupominku(Model model,RedirectAttributes redirectAttributes,@RequestParam int idvypujcky,@RequestParam int iduzivatele, @RequestParam int idknihy)
    {
        model.addAttribute("idknihy",idknihy);
        model.addAttribute("idvypujcky",idvypujcky);
        model.addAttribute("iduzivatele",iduzivatele);
        return "upominka-form";
    }


    @RequestMapping(value = "/vlozitknihu", method=RequestMethod.POST)
    protected String editace(RedirectAttributes redirectAttributes, @RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            ,@RequestParam int pocet_kusu, @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis)
    {
        Kniha kniha = new Kniha(nazev,popis,zanr,datum_vydani,pocet_stran,nakladatelstvi,isbn,pocet_kusu,jazyk);
        spravaDb.vlozKnihu(kniha);
        redirectAttributes.addFlashAttribute("message", "Kniha vložena");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }

    @RequestMapping(value = "/vlozautora", method=RequestMethod.POST)
    protected String vlozautora(RedirectAttributes redirectAttributes, @RequestParam int knihaID, @RequestParam String jmeno,@RequestParam String vztah)
    {
        spravaDb.vlozAutora(knihaID,jmeno,vztah);
        redirectAttributes.addFlashAttribute("message", "Autor vložen");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }


    @RequestMapping(value = "/blokovatuzivatele", method=RequestMethod.GET)
    protected String blokovatuzivatele(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        uzivatelDb.blokovatUzivatele(idecko);
        redirectAttributes.addFlashAttribute("message", "Uživatel blokován");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/odblokovatuzivatele", method=RequestMethod.GET)
    protected String odblokovatuzivatele(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        uzivatelDb.odblokovatUzivatele(idecko);
        redirectAttributes.addFlashAttribute("message", "Uživatel odblokován");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/rezervacinavypujcku", method=RequestMethod.GET)
    protected String rezervacinavypujcku(@RequestParam int idecko, RedirectAttributes redirectAttributes)
    {
        spravaDb.prevedRezervaciNaVypujcku(idecko);
        redirectAttributes.addFlashAttribute("message", "Rezervace převedena na výpůjčku");
        redirectAttributes.addFlashAttribute("status",1);
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
        List<Vypujcka> vypujcka = spravaDb.najdiVypPodleId(idecko);
        model.addAttribute("vypujcka", vypujcka);
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
    protected String editupo(RedirectAttributes redirectAttributes, @RequestParam int idecko,@RequestParam int pokuta,@RequestParam String popis) {
        spravaDb.upravUpominku(idecko,pokuta,popis);
        redirectAttributes.addFlashAttribute("message", "Upomínka upravena");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/sprava";
    }



    @RequestMapping(value = "/upravknihu", method=RequestMethod.GET)
    protected String editknihy(RedirectAttributes redirectAttributes, @RequestParam int idecko,@RequestParam String nazev, @RequestParam String jazyk, @RequestParam String zanr, @RequestParam String nakladatelstvi
            ,@RequestParam int pocet_kusu, @RequestParam int pocet_stran, @RequestParam String isbn, @RequestParam String datum_vydani, @RequestParam String popis) {

        spravaDb.upravKnihu(idecko,nazev,jazyk,zanr,nakladatelstvi,datum_vydani,isbn,pocet_kusu,pocet_stran,popis);
        redirectAttributes.addFlashAttribute("message", "Kniha upravena");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }


    @RequestMapping(value = "/upravvypujcku", method=RequestMethod.GET)
    protected String editvyp(RedirectAttributes redirectAttributes, @RequestParam(value = "vraceno", defaultValue = "false") final String vraceno,@RequestParam int idecko
    ,@RequestParam String datum_vypujceni,@RequestParam String vypujceno_do) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date datum_vyp = dateFormat.parse(datum_vypujceni);
            Date vyp_do = dateFormat.parse(vypujceno_do);
            if (vraceno.endsWith("on")) {
                spravaDb.upravVypujcku(idecko, datum_vyp, vyp_do, true);
            } else {
                spravaDb.upravVypujcku(idecko, datum_vyp, vyp_do, false);
            }
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("message", "Výpůjčka neupravena, špatné formátování data");
            redirectAttributes.addFlashAttribute("status",0);
            return "redirect:/sprava";
        }

        redirectAttributes.addFlashAttribute("message", "Výpůjčka upravena");
        redirectAttributes.addFlashAttribute("status", 1);
        return "redirect:/sprava";
    }

    @RequestMapping(value = "/upravrezervaci", method=RequestMethod.GET)
    protected String editrezer1(RedirectAttributes redirectAttributes, @RequestParam int idecko, @RequestParam String rezervace_od, @RequestParam String rezervace_do) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date rez_od = dateFormat.parse(rezervace_od);
            Date rez_do = dateFormat.parse(rezervace_do);
            spravaDb.upravRezervaci(idecko,rez_od,rez_do);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("message", "Rezervace neupravena, špatné formátování data");
            redirectAttributes.addFlashAttribute("status",0);
            return "redirect:/sprava";
        }
        redirectAttributes.addFlashAttribute("message", "Rezervace upravena");
        redirectAttributes.addFlashAttribute("status",1);

        return "redirect:/sprava";
    }

    @RequestMapping(value = "/smazatknihu", method=RequestMethod.GET)
    protected String smazatknihu(RedirectAttributes redirectAttributes, @RequestParam int idknihy) {
        spravaDb.odstranKnihu(idknihy);
        redirectAttributes.addFlashAttribute("message", "Kniha smazána");
        redirectAttributes.addFlashAttribute("status",1);
        return "redirect:/";
    }

    @RequestMapping(value = "/filtraceSpravyRez", method=RequestMethod.GET)
    protected String filtraceSpravyRez(Model model,@RequestParam String nazevknihy) {
        model.addAttribute("rezervace",spravaDb.filtraceProRez(nazevknihy));
        return "/sprava";
    }

    @RequestMapping(value = "/filtraceSpravyVyp", method=RequestMethod.GET)
    protected String filtraceSpravyVyp(Model model,@RequestParam String nazevknihy) {
        model.addAttribute("vypujcky",spravaDb.filtraceProVyp(nazevknihy));
        return "/sprava";
    }

    @RequestMapping(value = "/filtraceSpravyRec", method=RequestMethod.GET)
    protected String filtraceSpravyRec(Model model,@RequestParam String nazevknihy) {
        model.addAttribute("recenze",spravaDb.filtraceProRec(nazevknihy));
        return "/sprava";
    }

    @RequestMapping(value = "/filtraceSpravyUzi", method=RequestMethod.GET)
    protected String filtraceSpravyUzi(Model model,@RequestParam String emailuzivatele) {
        model.addAttribute("uzivatele",uzivatelDb.filtraceProUzi(emailuzivatele));
        return "/sprava";
    }

    @RequestMapping(value = "/filtraceSpravyUpo", method=RequestMethod.GET)
    protected String filtraceSpravyUpo(Model model,@RequestParam String emailuzivatele) {
        model.addAttribute("upominky",spravaDb.filtraceProUpo(emailuzivatele));
        return "/sprava";
    }
}
