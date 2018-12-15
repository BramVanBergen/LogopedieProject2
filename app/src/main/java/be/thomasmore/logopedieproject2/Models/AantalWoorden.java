package be.thomasmore.logopedieproject2.Models;

public class AantalWoorden {
    private long id;
    private int productiviteit;
    private int efficientie;
    private int substitutiegedrag;
    private int coherentie;
    private String datum;
    private long patientId;

    public AantalWoorden() {
    }

    public AantalWoorden(long id, int productiviteit, int efficientie, int substitutiegedrag, int coherentie, String datum, long patientId) {
        this.id = id;
        this.productiviteit = productiviteit;
        this.efficientie = efficientie;
        this.substitutiegedrag = substitutiegedrag;
        this.coherentie = coherentie;
        this.datum = datum;
        this.patientId = patientId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProductiviteit() {
        return productiviteit;
    }

    public void setProductiviteit(int productiviteit) {
        this.productiviteit = productiviteit;
    }

    public int getEfficientie() {
        return efficientie;
    }

    public void setEfficientie(int efficientie) {
        this.efficientie = efficientie;
    }

    public int getSubstitutiegedrag() {
        return substitutiegedrag;
    }

    public void setSubstitutiegedrag(int substitutiegedrag) {
        this.substitutiegedrag = substitutiegedrag;
    }

    public int getCoherentie() {
        return coherentie;
    }

    public void setCoherentie(int coherentie) {
        this.coherentie = coherentie;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
