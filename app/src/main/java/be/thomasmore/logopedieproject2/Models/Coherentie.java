package be.thomasmore.logopedieproject2.Models;

public class Coherentie {
    private long id;
    private String oorzaak;
    private String gevolg;

    public Coherentie() {
    }

    public Coherentie(long id, String oorzaak, String gevolg) {
        this.id = id;
        this.oorzaak = oorzaak;
        this.gevolg = gevolg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOorzaak() {
        return oorzaak;
    }

    public void setOorzaak(String oorzaak) {
        this.oorzaak = oorzaak;
    }

    public String getGevolg() {
        return gevolg;
    }

    public void setGevolg(String gevolg) {
        this.gevolg = gevolg;
    }
}
