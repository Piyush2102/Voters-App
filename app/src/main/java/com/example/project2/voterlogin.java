package com.example.project2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class voterlogin extends AppCompatActivity {
    TextView idnum, otptxt, otpn, resend;
    Button ok, loginf;
    String Otp;

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
            setContentView(R.layout.activity_voterlogin);
        }
        else
        {
            Toast toast = Toast.makeText(voterlogin.this, "No Internet Connection", Toast.LENGTH_LONG);
            toast.show();
        }
        idnum = findViewById(R.id.idnum);
        otptxt = findViewById(R.id.otptxt);
        otpn = findViewById(R.id.otpn);
        resend = findViewById(R.id.resend);
        loginf = findViewById(R.id.loginf);
        ok = findViewById(R.id.ok);
        otptxt.setVisibility(View.GONE);
        otpn.setVisibility(View.GONE);
        resend.setVisibility(View.GONE);
        loginf.setVisibility(View.GONE);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id=idnum.getText().toString();
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                Log.d("msg",firebaseDatabase.toString());
                DatabaseReference databaseReference=firebaseDatabase.getReference("datauser");
                Log.d("msg",databaseReference.toString());
                Query check=databaseReference.orderByChild("id").equalTo(id);
                Log.d("msg",check.toString());
                //Intent intent = new Intent(voterlogin.this, vote.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //intent.putExtra("id",idnum.getText().toString());
              //  startActivity(intent);
               ///*
                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()) {
                            Toast.makeText(voterlogin.this, "Your id is not registered!!", Toast.LENGTH_SHORT).show();}
                            //else //if(snapshot.child("voted"))
                            //{
                           // Toast.makeText(voterlogin.this, "You have already voted!!", Toast.LENGTH_SHORT).show();}
                            else {
                           final String phone ="+91"+snapshot.child(id).child("phone").getValue(String.class);
                            Toast.makeText(voterlogin.this,phone,Toast.LENGTH_SHORT).show();
                           ok.setVisibility(View.GONE);
                            otptxt.setVisibility(View.VISIBLE);
                            otpn.setVisibility(View.VISIBLE);
                            resend.setVisibility(View.VISIBLE);
                            loginf.setVisibility(View.VISIBLE);
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 60, TimeUnit.SECONDS, voterlogin.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                 Log.d("ok","ok");
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(voterlogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Otp=s;
                                Toast.makeText(voterlogin.this,"sent",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(voterlogin.this,"error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        loginf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(Otp,otpn.getText().toString());
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(voterlogin.this, vote.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("id",idnum.getText().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(voterlogin.this, "wrong OTP!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //*/
                }
        });
    }
}