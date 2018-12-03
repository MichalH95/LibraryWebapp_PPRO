package com.ppro.projekt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Kniha {
    @Id
    @GeneratedValue
    private int Id;
    private String nazev;
    @Column(length = 2550)
    private String popis;
    private String zanr;
    private Date datum_vydani;
    private int pocet_stran;
    private String nakladatelstvi;
    @Column(length = 2550)
    private String isbn;
    private String jazyk;

    @OneToMany(mappedBy="kniha")
    private List<Recenze> recenze;

    @OneToMany(mappedBy="kniha")
    private List<Vypujcky> vypujcky;

    @OneToMany(mappedBy="kniha")
    private List<Rezervace> rezervace;

    @OneToMany(mappedBy="kniha")
    private List<Autori> autori;

    public Kniha(String nazev, String popis, String zanr, Date datum_vydani, int pocet_stran, String nakladatelstvi, String isbn, String jazyk) {
        this.nazev = nazev;
        this.popis = popis;
        this.zanr = zanr;
        this.datum_vydani = datum_vydani;
        this.pocet_stran = pocet_stran;
        this.nakladatelstvi = nakladatelstvi;
        this.isbn = isbn;
        this.jazyk = jazyk;
    }

    public Kniha(String nazev, String popis, String zanr, Date datum_vydani) {
        this.nazev = nazev;
        this.popis = popis;
        this.zanr = zanr;
        this.datum_vydani = datum_vydani;
    }

    public Kniha() {

    }

    public void setPocet_stran(int pocet_stran) {
        this.pocet_stran = pocet_stran;
    }

    public void setNakladatelstvi(String nakladatelstvi) {
        this.nakladatelstvi = nakladatelstvi;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setJazyk(String jazyk) {
        this.jazyk = jazyk;
    }

    public int getPocet_stran() {
        return pocet_stran;
    }

    public String getNakladatelstvi() {
        return nakladatelstvi;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getJazyk() {
        return jazyk;
    }

    public void setVypujcky(List<Vypujcky> vypujcky) {
        this.vypujcky = vypujcky;
    }

    public void setRezervace(List<Rezervace> rezervace) {
        this.rezervace = rezervace;
    }

    public void setAutori(List<Autori> autori) {
        this.autori = autori;
    }

    public List<Vypujcky> getVypujcky() {
        return vypujcky;
    }

    public List<Rezervace> getRezervace() {
        return rezervace;
    }

    public List<Autori> getAutori() {
        return autori;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public void setDatum_vydani(Date datum_vydani) {
        this.datum_vydani = datum_vydani;
    }

    public int getId() {
        return Id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public String getZanr() {
        return zanr;
    }

    public Date getDatum_vydani() {
        return datum_vydani;
    }

    @Override
    public String toString() {
        return "Kniha{" +
                "Id=" + Id +
                ", nazev='" + nazev + '\'' +
                ", popis='" + popis + '\'' +
                ", zanr='" + zanr + '\'' +
                ", Datum_vydani='" + datum_vydani + '\'' +
                ", vypujcky=" + vypujcky +
                ", rezervace=" + rezervace +
                ", autori=" + autori +
                '}';
    }
}