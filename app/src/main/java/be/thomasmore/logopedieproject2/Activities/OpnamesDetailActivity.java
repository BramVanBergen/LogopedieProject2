package be.thomasmore.logopedieproject2.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.Adapters.OpnamesFilesAdapter;
import be.thomasmore.logopedieproject2.Adapters.OpnamesFolderAdapter;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.R;

public class OpnamesDetailActivity extends MenuActivity {

    List<Opname> opnameLijst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opnames_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Opnames");

        Bundle bundle = getIntent().getExtras();
        String folder = bundle.getString("folder");

        getOpnames(folder);
        useCustomAdapter();
    }

    private void getOpnames(String folder) {
        String path = this.getFilesDir() + "/Audio/Logopedie/" + folder;
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                opnameLijst.add(new Opname(files[i].getName()));
            }
        }
    }

    private void useCustomAdapter() {
        OpnamesFilesAdapter opnameLijstAdapter =
                new OpnamesFilesAdapter(getApplicationContext(), opnameLijst);

        final ListView listViewPlatforms = (ListView) findViewById(R.id.listView_opnames_detail);
        listViewPlatforms.setAdapter(opnameLijstAdapter);

//        listViewPlatforms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parentView, View childView, int position, long id) {
//                toonFiles(opnameLijst.get(position).getNaam());
//            }
//        });
    }
}
