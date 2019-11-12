package com.ahmedradwan.mylrmc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedradwan.mylrmc.DrugSample;
import com.ahmedradwan.mylrmc.DrugSampleFree;
import com.ahmedradwan.mylrmc.R;

import java.util.ArrayList;
import java.util.List;

public class DrugListAdapterFree extends BaseAdapter {

    private  static LayoutInflater inflater = null;
    Context context;
    List data;

    public DrugListAdapterFree(Context context, List data)
    {
        this.context = context;
        this.data = data;

    }
    @Override
    public int getCount() {
        return data.size();
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
            vi = inflater.inflate(R.layout.listview_free_cell, parent,false);

        TextView drug_name = (TextView) vi.findViewById(R.id.drug_name_free);
        TextView quantity = (TextView) vi.findViewById(R.id.quantity_free);
        TextView pay = (TextView) vi.findViewById(R.id.pay_free);

        try{
            DrugSampleFree data_per_position = (DrugSampleFree)data.get(position);

            drug_name.setText(data_per_position.getName());
            quantity.setText(data_per_position.getQuantity());
            pay.setText("Free");

        }
        catch (Exception e)
        {
            Toast.makeText(context, "No data from server!", Toast.LENGTH_SHORT).show();
        }

        return vi;
    }
}