package be.thomasmore.logopedieproject2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import be.thomasmore.logopedieproject2.Activities.LoginActivity;


public class MainActivity extends MenuActivity {

    SharedPreferences prf;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");

        TextView result = (TextView) findViewById(R.id.main_gebruiker);
        Button btnLogOut = (Button) findViewById(R.id.logUitButton);
        prf = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(MainActivity.this, LoginActivity.class);
        result.setText("Hallo " + prf.getString("gebruikersnaam", null));
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });
    }


}
