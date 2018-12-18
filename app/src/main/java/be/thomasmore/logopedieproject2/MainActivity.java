package be.thomasmore.logopedieproject2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.thomasmore.logopedieproject2.Activities.PatientActivity;
import be.thomasmore.logopedieproject2.DataService.SoortAfasieDataService;
import be.thomasmore.logopedieproject2.Models.SoortAfasie;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void berekenChronologischeLeeftijd() {

        /*

        String geboortedatumtest = ((EditText) findViewById(R.id.geboortedatum)).toString();
        String testdatumtest = ((EditText) findViewById(R.id.testdatum)).toString();
        EditText chronologischeleeftijd = (EditText) findViewById(R.id.chronologischeLeeftijd);



        if (geboortedatumtest != null && testdatumtest != null) {

            chronologischeleeftijd.setText(PA.ChronologischeDatum(geboortedatumtest, testdatumtest));
        }

        */

    }



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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
