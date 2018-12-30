package be.thomasmore.logopedieproject2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.Models.Score;
import be.thomasmore.logopedieproject2.R;

public class HistoriekDatumAdapter extends ArrayAdapter<Score> {
    private final Context context;
    private final List<Score> scores;

    public HistoriekDatumAdapter(Context context, List<Score> scores) {
        super(context, R.layout.historiek_listviewitem, scores);
        this.context = context;
        this.scores = scores;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.historiek_listviewitem, parent, false);

        final TextView textViewName = (TextView) rowView.findViewById(R.id.name_folder);

        String datumString = scores.get(position).getDatum();
        textViewName.setText(datumString);

        return rowView;
    }
}
