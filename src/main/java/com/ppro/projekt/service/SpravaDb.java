package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Uzivatele;

import java.util.List;

public interface SpravaDb {

    void vlozKnihu(Kniha Kniha);

    void vlozUzivatele(Uzivatele Uzivatele);

    void odstranKnihu(int id);

    boolean existujeuzivatel(String email);
    boolean overlogin(String email,String heslo);

    Kniha najdiPodleId(long id);

    List<Kniha> filtrace(String zanr,String jazyk,String nakladatelstvi);
    List<Kniha> najdiVsechnyKnihy();

}
