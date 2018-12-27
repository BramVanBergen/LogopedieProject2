package be.thomasmore.logopedieproject2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.logopedieproject2.Models.Opname;
import be.thomasmore.logopedieproject2.R;

public class OpnamesFilesAdapter extends ArrayAdapter<Opname> {
    private final Context context;
    private final List<Opname> values;

    public OpnamesFilesAdapter(Context context, List<Opname> values) {
        super(context, R.layout.opnames_listviewitem, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.opnames_detail_listviewitem, parent, false);

        final TextView textViewName = (TextView) rowView.findViewById(R.id.name_folder);

        textViewName.setText(values.get(position).getNaam());

        return rowView;
    }
}
