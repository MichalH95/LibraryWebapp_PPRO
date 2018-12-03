package com.ppro.projekt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rezervace {
    @Id
    @GeneratedValue
    private int Id;
    private String rezervace_od;
    private String rezervace_do;

    @ManyToOne
    private Kniha kniha;

    @ManyToOne
    private Uzivatele uzivatele;

    public Rezervace() {
    }

    public Kniha getKniha() {
        return kniha;
    }


    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public void setUzivatele(Uzivatele uzivatele) {
        this.uzivatele = uzivatele;
    }

    public Uzivatele getUzivatele() {
        return uzivatele;
    }

    public Rezervace(String rezervace_od, String rezervace_do) {
        this.rezervace_od = rezervace_od;
        this.rezervace_do = rezervace_do;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setRezervace_od(String rezervace_od) {
        this.rezervace_od = rezervace_od;
    }

    public void setRezervace_do(String rezervace_do) {
        this.rezervace_do = rezervace_do;
    }

    public int getId() {
        return Id;
    }

    public String getRezervace_od() {
        return rezervace_od;
    }

    public String getRezervace_do() {
        return rezervace_do;
    }

    @Override
    public String toString() {
        return "Rezervace{" +
                "Id=" + Id +
                ", rezervace_od='" + rezervace_od + '\'' +
                ", rezervace_do='" + rezervace_do + '\'' +
                ", kniha=" + kniha +
                ", uzivatele=" + uzivatele +
                '}';
    }
}
