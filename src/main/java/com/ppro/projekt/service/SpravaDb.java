package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;

import java.util.List;

public interface SpravaDb {

    void vlozKnihu(Kniha Kniha);

    void vratVypujcku(int id);
    void odstranKnihu(int id);
    void odstranRezervaci(int id);
    void upravRezervaci(int idecko,String rezer_od,String rezer_do);
    void prevedRezervaciNaVypujcku(int idecko);
    void upravVypujcku(int idecko,String datum_vypujceni,String vypujceno_do,Boolean vraceno);
    void upravUpominku (int idecko,int pokuta,String popis);
    void upravKnihu (int idecko,String nazev,String jazyk,String zanr,String nakladatelstvi,String datum_vydani,String isbn,int pocet_kusu, int pocet_stran,String popis);
    void odstranUpominku(int idecko);
    void odstranRecenzi(int idecko);
    boolean dostupnost(int idecko);

    List<Upominka> najdiUpoPodleId(int id);
    List<Rezervace> najdiRezPodleId(int id);
    List<Vypujcka> najdiVypPodleId(int id);
    List<Kniha> najdiKniPodleId(int id);

    List<Upominka> vypisUpominky();
    List<Rezervace> vypisRezervace();
    List<Recenze> vypisRecenze();
    List<Vypujcka> najdiVypujcky(String email);
    List<Vypujcka> najdiVypujckyProSpravu();
    List<Kniha> filtrace(String zanr,String jazyk,String nakladatelstvi);
    List<Kniha> najdiVsechnyKnihy();

}
