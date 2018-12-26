package be.thomasmore.logopedieproject2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");

        TextView testTextView = (TextView) findViewById(R.id.test);
        testTextView.setText(this.getFilesDir().getAbsolutePath());
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



}
