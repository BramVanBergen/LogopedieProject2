package be.thomasmore.logopedieproject2.Activities;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import be.thomasmore.logopedieproject2.DataService.AantalWoordenDataService;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.AantalWoorden;
import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.Models.Score;
import be.thomasmore.logopedieproject2.R;

public class HistoriekDetailActivity extends MenuActivity {
    ScoreDataService dbScores = new ScoreDataService(new DatabaseHelper(this));
    AantalWoordenDataService dbAantalWoorden = new AantalWoordenDataService(new DatabaseHelper(this));
    PatientDataService dbPatient = new PatientDataService(new DatabaseHelper(this));

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

//    List<Opname> opnameLijst = new ArrayList<>();
    Score score;
    AantalWoorden aantalWoorden;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historiek_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle bundle = getIntent().getExtras();
        Long scoreId = bundle.getLong("scoreId");

        getScore(scoreId);

        getSupportActionBar().setTitle("Sessie: " + score.getDatum());
    }

    private void getScore(Long scoreId) {
        score = dbScores.getScore(scoreId);
        aantalWoorden = dbAantalWoorden.getAantalWoorden(scoreId);

        TextView efficientie = (TextView)findViewById(R.id.content_historiek_detail_efficientie);
        TextView productiviteit = (TextView)findViewById(R.id.content_historiek_detail_productiviteit);
        TextView substitutiegedrag = (TextView)findViewById(R.id.content_historiek_detail_substitutiegedrag);
        TextView coherentie = (TextView)findViewById(R.id.content_historiek_detail_coherentie);

        efficientie.setText(score.getEfficientie() + " (woorden: " + aantalWoorden.getEfficientie() + ")");
        productiviteit.setText(score.getProductiviteit() + " (woorden: " + aantalWoorden.getProductiviteit() + ")");
        substitutiegedrag.setText(score.getSubstitutiegedrag() + " (woorden: " + aantalWoorden.getSubstitutiegedrag() + ")");
        coherentie.setText(score.getCoherentie() + " (woorden: " + aantalWoorden.getCoherentie() + ")");


        getPatient(score.getPatientId());
        getOpname(score.getAudioFile());
    }

    private void getPatient(Long patientId) {
        Patient patient = dbPatient.getPatient(patientId);

        // chronologische leeftijd berekenen
//        Date chronologischeLeeftijd;
        Long verschilDatums;
        String testDatumString = score.getDatum();
        String geboorteDatumString = patient.getGeboortedatum();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        TextView patientNaam = (TextView)findViewById(R.id.content_historiek_detail_patientNaam);
        TextView chronologischeLeeftijd = (TextView)findViewById(R.id.content_historiek_detail_chronologischeLeeftijd);

        patientNaam.setText(patient.getVoornaam() + " " + patient.getAchternaam());
        try {
            Date testDatum = format.parse(testDatumString);
            Date geboorteDatum = format.parse(geboorteDatumString);

            verschilDatums = Math.abs(testDatum.getTime() - geboorteDatum.getTime());
            chronologischeLeeftijd.setText(verschilDatums + "");

//            assertEquals(diff, 6);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void getOpname(String audioFile) {
        List<Score> scores = dbScores.getScoreList();

        String path = this.getFilesDir() + "/Audio/Logopedie/" + score.getPatientId() + "/" + audioFile;
    }
}
