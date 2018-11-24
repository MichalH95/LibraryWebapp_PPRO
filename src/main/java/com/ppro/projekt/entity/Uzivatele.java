package com.ppro.projekt.entity;

import sun.security.util.Password;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Uzivatele {
    @Id
    @GeneratedValue
    private int Id;
    private String jmeno;
    private String prijmeni;
    private String mesto;
    private String ulice;
    private int c_popisne;
    private int psc;
    private String email;
    private String heslo;
    private boolean blokace;

    @OneToMany(mappedBy="uzivatele")
    private List<Vypujcky> vypujcky;

    @OneToMany(mappedBy="uzivatele")
    private List<Recenze> recenze;

    @OneToMany(mappedBy="uzivatele")
    private List<Rezervace> rezervace;

    public Uzivatele() {
    }

    public void setVypujcky(List<Vypujcky> vypujcky) {
        this.vypujcky = vypujcky;
    }

    public void setRezervace(List<Rezervace> rezervace) {
        this.rezervace = rezervace;
    }

    public List<Vypujcky> getVypujcky() {
        return vypujcky;
    }

    public List<Rezervace> getRezervace() {
        return rezervace;
    }

    public void setId(int id) {
        Id = id;
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

    public void setC_popisne(int c_popisne) {
        this.c_popisne = c_popisne;
    }

    public void setPsc(int psc) {
        this.psc = psc;
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
        return Id;
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

    public int getC_popisne() {
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

    public boolean isBlokace() {
        return blokace;
    }

    public Uzivatele(String jmeno, String prijmeni, String mesto, String ulice, int c_popisne, int psc, String email, String heslo, boolean blokace) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.mesto = mesto;
        this.ulice = ulice;
        this.c_popisne = c_popisne;
        this.psc = psc;
        this.email = email;
        this.heslo = heslo;
        this.blokace = blokace;
    }

    @Override
    public String toString() {
        return "Uzivatele{" +
                "Id=" + Id +
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
