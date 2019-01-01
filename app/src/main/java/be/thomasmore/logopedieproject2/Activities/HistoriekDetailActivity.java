package be.thomasmore.logopedieproject2.Activities;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import be.thomasmore.logopedieproject2.DataService.AantalWoordenDataService;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.AantalWoorden;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.Models.Score;
import be.thomasmore.logopedieproject2.R;

public class HistoriekDetailActivity extends MenuActivity {
    ScoreDataService dbScores = new ScoreDataService(new DatabaseHelper(this));
    AantalWoordenDataService dbAantalWoorden = new AantalWoordenDataService(new DatabaseHelper(this));
    PatientDataService dbPatient = new PatientDataService(new DatabaseHelper(this));

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    int length = 0;

    ImageView imageViewPlay, imageViewPause, imageViewStop;

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
        Long verschilDatums;
        String testDatumString = score.getDatum();
        String geboorteDatumString = patient.getGeboortedatum();
        DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        TextView patientNaam = (TextView)findViewById(R.id.content_historiek_detail_patientNaam);
        TextView chronologischeLeeftijd = (TextView)findViewById(R.id.content_historiek_detail_chronologischeLeeftijd);

        patientNaam.setText(patient.getVoornaam() + " " + patient.getAchternaam());
        try {
            Date testDatum = formatDate.parse(testDatumString);
            Date geboorteDatum = formatDate.parse(geboorteDatumString);

            verschilDatums = testDatum.getTime() - geboorteDatum.getTime();

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(verschilDatums);

            int jaren = c.get(Calendar.YEAR)-1970;
            int maanden = c.get(Calendar.MONTH);
            int dagen = c.get(Calendar.DAY_OF_MONTH)-1;

            chronologischeLeeftijd.setText(jaren + " jaar, " + maanden + " maanden en " + dagen + " dagen");
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void getOpname(final String audioFile) {
        List<Score> scores = dbScores.getScoreList();

        imageViewPlay = (ImageView) findViewById(R.id.opnames_detail_play);
        imageViewPause = (ImageView) findViewById(R.id.opnames_detail_pause);
        imageViewStop = (ImageView) findViewById(R.id.opnames_detail_stop);

        if (audioFile != null) {
            imageViewPlay.setEnabled(true);
            imageViewPause.setEnabled(false);
            imageViewStop.setEnabled(false);

            imageViewPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageViewPlay.setEnabled(false);
                    imageViewPause.setEnabled(true);
                    imageViewStop.setEnabled(true);

                    play(view, audioFile);
                }
            });

            imageViewPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageViewPlay.setEnabled(true);
                    imageViewPause.setEnabled(false);
                    imageViewStop.setEnabled(false);

                    pause(view);
                }
            });

            imageViewStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageViewPlay.setEnabled(true);
                    imageViewPause.setEnabled(false);
                    imageViewStop.setEnabled(false);

                    stop(view);
                }
            });
        } else {
            TextView opnamesTextView = (TextView) findViewById(R.id.content_historiek_detail_opnames);
            opnamesTextView.setVisibility(View.INVISIBLE);

            imageViewPlay.setVisibility(View.INVISIBLE);
            imageViewPause.setVisibility(View.INVISIBLE);
            imageViewStop.setVisibility(View.INVISIBLE);
        }



    }

    private void play(View view, String audioFile) {
        String pathsave = this.getFilesDir() + "/Audio/Logopedie/" + score.getPatientId() + "/" + audioFile;

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(pathsave);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        mediaPlayer.seekTo(length);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imageViewPlay.setEnabled(true);
            }
        });

        Toast.makeText(this, "Afspelen...", Toast.LENGTH_SHORT).show();
    }

    private void pause(View view) {
        length = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
        Toast.makeText(this, "Pauzeren...", Toast.LENGTH_SHORT).show();
    }

    private void stop(View view) {
        length = 0;
        mediaPlayer.stop();
        mediaPlayer.release();
        Toast.makeText(this, "Stoppen...", Toast.LENGTH_SHORT).show();
    }
}
