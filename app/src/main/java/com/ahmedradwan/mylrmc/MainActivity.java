package com.ahmedradwan.mylrmc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button patientBtn, locationBtn, fluBtn, listBtn, dentalBtn, callBtn, localAppBtn, newBtn;
    String HealowappPackageName, appPackageName;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientBtn = findViewById(R.id.patientBtn);
        locationBtn = findViewById(R.id.locationBtn);
        fluBtn = findViewById(R.id.fluBtn);
        listBtn = findViewById(R.id.listBtn);
        dentalBtn = findViewById(R.id.dentalBtn);
        callBtn = findViewById(R.id.callBtn);
        localAppBtn = findViewById(R.id.localApp);
        newBtn = findViewById(R.id.newPatient);

        patientBtn.setOnClickListener(this);
        locationBtn.setOnClickListener(this);
        fluBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        dentalBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);
        localAppBtn.setOnClickListener(this);
        newBtn.setOnClickListener(this);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    SharedPreferences preferences = getBaseContext().getSharedPreferences("MYPREF", MODE_PRIVATE);
                    SharedPreferences.Editor  editor = preferences.edit();

                    editor.putBoolean("Location Allowed", true);
                    editor.apply();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Can't use this because you denied location permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferences = getBaseContext().getSharedPreferences("MYPREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        switch (view.getId()){
            case R.id.patientBtn:
                intent = new Intent(getApplicationContext(), WebActivity.class);
                editor.putString("type_url", "patient");
                editor.apply();
                break;
            case R.id.locationBtn:

                intent = new Intent(getApplicationContext(), MapActivity.class);
                break;
            case R.id.fluBtn:
                intent = new Intent(getApplicationContext(), FluActivity.class);
                break;
            case R.id.listBtn:
                intent = new Intent(getApplicationContext(), ListActivity.class);
                break;
            case R.id.dentalBtn:
                intent = new Intent(getApplicationContext(), WebActivity.class);
                editor.putString("type_url", "dental");
                editor.apply();
                break;
            case R.id.callBtn:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8436638000"));
                break;
            case R.id.localApp:

                appPackageName = "com.newtechsys.rxlocalmobile";
                boolean isLocalAppInstalled = appInstalledOrNot(appPackageName);

                if(isLocalAppInstalled) {
                    //This intent will help you to launch if the package is already installed
                    intent = getPackageManager()
                            .getLaunchIntentForPackage(appPackageName);

                    break;

                } else {

                    try {

                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
//                        Toast.makeText(this, "market://details?id=" + appPackageName, Toast.LENGTH_SHORT).show();

                    } catch (android.content.ActivityNotFoundException anfe) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName));
                        Toast.makeText(this, "https://play.google.com/store/apps/details?id=" + appPackageName, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }


            case R.id.newPatient:
                HealowappPackageName = "com.ecw.healow";
                boolean isHealowAppInstalled = appInstalledOrNot(HealowappPackageName);

                if(isHealowAppInstalled) {
                    //This intent will help you to launch if the package is already installed
                    intent = getPackageManager()
                            .getLaunchIntentForPackage(HealowappPackageName);



                } else {

                    try {

                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + HealowappPackageName));

                    } catch (android.content.ActivityNotFoundException anfe) {
                        intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + HealowappPackageName));

                    }

                }
                break;
        }

        startActivity(intent);

    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}
