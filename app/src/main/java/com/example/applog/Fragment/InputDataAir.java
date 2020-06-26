package com.example.applog.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.applog.Activity.MenuInput;
import com.example.applog.Adapter.RequestDataAir;
import com.example.applog.Adapter.RequestHasilPanen;
import com.example.applog.Adapter.RequestUpdateAir;
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


public class InputDataAir extends Fragment {
    private EditText tanggalair, tinggiair, dopagi, domalam, phpagi, phmalam,
            kecerahan, alkalinitas, suhu, ca, mg, no2, no3, nh3;
    private Button btnsimpan;
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
        FirebaseUser user = mAuth.getCurrentUser();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input_data_air, container, false);
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tanggalair = (EditText)view.findViewById(R.id.itanggalair);
        tanggalair.setText(date_n);
        tinggiair = (EditText)view.findViewById(R.id.itinggiair);
        dopagi = (EditText)view.findViewById(R.id.idopagi);
        domalam = (EditText)view.findViewById(R.id.idomalam);
        phpagi = (EditText)view.findViewById(R.id.ippagi);
        phmalam = (EditText)view.findViewById(R.id.iphmalam);
        kecerahan = (EditText)view.findViewById(R.id.ikecerahan);
        alkalinitas = (EditText)view.findViewById(R.id.ialkalinitas);
        suhu = (EditText)view.findViewById(R.id.isuhu);
        ca = (EditText)view.findViewById(R.id.ica);
        mg = (EditText)view.findViewById(R.id.img);
        no2 = (EditText)view.findViewById(R.id.ino2);
        no3 = (EditText)view.findViewById(R.id.ino3);
        nh3 = (EditText)view.findViewById(R.id.inh3);
        btnsimpan = (Button)view.findViewById(R.id.buttonsimpanair);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setTitle("Pemberitahuan");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                simpandata(new RequestDataAir(tanggalair.getText().toString(), tinggiair.getText().toString(),
                                        dopagi.getText().toString(), domalam.getText().toString(), phpagi.getText().toString(),
                                        phmalam.getText().toString(), kecerahan.getText().toString(), alkalinitas.getText().toString(),
                                        suhu.getText().toString(), ca.getText().toString(), mg.getText().toString(),
                                        no2.getText().toString(), no3.getText().toString(), nh3.getText().toString()));
                                updatedata(new RequestUpdateAir(tanggalair.getText().toString(), tinggiair.getText().toString(),
                                        dopagi.getText().toString(), domalam.getText().toString(), phpagi.getText().toString(),
                                        phmalam.getText().toString(), kecerahan.getText().toString(), alkalinitas.getText().toString(),
                                        suhu.getText().toString(), ca.getText().toString(), mg.getText().toString(),
                                        no2.getText().toString(), no3.getText().toString(), nh3.getText().toString()));
                                tinggiair.setText("");
                                dopagi.setText("");
                                domalam.setText("");
                                phmalam.setText("");
                                phpagi.setText("");
                                kecerahan.setText("");
                                alkalinitas.setText("");
                                suhu.setText("");
                                ca.setText("");
                                mg.setText("");
                                no2.setText("");
                                no3.setText("");
                                nh3.setText("");
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

    private void simpandata(RequestDataAir requestDataAir){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("Air")
                .push()
                .setValue(requestDataAir)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void updatedata(RequestUpdateAir requestUpdateAir){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdateAir")
                .setValue(requestUpdateAir)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
}
