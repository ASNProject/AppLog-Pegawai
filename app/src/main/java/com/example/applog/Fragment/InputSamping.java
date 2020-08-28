package com.example.applog.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.applog.Activity.MenuInput;
import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.Adapter.RequestDataSampling;
import com.example.applog.Adapter.RequestUpdatePakan;
import com.example.applog.Adapter.RequestUpdateSampling;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class InputSamping extends Fragment {
    private EditText htanggal, hjumlahratarata, hmbw, hpakanhari, hpakantotal, hfr;
    private TextView hpopulasi, hadgmingguan, hbiomass, hsp, hkonsumsifeed, hfcr, hmbwlama, pancingan;
    private Button simpan;
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
        final View view = inflater.inflate(R.layout.fragment_input_samping, container, false);
        String date_m = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        htanggal = (EditText)view.findViewById(R.id.itanggalsampling);
        htanggal.setText(date_m);
        hjumlahratarata = (EditText)view.findViewById(R.id.ijumlahratarata);
        hmbw = (EditText)view.findViewById(R.id.imbw);
        hpakanhari = (EditText)view.findViewById(R.id.ipakanhari);
        hpakantotal = (EditText)view.findViewById(R.id.itotalpakan);
        hfr = (EditText)view.findViewById(R.id.ifr);
        hpopulasi = (TextView)view.findViewById(R.id.ipopulasi);
        hadgmingguan = (TextView)view.findViewById(R.id.iadg);
        hbiomass = (TextView)view.findViewById(R.id.ibiomass);
        hsp = (TextView)view.findViewById(R.id.isp);
        hkonsumsifeed = (TextView)view.findViewById(R.id.ifeed);
        hfcr = (TextView)view.findViewById(R.id.ifcr);
        hmbwlama =(TextView)view.findViewById(R.id.imbwlama);
        pancingan = (TextView)view.findViewById(R.id.pancingsampling);
        simpan = (Button)view.findViewById(R.id.buttonsimpansampling);
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdateSampling").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateSampling requestUpdateSampling = dataSnapshot.getValue(RequestUpdateSampling.class);
                String ambilmbw = requestUpdateSampling.getMbw();
                hmbwlama.setText(ambilmbw);
                String datatanggal = requestUpdateSampling.getTanggalsampling();
                String inputantanggal = htanggal.getText().toString();

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pancingan.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int data = Integer.parseInt(pancingan.getText().toString());
                if (data == 0){
                    AlertDialog.Builder alertDialogB = new AlertDialog.Builder(v.getContext());
                    alertDialogB.setTitle("Pemberitahuan");
                    alertDialogB.setMessage("Data sudah ditambahkan hari ini")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(getContext(), MenuInput.class);
                                    startActivity(i);

                                }
                            });
                    AlertDialog alertDialog=alertDialogB.create();
                    alertDialog.show();
                }
                else {
                    final String tanggalsampling = htanggal.getText().toString();
                    String jumlahtebarsamplings = hjumlahratarata.getText().toString();
                    if (jumlahtebarsamplings.equals("")){
                        jumlahtebarsamplings = "0.0";
                    }
                    String mbw = hmbw.getText().toString();
                    if (mbw.equals("")){
                        mbw = "0.0";
                    }
                    String pakanseharisampling = hpakanhari.getText().toString();
                    if (pakanseharisampling.equals("")){
                        pakanseharisampling = "0.0";
                    }
                    String totalpakansampling = hpakantotal.getText().toString();
                    if (totalpakansampling.equals("")){
                        totalpakansampling = "0.0";
                    }
                    String fr = hfr.getText().toString();
                    if (fr.equals("")){
                        fr = "0.0";
                    }

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    final String finalPakanseharisampling = pakanseharisampling;
                    final String finalMbw = mbw;
                    final String finalJumlahtebarsamplings = jumlahtebarsamplings;
                    final String finalFr = fr;
                    final String finalTotalpakansampling = totalpakansampling;
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    hitungbiomass(Double.parseDouble(finalPakanseharisampling), Double.parseDouble(finalFr));
                                    String biomass = hbiomass.getText().toString();
                                    hitungpopulasi(Double.parseDouble(finalMbw), Double.parseDouble(biomass));
                                    String populasi = hpopulasi.getText().toString();
                                    hitungsp(Double.parseDouble(populasi), Double.parseDouble(finalJumlahtebarsamplings));
                                    String sp = hsp.getText().toString();
                                    hitungkonsumsifeed(Double.parseDouble(finalMbw), Double.parseDouble(finalFr), Double.parseDouble(finalJumlahtebarsamplings));
                                    String konsumsifeed = hkonsumsifeed.getText().toString();
                                    hitungfcr(Double.parseDouble(finalTotalpakansampling), Double.parseDouble(biomass));
                                    String fcr = hfcr.getText().toString();
                                    String lamambw = hmbwlama.getText().toString();
                                    hitungadg(Double.parseDouble(finalMbw), Double.parseDouble(lamambw));
                                    String adgmingguan = hadgmingguan.getText().toString();
                                    String usia = sessions.getUsia();

                                    datasampling(new RequestDataSampling(tanggalsampling, finalJumlahtebarsamplings, finalMbw, finalPakanseharisampling, finalTotalpakansampling, finalFr, populasi, adgmingguan, biomass, sp, konsumsifeed, fcr, usia));
                                    updatedatasampling(new RequestUpdateSampling(tanggalsampling, finalJumlahtebarsamplings, finalMbw, finalPakanseharisampling, finalTotalpakansampling, finalFr, populasi, adgmingguan, biomass, sp, konsumsifeed, fcr));
                                    hjumlahratarata.setText("");
                                    hmbw.setText("");
                                    hpakanhari.setText("");
                                    hpakantotal.setText("");
                                    hfr.setText("");

                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
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
            }
        });

        return view;
    }
    private void hitungbiomass(double pakanperharisampling, double fr){
        double jumlahbiomas= pakanperharisampling/(fr/100);
        if (Double.isNaN(jumlahbiomas)){
            jumlahbiomas = 0.0;
        }
        hbiomass.setText(String.valueOf( jumlahbiomas));
    }
    private void hitungpopulasi(double mbw, double biomass){
        double hasilpopulasi = (1000/mbw)*biomass;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        hpopulasi.setText(String.valueOf( hasilpopulasi));
    }
    private void hitungsp(double hasilpopulasi, double jumlahtebarsampling){
        double jumlahsp = (hasilpopulasi/jumlahtebarsampling)*100;
        if (Double.isNaN(jumlahsp)){
            jumlahsp = 0.0;
        }
        hsp.setText(String.valueOf( jumlahsp));
    }
    private void hitungkonsumsifeed(double mbw, double fr, double jumlahtebarsamplings){
        double jumlahkonsumsifeed = (mbw * fr * jumlahtebarsamplings)/100000;
        if (Double.isNaN(jumlahkonsumsifeed)){
            jumlahkonsumsifeed = 0.0;
        }
        hkonsumsifeed.setText(String.valueOf( jumlahkonsumsifeed));
    }
    private void hitungfcr(double totalpakansampling, double biomass){
        double hitungfcr = totalpakansampling/biomass;
        if (Double.isNaN(hitungfcr)){
            hitungfcr = 0.0;
        }
        hfcr.setText(String.valueOf(hitungfcr));
    }
    private void hitungadg(double mbw, double lamambw){
        double hasilagd = (mbw-lamambw)/6;
        if (Double.isNaN(hasilagd)){
            hasilagd = 0.0;
        }
        hadgmingguan.setText(String.valueOf(hasilagd));
    }

    private void datasampling(RequestDataSampling requestDataSampling){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Sampling").push().setValue(requestDataSampling)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatasampling(RequestUpdateSampling requestUpdateSampling){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdateSampling").setValue(requestUpdateSampling)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
}
