package com.ppro.projekt.web;

import com.ppro.projekt.ProjektTools;
import com.ppro.projekt.entity.Uzivatel;
import com.ppro.projekt.service.SpravaDb;
import com.ppro.projekt.service.UzivatelDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Controller
public class RegistraceController {


    private SpravaDb spravaDb;

    private UzivatelDb uzivatelDb;

    public RegistraceController(@Autowired SpravaDb spravaDb, @Autowired UzivatelDb uzivatelDb) {
        this.spravaDb = spravaDb;
        this.uzivatelDb = uzivatelDb;
    }


    @RequestMapping(value = "/registrace", method = RequestMethod.GET)
    public String showForm() {
        return "registrace";
    }


    @RequestMapping(value = "/vlozituzivatele", method = RequestMethod.POST)
    protected String editace(RedirectAttributes redirectAttributes, @RequestParam String jmeno, @RequestParam String prijmeni, @RequestParam String mesto, @RequestParam String ulice, @RequestParam String cpp
            , @RequestParam int psc, @RequestParam String email, @RequestParam String heslo1, @RequestParam String heslo2) {
        boolean uzivatelexist = uzivatelDb.existujeUzivatel(email);
        if (uzivatelexist) {
            redirectAttributes.addFlashAttribute("message", "Email je již registrován");
            redirectAttributes.addFlashAttribute("status",0);
            return "redirect:/registrace";
        } else {
            if (heslo1.equals(heslo2)) {
                String hesloHash;
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");

                    md.update(heslo1.getBytes());

                    hesloHash = ProjektTools.bytesToHex(md.digest());
                } catch (NoSuchAlgorithmException e) {
                    System.out.println("SHA-256 neni k dispozici");
                    hesloHash = heslo1;
                }

                Uzivatel uzivatel = new Uzivatel(jmeno, prijmeni, mesto, ulice, cpp, psc, email, hesloHash, false, 0);
                uzivatelDb.vlozUzivatele(uzivatel);
                redirectAttributes.addFlashAttribute("message", "Uživatel vložen");
                redirectAttributes.addFlashAttribute("status",1);
                return "redirect:/login";
            }
            redirectAttributes.addFlashAttribute("message", "Hesla se neshodují");
            redirectAttributes.addFlashAttribute("status",0);
            return "redirect:/registrace";
        }
    }


}
