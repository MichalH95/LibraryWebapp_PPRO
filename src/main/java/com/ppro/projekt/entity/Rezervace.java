package com.ppro.projekt.entity;

import javax.persistence.*;

@Entity
public class Rezervace {
    @Id
    @GeneratedValue
    private int id;
    private String rezervace_od;
    private String rezervace_do;

    @ManyToOne
    @JoinColumn(name="kniha_id",referencedColumnName = "id")
    private Kniha kniha;

    @ManyToOne
    @JoinColumn(name="uzivatel_id",referencedColumnName = "id")
    private Uzivatel uzivatel;

    public Rezervace() {
    }

    public Kniha getKniha() {
        return kniha;
    }


    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public Rezervace(String rezervace_od, String rezervace_do) {
        this.rezervace_od = rezervace_od;
        this.rezervace_do = rezervace_do;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRezervace_od(String rezervace_od) {
        this.rezervace_od = rezervace_od;
    }

    public void setRezervace_do(String rezervace_do) {
        this.rezervace_do = rezervace_do;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", rezervace_od='" + rezervace_od + '\'' +
                ", rezervace_do='" + rezervace_do + '\'' +
                ", kniha=" + kniha +
                ", uzivatel=" + uzivatel +
                '}';
    }
}
