package com.example.applog.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applog.Adapter.RequestDataPanen;
import com.example.applog.Adapter.RequestDataPerlakuan;
import com.example.applog.Adapter.RequestUpdatePanen;
import com.example.applog.Adapter.RequestUpdatePerlakuan;
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

public class InputPerlakuan extends Fragment {
    private EditText tanggal, hperlakuan;
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
        final View view = inflater.inflate(R.layout.fragment_input_perlakuan, container, false);
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tanggal = (EditText)view.findViewById(R.id.itanggalperlakuan);
        tanggal.setText(date_n);
        hperlakuan = (EditText)view.findViewById(R.id.iperlakuan);
        simpan = (Button)view.findViewById(R.id.buttonsimpanperlakuan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setTitle("Pemberitahuan");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tanggalperlakuan = tanggal.getText().toString();
                                String perlakuan = hperlakuan.getText().toString();
                                dataperlakuan(new RequestDataPerlakuan(tanggalperlakuan, perlakuan));
                                updatedataperlakuan(new RequestUpdatePerlakuan(tanggalperlakuan, perlakuan));
                                Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                hperlakuan.setText("");
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
    private void dataperlakuan(RequestDataPerlakuan requestDataPerlakuan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").push().setValue(requestDataPerlakuan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedataperlakuan(RequestUpdatePerlakuan requestUpdatePerlakuan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").setValue(requestUpdatePerlakuan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
}
