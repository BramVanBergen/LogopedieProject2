package be.thomasmore.logopedieproject2.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.DataService.LogopedistDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MainActivity;
import be.thomasmore.logopedieproject2.Models.Logopedist;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.R;

public class RegistreerActivity extends AppCompatActivity {

    LogopedistDataService dbL;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registreer");

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(RegistreerActivity.this, MainActivity.class);

        //BUTTON SUBMIT ONCLICK
        Button btnSubmit = (Button) findViewById(R.id.registreer_button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registreer();
            }
        });

    }

    public void registreer() {
        dbL = new LogopedistDataService(new DatabaseHelper(this));

        List<Logopedist> logopedisten = dbL.getLogopedistList();
        boolean gebruikersnaamInGebruik = false, emailInGebruik = false;
        Logopedist logopedist = new Logopedist();

        String voornaam = ((EditText) findViewById(R.id.registreer_voornaam)).getText().toString();
        String achternaam = ((EditText) findViewById(R.id.registreer_achternaam)).getText().toString();
        String email = ((EditText) findViewById(R.id.registreer_email)).getText().toString();
        String gebruikersnaam = ((EditText) findViewById(R.id.registreer_gebruikersnaam)).getText().toString();
        String wachtwoord = ((EditText) findViewById(R.id.registreer_wachtwoord)).getText().toString();
        String bevestigwachtwoord = ((EditText) findViewById(R.id.registreer_wachtwoord_bevestiging)).getText().toString();

        for (int i = 0; i < logopedisten.size(); i++) {
            if (logopedisten.get(i).getGebruikersnaam().equals(gebruikersnaam)) {
                gebruikersnaamInGebruik = true;
            }
            if (logopedisten.get(i).getEmail().equals(email)) {
                emailInGebruik = true;
            }
        }

        if (gebruikersnaamInGebruik && emailInGebruik) {
            Toast.makeText(getApplicationContext(), "Email en gebruikersnaam zijn al in gebruik", Toast.LENGTH_SHORT).show();
        } else if (gebruikersnaamInGebruik) {
            Toast.makeText(getApplicationContext(), "Gebruikersnaam is al in gebruik", Toast.LENGTH_SHORT).show();
        } else if (emailInGebruik) {
            Toast.makeText(getApplicationContext(), "Email is al in gebruik", Toast.LENGTH_SHORT).show();
        }
        else {
            if (!wachtwoord.equals(bevestigwachtwoord)) {
                Toast.makeText(getApplicationContext(), "Wachtwoorden komen niet overeen", Toast.LENGTH_SHORT).show();
            } else {
                logopedist.setVoornaam(voornaam);
                logopedist.setAchternaam(achternaam);
                logopedist.setEmail(email);
                logopedist.setGebruikersnaam(gebruikersnaam);
                logopedist.setWachtwoord(wachtwoord);

                Long id = dbL.insertLogopedist(logopedist);

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("gebruikersnaam", logopedist.getGebruikersnaam());
                editor.putString("email", logopedist.getEmail());
                editor.putString("achternaam", logopedist.getAchternaam());
                editor.putString("voornaam", logopedist.getVoornaam());
                editor.putLong("id", id);
                editor.putString("wachtwoord", logopedist.getWachtwoord());
                editor.commit();
                Toast.makeText(getApplicationContext(), "Login succesvol!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                dbL = new LogopedistDataService(new DatabaseHelper(this));

                //TODO VERWIJDEREN
                List logopedistentest = dbL.getLogopedistList();
            }
        }
    }
}
