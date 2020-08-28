package com.example.applog.Activity;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditKolam extends AppCompatActivity {
    private EditText tanggalkolam, namakolam, namapetani, luasarea, jumlahtebar, jumlahaktual, keterangan;
    private Button btnsimpankolam;

    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kolam);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(EditKolam.this.getApplicationContext());

        tanggalkolam = findViewById(R.id.etanggalkolam);
        namakolam = findViewById(R.id.enamakolam);
        namapetani = findViewById(R.id.enamapetani);
        luasarea = findViewById(R.id.eluasarea);
        jumlahtebar = findViewById(R.id.ejumlahtebarekor);
        jumlahaktual = findViewById(R.id.ejumlahtebarsampling);
        keterangan = findViewById(R.id.eketerangankolam);
        btnsimpankolam = findViewById(R.id.esimpankolam);

        loaddata();
        Simpandata();
    }
    private void loaddata(){
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Request_Data_Kolam request_data_kolam= dataSnapshot.child(sessions.getData()).getValue(Request_Data_Kolam.class);
                tanggalkolam.setText(request_data_kolam.getTanggaltebar());
                namakolam.setText(request_data_kolam.getNamakolam());
                namapetani.setText(request_data_kolam.getNamapetani());
                luasarea.setText(request_data_kolam.getLuasarea());
                jumlahtebar.setText(request_data_kolam.getJumlahtebarekor());
                jumlahaktual.setText(request_data_kolam.getJumlahtebarsampling());
                keterangan.setText(request_data_kolam.getKeterangankolam());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Simpandata(){
        btnsimpankolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = tanggalkolam.getText().toString();
                String kolam = namakolam.getText().toString();
                String petani = namapetani.getText().toString();
                String area = luasarea.getText().toString();
                String tebar = jumlahtebar.getText().toString();
                String aktual = jumlahaktual.getText().toString();
                String ket = keterangan.getText().toString();

                double ptebar = Double.parseDouble(tebar);
                double paktual = Double.parseDouble(aktual);
                double parea = Double.parseDouble(area);
                double pjumlahbutro = ptebar/parea;
                if(Double.isNaN(pjumlahbutro)){
                    pjumlahbutro = 0.0;
                }
                String hasilbruto = String.valueOf(pjumlahbutro);
                double pjumlahaktual = paktual/parea;
                if(Double.isNaN(pjumlahaktual)){
                    pjumlahaktual = 0.0;
                }
                String hasilaktual = String.valueOf(pjumlahaktual);
                double rata = (ptebar+paktual)/2;
                if (Double.isNaN(rata)){
                    rata = 0.0;
                }
                String hasil = String.valueOf(rata);

                datakolam(new Request_Data_Kolam(tanggal, kolam, petani, area, tebar, aktual, hasilbruto, hasil, ket, hasilaktual));
            }
        });

    }
    private void datakolam(Request_Data_Kolam request_data_kolam){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions = new SharePreferences(EditKolam.this.getApplicationContext());
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
                                Intent i = new Intent(EditKolam.this, MenuInput.class);
                                startActivity(i);
                                finish();
                            }
                        });

            }
        }
    }
}
