package com.ppro.projekt.entity;

import javax.persistence.*;


@Entity
public class Recenze {
    @Id
    @GeneratedValue
    private int id;
    private String jmeno_autora;
    @Column(length = 2550)
    private String recenze;
    private int hodnoceni;

    @ManyToOne
    @JoinColumn(name="kniha_id",referencedColumnName = "id")
    private Kniha kniha;

    @ManyToOne
    @JoinColumn(name="uzivatel_id",referencedColumnName = "id")
    private Uzivatel uzivatel;



    public Recenze(String jmeno_autora, String recenze, int hodnoceni) {
        this.jmeno_autora = jmeno_autora;
        this.recenze = recenze;
        this.hodnoceni = hodnoceni;
    }

    public Recenze(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJmeno_autora(String jmeno_autora) {
        this.jmeno_autora = jmeno_autora;
    }

    public void setRecenze(String recenze) {
        this.recenze = recenze;
    }

    public void setHodnoceni(int hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public int getId() {
        return id;
    }

    public String getJmeno_autora() {
        return jmeno_autora;
    }

    public String getRecenze() {
        return recenze;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    @Override
    public String toString() {
        return "Recenze{" +
                "id=" + id +
                ", jmeno_autora='" + jmeno_autora + '\'' +
                ", recenze='" + recenze + '\'' +
                ", hodnoceni=" + hodnoceni +
                ", kniha=" + kniha +
                ", uzivatel=" + uzivatel +
                '}';
    }
}
