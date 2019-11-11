package com.ahmedradwan.mylrmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    String[] typeStrArr = {"Medical", "Dental", "Pharmacy", "Behavioral Health", "WIC", "HIV/AIDS", "Migrant", "Seasonal Farm Worker", "Homeless", "Chronic Care Management", "Telehealth", "School Dental"};
    ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = findViewById(R.id.typeList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listitem_cell,typeStrArr);

        listView.setAdapter(adapter);
    }
}
