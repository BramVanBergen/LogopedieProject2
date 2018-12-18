package be.thomasmore.logopedieproject2;

import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.Models.Score;

public class PatientActivity extends AppCompatActivity {
    private PatientDataService dbP;
    private ScoreDataService dbS;
    String testdatumVar = "";

    //
    //  Submit
    //
    public void onSubmit() {
        dbP = new PatientDataService();
        Patient patient = new Patient();

        String voornaam = ((TextView) findViewById(R.id.voornaam)).getText().toString();
        String naam = ((TextView) findViewById(R.id.naam)).getText().toString();
        String geboortedatum = ((TextView) findViewById(R.id.geboortedatum)).getText().toString();
        String testdatuminput = ((TextView) findViewById(R.id.testdatum)).getText().toString();
        Spinner geslacht = (Spinner) findViewById(R.id.geslacht);
        Spinner soortafasie = (Spinner) findViewById(R.id.afasie);

        patient.setVoornaam(voornaam);
        patient.setAchternaam(naam);
        patient.setGeboortedatum(geboortedatum);
        patient.setGeslacht(geslacht.toString());
        patient.setSoortAfasieId(soortafasie.getId());

        testdatumVar = testdatuminput;

        dbP.insertPatient(patient);
    }

    //
    //  Bereken Chronologische datum
    //
    public String ChronologischeLeeftijd(String geboortedatum, String testdatum) {
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        String chronologischeLeeftijd = "";

        try {
            long difference = Math.abs(format.parse(testdatum).getTime() - format.parse(geboortedatum).getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            chronologischeLeeftijd = Long.toString(differenceDates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return chronologischeLeeftijd;
    }

}
