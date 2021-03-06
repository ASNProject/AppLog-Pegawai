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

import com.example.applog.Activity.Input_Data_Kolam;
import com.example.applog.Activity.MenuInput;
import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.Adapter.RequestUpdatePakan;
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

public class InputPakan extends Fragment {
    private EditText tanggalpakan, kodepakan, jam6, jam10, jam14, jam18, jam22, keteranganpakan;
    private String jumlahharian, jumlahtotal, usia;
    private TextView jumlah, totals, ambil, pancingan;
    private Button simpanpakan;
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
        final View view = inflater.inflate(R.layout.fragment_input_pakan, container, false);
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tanggalpakan = (EditText)view.findViewById(R.id.itanggalpakan);
        tanggalpakan.setText(date_n);
        kodepakan = (EditText)view.findViewById(R.id.ikodepakan);
        jam6 = (EditText)view.findViewById(R.id.ijam6);
        jam10 = (EditText)view.findViewById(R.id.ijam10);
        jam14 = (EditText)view.findViewById(R.id.ijam14);
        jam18 = (EditText)view.findViewById(R.id.ijam18);
        jam22 = (EditText)view.findViewById(R.id.ijam22);
        keteranganpakan = (EditText)view.findViewById(R.id.icatatan);
        simpanpakan = (Button)view.findViewById(R.id.buttonsimpanpakan);
        ambil = (TextView) view.findViewById(R.id.ambiltotal);
        jumlah = (TextView)view.findViewById(R.id.jumlahharian);
        totals = (TextView)view.findViewById(R.id.jumlahtotal);
        pancingan = (TextView)view.findViewById(R.id.pancingpakan);

        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePakan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdatePakan requestUpdatePakan = dataSnapshot.getValue(RequestUpdatePakan.class);
                String ambiltotal = requestUpdatePakan.getJumlahtotal();
                ambil.setText(ambiltotal);
                String datatanggal = requestUpdatePakan.getTanggalpakan();
                String inputantanggal = tanggalpakan.getText().toString();

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
        simpanpakan.setOnClickListener(new View.OnClickListener() {
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
                    final String htanggalpakan = tanggalpakan.getText().toString();
                    final String hkodepakan = kodepakan.getText().toString();
                    String hjam6 = jam6.getText().toString();
                    if (hjam6.equals("")){
                        hjam6 = "0.0";
                    }
                    String hjam10 = jam10.getText().toString();
                    if (hjam10.equals("")){
                        hjam10 = "0.0";
                    }
                    String hjam14 = jam14.getText().toString();
                    if (hjam14.equals("")){
                        hjam14 = "0.0";
                    }
                    String hjam18 = jam18.getText().toString();
                    if (hjam18.equals("")){
                        hjam18 = "0.0";
                    }
                    String hjam22 = jam22.getText().toString();
                    if (hjam22.equals("")){
                        hjam22 = "0.0";
                    }
                    final String hketeranngan = keteranganpakan.getText().toString();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    final String finalHjam = hjam6;
                    final String finalHjam1 = hjam10;
                    final String finalHjam11 = hjam14;
                    final String finalHjam12 = hjam18;
                    final String finalHjam2 = hjam22;
                    alertDialogBuilder.setMessage("Simpan penambahan data?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String usia = sessions.getUsia();
                                    String ambildata = ambil.getText().toString();
                                    hitung3(Double.parseDouble(finalHjam), Double.parseDouble(finalHjam1), Double.parseDouble(finalHjam11), Double.parseDouble(finalHjam12), Double.parseDouble(finalHjam2));
                                    String jumlahharian = jumlah.getText().toString();
                                    hitung4(Double.parseDouble(jumlahharian), Double.parseDouble(ambildata));
                                    String jumlahtotal = totals.getText().toString();

                                    datapakan(new RequestDataPakan(htanggalpakan, hkodepakan, finalHjam, finalHjam1, finalHjam11, finalHjam12, finalHjam2, jumlahharian, jumlahtotal, hketeranngan, usia));
                                    updatedatapakan(new RequestUpdatePakan(htanggalpakan, hkodepakan, finalHjam, finalHjam1, finalHjam11, finalHjam12, finalHjam2, jumlahharian, jumlahtotal, hketeranngan, usia));

                                    kodepakan.setText("");
                                    jam6.setText("");
                                    jam10.setText("");
                                    jam14.setText("");
                                    jam18.setText("");
                                    jam22.setText("");
                                    keteranganpakan.setText("");

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
    private void updatedatapakan(RequestUpdatePakan requestUpdatePakan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePakan").setValue(requestUpdatePakan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }

    private void hitung3(double hjam6, double hjam10, double hjam14, double hjam18, double hjam22){
        double jumlahpakanharian = hjam6+hjam10+hjam14+hjam18+hjam22;
        jumlah.setText(String.valueOf(jumlahpakanharian));
    }
    private void hitung4(double jumlahharian, double ambildata){
        final double totalkonsumsipakan = jumlahharian+ambildata;
        totals.setText(String.valueOf(totalkonsumsipakan));
    }
}
