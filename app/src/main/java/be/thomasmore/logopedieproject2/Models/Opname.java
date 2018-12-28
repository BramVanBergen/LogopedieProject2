package be.thomasmore.logopedieproject2.Models;

public class Opname {
    private long id;
    private String naam;

    public Opname() {

    }

    public Opname(String naam) {
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
