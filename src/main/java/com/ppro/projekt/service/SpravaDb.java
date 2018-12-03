package com.ppro.projekt.service;

import com.ppro.projekt.entity.Kniha;

import java.util.List;

public interface SpravaDb {

    void vlozKnihu(Kniha Kniha);

    void odstranKnihu(int id);

    Kniha najdiPodleId(long id);

    List<Kniha> najdiVsechnyKnihy();

}
