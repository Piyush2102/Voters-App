package com.example.project2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    ArrayList<recyclerviewadapter> cnts=new ArrayList<>();
    Context context;
    String idss;

    adapter(Context context,ArrayList<recyclerviewadapter> cnts)
    {
        this.context=context;
        this.cnts=cnts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemview=LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int Position)
    {
      holder.image.setImageResource(cnts.get(Position).imG);
        holder.id.setText(cnts.get(Position).id);
        holder.name.setText(cnts.get(Position).name);
        idss=cnts.get(Position).id;
    }
    @Override
    public int getItemCount() {
        return cnts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,id;
        ImageView image;
        Button like;
        public ViewHolder(View itemview) {
            super(itemview);
            name=itemview.findViewById(R.id.namerow);
            id=itemview.findViewById(R.id.idrow);
            image=itemview.findViewById(R.id.imagerow);
            like=itemview.findViewById(R.id.like);
            /*like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=firebaseDatabase.getReference("contestant").child(idss);
                  //databaseReference.child(idss).child("votes").setValue(String.valueOf(Integer.parseInt(vote)+1));
                     databaseReference.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Log.d("show",snapshot.child("name").getValue(String.class)+idss);
                          // int votes=snapshot.child(idss).child("votes").getValue(Integer.class);
                          //databaseReference.child(String.valueOf(id.toString())).child("votes").setValue(votes+1);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            Log.d("show",snapshot.getValue(String.class)+idss);
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
                    DatabaseReference dbc=firebaseDatabase.getReference("datauser");
                    vote v=new vote();
                    Log.d("vid",v.getId()+"ss");
                   // dbc.child(v.id).child("voted").setValue(true);
                }
            });*/
        }
    }
}
