package be.thomasmore.logopedieproject2;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import be.thomasmore.logopedieproject2.DataService.CoherentieDataService;
import be.thomasmore.logopedieproject2.DataService.EfficientieDataService;
import be.thomasmore.logopedieproject2.DataService.SubstitutiegedragDataService;
import be.thomasmore.logopedieproject2.Models.Coherentie;
import be.thomasmore.logopedieproject2.Models.Efficientie;
import be.thomasmore.logopedieproject2.Models.Substitutiegedrag;

public class OefeningenHelper {
    private Context context;

    private EfficientieDataService dbEfficientie;
    private SubstitutiegedragDataService dbSubstitutiegedrag;
    private CoherentieDataService dbCoherentie;

    public OefeningenHelper(Context context) {
        this.context = context;
        dbEfficientie = new EfficientieDataService(new DatabaseHelper(context));
        dbSubstitutiegedrag = new SubstitutiegedragDataService(new DatabaseHelper(context));
        dbCoherentie = new CoherentieDataService(new DatabaseHelper(context));
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
        List<Coherentie> OorzaakOnderwerpLijst = new ArrayList<Coherentie>();
        List<Coherentie> OorzaakWerkwoordLijst = new ArrayList<Coherentie>();
        List<Coherentie> GevolgOnderwerpLijst = new ArrayList<Coherentie>();
        List<Coherentie> GevolgWerkwoordLijst = new ArrayList<Coherentie>();
        List<Coherentie> coherentieLijstFinaal = new ArrayList<Coherentie>();

        int woordenTeller = 0;
//        int tellerOorzaak = 0;
//        int tellerGevolg = 0;
        List<Coherentie> coherentieLijst = dbCoherentie.getCoherentieList();

        for (int i = 0; i < coherentieLijst.size(); i++) {
            for (String woord : woorden) {
                if (coherentieLijst.get(i).getOorzaakOnderwerp().toLowerCase().matches("(.*)" + woord.toLowerCase() + "(.*)")) {
                    OorzaakOnderwerpLijst.add(coherentieLijst.get(i));
                }
            }
        }

        for (Coherentie oorzaakOnderwerp: OorzaakOnderwerpLijst) {
            for (String woord: woorden) {
                if (oorzaakOnderwerp.getOorzaakWerkwoord().toLowerCase().matches("(.*)" + woord.toLowerCase() + "(.*)")) {
                    OorzaakWerkwoordLijst.add(oorzaakOnderwerp);
                }
            }
        }

        for (Coherentie oorzaakWerkwoord: OorzaakWerkwoordLijst) {
            for (String woord: woorden) {
                if (oorzaakWerkwoord.getGevolgOnderwerp().toLowerCase().matches("(.*)" + woord.toLowerCase() + "(.*)")) {
                    GevolgOnderwerpLijst.add(oorzaakWerkwoord);
                }
            }
        }

        for (Coherentie gevolgOnderwerp: GevolgOnderwerpLijst) {
            for (String woord: woorden) {
                if (gevolgOnderwerp.getGevolgOnderwerp().toLowerCase().matches("(.*)" + woord.toLowerCase() + "(.*)")) {
                    GevolgWerkwoordLijst.add(gevolgOnderwerp);
                }
            }
        }

        Set<Coherentie> listWithoutDuplicates = new LinkedHashSet<Coherentie>(GevolgWerkwoordLijst);
        coherentieLijstFinaal.clear();
        coherentieLijstFinaal.addAll(listWithoutDuplicates);

        return coherentieLijstFinaal.size();
    }
}
