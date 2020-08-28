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

import com.example.applog.Adapter.RequestAbusekam;
import com.example.applog.Adapter.RequestAlkalinmag;
import com.example.applog.Adapter.RequestAriakekuro;
import com.example.applog.Adapter.RequestArtemia;
import com.example.applog.Adapter.RequestAtbak;
import com.example.applog.Adapter.RequestAzomit;
import com.example.applog.Adapter.RequestDataAir;
import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.Adapter.RequestDataPanen;
import com.example.applog.Adapter.RequestDataPerlakuan;
import com.example.applog.Adapter.RequestDataSampling;
import com.example.applog.Adapter.RequestDaunkates;
import com.example.applog.Adapter.RequestDolomit;
import com.example.applog.Adapter.RequestHasilPanen;
import com.example.applog.Adapter.RequestJamu;
import com.example.applog.Adapter.RequestKCL;
import com.example.applog.Adapter.RequestKaptan;
import com.example.applog.Adapter.RequestKapuraktif;
import com.example.applog.Adapter.RequestMolase;
import com.example.applog.Adapter.RequestMonodon;
import com.example.applog.Adapter.RequestProbion;
import com.example.applog.Adapter.RequestRO2;
import com.example.applog.Adapter.RequestRagi;
import com.example.applog.Adapter.RequestUpdateAbusekam;
import com.example.applog.Adapter.RequestUpdateAir;
import com.example.applog.Adapter.RequestUpdateAlkalinmag;
import com.example.applog.Adapter.RequestUpdateAriakekuro;
import com.example.applog.Adapter.RequestUpdateArtemia;
import com.example.applog.Adapter.RequestUpdateAtbak;
import com.example.applog.Adapter.RequestUpdateAzomit;
import com.example.applog.Adapter.RequestUpdateDaunkates;
import com.example.applog.Adapter.RequestUpdateDolomit;
import com.example.applog.Adapter.RequestUpdateJamu;
import com.example.applog.Adapter.RequestUpdateKCL;
import com.example.applog.Adapter.RequestUpdateKaptan;
import com.example.applog.Adapter.RequestUpdateKapuraktif;
import com.example.applog.Adapter.RequestUpdateMolase;
import com.example.applog.Adapter.RequestUpdatePakan;
import com.example.applog.Adapter.RequestUpdatePanen;
import com.example.applog.Adapter.RequestUpdatePerlakuan;
import com.example.applog.Adapter.RequestUpdateProbion;
import com.example.applog.Adapter.RequestUpdateRO2;
import com.example.applog.Adapter.RequestUpdateRagi;
import com.example.applog.Adapter.RequestUpdateSampling;
import com.example.applog.Adapter.RequestUpdateVirkon;
import com.example.applog.Adapter.RequestUpdatemonodon;
import com.example.applog.Adapter.RequestVirkon;
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
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
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
                double bruto = Double.parseDouble(jumlahtebarekor.getText().toString());
                double parea = Double.parseDouble(luasarea.getText().toString());
                double hasilkepadatan = bruto/parea;
                if (Double.isNaN(hasilkepadatan)){
                    hasilkepadatan = 0.0;
                }
                String hasil2 = String.valueOf(hasilkepadatan);
                //Perhitungan Kepadatanaktual
                double aktual = Double.parseDouble(jumlahtebarsampling.getText().toString());
                double hasilkepadatanaktual = aktual/parea;
                if (Double.isNaN(hasilkepadatanaktual)){
                    hasilkepadatanaktual = 0.0;
                }
                String hasil3 = String.valueOf(hasilkepadatanaktual);

                //AIR
                String tanggalair = "00-00-0000";
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
                String tanggalpakan = "00-00-0000";
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
                String tanggalsampling = "00-00-0000";
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
                String tanggalperlakuan = "00-00-0000";
                String jumlahalkalinmag = " ";
                String talkalinmag ="";
                String j1 = " ";
                String t1 ="";
                String j2 = " ";
                String t2 ="";
                String j3 = " ";
                String t3 ="";
                String j4 = " ";
                String t4 ="";
                String j5 = " ";
                String t5 ="";
                String j6 = " ";
                String t6 ="";
                String j7 = " ";
                String t7 ="";
                String j8 = " ";
                String t8 ="";
                String j9 = " ";
                String t9 ="";
                String j10 = " ";
                String t10 ="";
                String j11 = " ";
                String t11 ="";
                String j12 = " ";
                String t12 ="";
                String j13 = " ";
                String t13 ="";
                String j14 = " ";
                String t14 ="";
                String j15 = " ";
                String t15 ="";
                String j16 = " ";
                String t16 ="";
                String j17 = " ";
                String t17 ="";
                String p1 = "00-00-0000";
                String p2 = "00-00-0000";
                String p3 = "00-00-0000";
                String p4 = "00-00-0000";
                String p5 = "00-00-0000";
                String p6 = "00-00-0000";
                String p7 = "00-00-0000";
                String p8 = "00-00-0000";
                String p9 = "00-00-0000";
                String p10 = "00-00-0000";
                String p11 = "00-00-0000";
                String p12 = "00-00-0000";
                String p13 = "00-00-0000";
                String p14 = "00-00-0000";
                String p15 = "00-00-0000";
                String p16 = "00-00-0000";
                String p17 = "00-00-0000";

                //HASILPANEN
                String tonha = "0.0";
                String totalpopulasi = "0.0";
                String panentotal = "0.0";
                String totalsr = "0.0";
                String totalpakan = "0.0";
                String fcrtotal = "0.0";

                //PANEN
                String tanggalpanen = "00-00-0000";
                String doc = "0.0";
                String tonase = "0.0";
                String abw = "0.0";
                String size = "0.0";
                String populasipanen = "0.0";

                datakolam(new Request_Data_Kolam(tanggaltebar, kolam, petani,
                        luas, tebarekor, tebarsampling, hasil2, hasil1, keterangankolam, hasil3));
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
                dataalkalinmag(new RequestAlkalinmag(tanggalperlakuan, jumlahalkalinmag, talkalinmag));
                updatealkalinmag(new RequestUpdateAlkalinmag(tanggalperlakuan, jumlahalkalinmag, talkalinmag));
                datakcl(new RequestKCL(p1, j1, t1));
                updateKCL(new RequestUpdateKCL(p1, j1, t1));
                dataazomit(new RequestAzomit(p2, j2, t2));
                updatezomit(new RequestUpdateAzomit(p2, j2, t2));
                dataalmonodon(new RequestMonodon(p3, j3, t3));
                updatemonodon(new RequestUpdatemonodon(p3, j3, t3));
                dataatbak(new RequestAtbak(p4, j4, t4));
                updatetatbak(new RequestUpdateAtbak(p4, j4, t4));
                dataragi(new RequestRagi(p5, j5, t5));
                updateragi(new RequestUpdateRagi(p5, j5, t5));
                datadaunkates(new RequestDaunkates(p6, j6, t6));
                updatedaunkates(new RequestUpdateDaunkates(p6, j6, t6));
                datajamu(new RequestJamu(p7, j7, t7));
                updatejamu(new RequestUpdateJamu(p7, j7, t7));
                dataro2(new RequestRO2(p8, j8, t8));
                updatero2(new RequestUpdateRO2(p8, j8, t8));
                datavirkon(new RequestVirkon(p9, j9, t9));
                updatevirkon(new RequestUpdateVirkon(p9, j9, t9));
                dataariakekuro(new RequestAriakekuro(p10, j10, t10));
                updateariakekuro(new RequestUpdateAriakekuro(p10, j10, t10));
                datakapuraktif(new RequestKapuraktif(p11, j11, t11));
                updatekapuraktif(new RequestUpdateKapuraktif(p11, j11, t11));
                datadolomit(new RequestDolomit(p12, j12, t12));
                updatedolomit(new RequestUpdateDolomit(p12, j12, t12));
                datakaptan(new RequestKaptan(p13, j13, t13));
                updatekaptan(new RequestUpdateKaptan(p13, j13, t13));
                dataabusekam(new RequestAbusekam(p14, j14, t14));
                updateabusekam(new RequestUpdateAbusekam(p14, j14, t14));
                dataartemia(new RequestArtemia(p15, j15, t15));
                updateartemia(new RequestUpdateArtemia(p15, j15, t15));
                datamolase(new RequestMolase(p16, j16, t16));
                updatemolase(new RequestUpdateMolase(p16, j16, t16));
                dataprobion(new RequestProbion(p17, j17, t17));
                updateprobion(new RequestUpdateProbion(p17, j17, t17));
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
    private void updatealkalinmag(RequestUpdateAlkalinmag requestUpdateAlkalinmag){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Alkalinmag").setValue(requestUpdateAlkalinmag)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataalkalinmag(RequestAlkalinmag requestAlkalinmag){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Alkalinmag").push().setValue(requestAlkalinmag)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datakcl(RequestKCL requestKCL){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("KCL").push().setValue(requestKCL)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateKCL(RequestUpdateKCL requestUpdateKCL){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("KCL").setValue(requestUpdateKCL)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataazomit(RequestAzomit requestAzomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Azomit").push().setValue(requestAzomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatezomit(RequestUpdateAzomit requestUpdateAzomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Azomit").setValue(requestUpdateAzomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataalmonodon(RequestMonodon requestMonodon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Monodon").push().setValue(requestMonodon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatemonodon(RequestUpdatemonodon requestUpdatemonodon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Monodon").setValue(requestUpdatemonodon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataatbak(RequestAtbak requestAtbak){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Atbak").push().setValue(requestAtbak)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatetatbak(RequestUpdateAtbak requestUpdateAtbak){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Atbak").setValue(requestUpdateAtbak)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataragi(RequestRagi requestRagi){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Ragi").push().setValue(requestRagi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateragi(RequestUpdateRagi requestUpdateRagi){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Ragi").setValue(requestUpdateRagi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datadaunkates(RequestDaunkates requestDaunkates){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Daunkates").push().setValue(requestDaunkates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedaunkates(RequestUpdateDaunkates requestUpdateDaunkates){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Daunkates").setValue(requestUpdateDaunkates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datajamu(RequestJamu requestJamu){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Jamu").push().setValue(requestJamu)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatejamu(RequestUpdateJamu requestUpdateJamu){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Jamu").setValue(requestUpdateJamu)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataro2(RequestRO2 requestRO2){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString()).child("Perlakuan").child("RO2").push().setValue(requestRO2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatero2(RequestUpdateRO2 requestUpdateRO2){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("RO2").setValue(requestUpdateRO2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datavirkon(RequestVirkon requestVirkon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Virkon").push().setValue(requestVirkon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatevirkon(RequestUpdateVirkon requestUpdateVirkon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Virkon").setValue(requestUpdateVirkon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataariakekuro(RequestAriakekuro requestAriakekuro){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Ariakekuro").push().setValue(requestAriakekuro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateariakekuro(RequestUpdateAriakekuro requestUpdateAriakekuro){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Ariakekuro").setValue(requestUpdateAriakekuro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datakapuraktif(RequestKapuraktif requestKapuraktif){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Kapuraktif").push().setValue(requestKapuraktif)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatekapuraktif(RequestUpdateKapuraktif requestUpdateKapuraktif){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Kapuraktif").setValue(requestUpdateKapuraktif)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datadolomit(RequestDolomit requestDolomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Dolomit").push().setValue(requestDolomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedolomit(RequestUpdateDolomit requestUpdateDolomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Dolomit").setValue(requestUpdateDolomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datakaptan(RequestKaptan requestKaptan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Kaptan").push().setValue(requestKaptan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatekaptan(RequestUpdateKaptan requestUpdateKaptan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Kaptan").setValue(requestUpdateKaptan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataabusekam(RequestAbusekam requestAbusekam){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Abusekam").push().setValue(requestAbusekam)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateabusekam(RequestUpdateAbusekam requestUpdateAbusekam){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Abusekam").setValue(requestUpdateAbusekam)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datamolase(RequestMolase requestMolase){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Molase").push().setValue(requestMolase)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatemolase(RequestUpdateMolase requestUpdateMolase){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Molase").setValue(requestUpdateMolase)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataprobion(RequestProbion requestProbion){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Probion").push().setValue(requestProbion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateprobion(RequestUpdateProbion requestUpdateProbion){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Probion").setValue(requestUpdateProbion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataartemia(RequestArtemia requestArtemia){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("Perlakuan").child("Artemia").push().setValue(requestArtemia)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateartemia(RequestUpdateArtemia requestUpdateArtemia){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(Input_Data_Kolam.this.getApplicationContext());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(namakolam.getText().toString())
                .child("UpdatePerlakuan").child("Artemia").setValue(requestUpdateArtemia)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
}
