package com.ppro.projekt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(mappedBy = "projekty")
    private List<Student> studenti = new ArrayList<>();

    public Project() {
    }

    public Project(String name) {
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

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studenti=" + studenti +
                '}';
    }
}
