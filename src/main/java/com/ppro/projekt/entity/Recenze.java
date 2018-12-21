package com.ppro.projekt.entity;

import javax.persistence.*;


@Entity
public class Recenze {
    @Id
    @GeneratedValue
    private int Id;
    private String jmeno_autora;
    @Column(length = 2550)
    private String recenze;
    private int hodnoceni;

    @ManyToOne
    @JoinColumn(name="kniha_id",referencedColumnName = "id")
    private Kniha kniha;

    @ManyToOne
    @JoinColumn(name="uzivatele_id",referencedColumnName = "id")
    private Uzivatele uzivatele;



    public Recenze(String jmeno_autora, String recenze, int hodnoceni) {
        this.jmeno_autora = jmeno_autora;
        this.recenze = recenze;
        this.hodnoceni = hodnoceni;
    }

    public Recenze(){

    }

    public void setId(int id) {
        Id = id;
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

    public void setUzivatele(Uzivatele uzivatele) {
        this.uzivatele = uzivatele;
    }

    public int getId() {
        return Id;
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

    public Uzivatele getUzivatele() {
        return uzivatele;
    }

    @Override
    public String toString() {
        return "Recenze{" +
                "Id=" + Id +
                ", jmeno_autora='" + jmeno_autora + '\'' +
                ", recenze='" + recenze + '\'' +
                ", hodnoceni=" + hodnoceni +
                ", kniha=" + kniha +
                ", uzivatele=" + uzivatele +
                '}';
    }
}
