package com.ppro.projekt.service;

import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Upominka;
import com.ppro.projekt.entity.Uzivatel;

import java.util.List;

public interface UzivatelDb {

    void vlozUzivatele(Uzivatel Uzivatel);

    void odstranUzivatele(int idecko);

    boolean existujeUzivatel(String email);

    Uzivatel najdiUzivatele(String email);

    void nastavitVypujcku(int idKnihy, String email);
    void odblokovatUzivatele(int idecko);
    void blokovatUzivatele(int idecko);
    boolean overlogin(String email, String heslo);
    boolean privilegium(String email);

    List<Rezervace>  vypisRezervaceProUzivatele(String email);
    List<Upominka>  vypisUpominkyProUzivatele(String email);
    List<Uzivatel> vypisUzivatele();

}
