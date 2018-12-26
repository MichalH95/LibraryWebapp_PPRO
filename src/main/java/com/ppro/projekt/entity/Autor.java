package com.ppro.projekt.entity;

import javax.persistence.*;

@Entity
public class Autor {
    @Id
    @GeneratedValue
    private int id;
    private String vztah_ke_knize;
    private String jmeno;


    @ManyToOne
    @JoinColumn(name = "kniha_id", referencedColumnName = "id")
    private Kniha kniha;

    public Autor() {
    }

    public Autor(String vztah_ke_knize, String jmeno) {
        this.vztah_ke_knize = vztah_ke_knize;
        this.jmeno = jmeno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }

    public Kniha getKniha() {
        return kniha;
    }

    public void setVztah_ke_knize(String vztah_ke_knize) {
        this.vztah_ke_knize = vztah_ke_knize;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getId() {
        return id;
    }

    public String getVztah_ke_knize() {
        return vztah_ke_knize;
    }

    public String getJmeno() {
        return jmeno;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", vztah_ke_knize='" + vztah_ke_knize + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", kniha=" + kniha +
                '}';
    }
}
