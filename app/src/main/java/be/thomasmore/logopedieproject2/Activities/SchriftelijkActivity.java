package be.thomasmore.logopedieproject2.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.logopedieproject2.DataService.CoherentieDataService;
import be.thomasmore.logopedieproject2.DataService.EfficientieDataService;
import be.thomasmore.logopedieproject2.DataService.SubstitutiegedragDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Coherentie;
import be.thomasmore.logopedieproject2.Models.Efficientie;
import be.thomasmore.logopedieproject2.Models.Substitutiegedrag;
import be.thomasmore.logopedieproject2.R;

import static android.widget.RelativeLayout.TRUE;

public class SchriftelijkActivity extends MenuActivity {
    private EfficientieDataService dbEfficientie;
    private SubstitutiegedragDataService dbSubstitutiegedrag;
    private CoherentieDataService dbCoherentie;

    private String schriftelijkeBeschrijving;
    private String[] woorden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schriftelijk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Schriftelijke oefening");

        dbEfficientie = new EfficientieDataService(new DatabaseHelper(this));
        dbSubstitutiegedrag = new SubstitutiegedragDataService(new DatabaseHelper(this));
        dbCoherentie = new CoherentieDataService(new DatabaseHelper(this));

        setSituatieplaat();
    }

    public void setSituatieplaat() {
        // situatieplaat in de app zetten
        ImageView situatieplaat = (ImageView) findViewById(R.id.situatieplaat);
        situatieplaat.setImageResource(R.drawable.situatieplaat_1);
    }

    public void onSubmit(View v) {
        // de beschrijving ophalen
        EditText beschrijving = (EditText) findViewById(R.id.beschrijving_schriftelijke_plaat);
        schriftelijkeBeschrijving = beschrijving.getText().toString();

        // string opsplitsen in array van woorden
        woorden = schriftelijkeBeschrijving.split("\\s+");

        // bereken de verschillende soorten scores
        berekenScores();
    }

    public void berekenScores() {
        int productiviteit = berekenProductiviteit();
        int efficientie = berekenEfficientie();
        int substitutiegedrag = berekenSubstitutiegedrag();
        int coherentie = berekenCoherentie();
    }

    public int berekenProductiviteit() {
        // bereking productiviteit
        return woorden.length;
    }

    public int berekenEfficientie() {
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

    public int berekenSubstitutiegedrag() {
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

    public int berekenCoherentie() {
        // berekening coherentie
        List<Coherentie> coherentieLijst = dbCoherentie.getCoherentieList();

        for (int i = 0; i < coherentieLijst.size(); i++) {
            for (String woord : woorden) {
                if (coherentieLijst.get(i).getOorzaak().equals(woord)) {
//                    woordenTeller++;
                }
            }
        }

        return 0;
    }
}
