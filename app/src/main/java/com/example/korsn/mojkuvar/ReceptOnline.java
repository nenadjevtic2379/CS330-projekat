package com.example.korsn.mojkuvar;

/**
 * Created by Korsn on 11.8.2017..
 */

public class ReceptOnline {

    private String id;
    private String ime;
    private String vrsta;
    private String sastojci;
    private String opis;
    private String email;

    public ReceptOnline() {
    }

    public ReceptOnline(String id, String ime, String vrsta, String sastojci, String opis, String email) {
        this.id = id;
        this.ime = ime;
        this.vrsta = vrsta;
        this.sastojci = sastojci;
        this.opis = opis;
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
