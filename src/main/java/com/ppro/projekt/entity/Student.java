package com.ppro.projekt.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String surname;
    private String firstname;
    @ManyToOne
    private University univerzita;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Student_Projekt",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Project> projekty = new ArrayList<>();

    public Student() {
    }

    public Student(String surname, String firstname) {
        this.surname = surname;
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public University getUniverzita() {
        return univerzita;
    }

    public void setUniverzita(University u) {
        this.univerzita = u;
    }

    public List<Project> getProjekty() {
        return projekty;
    }

    public void setProjekty(List<Project> projekty) {
        this.projekty = projekty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", univerzita=" + univerzita.getName() +
                '}';
    }
}
