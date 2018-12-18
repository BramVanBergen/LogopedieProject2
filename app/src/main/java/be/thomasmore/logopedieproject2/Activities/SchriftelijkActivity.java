package be.thomasmore.logopedieproject2.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.R;

public class SchriftelijkActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schriftelijk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Schriftelijke oefening");

        setSituatieplaat();
    }

    public void setSituatieplaat() {
        ImageView situatieplaat = (ImageView) findViewById(R.id.situatieplaat);
        situatieplaat.setImageResource(R.drawable.situatieplaat_1);
    }

}
