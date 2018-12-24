package be.thomasmore.logopedieproject2.Activities;

import android.Manifest;
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
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import be.thomasmore.logopedieproject2.MenuActivity;
import be.thomasmore.logopedieproject2.R;

public class MondelingActivity extends MenuActivity {
    Button buttonStartOpname, buttonStopOpname, buttonOpnameOpslaan, buttonPlay, buttonStop;

    String pathsave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

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
        buttonStartOpname = (Button)findViewById(R.id.button_start_opnemen);
        buttonStopOpname = (Button)findViewById(R.id.button_stop_opnemen);
        buttonOpnameOpslaan = (Button)findViewById(R.id.button_opname_opslaan);
        buttonPlay = (Button)findViewById(R.id.button_opname_afspelen);
        buttonStop = (Button)findViewById(R.id.button_opname_stoppen);


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

            buttonStop.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    opnameStoppen(v);
                }
            });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    public void startOpnemen(View v) {
        if (checkPermissionFromDevice()) {
            pathsave = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/"
                    + UUID.randomUUID().toString() + "_audio_record.3gp";
            setupMediaRecorder();
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            buttonPlay.setEnabled(false);
            buttonStop.setEnabled(false);

            Toast.makeText(MondelingActivity.this, "Opnemen...", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }
    }

    public void stopOpnemen(View v) {
        mediaRecorder.stop();
        buttonStopOpname.setEnabled(false);
        buttonPlay.setEnabled(true);
        buttonStartOpname.setEnabled(true);
        buttonStop.setEnabled(false);
    }

    public void opnameAfspelen(View v) {
        buttonStop.setEnabled(true);
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
        Toast.makeText(MondelingActivity.this, "Afspelen...", Toast.LENGTH_SHORT).show();
    }

    public void opnameStoppen(View v) {
        buttonStopOpname.setEnabled(false);
        buttonStartOpname.setEnabled(true);
        buttonStop.setEnabled(false);
        buttonPlay.setEnabled(true);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            setupMediaRecorder();
        } else {

        }
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathsave);
    }

    public void opnameOpslaan(View v) {

    }
}
