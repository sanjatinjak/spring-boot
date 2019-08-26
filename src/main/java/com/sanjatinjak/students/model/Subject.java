package com.sanjatinjak.students.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="kolegij", schema = "public")
public class Subject {

    public Subject(){

    }

    public Subject(Long id, String naziv, int semestar){
        this.id=id;
        this.naziv=naziv;
        this.semestar=semestar;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="naziv_kolegija", nullable = false)
    private String naziv;

    @Column(name="semestar_izvodenja", nullable = false)
    private int semestar;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "kolegij_student",
            joinColumns = { @JoinColumn(name = "id_kolegija") },
            inverseJoinColumns = { @JoinColumn(name = "id_studenta") }
    )
    private Set<Student> students = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "profesor_kolegij")
    private Set<Professor> professors;

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getSemestar() {
        return semestar;
    }

    public void setSemestar(int semestar) {
        this.semestar = semestar;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
