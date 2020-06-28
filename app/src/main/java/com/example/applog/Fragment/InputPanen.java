package com.example.applog.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.Adapter.RequestDataPanen;
import com.example.applog.Adapter.RequestHasilPanen;
import com.example.applog.Adapter.RequestUpdatePakan;
import com.example.applog.Adapter.RequestUpdatePanen;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputPanen extends Fragment {
    private EditText htanggal, hdoc, htonase, habw;
    private Button simpan;
    private TextView hpopulasipanen, hsize, htotalpanen, htotalpopulasi,
            htotalsr, htotalfcrpanen, htotalton, ambiltonase, ambilpopulasi,
            ambilluastambak, ambilpakan, pakanpanen;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        final FirebaseUser user = mAuth.getCurrentUser();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_panen, container, false);
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        htanggal = (EditText)view.findViewById(R.id.itanggalpanen);
        htanggal.setText(date_n);
        hdoc = (EditText)view.findViewById(R.id.idoc);
        htonase = (EditText)view.findViewById(R.id.itonanse);
        habw = (EditText)view.findViewById(R.id.iabw);
        hpopulasipanen = (TextView) view.findViewById(R.id.ipopulasipanen);
        hsize = (TextView) view.findViewById(R.id.isize);
        htotalpanen = (TextView) view.findViewById(R.id.itotalpanen);
        htotalpopulasi = (TextView) view.findViewById(R.id.itotalpopulasi);
        htotalsr = (TextView) view.findViewById(R.id.itotalsr);
        htotalfcrpanen = (TextView) view.findViewById(R.id.itotalfcrpanen);
        htotalton = (TextView) view.findViewById(R.id.itotalton);
        ambiltonase= (TextView) view.findViewById(R.id.iatonase);
        ambilpopulasi = (TextView) view.findViewById(R.id.iapopulasi);
        ambilluastambak = (TextView) view.findViewById(R.id.ialuastambak);
        ambilpakan = (TextView) view.findViewById(R.id.iapakan);
        pakanpanen = (TextView) view.findViewById(R.id.iapakanpanen);

        simpan = (Button)view.findViewById(R.id.buttonsimpanpanen);
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("HasilPanen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestHasilPanen requestHasilPanen = dataSnapshot.getValue(RequestHasilPanen.class);
                ambiltonase.setText(requestHasilPanen.getPanentotal());
                ambilpopulasi.setText(requestHasilPanen.getTotalpopulasi());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Request_Data_Kolam request_data_kolam = dataSnapshot.getValue(Request_Data_Kolam.class);
                ambilluastambak.setText(request_data_kolam.getLuasarea());
                ambilpakan.setText(request_data_kolam.getJumlahtebarekor());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePakan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdatePakan requestUpdatePakan = dataSnapshot.getValue(RequestUpdatePakan.class);
                pakanpanen.setText(requestUpdatePakan.getJumlahtotal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String tanggalpanen = htanggal.getText().toString();
                final String doc = hdoc.getText().toString();
                final String tonase = htonase.getText().toString();
                final String abw = habw.getText().toString();
                size(Double.parseDouble(abw));
                final String size = hsize.getText().toString();
                populasi(Double.parseDouble(abw), Double.parseDouble(tonase));
                final String populasi = hpopulasipanen.getText().toString();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setTitle("Pemberitahuan");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String totalpakan = pakanpanen.getText().toString();
                                String atonase = ambiltonase.getText().toString();
                                String apopulasi = ambilpopulasi.getText().toString();
                                String jumlahtebar = ambilpakan.getText().toString();
                                String luastambak = ambilluastambak.getText().toString();
                                totalpanen(Double.parseDouble(atonase), Double.parseDouble(tonase));
                                String panentotal = htotalpanen.getText().toString();
                                totalpopulasi(Double.parseDouble(apopulasi), Double.parseDouble(populasi));
                                String totalpopulasi = htotalpopulasi.getText().toString();
                                totalsr(Double.parseDouble(jumlahtebar), Double.parseDouble(totalpopulasi));
                                String totalsr = htotalsr.getText().toString();
                                totalfcr(Double.parseDouble(totalpakan), Double.parseDouble(panentotal));
                                String fcrtotal = htotalfcrpanen.getText().toString();
                                totalton(Double.parseDouble(luastambak), Double.parseDouble(panentotal));
                                String tonha = htotalton.getText().toString();

                                datapanen(new RequestDataPanen(tanggalpanen, doc, tonase, abw, size, populasi, tonha, totalpopulasi, panentotal, totalsr, totalpakan, fcrtotal));
                                updatedatapanen(new RequestUpdatePanen(tanggalpanen, doc, tonase, abw, size, populasi));

                                Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                updatehasilpanen(new RequestHasilPanen(tonha, totalpopulasi, panentotal, totalsr, totalpakan, fcrtotal));
                                hdoc.setText("0.0");
                                htonase.setText("0.0");
                                habw.setText("0.0");
                            }
                        })
                        .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
            }
        });






        return view;
    }
    private void size(double abw){
        double hasilsize = 1000/abw;
        if (Double.isNaN(hasilsize)){
            hasilsize = 0.0;
        }
        hsize.setText(String.valueOf(hasilsize));
    }

    private void populasi(double abw, double tonase){
        double hasilpopulasi = abw * tonase;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        hpopulasipanen.setText(String.valueOf(hasilpopulasi));
    }
    private void totalpanen(double atonase, double tonase){
        double hasiltotal = atonase+tonase;
        if (Double.isNaN(hasiltotal)){
            hasiltotal = 0.0;
        }
       htotalpanen.setText(String.valueOf(hasiltotal));
    }
    private void totalpopulasi(double apopulasi, double populasi){
        double hasilpopulasi = apopulasi+populasi;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        htotalpopulasi.setText(String.valueOf(hasilpopulasi));
    }
    private void totalsr(double jumlahtebar, double totalpopulasi){
        double hasilsr = (jumlahtebar/totalpopulasi)*100;
        if (Double.isNaN(hasilsr)){
            hasilsr = 0.0;
        }
        htotalsr.setText(String.valueOf(hasilsr));
    }
    private void totalfcr(double totalpakan, double panentotal){
        double hasilfcr = totalpakan/panentotal;
        if (Double.isNaN(hasilfcr)){
            hasilfcr = 0.0;
        }
        htotalfcrpanen.setText(String.valueOf(hasilfcr));
    }
    private void totalton(double luastambak, double panentotal){
        double hasilton = (10000/luastambak)*(panentotal/1000);
        if (Double.isNaN(hasilton)){
            hasilton = 0.0;
        }
        htotalton.setText(String.valueOf(hasilton));
    }
    private void datapanen(RequestDataPanen requestDataPanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Panen").push().setValue(requestDataPanen)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatapanen(RequestUpdatePanen requestUpdatePanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePanen").setValue(requestUpdatePanen)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datapakan(RequestDataPakan requestDataPakan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Pakan").push().setValue(requestDataPakan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatehasilpanen(RequestHasilPanen requestHasilPanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("HasilPanen").setValue(requestHasilPanen)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
}
