package com.ppro.projekt.web;

import com.ppro.projekt.ProjektTools;
import com.ppro.projekt.entity.Uzivatel;
import com.ppro.projekt.service.SpravaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegistraceController {


    private SpravaDb spravaDb;


    public RegistraceController(@Autowired SpravaDb spravaDb
                         ) {
        this.spravaDb = spravaDb;

    }


    @RequestMapping(value = "/registrace",method = RequestMethod.GET)
    public String showForm()
    {
        return "registrace";
    }


    @RequestMapping(value = "/vlozituzivatele", method=RequestMethod.POST)
    @ResponseBody
    protected String editace(@RequestParam String jmeno, @RequestParam String prijmeni, @RequestParam String mesto, @RequestParam String ulice, @RequestParam String cpp
    ,@RequestParam int psc,@RequestParam String email,@RequestParam String heslo1,@RequestParam String heslo2)
    {
        boolean uzivatelexist = spravaDb.existujeUzivatel(email);
        if(uzivatelexist)
        {
            return "<script>alert('Email je již registrován');window.history.back();</script>";
        }else
        {
    if(heslo1.equals(heslo2)) {
        String hesloHash;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(heslo1.getBytes());

            hesloHash = ProjektTools.bytesToHex(md.digest());
        } catch ( NoSuchAlgorithmException e ) {
            System.out.println("SHA-256 neni k dispozici");
            hesloHash = heslo1;
        }

        Uzivatel uzivatel = new Uzivatel(jmeno, prijmeni, mesto, ulice, cpp, psc, email, hesloHash, false,0);
        spravaDb.vlozUzivatele(uzivatel);
        return "<script>alert('Uživatel vložen');window.location.replace('/login');</script>";
    }
        return "<script>alert('Hesla se neshodují');window.history.back();</script>";
    }
    }



}
