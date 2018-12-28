package be.thomasmore.logopedieproject2.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.R;

public class MondelingActivity extends MenuActivity {
    Button buttonStartOpname, buttonStopOpname;
    ImageView buttonPlay, buttonPause, buttonStop;

    String pathsave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    int length = 0;

    final int REQUEST_PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondeling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mondelinge oefening");

        // request Runtime permission
        if (!checkPermissionFromDevice()) {
            requestPermission();
        }

        // init view
        buttonStartOpname = (Button) findViewById(R.id.button_start_opnemen);
        buttonStopOpname = (Button) findViewById(R.id.button_stop_opnemen);
        buttonPlay = (ImageView) findViewById(R.id.button_opname_afspelen);
        buttonPause = (ImageView) findViewById(R.id.button_opname_pauzeren);
        buttonStop = (ImageView) findViewById(R.id.button_opname_stoppen);

        // disable buttons
        buttonStopOpname.setEnabled(false);
        buttonStartOpname.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonPlay.setEnabled(false);
        buttonPause.setEnabled(false);


        buttonStartOpname.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startOpnemen(v);
            }
        });

        buttonStopOpname.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopOpnemen(v);
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opnameAfspelen(v);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opnamePauzeren(v);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                opnameStoppen(v);
            }
        });

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    private void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Toestemming verleend", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Toestemming geweigerd", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED && record_audio_result == PackageManager.PERMISSION_GRANTED;
    }

    private void startOpnemen(View v) {
        if (checkPermissionFromDevice()) {
            String datumVandaag = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            String tijdUur = new SimpleDateFormat("HH", Locale.getDefault()).format(new Date());
            String tijdMin = new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());
//
            File folder = new File(this.getFilesDir() +
                    File.separator + "Audio/Logopedie/Nuyts Tom/");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            pathsave = folder + "/" + datumVandaag + "_" + tijdUur + "u" + tijdMin + "_NuytsTom_logopedieSessie.mp3";

            setupMediaRecorder();
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            buttonStartOpname.setEnabled(false);
            buttonStopOpname.setEnabled(true);
            buttonPlay.setEnabled(false);
            buttonStop.setEnabled(false);
            buttonPause.setEnabled(false);

            Toast.makeText(MondelingActivity.this, "Opnemen...", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }
    }

    private void stopOpnemen(View v) {
        mediaRecorder.stop();
        buttonStopOpname.setEnabled(false);
        buttonPlay.setEnabled(true);
        buttonStartOpname.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonPause.setEnabled(false);
    }

    private void opnameAfspelen(View v) {
        buttonStop.setEnabled(true);
        buttonPause.setEnabled(true);
        buttonStopOpname.setEnabled(false);
        buttonStartOpname.setEnabled(false);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(pathsave);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.seekTo(length);
        Toast.makeText(MondelingActivity.this, "Afspelen...", Toast.LENGTH_SHORT).show();
    }

    private void opnamePauzeren(View v) {
        buttonStartOpname.setEnabled(true);
        buttonStopOpname.setEnabled(false);
        buttonStop.setEnabled(true);
        buttonPlay.setEnabled(true);
        buttonPause.setEnabled(false);

        if (mediaPlayer != null) {
            mediaPlayer.pause();
            length = mediaPlayer.getCurrentPosition();
        }

        Toast.makeText(MondelingActivity.this, "Pauzeren...", Toast.LENGTH_SHORT).show();
    }

    private void opnameStoppen(View v) {
        buttonStopOpname.setEnabled(false);
        buttonStartOpname.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonPlay.setEnabled(true);
        buttonPause.setEnabled(true);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            length = 0;
            setupMediaRecorder();
        } else {

        }

        Toast.makeText(MondelingActivity.this, "Stoppen...", Toast.LENGTH_SHORT).show();
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathsave);

        File file = new File(pathsave);
        file.setReadable(true, false);
        file.setWritable(true, false);
    }
}
