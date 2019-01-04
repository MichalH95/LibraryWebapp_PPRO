package com.ppro.projekt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rezervace {
    @Id
    @GeneratedValue
    private int id;
    private long poradi;
    private Date rezervace_od;
    private Date rezervace_do;

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

    public Rezervace(long poradi, Date rezervace_od, Date rezervace_do) {
        this.poradi = poradi;
        this.rezervace_od = rezervace_od;
        this.rezervace_do = rezervace_do;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRezervace_od(Date rezervace_od) {
        this.rezervace_od = rezervace_od;
    }

    public void setRezervace_do(Date rezervace_do) {
        this.rezervace_do = rezervace_do;
    }

    public int getId() {
        return id;
    }

    public Date getRezervace_od() {
        return rezervace_od;
    }

    public Date getRezervace_do() {
        return rezervace_do;
    }

    public long getPoradi() {
        return poradi;
    }

    public void setPoradi(long poradi) {
        this.poradi = poradi;
    }

    @Override
    public String toString() {
        return "Rezervace{" +
                "id=" + id +
                ", poradi='" + poradi + '\'' +
                ", rezervace_od='" + rezervace_od + '\'' +
                ", rezervace_do='" + rezervace_do + '\'' +
                ", kniha=" + kniha +
                ", uzivatel=" + uzivatel +
                '}';
    }
}
