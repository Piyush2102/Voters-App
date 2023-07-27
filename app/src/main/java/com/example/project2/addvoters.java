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

import com.example.project2.R.id;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addvoters extends AppCompatActivity {
TextView name,phone,id;
Button add;
FirebaseDatabase firebasedatabase;
DatabaseReference reference;
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
            setContentView(R.layout.activity_addvoters);
        }
        else
        {
            Toast toast = Toast.makeText(addvoters.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }

        name = findViewById(R.id.entername);
        phone = findViewById(R.id.enterphone);
        id = findViewById(R.id.enterid);
        add=findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String names, phones, ids;
                names = name.getText().toString().trim();
                phones = phone.getText().toString().trim();
                ids = id.getText().toString().trim();
                if(names.isEmpty())
                {
                    Toast.makeText(addvoters.this,"Enter name first!!",Toast.LENGTH_SHORT).show();
                }
                else if(phones.length()!=10)
                    Toast.makeText(addvoters.this,"Invalid Phone number!!",Toast.LENGTH_SHORT).show();
               else if(ids.isEmpty())
                    Toast.makeText(addvoters.this,"Enter id first!!",Toast.LENGTH_SHORT).show();
                else {
                    firebasedatabase = FirebaseDatabase.getInstance();
                    firebasedatabase.getReference("voter name").child(ids).setValue(names);
                    reference = firebasedatabase.getReference("datauser");
                    storingdata store = new storingdata(names, phones, ids,false);
                    reference.child(ids).setValue(store);
                    Toast.makeText(addvoters.this,"Added successfully!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addvoters.this,adminmenu.class));
                }
            }
        });

    }
}