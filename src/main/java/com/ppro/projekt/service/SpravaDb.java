package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;

import java.util.List;

public interface SpravaDb {

    void vlozKnihu(Kniha Kniha);

    void vlozUzivatele(Uzivatel Uzivatel);

    void odstranVypujcku(int id);
    void odstranKnihu(int id);
    void odstranRezervaci(int id);
    void upravRezervaci(int idecko,String rezer_od,String rezer_do);
    void upravVypujcku(int idecko,String datum_vypujceni,String vypujceno_do,Boolean vraceno);
    void upravUpominku (int idecko,int pokuta,String popis);
    void upravKnihu (int idecko,String nazev,String jazyk,String zanr,String nakladatelstvi,String datum_vydani,String isbn,int pocet_kusu, int pocet_stran,String popis);
    void odstranUpominku(int idecko);
    void odstranUzivatele(int idecko);
    void odstranRecenzi(int idecko);
    void odblokovatuzivatele(int idecko);
    void blokovatuzivatele(int idecko);
    void nastavitVypujcku(int idecko,String email);
    boolean existujeuzivatel(String email);
    boolean overlogin(String email,String heslo);
    boolean dostupnost(int idecko);

    boolean privilegium(String email);

    List<Upominka> najdiUpoPodleId(int id);
    List<Rezervace> najdiRezPodleId(int id);
    List<Vypujcka> najdiVypPodleId(int id);
    List<Kniha> najdiKniPodleId(int id);

    List<Rezervace>  vypisRezervaceProUzivatele(String email);
    List<Upominka>  vypisUpominkyProUzivatele(String email);
    List<Upominka> vypisUpominky();
    List<Uzivatel> vypisUzivatele();
    List<Rezervace> vypisrezervace();
    List<Recenze> vypisrecenze();
    List<Vypujcka> najdiVypujcky(String email);
    List<Vypujcka> najdiVypujckyProSpravu();
    List<Kniha> filtrace(String zanr,String jazyk,String nakladatelstvi);
    List<Kniha> najdiVsechnyKnihy();

}
