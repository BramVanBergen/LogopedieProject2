package be.thomasmore.logopedieproject2.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.R;

public class MondelingActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondeling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mondelinge oefening");
    }

}
