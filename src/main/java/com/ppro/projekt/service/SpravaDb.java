package com.ppro.projekt.service;

import com.ppro.projekt.entity.*;

import java.util.Date;
import java.util.List;

public interface SpravaDb {

    void vlozKnihu(Kniha Kniha);
    void vlozRezervaci(Rezervace rezervace);
    void vytvorNoveUpominky();
    void vlozAutora(int idKnihy, String jmeno, String vztah);
    void ulozitRecenzi(int idknihy,String emailuzivatele,String popis,int hodnoceni, String jmeno);

    void prevedRezervaciNaVypujcku(int idecko);

    void vratVypujcku(int id);
    void zkontrolujRezervace(int idKnihy);

    void odstranKnihu(int id);
    void odstranAutora(int id);
    void odstranRezervaci(int id);
    void odstranUpominku(int idecko);
    void odstranRecenzi(int idecko);
    void smazStareRezervace();

    void upravRezervaci(int idecko, Date rezer_od, Date rezer_do);
    void upravVypujcku(int idecko, Date datum_vypujceni, Date vypujceno_do, Boolean vraceno);
    void upravUpominku (int idecko,int pokuta,String popis);
    void upravKnihu (int idecko,String nazev,String jazyk,String zanr,String nakladatelstvi,String datum_vydani,String isbn,int pocet_kusu, int pocet_stran,String popis);


    boolean dostupnost(int idecko);
    boolean existujeKniha(String nazev);
    boolean existujeAutor(String jmeno);

    List<Upominka> najdiUpoPodleId(int id);
    List<Rezervace> najdiRezPodleId(int id);
    List<Vypujcka> najdiVypPodleId(int id);
    List<Kniha> najdiKniPodleId(int id);
    List<Recenze> najdiRecenzi(String nazevknihy);
    Kniha najdiKnihu(String nazev);
    List<Vypujcka> najdiVypujcky(String email);
    List<Vypujcka> najdiVypujckyProSpravu();
    List<Kniha> najdiVsechnyKnihy();

    List<Upominka> vypisUpominky();
    List<Rezervace> vypisRezervace();
    List<Recenze> vypisRecenze();

    List<Kniha> filtrace(String zanr,String jazyk,String nakladatelstvi,String hledani);
    List<Kniha> filtraceProRez(String nazevknihy);
    List<Vypujcka> filtraceProVyp(String nazevknihy);
    List<Recenze> filtraceProRec(String nazevknihy);
    List<Upominka> filtraceProUpo(String emailuzivatele);

}
