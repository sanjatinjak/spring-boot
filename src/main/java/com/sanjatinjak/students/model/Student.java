package com.sanjatinjak.students.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="student", schema = "public")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ime", nullable = false)
    private String ime;

    @Column(name = "prezime", nullable = false)
    private String prezime;

    @Column(name = "br_indeksa", nullable = false)
    private String br_indeksa;

    @Column(name = "godina_studija", nullable = false)
    private int godina_studija;

    public Student(Long id, String ime, String prezime, String br_indeksa, int godina_studija) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.br_indeksa = br_indeksa;
        this.godina_studija = godina_studija;
    }

    public Student(Long id, String ime, String prezime, String br_indeksa, int godina_studija, Set<Subject> subjects) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.br_indeksa = br_indeksa;
        this.godina_studija = godina_studija;
        this.subjects = subjects;
    }


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "students")
    private Set<Subject> subjects = new HashSet<>();

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBr_indeksa() {
        return br_indeksa;
    }

    public void setBr_indeksa(String br_indeksa) {
        this.br_indeksa = br_indeksa;
    }

    public int getGodina_studija() {
        return godina_studija;
    }

    public void setGodina_studija(int godina_studija) {
        this.godina_studija = godina_studija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
