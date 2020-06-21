package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.applog.Adapter.Request_Data_Kolam;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Input_Data_Kolam extends AppCompatActivity {
    private EditText tanggalkolam, namakolam, namapetani, luasarea, jumlahtebarekor, jumlahtebarsampling, keterangan;
    private Button btnsimpankolam;

    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__data__kolam);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        sessions = new SharePreferences(Input_Data_Kolam.this.getApplicationContext());

        //Data Kolam
        tanggalkolam = findViewById(R.id.itanggalkolam);
        tanggalkolam.setText(date_n);
        namakolam = findViewById(R.id.inamakolam);
        namapetani = findViewById(R.id.inamapetani);
        luasarea = findViewById(R.id.iluasarea);
        jumlahtebarekor = findViewById(R.id.ijumlahtebarekor);
        jumlahtebarsampling =findViewById(R.id.ijumlahtebarsampling);
        keterangan = findViewById(R.id.keterangankolam);
        btnsimpankolam = findViewById(R.id.simpankolam);

        //Panggil Program
        simpan();
    }

    private void simpan() {
        btnsimpankolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kolam = namakolam.getText().toString();
                if(kolam.equals("")){
                    namakolam.setError("Masukkan nama kolam");
                    namakolam.requestFocus();
                    return;
                }
                String petani = namapetani.getText().toString();
                if(petani.equals("")){
                    namapetani.setError("Masukkan nama petani");
                    namapetani.requestFocus();
                    return;
                }
                String luas = luasarea.getText().toString();
                if(luas.equals("")){
                    luasarea.setError("Masukkan luas area kolam");
                    luasarea.requestFocus();
                    return;
                }
                String tebarekor = jumlahtebarekor.getText().toString();
                if(tebarekor.equals("")){
                    jumlahtebarekor.setError("Masukkan jumlah tebar");
                    jumlahtebarekor.requestFocus();
                    return;
                }
                String tebarsampling = jumlahtebarsampling.getText().toString();
                if(tebarsampling.equals("")){
                    jumlahtebarsampling.setError("Masukkan jumlah tebar sampling");
                    jumlahtebarsampling.requestFocus();
                    return;
                }
                String tanggaltebar = tanggalkolam.getText().toString();
                String keterangankolam = keterangan.getText().toString();

                //Perhitungan jumlah tebar rata-rata
                double pjumlahtebarekor = Double.parseDouble(jumlahtebarekor.getText().toString());
                double pjumlahtebarsampling = Double.parseDouble(jumlahtebarsampling.getText().toString());
                double hasilratarata = ((pjumlahtebarekor+pjumlahtebarsampling)/2);
                if (Double.isNaN(hasilratarata)){
                    hasilratarata = 0.0;
                }
                String hasil1 = String.valueOf(hasilratarata);

                //Perhitungan Kepadatan
                double parea = Double.parseDouble(luasarea.getText().toString());
                double hasilkepadatan = hasilratarata/parea;
                if (Double.isNaN(hasilkepadatan)){
                    hasilkepadatan = 0.0;
                }
                String hasil2 = String.valueOf(hasilkepadatan);

                datakolam(new Request_Data_Kolam(tanggaltebar, kolam, petani,
                        luas, tebarekor, tebarsampling, hasil1, hasil2, keterangankolam));

                progressDialog = new ProgressDialog(Input_Data_Kolam.this);
                progressDialog.setMessage("Menyimpan Data");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(true);
                progressDialog.show();

            }
        });
    }

    private void datakolam(Request_Data_Kolam request_data_kolam){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions = new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        if (user !=null){
            if (user.getDisplayName() !=null){
                String username = String.valueOf(user.getDisplayName());
                String kolamnama = namakolam.getText().toString();
                mDatabase.child("Data User").child(username).child("Database")
                        .child(kolamnama)
                        .setValue(request_data_kolam)
                        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Intent i = new Intent(Input_Data_Kolam.this, Dashboard.class);
                                startActivity(i);
                                finish();
                            }
                        });

            }
        }
    }
}
