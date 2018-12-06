package com.ppro.projekt.web;

import com.ppro.projekt.entity.Uzivatele;
import com.ppro.projekt.service.InitDbService;
import com.ppro.projekt.service.SpravaDb;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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
        boolean uzivatelexist = spravaDb.existujeuzivatel(email);
        if(uzivatelexist == true)
        {
           // TODO HASH CODING
            return "<script>alert('Email je již registrován');window.history.back();</script>";
        }else
        {
    if(heslo1.equals(heslo2)) {
        Uzivatele uzivatel = new Uzivatele(jmeno, prijmeni, mesto, ulice, cpp, psc, email, heslo1, false);
        spravaDb.vlozUzivatele(uzivatel);
        return "<script>alert('Uživatel vložen');window.location.replace('/login');</script>";
    }
        return "<script>alert('Hesla se neshodují');window.history.back();</script>";
    }
    }



}
