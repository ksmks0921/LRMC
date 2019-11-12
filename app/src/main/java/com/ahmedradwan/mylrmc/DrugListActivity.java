package com.ahmedradwan.mylrmc;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;

import com.ahmedradwan.mylrmc.Adapter.DrugListAdapter;
import com.google.android.gms.common.internal.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrugListActivity extends AppCompatActivity {

    private TextView textView;
    private String TAG = "DrugListActivity";
    private List<DrugSample> drugSample = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_list);

//        textView = findViewById(R.id.textview);
        readExcelFileFromAssets();
        ListView listView = (ListView) findViewById(R.id.drugList);
        listView.setAdapter(new DrugListAdapter(this, drugSample));

    }




    public void readExcelFileFromAssets() {

        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        try {

            reader.readLine();

            while ((line = reader.readLine()) != null){

                Log.d("DDDDD","Line:" + line);
                String[] tokens = line.split(",");
                Log.d("DDDDD","token0:" + tokens.length);
                DrugSample sample = new DrugSample();
                if(tokens.length >= 5){
                    if(tokens[0].length() > 0){
                        sample.setName(tokens[0]);
                    }
                    else {
                        sample.setName("0");
                    }

                    if(tokens[1].length() > 0){
                        sample.setQuantity_1(tokens[1]);
                    }
                    else {
                        sample.setQuantity_1("0");
                    }
                    if(tokens[2].length() > 0){
                        sample.setPay_1(tokens[2]);
                    }
                    else {
                        sample.setPay_1("0");
                    }
                    if(tokens[3].length() > 0){
                        sample.setQuantity_2(tokens[3]);
                    }
                    else {
                        sample.setQuantity_2("0");
                    }
                    if(tokens[4].length() > 0){
                        sample.setPay_2(tokens[4]);
                    }
                    else {
                        sample.setPay_2("0");
                    }

                    drugSample.add(sample);
                }


            }
        }
        catch(IOException e){
            Log.e(TAG, "error " + e.toString());
        }


    }
}


