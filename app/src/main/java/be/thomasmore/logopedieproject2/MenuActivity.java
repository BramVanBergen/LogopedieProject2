package be.thomasmore.logopedieproject2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.thomasmore.logopedieproject2.Activities.MondelingActivity;
import be.thomasmore.logopedieproject2.Activities.SchriftelijkAcitivty;
import be.thomasmore.logopedieproject2.DataService.SoortAfasieDataService;
import be.thomasmore.logopedieproject2.Models.SoortAfasie;


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
                startActivity(new Intent(this, MondelingActivity.class));
                return true;
            case R.id.activity_schriftelijk:
                startActivity(new Intent(this, SchriftelijkAcitivty.class));
                return true;
            case R.id.activity_nieuwe_patient:
                startActivity(new Intent(this, PatientActivity.class));
                return true;
            default:
                return false;
        }
    }
}
