package com.ppro.projekt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Uzivatel {
    @Id
    @GeneratedValue
    private int id;
    private String jmeno;
    private String prijmeni;
    private String mesto;
    private String ulice;
    private String c_popisne;
    private int psc;
    private String email;
    private String heslo;
    private boolean blokace;
    private int privilegia;



    @OneToMany(mappedBy="uzivatel")
    private List<Vypujcka> vypujcky;

    @OneToMany(mappedBy="uzivatel")
    private List<Recenze> recenze;

    @OneToMany(mappedBy="uzivatel")
    private List<Rezervace> rezervace;

    @OneToMany(mappedBy="uzivatel")
    private List<Rezervace> upominky;

    public Uzivatel() {
    }

    public List<Recenze> getRecenze() {
        return recenze;
    }

    public List<Rezervace> getUpominky() {
        return upominky;
    }

    public void setRecenze(List<Recenze> recenze) {
        this.recenze = recenze;
    }

    public void setUpominky(List<Rezervace> upominky) {
        this.upominky = upominky;
    }

    public int getPrivilegia() {
        return privilegia;
    }

    public void setPrivilegia(int privilegia) {
        this.privilegia = privilegia;
    }

    public void setVypujcky(List<Vypujcka> vypujcky) {
        this.vypujcky = vypujcky;
    }

    public void setRezervace(List<Rezervace> rezervace) {
        this.rezervace = rezervace;
    }

    public List<Vypujcka> getVypujcky() {
        return vypujcky;
    }

    public List<Rezervace> getRezervace() {
        return rezervace;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    public void setC_popisne(String c_popisne) {
        this.c_popisne = c_popisne;
    }

    public void setPsc(int psc) {
        this.psc = psc;
    }

    public Uzivatel(String jmeno, String prijmeni, String mesto, String ulice, String c_popisne, int psc, String email, String heslo, boolean blokace, int privilegia) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.mesto = mesto;
        this.ulice = ulice;
        this.c_popisne = c_popisne;
        this.psc = psc;
        this.email = email;
        this.heslo = heslo;
        this.blokace = blokace;
        this.privilegia = privilegia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setBlokace(boolean blokace) {
        this.blokace = blokace;
    }

    public int getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getMesto() {
        return mesto;
    }

    public String getUlice() {
        return ulice;
    }

    public String getC_popisne() {
        return c_popisne;
    }

    public int getPsc() {
        return psc;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }

    public boolean getBlokace() {
        return blokace;
    }

    @Override
    public String toString() {
        return "Uzivatel{" +
                "id=" + id +
                ", jmeno='" + jmeno + '\'' +
                ", prijmeni='" + prijmeni + '\'' +
                ", mesto='" + mesto + '\'' +
                ", ulice='" + ulice + '\'' +
                ", c_popisne=" + c_popisne +
                ", psc=" + psc +
                ", email='" + email + '\'' +
                ", heslo='" + heslo + '\'' +
                ", blokace=" + blokace +
                ", vypujcky=" + vypujcky +
                ", rezervace=" + rezervace +
                '}';
    }
}
