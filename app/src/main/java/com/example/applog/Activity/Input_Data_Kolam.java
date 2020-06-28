package com.example.applog.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applog.Adapter.RequestDataAir;
import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.Adapter.RequestDataPanen;
import com.example.applog.Adapter.RequestDataPerlakuan;
import com.example.applog.Adapter.RequestDataSampling;
import com.example.applog.Adapter.RequestHasilPanen;
import com.example.applog.Adapter.RequestUpdateAir;
import com.example.applog.Adapter.RequestUpdatePakan;
import com.example.applog.Adapter.RequestUpdatePanen;
import com.example.applog.Adapter.RequestUpdatePerlakuan;
import com.example.applog.Adapter.RequestUpdateSampling;
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

                //AIR
                String tanggalair = "";
                String tinggiair = "0.0";
                String dopagi = "0.0";
                String domalam = "0.0";
                String phpagi = "0.0";
                String phmalam = "0.0";
                String kecerahan = "";
                String alkalinitas = "0.0";
                String suhu = "0.0";
                String cas = "0.0";
                String no2 = "0.0";
                String no3 = "0.0";
                String nh3 = "0.0";
                String warna = "";

                //PAKAN
                String tanggalpakan = "";
                String kodepakan = "";
                String jam6 = "0.0";
                String jam10 = "0.0";
                String jam14 = "0.0";
                String jam18 = "0.0";
                String jam22 ="0.0";
                String keteranganpakan = "";
                String jumlahharian = "0.0";
                String jumlahtotal ="0.0";
                String usia = "0";

                //SAMPLING
                String tanggalsampling = "";
                String jumlahtebarsamplings = "0.0";
                String mbw = "0.0";
                String pakanseharisampling = "0.0";
                String totalpakansampling = "0.0";
                String fr ="0.0";
                String populasi = "0.0";
                String adgmingguan = "0.0";
                String biomass = "0.0";
                String sp = "0.0";
                String konsumsifeed = "0.0";
                String fcr = "0.0";
                String usias = "0";

                //PERLAKUAN
                String perlakuan = "";
                String tanggalperlakuan = "";

                //HASILPANEN
                String tonha = "0.0";
                String totalpopulasi = "0.0";
                String panentotal = "0.0";
                String totalsr = "0.0";
                String totalpakan = "0.0";
                String fcrtotal = "0.0";

                //PANEN
                String tanggalpanen = "";
                String doc = "0.0";
                String tonase = "0.0";
                String abw = "0.0";
                String size = "0.0";
                String populasipanen = "0.0";

                datakolam(new Request_Data_Kolam(tanggaltebar, kolam, petani,
                        luas, tebarekor, tebarsampling, hasil1, hasil2, keterangankolam));
                dataair(new RequestDataAir(tanggalair, tinggiair, dopagi, domalam, phpagi, phmalam,
                        kecerahan, alkalinitas, suhu, cas, no2, no3, nh3, warna));
                updatedataair(new RequestUpdateAir(tanggalair, tinggiair, dopagi, domalam, phpagi, phmalam,
                        kecerahan, alkalinitas, suhu, cas, no2, no3, nh3, warna));
                datapakan(new RequestDataPakan(tanggalpakan, kodepakan, jam6, jam10, jam14, jam18, jam22, keteranganpakan, jumlahharian, jumlahtotal, usia));
                updatedatapakan(new RequestUpdatePakan(tanggalpakan, kodepakan, jam6, jam10, jam14, jam18, jam22, keteranganpakan, jumlahharian, jumlahtotal, usia));
                datasampling(new RequestDataSampling(tanggalsampling, jumlahtebarsamplings, mbw, pakanseharisampling, totalpakansampling, fr, populasi, adgmingguan,
                        biomass, sp, konsumsifeed, fcr, usias));
                updatedatasampling(new RequestUpdateSampling(tanggalsampling, jumlahtebarsamplings, mbw, pakanseharisampling, totalpakansampling, fr, populasi, adgmingguan,
                        biomass, sp, konsumsifeed, fcr));
                dataperlakuan(new RequestDataPerlakuan(perlakuan, tanggalperlakuan));
                updatedataperlakuan(new RequestUpdatePerlakuan(perlakuan, tanggalperlakuan));
                datahasilpanen(new RequestHasilPanen(tonha, totalpopulasi, panentotal, totalsr, totalpakan, fcrtotal));
                datapanen(new RequestDataPanen(tanggalpanen, doc, tonase, abw, size, populasipanen, tonha, totalpopulasi, panentotal, totalsr, totalpakan, fcrtotal));
                updatedatapanen(new RequestUpdatePanen(tanggalpanen, doc, tonase, abw, size, populasipanen));

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
                final String username = String.valueOf(user.getDisplayName());
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
   private void dataair(RequestDataAir requestDataAir){
       final FirebaseUser user = mAuth.getCurrentUser();
       final String kolamnama = namakolam.getText().toString();
       final String usernames = user.getDisplayName();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        if (user !=null){
            if(user.getDisplayName() !=null){
                mDatabase.child("Data User").child(usernames)
                        .child("Database").child(kolamnama)
                        .child("Air").push().setValue(requestDataAir)
                        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                            }
                        });
            }
        }
   }
    private void updatedataair(RequestUpdateAir requestUpdateAir){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdateAir").setValue(requestUpdateAir)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datapakan(RequestDataPakan requestDataPakan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Pakan").push().setValue(requestDataPakan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatapakan(RequestUpdatePakan requestUpdatePakan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePakan").setValue(requestUpdatePakan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datasampling(RequestDataSampling requestDataSampling){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Sampling").push().setValue(requestDataSampling)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatasampling(RequestUpdateSampling requestUpdateSampling){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdateSampling").setValue(requestUpdateSampling)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datapanen(RequestDataPanen requestDataPanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Panen").push().setValue(requestDataPanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatapanen(RequestUpdatePanen requestUpdatePanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePanen").setValue(requestUpdatePanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataperlakuan(RequestDataPerlakuan requestDataPerlakuan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").push().setValue(requestDataPerlakuan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedataperlakuan(RequestUpdatePerlakuan requestUpdatePerlakuan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").setValue(requestUpdatePerlakuan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datahasilpanen(RequestHasilPanen requestHasilPanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("HasilPanen").setValue(requestHasilPanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
}
