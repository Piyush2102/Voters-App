package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addcontestant extends AppCompatActivity {
 TextView name,id,phone;
 Button add;
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
            setContentView(R.layout.activity_addcontestant);
        } else {
            Toast toast = Toast.makeText(addcontestant.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }
        name = findViewById(R.id.entrname);
        phone = findViewById(R.id.entrphone);
        id = findViewById(R.id.entrid);
        add = findViewById(R.id.addcntstant);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names, phones, ids;
                names = name.getText().toString().trim();
                phones = phone.getText().toString().trim();
                ids = id.getText().toString().trim();
                if (names.isEmpty()) {
                    Toast.makeText(addcontestant.this, "Enter name first!!", Toast.LENGTH_SHORT).show();
                } else if (phones.length() != 10)
                    Toast.makeText(addcontestant.this, "Invalid Phone number!!", Toast.LENGTH_SHORT).show();
                else if (ids.isEmpty())
                    Toast.makeText(addcontestant.this, "Enter id first!!", Toast.LENGTH_SHORT).show();
                else {
                    FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
                    DatabaseReference db = firebasedatabase.getReference("datauser");
                    DatabaseReference dbc = firebasedatabase.getReference("contestant");
                    firebasedatabase.getReference("contestant name").child(ids).setValue(names);
                    storingdata store = new storingdata(names, phones, ids, false);
                    db.child(ids).setValue(store);
                    dbc.child(ids).setValue(store);
                    Toast.makeText(addcontestant.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addcontestant.this, adminmenu.class));
                }
            }
        });
      }
    }
