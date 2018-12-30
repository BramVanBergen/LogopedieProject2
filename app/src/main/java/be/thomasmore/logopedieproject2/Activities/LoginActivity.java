package be.thomasmore.logopedieproject2.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import be.thomasmore.logopedieproject2.DataService.LogopedistDataService;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Logopedist;
import be.thomasmore.logopedieproject2.R;

public class LoginActivity extends AppCompatActivity {

    private LogopedistDataService dbL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log in");

        //BUTTON SUBMIT ONCLICK
        Button btnSubmit = (Button) findViewById(R.id.buttonsubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn() {
        dbL = new LogopedistDataService(new DatabaseHelper(this));
Logopedist logopedist = new Logopedist();

        String gebruikersnaam = ((EditText)findViewById(R.id.gebruikersnaam)).getText().toString();
        String wachtwoord = ((EditText)findViewById(R.id.wachtwoord)).getText().toString();


        if (gebruikersnaam.contains("@")){
            logopedist.setEmail(gebruikersnaam);
        }
        else {
            logopedist.setGebruikersnaam(gebruikersnaam);
        }


        List<Logopedist> logopedisten = dbL.getLogopedistList();
        if (logopedisten.contains(logopedist) )
        {
            dbL.getLogopedist()
        }

    }

}
