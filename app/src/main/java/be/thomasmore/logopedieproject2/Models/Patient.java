package be.thomasmore.logopedieproject2.Models;

import java.util.Date;

public class Patient {
    private long id;
    private String voornaam;
    private String achternaam;
    private String geslacht;
    private String geboortedatum;
    private String soortAfasie;
    private long logopedistId;

    public Patient() {

    }

    public Patient(long id, String voornaam, String achternaam, String geslacht, String geboortedatum, String soortAfasie, long logopedistId) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geslacht = geslacht;
        this.geboortedatum = geboortedatum;
        this.soortAfasie = soortAfasie;
        this.logopedistId = logopedistId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(String geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getSoortAfasie() {
        return soortAfasie;
    }

    public void setSoortAfasie(String soortAfasie) {
        this.soortAfasie = soortAfasie;
    }

    public long getLogopedistId() {
        return logopedistId;
    }

    public void setLogopedistId(long logopedistId) {
        this.logopedistId = logopedistId;
    }
}