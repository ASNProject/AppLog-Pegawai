package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applog.Adapter.AdapterGaleri;
import com.example.applog.Adapter.Upload;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Galeri extends AppCompatActivity {
    private TextView datas;
    SharePreferences sessions;
    private RecyclerView recyclerView;
    private AdapterGaleri adapterGaleri;
    private DatabaseReference mDatabase;
    private List<Upload> uploads;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);
        sessions = new SharePreferences(Galeri.this.getApplicationContext());
        datas = findViewById(R.id.datas);
        datas.setText(sessions.getData());
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mManager = new LinearLayoutManager(this);
        mManager.setStackFromEnd(true);
        mManager.setReverseLayout(true);
        recyclerView = findViewById(R.id.recycleviewgaleri);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mManager);


        uploads = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("uploads");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploads.add(upload);
                }
                adapterGaleri = new AdapterGaleri(Galeri.this, uploads);
                recyclerView.setAdapter(adapterGaleri);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Galeri.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}