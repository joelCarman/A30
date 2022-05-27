package com.example.testlocation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private static TextView tv_latitude, tv_longitude,tv_speed;
    private Button btn_getLocation;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);
        tv_latitude = (TextView)findViewById(R.id.tv_latitude);
        tv_longitude = (TextView)findViewById(R.id.tv_longitude);
        tv_speed = (TextView)findViewById(R.id.tv_speed);
        btn_getLocation = (Button)findViewById(R.id.btn_getLocation);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},101);
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });


    }
    public void getLocation(){
        if (gpsTracker == null) {
            gpsTracker = new GpsTracker(MainActivity.this);
        }
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            tv_latitude.setText(String.valueOf(latitude));
            tv_longitude.setText(String.valueOf(longitude));
        }
    }

    public static void update(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        tv_latitude.setText(String.valueOf(latitude));
        tv_longitude.setText(String.valueOf(longitude));
        tv_speed.setText(String.valueOf(location.getSpeed()));
    }

    public void popup(String text){
//        builder.setMessage(text);
//        builder.setTitle("Alert !");
//        // Create the Alert dialog
//        AlertDialog alertDialog = builder.create();
//
//        // Show the Alert Dialog box
//        alertDialog.show();
    }
}