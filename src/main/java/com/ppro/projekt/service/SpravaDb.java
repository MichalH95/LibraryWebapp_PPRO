package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;
import com.ppro.projekt.entity.Rezervace;
import com.ppro.projekt.entity.Uzivatele;
import com.ppro.projekt.entity.Vypujcky;

import java.util.List;

public interface SpravaDb {

    void vlozKnihu(Kniha Kniha);

    void vlozUzivatele(Uzivatele Uzivatele);

    void odstranVypujcku(int id);
    void odstranKnihu(int id);
    void odstranRezervaci(int id);
    void upravRezervaci(int idecko,String rezer_od,String rezer_do);
    void upravVypujcku(int idecko,String datum_vypujceni,String vypujceno_do,Boolean vraceno);
    boolean existujeuzivatel(String email);
    boolean overlogin(String email,String heslo);

    boolean privilegium(String email);

    List<Rezervace> najdiRezPodleId(int id);
    List<Vypujcky> najdiVypPodleId(int id);

    List<Rezervace> vypisrezervace();
    List<Vypujcky> najdiVypujcky(String email);
    List<Vypujcky> najdiVypujckyProSpravu();
    List<Kniha> filtrace(String zanr,String jazyk,String nakladatelstvi);
    List<Kniha> najdiVsechnyKnihy();

}
