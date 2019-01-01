package be.thomasmore.logopedieproject2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.Adapters.CustomSpinnerAdapter;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.R;

public class LaadPatientSchriftelijkActivity extends AppCompatActivity {
    PatientDataService dbPatient;
    List<Patient> patienten;
    Spinner patientenSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laad_patient_schriftelijk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kies patiënt");

        dbPatient = new PatientDataService(new DatabaseHelper(this));
        patientenSpinner = (Spinner) findViewById(R.id.laad_patient_schriftelijk_spinner);

        getPatienten();

        Button verder = (Button) findViewById(R.id.laad_patient_schriftelijk_button);

        verder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gaNaarOefening(view);
            }
        });
    }

    private void getPatienten() {
        patienten = dbPatient.getPatientList();
        List<String> patientenStrings = new ArrayList<String>();

        for (int i = 0; i < patienten.size(); i++) {
            patientenStrings.add(patienten.get(i).getVoornaam() + " " + patienten.get(i). getAchternaam());
        }

        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(getApplicationContext(), patienten, patientenStrings);
        patientenSpinner.setAdapter(spinnerAdapter);
    }

    private void gaNaarOefening(View v) {
        int itemIndex = patientenSpinner.getSelectedItemPosition();
        long patientId = patienten.get(itemIndex).getId();

        Bundle bundle = new Bundle();
        bundle.putLong("patientId", patientId);

        Intent intent = new Intent(this, SchriftelijkActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
