package com.example.project2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class seelist extends AppCompatActivity {
 ListView lvc,lvv;
 ArrayList<String> arv=new ArrayList<>();
 ArrayList<String> arc=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seelist);
        lvv=findViewById(R.id.lvv);
        lvc=findViewById(R.id.lvc);
        ArrayAdapter<String> arav=new ArrayAdapter<>(seelist.this, android.R.layout.simple_list_item_1,arv);
        lvv.setAdapter(arav);
        ArrayAdapter<String> arac=new ArrayAdapter<>(seelist.this, android.R.layout.simple_list_item_1,arc);
        lvc.setAdapter(arac);
        DatabaseReference dbv= FirebaseDatabase.getInstance().getReference("voter name");
        DatabaseReference dbc=FirebaseDatabase.getInstance().getReference("contestant name");
        dbv.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getValue(String.class);
                arv.add(value);
                arav.notifyDataSetChanged();
                Log.d("TAG","added:"+snapshot.getValue(String.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getValue(String.class);
                arv.add(value);
                arav.notifyDataSetChanged();
                Log.d("TAG","changed:"+snapshot.getValue(String.class));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d("TAG","removed:"+snapshot.getValue(String.class));

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("TAG","moved:"+snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        dbc.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getValue(String.class);
                arc.add(value);
                arac.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value=snapshot.getValue(String.class);
                arc.add(value);
                arac.notifyDataSetChanged();
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
}