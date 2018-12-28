package com.ppro.projekt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Vypujcka {
    @Id
    @GeneratedValue
    private int id;
    private Date datum_vypujceni;
    private long vypujceno_do;
    private boolean vraceno;

    @OneToMany(mappedBy="vypujcka")
    private List<Upominka> upominky;

    @ManyToOne
    @JoinColumn(name="kniha_id",referencedColumnName = "id")
    private Kniha kniha;

    @ManyToOne
    @JoinColumn(name="uzivatel_id",referencedColumnName = "id")
    private Uzivatel uzivatel;

    public Vypujcka(Date s, long s1, boolean b, int i, int i1) {
    }

    public void setUpominky(List<Upominka> upominky) {
        this.upominky = upominky;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public List<Upominka> getUpominky() {
        return upominky;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public Vypujcka(Date datum_vypujceni, long vypujceno_do, boolean vraceno) {
        this.datum_vypujceni = datum_vypujceni;
        this.vypujceno_do = vypujceno_do;
        this.vraceno = vraceno;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDatum_vypujceni(Date datum_vypujceni) {
        this.datum_vypujceni = datum_vypujceni;
    }

    public void setVypujceno_do(long vypujceno_do) {
        this.vypujceno_do = vypujceno_do;
    }

    public void setVraceno(boolean vraceno) {
        this.vraceno = vraceno;
    }

    public int getId() {
        return id;
    }


    public Date getDatum_vypujceni() {
        return datum_vypujceni;
    }

    public long getVypujceno_do() {
        return vypujceno_do;
    }

    public boolean isVraceno() {
        return vraceno;
    }
}
