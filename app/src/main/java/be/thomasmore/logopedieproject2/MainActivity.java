package be.thomasmore.logopedieproject2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import be.thomasmore.logopedieproject2.DataService.PatientDataService;
import be.thomasmore.logopedieproject2.DataService.ScoreDataService;
import be.thomasmore.logopedieproject2.DataService.SoortAfasieDataService;
import be.thomasmore.logopedieproject2.Models.SoortAfasie;


public class MainActivity extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    private SoortAfasieDataService dbAfasie;
    private int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day = calendar.get(Calendar.DATE);

    PatientActivity PA = new PatientActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

        //SOORTEN AFASIE
        // Spinner Drop down elements
        dbAfasie = new SoortAfasieDataService(new DatabaseHelper(this));
        List<SoortAfasie> soortenafasie = dbAfasie.getSoortAfasieList();
        List<String> SoortenAfasie = new ArrayList<>();
        for (SoortAfasie soortafasie: soortenafasie)
        {
            SoortenAfasie.add(soortafasie.getNaam());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> AfasieAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SoortenAfasie);

        Spinner SoortAfasieSpinner = (Spinner)findViewById(R.id.afasie);
        SoortAfasieSpinner.setAdapter(AfasieAdapter);

        //BUTTON SUBMIT ONCLICK
        Button btnSubmit = (Button) findViewById(R.id.buttonsubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PA.onSubmit();
            }
        });
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
        datepicker.setTitle(getString(R.string.Geboortedatumlabel));
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
