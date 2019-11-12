package com.ahmedradwan.mylrmc;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.ahmedradwan.mylrmc.Adapter.DrugListAdapter;
import com.ahmedradwan.mylrmc.Adapter.DrugListAdapterFree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DrugListActivity extends AppCompatActivity {

    private String TAG = "DrugListActivity";
    private List<DrugSample> drugSample = new ArrayList<>();
    private List<DrugSampleFree> drugSampleFree = new ArrayList<>();
    private LinearLayout discountBtn, freeBtn, free_title, discount_title, free_list, discount_list;
    private TextView free_text, discount_text;
    private ListView listViewDiscount, listViewFree;
    private InputStream is_discount, is_free;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_list);
        //   back button
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        readExcelFileFromAssets();
        listViewDiscount = (ListView) findViewById(R.id.drugList);
        listViewDiscount.setAdapter(new DrugListAdapter(this, drugSample));

        listViewFree = (ListView) findViewById(R.id.free_drugList);
        listViewFree.setAdapter(new DrugListAdapterFree(this, drugSampleFree));




        discountBtn = (LinearLayout) findViewById(R.id.discount_btn);
        freeBtn = findViewById(R.id.free_btn);
        free_text = (TextView) findViewById(R.id.free_text);
        discount_text = (TextView) findViewById(R.id.discount_text);

        free_title = (LinearLayout) findViewById(R.id.free_title);
        free_list = (LinearLayout) findViewById(R.id.free_list_content);
        discount_title = (LinearLayout) findViewById(R.id.title);
        discount_list = (LinearLayout) findViewById(R.id.list_content);

        discountBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
               discountBtn.setBackgroundColor(Color.parseColor("#00ABBF"));
               discount_text.setTextColor(Color.parseColor("#FFFFFF"));
               free_text.setTextColor(Color.parseColor("#00ABBF"));
               freeBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));



               free_title.setVisibility(LinearLayout.GONE);
               free_list.setVisibility(LinearLayout.GONE);

               discount_title.setVisibility(LinearLayout.VISIBLE);
               discount_list.setVisibility(LinearLayout.VISIBLE);


            }
        });



        freeBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                discountBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                discount_text.setTextColor(Color.parseColor("#00ABBF"));
                freeBtn.setBackgroundColor(Color.parseColor("#00ABBF"));
                free_text.setTextColor(Color.parseColor("#FFFFFF"));






                free_title.setVisibility(LinearLayout.VISIBLE);
                free_list.setVisibility(LinearLayout.VISIBLE);

                discount_title.setVisibility(LinearLayout.GONE);
                discount_list.setVisibility(LinearLayout.GONE);
            }
        });
    }




    public void readExcelFileFromAssets() {



        is_discount = getResources().openRawResource(R.raw.discount);
        is_free = getResources().openRawResource(R.raw.free);

        BufferedReader reader_discount = new BufferedReader(
                new InputStreamReader(is_discount, Charset.forName("UTF-8"))
        );


        String line_discount = "";

        try {

            reader_discount.readLine();

            while ((line_discount = reader_discount.readLine()) != null){


                String[] tokens = line_discount.split(",");

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

        // reading free drug list

        BufferedReader reader_free = new BufferedReader(
                new InputStreamReader(is_free, Charset.forName("UTF-8"))
        );
        String line_free = "";
        try {

            reader_free.readLine();

            while ((line_free = reader_free.readLine()) != null){

//                Log.d("DDDDD","Line:" + line);
                String[] tokens_free = line_free.split(",");
                Log.d("DDDDD","token0:" + tokens_free.length);

                DrugSampleFree free_sample = new DrugSampleFree();


                    if(tokens_free.length >= 2){
                        if(tokens_free[0].length() > 0){
                            free_sample.setName(tokens_free[0]);
                        }
                        else {
                            free_sample.setName("0");
                        }

                        if(tokens_free[1].length() > 0){
                            free_sample.setQuantity(tokens_free[1]);
                        }
                        else {
                            free_sample.setQuantity("0");
                        }


                        drugSampleFree.add(free_sample);
                    }

            }
        }
        catch(IOException e){
            Log.e(TAG, "error " + e.toString());
        }

    }
}


