package be.thomasmore.logopedieproject2.Activities;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject2.Adapters.OpnamesFilesAdapter;
import be.thomasmore.logopedieproject2.Adapters.OpnamesFolderAdapter;
import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.R;

public class OpnamesDetailActivity extends MenuActivity {
    ImageView opnamesPlay, opnamesPause, opnamesStop, opnamesDelete;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

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

        opnamesPlay = (ImageView)findViewById(R.id.opnames_detail_play);
        opnamesPause = (ImageView)findViewById(R.id.opnames_detail_pause);
        opnamesStop = (ImageView)findViewById(R.id.opnames_detail_stop);

        getOpnames(folder);
        useCustomAdapter(folder);
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

    private void useCustomAdapter(String folder) {
        OpnamesFilesAdapter opnameLijstAdapter =
                new OpnamesFilesAdapter(getApplicationContext(), opnameLijst, folder);

        final ListView listViewPlatforms = (ListView) findViewById(R.id.listView_opnames_detail);
        listViewPlatforms.setAdapter(opnameLijstAdapter);
    }
}
