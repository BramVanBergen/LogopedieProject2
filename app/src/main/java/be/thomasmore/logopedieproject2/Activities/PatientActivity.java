package be.thomasmore.logopedieproject2.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.DataService.SoortAfasieDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.Models.Score;
import be.thomasmore.logopedieproject2.Models.SoortAfasie;
import be.thomasmore.logopedieproject2.R;

public class PatientActivity extends MenuActivity {

    private PatientDataService dbP;

    Calendar calendar = Calendar.getInstance();
    private int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day = calendar.get(Calendar.DATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nieuwe patiënt");

        //DATEPICKER GEBOORTEDATUM + SET CHRONOLOGISCHE LEEFTIJD
        final EditText geboortedatum = (EditText) findViewById(R.id.geboortedatum);
        geboortedatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialogGeboortedatum();
            }
        });

        geboortedatum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDatePickerDialogGeboortedatum();
                }
            }
        });

        //DATEPICKER TESTDATUM + SET CHRONOLOGISCHE LEEFTIJD
        EditText testdatum = (EditText) findViewById(R.id.testdatum);
        testdatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialogTestdatum();
            }
        });

        testdatum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDatePickerDialogTestdatum();
                }
            }
        });

        //BUTTON SUBMIT ONCLICK
        Button btnSubmit = (Button) findViewById(R.id.buttonsubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    //
    //  Submit
    //
    public void onSubmit() {
        dbP = new PatientDataService(new DatabaseHelper(this));
        Patient patient = new Patient();

        //TODO logopedistID toevoegen

        String voornaam = ((EditText) findViewById(R.id.voornaam)).getText().toString();
        String naam = ((EditText) findViewById(R.id.naam)).getText().toString();
        String geboortedatum = ((EditText) findViewById(R.id.geboortedatum)).getText().toString();
        Spinner geslacht = findViewById(R.id.geslacht);
        String soortAfasie = ((EditText) findViewById(R.id.afasie)).getText().toString();


        patient.setVoornaam(voornaam);
        patient.setAchternaam(naam);
        patient.setGeboortedatum(geboortedatum);
        patient.setGeslacht(geslacht.getItemAtPosition(geslacht.getSelectedItemPosition()).toString());
        patient.setSoortAfasie(soortAfasie);

        dbP.insertPatient(patient);

    }

    //TODO Chronologische leeftijd weergeven verplaatsen naar oefeningen
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

    //DATEPICKER GEBOORTEDATUM
    public void showDatePickerDialogGeboortedatum() {
        DatePickerDialog datepicker = new DatePickerDialog(this, mDateSetListenerGeboortedatum, year, month, day);
        datepicker.setTitle(getString(R.string.Geboortedatumlabel));
        datepicker.show();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListenerGeboortedatum = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int newYear, int newMonth, int newDay) {
            year = newYear;
            month = newMonth;
            day = newDay;

            TextView textViewGeboortedatum = (TextView) findViewById(R.id.geboortedatum);
            textViewGeboortedatum.setText(day + "/" + (month + 1) + "/" + year);

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
        }
    };

    //DATEPICKER TESTDATUM
    public void showDatePickerDialogTestdatum() {
        DatePickerDialog datepicker = new DatePickerDialog(this, mDateSetListenertestdatum, year, month, day);
        datepicker.setTitle(getString(R.string.Testdatumlabel));
        datepicker.show();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListenertestdatum = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int newYear, int newMonth, int newDay) {
            year = newYear;
            month = newMonth;
            day = newDay;

            TextView textViewTestdatum = (TextView) findViewById(R.id.testdatum);
            textViewTestdatum.setText(day + "/" + (month + 1) + "/" + year);

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
        }
    };


}
