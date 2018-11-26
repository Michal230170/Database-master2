package com.example.studia.projekt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import com.example.studia.projekt.db.model.FieldRecords;

import java.util.ArrayList;

public class SimpleArrayAdapterFields extends ArrayAdapter<FieldRecords> {
    private final Context context;
    private final ArrayList<FieldRecords> values;

    public SimpleArrayAdapterFields(Context context, ArrayList<FieldRecords> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.rowlayout, parent, false);

        TextView textView = rowView.findViewById(R.id.label);
        textView.setText(values.get(position).getNumber());

        return rowView;
    }
}
