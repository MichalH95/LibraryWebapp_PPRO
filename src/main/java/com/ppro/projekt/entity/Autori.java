package com.ppro.projekt.entity;

import javax.persistence.*;

@Entity
public class Autori {
    @Id
    @GeneratedValue
    private int Id;
    private String vztah_ke_knize;
    private String jmeno;




    @ManyToOne
    @JoinColumn(name = "knihy_id", referencedColumnName = "id")
    private Kniha kniha;
    public Autori() {
    }

    public Autori(String vztah_ke_knize, String jmeno) {
        this.vztah_ke_knize = vztah_ke_knize;
        this.jmeno = jmeno;
    }

    public void setId(int id) {
        Id = id;
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
        return Id;
    }

    public String getVztah_ke_knize() {
        return vztah_ke_knize;
    }

    public String getJmeno() {
        return jmeno;
    }

    @Override
    public String toString() {
        return "Autori{" +
                "Id=" + Id +
                ", vztah_ke_knize='" + vztah_ke_knize + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", kniha=" + kniha +
                '}';
    }
}
