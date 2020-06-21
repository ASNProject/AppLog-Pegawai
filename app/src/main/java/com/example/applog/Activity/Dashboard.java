package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.applog.Adapter.AdapterListKolam;
import com.example.applog.Adapter.Request_Data_Kolam;
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

public class Dashboard extends AppCompatActivity {
    private TextView username, btnlogout;
    private Button btntambahkolam;
    SharePreferences sessions;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    ArrayList<Request_Data_Kolam> list;
    private FirebaseUser user;
    AdapterListKolam adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sessions = new SharePreferences(Dashboard.this.getApplicationContext());
        username = findViewById(R.id.textinputnamauser);
        btnlogout = findViewById(R.id.logout);
        btntambahkolam = findViewById(R.id.tambahkolam);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();


        logout();
        datauser();
        tambahkolam();

        mRecycler = (RecyclerView) findViewById(R.id.recycleviewlistkolam);
        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        list = new ArrayList<Request_Data_Kolam>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child(user.getDisplayName()).child("Database");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Request_Data_Kolam request_data_kolam = dataSnapshot1.getValue(Request_Data_Kolam.class);
                    list.add(request_data_kolam);
                }
                adapter = new AdapterListKolam(Dashboard.this,list);
                mRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void tambahkolam() {
        btntambahkolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, Input_Data_Kolam.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void datauser() {
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user !=null){
            if (user.getDisplayName() !=null){
                username.setText(user.getDisplayName());
            }
        }
    }

    private void logout() {
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessions.setEmail("");
                sessions.setPassword("");

                Intent i = new Intent(Dashboard.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }

}
