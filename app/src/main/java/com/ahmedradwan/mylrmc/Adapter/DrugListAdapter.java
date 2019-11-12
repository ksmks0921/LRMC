package com.ahmedradwan.mylrmc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedradwan.mylrmc.R;

import java.util.ArrayList;

public class DrugListAdapter extends BaseAdapter {

    private  static LayoutInflater inflater = null;
    Context context;
    ArrayList<String[]> data;

    public DrugListAdapter(Context context, ArrayList<String[]> data)
    {
        this.context = context;
        this.data = data;

    }
    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.listview_cell, parent,false);

        TextView drug_name = (TextView) vi.findViewById(R.id.drug_name);
        TextView quantity_1 = (TextView) vi.findViewById(R.id.quantity_1);
        TextView pay_1 = (TextView) vi.findViewById(R.id.pay_1);
        TextView quantity_2 = (TextView) vi.findViewById(R.id.quantity_2);
        TextView pay_2 = (TextView) vi.findViewById(R.id.pay_2);

        try{
            String[] data_per_position = (String[])data.get(position);

            drug_name.setText(data_per_position[0]);
            quantity_1.setText(data_per_position[1]);
            pay_1.setText(data_per_position[2]);
            quantity_2.setText(data_per_position[3]);
            pay_2.setText(data_per_position[4]);
        }
        catch (Exception e)
        {
            Toast.makeText(context, "No data from server!", Toast.LENGTH_SHORT).show();
        }

        return vi;
    }
}