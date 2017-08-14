package com.example.korsn.mojkuvar;

/**
 * Created by Korsn on 10.8.2017..
 */

public class Recept {

    private int id;
    private String ime;
    private String vrsta;
    private String sastojci;
    private String opis;

    public Recept(int id, String ime, String vrsta, String sastojci, String opis) {
        this.id = id;
        this.ime = ime;
        this.vrsta = vrsta;
        this.sastojci = sastojci;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
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




    //za spinner, bez ovoga pokazuje com.example......

    @Override
    public String toString() {
        return getIme();
    }
}
