package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applog.Adapter.Request_Data_Kolam;
import com.example.applog.Adapter.Request_Register;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MenuInput extends AppCompatActivity {
    private TextView namakolams, lokasi, tanggaltebar, usia, jumlahtebar, jumlahtebarsampling, luasarea
            ,kepadatan, keterangan;
    private CardView kualitasair, pakan, panen, sampling, perlakuan;
    private DatabaseReference mDatabase, mDatabase1;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_input);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        sessions = new SharePreferences(MenuInput.this.getApplicationContext());
        FirebaseUser user = mAuth.getCurrentUser();

        namakolams = findViewById(R.id.tnamakolam);
        namakolams.setText(sessions.getData());
        lokasi = findViewById(R.id.olocation);
        tanggaltebar = findViewById(R.id.otanggaltebar);
        usia = findViewById(R.id.ousia);
        jumlahtebar = findViewById(R.id.ojumlahtebarekor);
        jumlahtebarsampling = findViewById(R.id.ojumlahtebarsampling);
        luasarea = findViewById(R.id.oluasarea);
        kepadatan = findViewById(R.id.okepadatan);
        kualitasair = findViewById(R.id.cardview4);
        pakan = findViewById(R.id.cardview5);
        sampling = findViewById(R.id.cardview6);
        panen = findViewById(R.id.cardview7);
        perlakuan = findViewById(R.id.cardview8);

        tampilmenudata();
        lokasi();
        inputair();
        inputpakan();
        inputsampling();
        inputpanen();
        inputperlakuan();

    }
    private void tampilmenudata(){
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Request_Data_Kolam request_data_kolam= dataSnapshot.child(sessions.getData()).getValue(Request_Data_Kolam.class);
                        tanggaltebar.setText(request_data_kolam.getTanggaltebar());
                        jumlahtebar.setText(request_data_kolam.getJumlahtebarekor());
                        jumlahtebarsampling.setText(request_data_kolam.getJumlahtebarsampling());
                        luasarea.setText(request_data_kolam.getLuasarea());
                        kepadatan.setText(request_data_kolam.getKepadatankolam());
                        String tanggal = tanggaltebar.getText().toString();
                        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            Date tglawal = (Date) date.parse(date_n);
                            Date tglakhir = (Date) date.parse(tanggal);

                            long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                            usia.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
    private void lokasi(){
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase1 = FirebaseDatabase.getInstance().getReference();
        mDatabase1.child("Data User").child(user.getDisplayName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Request_Register request_register = dataSnapshot.getValue(Request_Register.class);
                lokasi.setText(request_register.getAlamat());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MenuInput.this, Dashboard.class);
        startActivity(i);
        finish();
    }

    private void inputair(){
        kualitasair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInput.this, DataAir.class);
                startActivity(i);

            }
        });
    }
    private void inputpakan(){
        pakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessions.setUsia(usia.getText().toString());
                Intent i = new Intent(MenuInput.this, DataPakan.class);
                startActivity(i);
            }
        });
    }
    private void inputsampling(){
        sampling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessions.setUsia(usia.getText().toString());
                Intent i = new Intent(MenuInput.this, DataSampling.class);
                startActivity(i);
            }
        });
    }
    private void inputpanen(){
        panen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInput.this, DataPanen.class);
                startActivity(i);
            }
        });
    }
    private void inputperlakuan(){
        perlakuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuInput.this, DataPerlakuan.class);
                startActivity(i);
            }
        });
    }
}
