package be.thomasmore.logopedieproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import be.thomasmore.logopedieproject2.Activities.LaadPatientSchriftelijkActivity;
import be.thomasmore.logopedieproject2.Activities.LoginActivity;
import be.thomasmore.logopedieproject2.Activities.LaadPatientMondelingActivity;
import be.thomasmore.logopedieproject2.Activities.MondelingActivity;
import be.thomasmore.logopedieproject2.Activities.HistoriekActivity;
import be.thomasmore.logopedieproject2.Activities.PatientActivity;
import be.thomasmore.logopedieproject2.Activities.SchriftelijkActivity;


public class MenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.activity_mondeling:
                startActivity(new Intent(this, LaadPatientMondelingActivity.class));
                return true;
            case R.id.activity_schriftelijk:
                startActivity(new Intent(this, LaadPatientSchriftelijkActivity.class));
                return true;
            case R.id.activity_nieuwe_patient:
                startActivity(new Intent(this, PatientActivity.class));
                return true;
            case R.id.activity_historiek:
                startActivity(new Intent(this, HistoriekActivity.class));
                return true;
            case R.id.activity_login:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return false;
        }
    }
}
