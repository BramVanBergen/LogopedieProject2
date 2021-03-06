package be.thomasmore.logopedieproject2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import be.thomasmore.logopedieproject2.DataService.AantalWoordenDataService;
import be.thomasmore.logopedieproject2.DataService.CoherentieDataService;
import be.thomasmore.logopedieproject2.DataService.EfficientieDataService;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.DataService.SubstitutiegedragDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MainActivity;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.AantalWoorden;
import be.thomasmore.logopedieproject2.Models.Coherentie;
import be.thomasmore.logopedieproject2.Models.Efficientie;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.Models.Score;
import be.thomasmore.logopedieproject2.Models.Substitutiegedrag;
import be.thomasmore.logopedieproject2.OefeningenHelper;
import be.thomasmore.logopedieproject2.R;

import static android.widget.RelativeLayout.TRUE;

public class SchriftelijkActivity extends MenuActivity {
    OefeningenHelper oefeningenHelper = new OefeningenHelper(this);
    ScoreDataService dbScore = new ScoreDataService(new DatabaseHelper(this));
    AantalWoordenDataService dbAantalWoorden = new AantalWoordenDataService(new DatabaseHelper(this));
    PatientDataService dbPatient = new PatientDataService(new DatabaseHelper(this));


    private String schriftelijkeBeschrijving;
    private String[] woorden;

    int productiviteitScore, productiviteitAantalWoorden, efficientieScore, efficientieAantalWoorden, substitutiegedragScore, substitutiegedragAantalWoorden, coherentieScore, coherentieAantalWoorden;

    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schriftelijk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Schriftelijke oefening");

        Bundle bundle = getIntent().getExtras();
        Long patientId = bundle.getLong("patientId");

        patient = getPatient(patientId);

        setSituatieplaat();
    }

    public Patient getPatient(Long patientId) {
        patient = dbPatient.getPatient(patientId);

        return patient;
    }

    private void setSituatieplaat() {
        // situatieplaat in de app zetten
        ImageView situatieplaat = (ImageView) findViewById(R.id.situatieplaat);
        situatieplaat.setImageResource(R.drawable.situatieplaat_1);
    }

    public void onSubmit(View v) {
        // folder aanmaken voor patiënt
        File folder = new File(this.getFilesDir() +
                File.separator + "Audio/Logopedie/" + patient.getId());

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // de beschrijving ophalen
        EditText beschrijving = (EditText) findViewById(R.id.beschrijving_schriftelijke_plaat);
        schriftelijkeBeschrijving = beschrijving.getText().toString();

        // string opsplitsen in array van woorden
        woorden = schriftelijkeBeschrijving.split("\\s+");

        // bereken de verschillende soorten scores
        berekenScores();

        // redirect naar homepage
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void berekenScores() {
        productiviteitScore = oefeningenHelper.berekenProductiviteitScore(woorden);
        productiviteitAantalWoorden = oefeningenHelper.berekenProductiviteitScore(woorden);

        efficientieScore = oefeningenHelper.berekenEfficientieScore(woorden);
        efficientieAantalWoorden = oefeningenHelper.berekenEfficientieAantalWoorden(woorden);

        substitutiegedragScore = oefeningenHelper.berekenSubstitutiegedragScore(woorden);
        substitutiegedragAantalWoorden = oefeningenHelper.berekenSubstitutiegedragAantalWoorden(woorden);

        coherentieScore = oefeningenHelper.berekenCoherentieScore(woorden);
        coherentieAantalWoorden = oefeningenHelper.berekenCoherentieScore(woorden);

        insertScores();
    }

    private void insertScores() {
        Date datumVandaag = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd-MM-yyyy HH:mm");
        String datumVandaagString = sdf.format(datumVandaag);

        Score score = new Score();
        score.setProductiviteit(productiviteitScore);
        score.setEfficientie(efficientieScore);
        score.setSubstitutiegedrag(substitutiegedragScore);
        score.setCoherentie(coherentieScore);
        score.setDatum(datumVandaagString);
        score.setPatientId(patient.getId());

        dbScore.insertScore(score);

        AantalWoorden aantalWoorden = new AantalWoorden();
        aantalWoorden.setProductiviteit(productiviteitAantalWoorden);
        aantalWoorden.setEfficientie(efficientieAantalWoorden);
        aantalWoorden.setSubstitutiegedrag(substitutiegedragAantalWoorden);
        aantalWoorden.setCoherentie(coherentieAantalWoorden);
        aantalWoorden.setDatum(datumVandaagString);
        aantalWoorden.setPatientId(patient.getId());

        dbAantalWoorden.insertAantalWoorden(aantalWoorden);
    }

}
