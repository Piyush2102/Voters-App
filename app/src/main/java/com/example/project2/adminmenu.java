package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class adminmenu extends AppCompatActivity {
Button addvoter,addcntstnt,seelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {
            setContentView(R.layout.activity_adminmenu);
        }
        else
        {
            Toast toast = Toast.makeText(adminmenu.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }
        addvoter=findViewById(R.id.addvoter);
        addcntstnt=findViewById(R.id.addcntstnt);
        seelist=findViewById(R.id.seelist);
        addvoter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminmenu.this,addvoters.class));
            }
        });
        addcntstnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminmenu.this,addcontestant.class));
            }
        });
        seelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminmenu.this,seelist.class));
            }
        });
    }
}