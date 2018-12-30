package be.thomasmore.logopedieproject2.Adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.R;

public class HistoriekFilesAdapter extends ArrayAdapter<Opname> {
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    private final Context context;
    private final List<Opname> values;
    private final String folder;
    private int length = 0;

    public HistoriekFilesAdapter(Context context, List<Opname> values, String folder) {
        super(context, R.layout.historiek_listviewitem, values);
        this.context = context;
        this.values = values;
        this.folder = folder;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.historiek_detail_listviewitem, parent, false);

        final TextView textViewName = (TextView) rowView.findViewById(R.id.name_folder);
        final ImageView imageViewPlay = (ImageView) rowView.findViewById(R.id.opnames_detail_play);
        final ImageView imageViewPause = (ImageView) rowView.findViewById(R.id.opnames_detail_pause);
        final ImageView imageViewStop = (ImageView) rowView.findViewById(R.id.opnames_detail_stop);

        textViewName.setText(values.get(position).getNaam());
        imageViewPlay.setTag(position);
        imageViewPause.setTag(position);
        imageViewStop.setTag(position);

        imageViewPlay.setEnabled(true);
        imageViewPause.setEnabled(false);
        imageViewStop.setEnabled(false);

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlay.setEnabled(false);
                imageViewPause.setEnabled(true);
                imageViewStop.setEnabled(true);

                play(view, values.get(position));
            }
        });

        imageViewPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlay.setEnabled(true);
                imageViewPause.setEnabled(false);
                imageViewStop.setEnabled(false);

                pause(view);
            }
        });

        imageViewStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewPlay.setEnabled(true);
                imageViewPause.setEnabled(false);
                imageViewStop.setEnabled(false);

                stop(view);
            }
        });

        return rowView;
    }

    private void play(View view, Opname opname) {
        String pathsave = context.getFilesDir() + "/Audio/Logopedie/" + folder + "/" + opname.getNaam();

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(pathsave);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.start();
        mediaPlayer.seekTo(length);


        Toast.makeText(context, "Afspelen...", Toast.LENGTH_SHORT).show();
    }

    private void pause(View view) {
        length = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
        Toast.makeText(context, "Pauzeren...", Toast.LENGTH_SHORT).show();
    }

    private void stop(View view) {
        length = 0;
        mediaPlayer.stop();
        mediaPlayer.release();
        Toast.makeText(context, "Stoppen...", Toast.LENGTH_SHORT).show();
    }
}
