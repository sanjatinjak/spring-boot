package com.sanjatinjak.students.model;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "profesor", schema = "public")
public class Professor {

    public Professor(){

    }

    public Professor(Long id, String ime, String prezime, Date datum_rodenja, String email, String br_mobitela, Long user_id) {
        this.id=id;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodenja = datum_rodenja;
        this.email = email;
        this.br_mobitela = br_mobitela;
        this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ime", nullable = false)
    private String ime;

    @Column(name = "prezime", nullable = false)
    private String prezime;

    @Column(name = "datum_rodenja", nullable = false)
    private Date datum_rodenja;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "br_mobitela", nullable = true)
    private String br_mobitela;


    private Long user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDatum_rodenja() {
        return datum_rodenja;
    }

    public void setDatum_rodenja(Date datum_rodenja) {
        this.datum_rodenja = datum_rodenja;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBr_mobitela() {
        return br_mobitela;
    }

    public void setBr_mobitela(String br_mobitela) {
        this.br_mobitela = br_mobitela;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
