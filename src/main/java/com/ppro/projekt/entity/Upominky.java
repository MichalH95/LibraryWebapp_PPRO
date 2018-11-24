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
    private Vypujcky vypujcky;

    public Upominky(String popis, int pokuta) {
        this.popis = popis;
        this.pokuta = pokuta;
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
