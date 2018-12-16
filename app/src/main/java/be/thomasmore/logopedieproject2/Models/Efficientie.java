package be.thomasmore.logopedieproject2.Models;

public class Efficientie {
    private long id;
    private String woord;

    public Efficientie() {
    }

    public Efficientie(long id, String woord) {
        this.id = id;
        this.woord = woord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }
}
