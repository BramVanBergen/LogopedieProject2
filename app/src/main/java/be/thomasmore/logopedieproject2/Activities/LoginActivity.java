package be.thomasmore.logopedieproject2.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import be.thomasmore.logopedieproject2.DataService.LogopedistDataService;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MainActivity;
import be.thomasmore.logopedieproject2.Models.Logopedist;
import be.thomasmore.logopedieproject2.R;

public class LoginActivity extends AppCompatActivity {

    private LogopedistDataService dbL;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log in");

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(LoginActivity.this, MainActivity.class);

        if (pref.contains("username") && pref.contains("password")) {
            startActivity(intent);
        }

        //BUTTON LOGIN ONCLICK
        Button logInButton = (Button) findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        //BUTTON LOGIN ONCLICK
        final Button registreerButton = (Button) findViewById(R.id.link_registreer);
        registreerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistreerActivity.class));
            }
        });
    }

    private void logIn() {
        dbL = new LogopedistDataService(new DatabaseHelper(this));
        Logopedist logopedist = new Logopedist();

        String gebruikersnaamLogin = ((EditText) findViewById(R.id.gebruikersnaam)).getText().toString();
        String wachtwoordLogin = ((EditText) findViewById(R.id.wachtwoord)).getText().toString();

        if (dbL.login(gebruikersnaamLogin, wachtwoordLogin) != null) {
            String gebruikersnaam = dbL.login(gebruikersnaamLogin, wachtwoordLogin).getGebruikersnaam();
            String achternaam = dbL.login(gebruikersnaamLogin, wachtwoordLogin).getAchternaam();
            String voornaam = dbL.login(gebruikersnaamLogin, wachtwoordLogin).getVoornaam();
            String email = dbL.login(gebruikersnaamLogin, wachtwoordLogin).getEmail();
            Long id = dbL.login(gebruikersnaamLogin, wachtwoordLogin).getId();
            String wachtwoord = dbL.login(gebruikersnaamLogin, wachtwoordLogin).getWachtwoord();

            logopedist.setGebruikersnaam(gebruikersnaam);
            logopedist.setEmail(email);
            logopedist.setAchternaam(achternaam);
            logopedist.setVoornaam(voornaam);
            logopedist.setId(id);
            logopedist.setWachtwoord(wachtwoord);

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("gebruikersnaam", gebruikersnaam);
            editor.putString("email", email);
            editor.putString("achternaam", achternaam);
            editor.putString("voornaam", voornaam);
            editor.putLong("id", id);
            editor.putString("wachtwoord", wachtwoord);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Login succesvol!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Foutieve gegevens", Toast.LENGTH_SHORT).show();
        }





    }
}
