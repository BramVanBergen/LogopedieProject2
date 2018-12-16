package be.thomasmore.logopedieproject2.Models;

import java.util.Date;

public class Patient {
    private long id;
    private String voornaam;
    private String achternaam;
    private String geslacht;
    private String geboortedatum;
    private long soortAfasieId;
    private long logopedistId;

    public Patient() {

    }

    public Patient(long id, String voornaam, String achternaam, String geslacht, String geboortedatum, long soortAfasieId, long logopedistId) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geslacht = geslacht;
        this.geboortedatum = geboortedatum;
        this.soortAfasieId = soortAfasieId;
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

    public long getSoortAfasieId() {
        return soortAfasieId;
    }

    public void setSoortAfasieId(long soortAfasieId) {
        this.soortAfasieId = soortAfasieId;
    }

    public long getLogopedistId() {
        return logopedistId;
    }

    public void setLogopedistId(long logopedistId) {
        this.logopedistId = logopedistId;
    }
}
