package be.thomasmore.logopedieproject2;

import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.Models.Patient;

public class PatientActivity extends MainActivity{
    private PatientDataService db;

    public void onSubmit() {
        db = new PatientDataService();
        Patient patient = new Patient();
        DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        String chronologischedatum = "";

        String voornaam = ((TextView) findViewById(R.id.voornaam)).getText().toString();
        String naam = ((TextView) findViewById(R.id.naam)).getText().toString();
        String geboortedatum = ((TextView) findViewById(R.id.geboortedatum)).getText().toString();
        String testdatum = ((TextView) findViewById(R.id.testdatum)).getText().toString();
        Spinner geslacht = (Spinner) findViewById(R.id.geslacht);
        Spinner soortafasie = (Spinner) findViewById(R.id.afasie);

        try {
            long difference = Math.abs(format.parse(testdatum).getTime() - format.parse(geboortedatum).getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            chronologischedatum = Long.toString(differenceDates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient.setVoornaam(voornaam);
        patient.setAchternaam(naam);
        patient.setGeboortedatum(geboortedatum);
        patient.setTestdatum(testdatum);
        patient.setChronologischedatum(chronologischedatum);
        patient.setGeslacht(geslacht.toString());
        patient.setSoortAfasieId(soortafasie.getId());

        db.insertPatient(patient);
    }

}
