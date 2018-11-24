package com.ppro.projekt.service;

import com.ppro.projekt.entity.Autori;
import com.ppro.projekt.entity.Knihy;

import java.util.List;

public interface SpravaDb {


    void VlozKnihu(Knihy Knihy);

    void OdstranKnihu(int id);

    List<Autori> NajdiAutoraByKnihaId();

    List<Knihy> zobrazKnihy();

    public boolean isUniversityExist(Knihy Knihy);

}
