package be.thomasmore.logopedieproject2.Models;

public class Opname {
    private long id;
    private String naam;

    public Opname() {

    }

    public Opname(long id, String naam) {
        this.id = id;
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
