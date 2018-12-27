package be.thomasmore.logopedieproject2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.Adapters.OpnamesFolderAdapter;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.R;

public class OpnamesActivity extends MenuActivity {

    List<Opname> opnameLijst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opnames);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Opnames");

        getOpnames();
        useCustomAdapter();
    }

    private void getOpnames() {
        String path = this.getFilesDir() + "/Audio/Logopedie/";
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                opnameLijst.add(new Opname(files[i].getName()));
            }
        }
    }

    private void useCustomAdapter() {
        OpnamesFolderAdapter opnameLijstAdapter =
                new OpnamesFolderAdapter(getApplicationContext(), opnameLijst);

        final ListView listViewPlatforms = (ListView) findViewById(R.id.listView_opnames);
        listViewPlatforms.setAdapter(opnameLijstAdapter);

        listViewPlatforms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                toonFiles(opnameLijst.get(position).getNaam());
            }
        });
    }

    private void toonFiles(String folder) {
        Bundle bundle = new Bundle();
        bundle.putString("folder", folder);

        Intent intent = new Intent(this, OpnamesDetailActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
