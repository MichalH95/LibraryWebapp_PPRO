package com.ppro.projekt.entity;

import javax.persistence.*;

@Entity
public class Upominky {
    @Id
    @GeneratedValue
    private int Id;
    private String popis;
    private int pokuta;


    @ManyToOne
    @JoinColumn(name="vypujcky_id",referencedColumnName = "id")
    private Vypujcky vypujcky;

    @ManyToOne
    @JoinColumn(name="uzivatele_id",referencedColumnName = "id")
    private Uzivatele uzivatele;

    @ManyToOne
    @JoinColumn(name="kniha_id",referencedColumnName = "id")
    private Kniha kniha;

    public Kniha getKniha() {
        return kniha;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public Upominky() {
    }

    public Upominky(String popis, int pokuta) {
        this.popis = popis;
        this.pokuta = pokuta;
    }

    public void setUzivatele(Uzivatele uzivatele) {
        this.uzivatele = uzivatele;
    }

    public Uzivatele getUzivatele() {
        return uzivatele;
    }

    public int getId() {
        return Id;
    }

    public String getPopis() {
        return popis;
    }

    public int getPokuta() {
        return pokuta;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setPokuta(int pokuta) {
        this.pokuta = pokuta;
    }

    public void setVypujcky(Vypujcky vypujcky) {
        this.vypujcky = vypujcky;
    }

    public Vypujcky getVypujcky() {
        return vypujcky;
    }

    @Override
    public String toString() {
        return "Upominky{" +
                "Id=" + Id +
                ", popis='" + popis + '\'' +
                ", pokuta=" + pokuta +
                ", vypujcky=" + vypujcky +
                '}';
    }
}
