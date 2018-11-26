package com.example.studia.projekt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.studia.projekt.db.model.FieldRecord;
import java.util.ArrayList;

public class SimpleArrayAdapter extends ArrayAdapter<FieldRecord> {

  private final Context context;
  private final ArrayList<FieldRecord> values;

  public SimpleArrayAdapter(Context context, ArrayList<FieldRecord> values) {
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
    textView.setText(values.get(position).getDate());

    return rowView;
  }
}
