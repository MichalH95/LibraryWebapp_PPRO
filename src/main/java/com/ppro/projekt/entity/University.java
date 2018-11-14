package com.ppro.projekt.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UNIVERSITY")
public class University {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "univerzita")
    private List<Student> studenti = new ArrayList<>();

    public University() {
    }

    public University(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> list) {
        this.studenti = list;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studenti=" + studenti +
                '}';
    }
}
