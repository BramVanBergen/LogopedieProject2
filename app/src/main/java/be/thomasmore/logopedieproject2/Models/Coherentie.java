package be.thomasmore.logopedieproject2.Models;

public class Coherentie {
    private long id;
    private String oorzaakOnderwerp;
    private String oorzaakWerkwoord;
    private String gevolgOnderwerp;
    private String gevolgWerkwoord;

    public Coherentie() {
    }

    public Coherentie(long id, String oorzaakOnderwerp, String oorzaakWerkwoord, String gevolgOnderwerp, String gevolgWerkwoord) {
        this.id = id;
        this.oorzaakOnderwerp = oorzaakOnderwerp;
        this.oorzaakWerkwoord = oorzaakWerkwoord;
        this.gevolgOnderwerp = gevolgOnderwerp;
        this.gevolgWerkwoord = gevolgWerkwoord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOorzaakOnderwerp() {
        return oorzaakOnderwerp;
    }

    public void setOorzaakOnderwerp(String oorzaakOnderwerp) {
        this.oorzaakOnderwerp = oorzaakOnderwerp;
    }

    public String getOorzaakWerkwoord() {
        return oorzaakWerkwoord;
    }

    public void setOorzaakWerkwoord(String oorzaakWerkwoord) {
        this.oorzaakWerkwoord = oorzaakWerkwoord;
    }

    public String getGevolgOnderwerp() {
        return gevolgOnderwerp;
    }

    public void setGevolgOnderwerp(String gevolgOnderwerp) {
        this.gevolgOnderwerp = gevolgOnderwerp;
    }

    public String getGevolgWerkwoord() {
        return gevolgWerkwoord;
    }

    public void setGevolgWerkwoord(String gevolgWerkwoord) {
        this.gevolgWerkwoord = gevolgWerkwoord;
    }
}
