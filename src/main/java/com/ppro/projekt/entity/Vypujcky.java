package com.ppro.projekt.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vypujcky {
    @Id
    @GeneratedValue
    private int Id;
    private String datum_vypujceni;
    private String vypujceno_do;
    private boolean vraceno;

    @OneToMany(mappedBy="vypujcky")
    private List<Upominky> upominky;

    @ManyToOne
    private Knihy knihy;

    @ManyToOne
    private Uzivatele uzivatele;

    public Vypujcky() {
    }

    public void setUpominky(List<Upominky> upominky) {
        this.upominky = upominky;
    }

    public void setKnihy(Knihy knihy) {
        this.knihy = knihy;
    }

    public void setUzivatele(Uzivatele uzivatele) {
        this.uzivatele = uzivatele;
    }

    public List<Upominky> getUpominky() {
        return upominky;
    }

    public Knihy getKnihy() {
        return knihy;
    }

    public Uzivatele getUzivatele() {
        return uzivatele;
    }

    public Vypujcky(String datum_vypujceni, String vypujceno_do, boolean vraceno) {
        this.datum_vypujceni = datum_vypujceni;
        this.vypujceno_do = vypujceno_do;
        this.vraceno = vraceno;
    }

    public void setId(int id) {
        Id = id;
    }


    public void setDatum_vypujceni(String datum_vypujceni) {
        this.datum_vypujceni = datum_vypujceni;
    }

    public void setVypujceno_do(String vypujceno_do) {
        this.vypujceno_do = vypujceno_do;
    }

    public void setVraceno(boolean vraceno) {
        this.vraceno = vraceno;
    }

    public int getId() {
        return Id;
    }


    public String getDatum_vypujceni() {
        return datum_vypujceni;
    }

    public String getVypujceno_do() {
        return vypujceno_do;
    }

    public boolean isVraceno() {
        return vraceno;
    }
}
