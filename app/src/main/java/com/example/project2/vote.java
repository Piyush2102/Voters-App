package com.example.project2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class vote extends AppCompatActivity {
    TextView name, ids, phone;
    RecyclerView rv;
    String id;
    Button like;
    ArrayList<recyclerviewadapter> cnts = new ArrayList<>();



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
            setContentView(R.layout.activity_vote);
        } else {
            Toast toast = Toast.makeText(vote.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = findViewById(R.id.usrname);
        ids = findViewById(R.id.usrid);
        phone = findViewById(R.id.usrphone);
        rv = findViewById(R.id.rv);
        ids.setText(id);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");
        Query check = databaseReference.orderByChild("id").equalTo(id);
        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("msg", id+" "+snapshot.child(id).toString());
                name.setText(snapshot.child(id).child("name").getValue(String.class));
                phone.setText(snapshot.child(id).child("phone").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("not shown", error.toString());
                Toast.makeText(vote.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        adapter adapter = new adapter(this, cnts);
        Log.d("cnts", cnts.toString());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
        DatabaseReference dbc = FirebaseDatabase.getInstance().getReference("contestant");
        dbc.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Log.d("mus", snapshot.child("name").getValue(String.class));
                cnts.add(new recyclerviewadapter("a", "b", R.drawable.common_google_signin_btn_icon_dark));
                cnts.add(new recyclerviewadapter(snapshot.child("name").getValue(String.class), snapshot.child("id").getValue(String.class), R.drawable.common_google_signin_btn_icon_dark_normal_background));

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                cnts.add(new recyclerviewadapter(snapshot.child("name").getValue(String.class), snapshot.child("id").getValue(String.class), R.drawable.common_google_signin_btn_icon_dark_normal_background));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public String getId() {
        return id;
    }
}