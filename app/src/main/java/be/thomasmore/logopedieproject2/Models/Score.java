package be.thomasmore.logopedieproject2.Models;

import java.util.Date;

public class Score {
    private long id;
    private int productiviteit;
    private int efficientie;
    private int substitutiegedrag;
    private int coherentie;
    private String datum;
    private String audioFile;
    private long patientId;

    public Score() {
    }

    public Score(long id, int productiviteit, int efficientie, int substitutiegedrag, int coherentie, String datum, String audioFile, long patientId) {
        this.id = id;
        this.productiviteit = productiviteit;
        this.efficientie = efficientie;
        this.substitutiegedrag = substitutiegedrag;
        this.coherentie = coherentie;
        this.datum = datum;
        this.audioFile = audioFile;
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

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
