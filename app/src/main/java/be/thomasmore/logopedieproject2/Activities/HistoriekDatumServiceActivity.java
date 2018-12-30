package be.thomasmore.logopedieproject2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import be.thomasmore.logopedieproject2.Adapters.HistoriekDatumAdapter;
import be.thomasmore.logopedieproject2.Adapters.HistoriekFolderAdapter;
import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.DatabaseHelper;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Score;
import be.thomasmore.logopedieproject2.R;

public class HistoriekDatumServiceActivity extends MenuActivity {
    ScoreDataService dbScore = new ScoreDataService(new DatabaseHelper(this));
    PatientDataService dbPatient = new PatientDataService(new DatabaseHelper(this));

    List<Score> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historiek_datum_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        Long patientId = bundle.getLong("patientId");

        getPatientHistory(patientId);
        useCustomAdapter();

        getSupportActionBar().setTitle("Historiek: " + dbPatient.getPatient(patientId).getVoornaam() + " " + dbPatient.getPatient(patientId).getAchternaam());
    }

    private void getPatientHistory(Long patientId) {
        scoreList = dbScore.getScoreListByPatientId(patientId);
    }

    private void useCustomAdapter() {
        HistoriekDatumAdapter datumAdapter =
                new HistoriekDatumAdapter(getApplicationContext(), scoreList);

        final ListView listViewDatums = (ListView) findViewById(R.id.content_historiek_datum_service_listview);
        listViewDatums.setAdapter(datumAdapter);

        listViewDatums.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                toonDetail(scoreList.get(position).getId());
            }
        });
    }

    private void toonDetail(long scoreId) {
        Bundle bundle = new Bundle();
        bundle.putLong("scoreId", scoreId);

        Intent intent = new Intent(this, HistoriekDetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
