package be.thomasmore.logopedieproject2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

import be.thomasmore.logopedieproject2.Models.Patient;
import be.thomasmore.logopedieproject2.R;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private List<Patient> patienten;
    private List<String> patientenNamen;
    private Context context;

    public CustomSpinnerAdapter (Context context, List<Patient> patienten, List<String> patientenNamen){
        super(context, android.R.layout.simple_spinner_dropdown_item, patientenNamen);
        this.context = context;
        this.patienten = patienten;
        this.patientenNamen = patientenNamen;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.spinner_custom_layout, parent, false);

        TextView spinnerText = (TextView) rowView.findViewById(R.id.spinner_custom_layout_textview);
        spinnerText.setText(patienten.get(position).getVoornaam() + " " + patienten.get(position).getAchternaam());
        spinnerText.setTag(patienten.get(position).getId());

        return rowView;
    }
}
