package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {
TextView username,password;
Button logina;
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
            setContentView(R.layout.activity_adminlogin);
        }
        else
        {
            Toast toast = Toast.makeText(adminlogin.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }
        username=findViewById(R.id.adminusrnme);
        logina=findViewById(R.id.admnlgin1);
        password=findViewById(R.id.admnpsswrd);
        logina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equalsIgnoreCase("admin")&&password.getText().toString().equals("admin123"))
                    startActivity(new Intent(adminlogin.this,adminmenu.class));
                else {
                    Toast.makeText(adminlogin.this, "Invalid credential!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}