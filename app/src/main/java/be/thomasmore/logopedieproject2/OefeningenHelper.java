package be.thomasmore.logopedieproject2;

import android.content.Context;

import java.util.List;

import be.thomasmore.logopedieproject2.DataService.CoherentieDataService;
import be.thomasmore.logopedieproject2.DataService.EfficientieDataService;
import be.thomasmore.logopedieproject2.DataService.SubstitutiegedragDataService;
import be.thomasmore.logopedieproject2.Models.Coherentie;
import be.thomasmore.logopedieproject2.Models.Efficientie;
import be.thomasmore.logopedieproject2.Models.Substitutiegedrag;

public class OefeningenHelper {
    private Context context;

    private EfficientieDataService dbEfficientie = new EfficientieDataService(new DatabaseHelper(context));
    private SubstitutiegedragDataService dbSubstitutiegedrag = new SubstitutiegedragDataService(new DatabaseHelper(context));
    private CoherentieDataService dbCoherentie = new CoherentieDataService(new DatabaseHelper(context));

    public OefeningenHelper(Context context) {
        this.context = context;
    }

    public int berekenProductiviteitScore(String[] woorden) {
        // bereking productiviteit
        return woorden.length;
    }

    public int berekenEfficientieScore(String[] woorden) {
        // berekening efficiëntie
        int woordenTeller = 0;
        List<Efficientie> efficientieLijst = dbEfficientie.getEfficientieList();

        // verhoog woordenTeller telkens er een match is tussen een woord uit de efficientielijst en de beschrijving van de plaat
        for (int i = 0; i < efficientieLijst.size(); i++) {
            for (String woord : woorden) {
                if (efficientieLijst.get(i).getWoord().equals(woord)) {
                    woordenTeller++;
                }
            }
        }

        // berekening uiteindelijke score
        int percentueleScore = (woordenTeller / efficientieLijst.size()) * 100;

        if (percentueleScore <= 25) {
            return 1;
        } else if (percentueleScore <= 50) {
            return 2;
        } else if (percentueleScore <= 75) {
            return 3;
        } else if (percentueleScore <= 100) {
            return 4;
        }

        return 0;
    }

    public int berekenEfficientieAantalWoorden(String[] woorden) {
        // berekening efficiëntie
        int woordenTeller = 0;
        List<Efficientie> efficientieLijst = dbEfficientie.getEfficientieList();

        // verhoog woordenTeller telkens er een match is tussen een woord uit de efficientielijst en de beschrijving van de plaat
        for (int i = 0; i < efficientieLijst.size(); i++) {
            for (String woord : woorden) {
                if (efficientieLijst.get(i).getWoord().equals(woord)) {
                    woordenTeller++;
                }
            }
        }

        return woordenTeller;
    }

    public int berekenSubstitutiegedragScore(String[] woorden) {
        // berekening substitutiegedrag
        int woordenTeller = 0;
        List<Substitutiegedrag> substitutiegedragLijst = dbSubstitutiegedrag.getSubstitutiegedragList();

        // verhoog woordenTeller telkens er een match is tussen een woord uit de substitutiegedraglijst en de beschrijving van de plaat
        for (int i = 0; i < substitutiegedragLijst.size(); i++) {
            for (String woord : woorden) {
                if (substitutiegedragLijst.get(i).getWoord().equals(woord)) {
                    woordenTeller++;
                }
            }
        }

        // berekening uiteindelijke score
        int percentueleScore = (woordenTeller / substitutiegedragLijst.size()) * 100;

        if (percentueleScore <= 25) {
            return 4;
        } else if (percentueleScore <= 50) {
            return 3;
        } else if (percentueleScore <= 75) {
            return 2;
        } else if (percentueleScore <= 100) {
            return 1;
        }

        return 0;
    }

    public int berekenSubstitutiegedragAantalWoorden(String[] woorden) {
        // berekening substitutiegedrag
        int woordenTeller = 0;
        List<Substitutiegedrag> substitutiegedragLijst = dbSubstitutiegedrag.getSubstitutiegedragList();

        // verhoog woordenTeller telkens er een match is tussen een woord uit de substitutiegedraglijst en de beschrijving van de plaat
        for (int i = 0; i < substitutiegedragLijst.size(); i++) {
            for (String woord : woorden) {
                if (substitutiegedragLijst.get(i).getWoord().equals(woord)) {
                    woordenTeller++;
                }
            }
        }

        return woordenTeller;
    }

    public int berekenCoherentieScore(String[] woorden) {
        // berekening coherentie
        int woordenTeller = 0;
        int tellerOorzaak = 0;
        int tellerGevolg = 0;
        List<Coherentie> coherentieLijst = dbCoherentie.getCoherentieList();

        for (int i = 0; i < coherentieLijst.size(); i++) {
            for (String woord : woorden) {
                if (coherentieLijst.get(i).getOorzaakOnderwerp().contains(woord)) {
                    for (int j = 1; j < 6; j++) {
                        if (coherentieLijst.get(i).getOorzaakWerkwoord().contains(woorden[tellerOorzaak + j])) {
                            for (String woordGevolg : woorden) {
                                if (coherentieLijst.get(i).getGevolgOnderwerp().contains(woordGevolg)) {
                                    for (int k = 1; k < 6; k++) {
                                        if (coherentieLijst.get(i).getGevolgWerkwoord().contains(woorden[tellerGevolg + k])) {
                                            woordenTeller++;
                                        }
                                    }
                                }

                                tellerGevolg++;
                            }
                        }
                    }
                }

                tellerOorzaak++;
            }
        }

        return woordenTeller;
    }
}
