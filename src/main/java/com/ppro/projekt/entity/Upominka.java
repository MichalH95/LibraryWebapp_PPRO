package com.ppro.projekt.entity;

import javax.persistence.*;

@Entity
public class Upominka {
    @Id
    @GeneratedValue
    private int id;
    private String popis;
    private int pokuta;


    @ManyToOne
    @JoinColumn(name="vypujcka_id",referencedColumnName = "id")
    private Vypujcka vypujcka;

    @ManyToOne
    @JoinColumn(name="uzivatel_id",referencedColumnName = "id")
    private Uzivatel uzivatel;

    @ManyToOne
    @JoinColumn(name="kniha_id",referencedColumnName = "id")
    private Kniha kniha;

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public Upominka() {
    }

    public Upominka(String popis, int pokuta) {
        this.popis = popis;
        this.pokuta = pokuta;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public int getId() {
        return id;
    }

    public String getPopis() {
        return popis;
    }

    public int getPokuta() {
        return pokuta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setPokuta(int pokuta) {
        this.pokuta = pokuta;
    }

    public void setVypujcka(Vypujcka vypujcka) {
        this.vypujcka = vypujcka;
    }

    public Vypujcka getVypujcka() {
        return vypujcka;
    }

    @Override
    public String toString() {
        return "Upominka{" +
                "id=" + id +
                ", popis='" + popis + '\'' +
                ", pokuta=" + pokuta +
                ", vypujcka=" + vypujcka +
                '}';
    }
}
