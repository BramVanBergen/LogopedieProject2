package be.thomasmore.logopedieproject2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.Adapters.HistoriekFolderAdapter;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.R;

public class HistoriekActivity extends MenuActivity {
    PatientDataService dbPatient = new PatientDataService(new DatabaseHelper(this));
    List<Opname> opnameLijst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historiek);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Historiek");

        getOpnames();
        useCustomAdapter();
    }

    private void getOpnames() {
        String path = this.getFilesDir() + "/Audio/Logopedie/";
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                Long patientId = Long.parseLong(files[i].getName());
                Patient patient = dbPatient.getPatient(patientId);

                opnameLijst.add(new Opname(patientId, patient.getVoornaam() + " " + patient.getAchternaam()));
            }
        }
    }

    private void useCustomAdapter() {
        HistoriekFolderAdapter opnameLijstAdapter =
                new HistoriekFolderAdapter(getApplicationContext(), opnameLijst);

        final ListView listViewPatienten = (ListView) findViewById(R.id.listView_opnames);
        listViewPatienten.setAdapter(opnameLijstAdapter);

        listViewPatienten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                toonFiles(opnameLijst.get(position).getId());
            }
        });
    }

    private void toonFiles(Long patientId) {
        Bundle bundle = new Bundle();
        bundle.putLong("patientId", patientId);

        Intent intent = new Intent(this, HistoriekDatumServiceActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
