package com.example.applog.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.applog.Adapter.PostAdapterPerlakuan;
import com.example.applog.Adapter.RequestDataPerlakuan;
import com.example.applog.Adapter.RequestUpdateAbusekam;
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
import com.example.applog.Adapter.RequestUpdateProbion;
import com.example.applog.Adapter.RequestUpdateRO2;
import com.example.applog.Adapter.RequestUpdateRagi;
import com.example.applog.Adapter.RequestUpdateVirkon;
import com.example.applog.Adapter.RequestUpdatemonodon;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DetailPerlakuan extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private FirebaseAuth mAuth;
    private PostAdapterPerlakuan adapterPerlakuan;
    SharePreferences sessions;
    private TextView alkalinmag, kcl, azomit, monodon, atbak, ragi, daunkates, jamu, ro2, virkon, ariakekuro, kapuraktif, dolomit, kaptan, abusekam, artemia, molase, probion, jalkalinmag, jkcl, jazomit, jmonodon, jatbak, jragi, jdaunkates, jjamu, jro2, jvirkon, jariakekuro, jkapuraktif, jdolomit, jkaptan, jabusekam, jartemia, jmolase, jprobion;
    private TextView talkalinmag, tkcl, tazomit, tartemia, tmonodon, tatbak, tragi, tdaunkates, tjamu, tro2, tvirkon, tariakekuro, tkapuraktif, tdolomit, tkaptan, tabusekam, tmolase, tprobion;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.item_perlakuan_baru, container, false);
        alkalinmag = (TextView) view.findViewById(R.id.djumlahalkalinmag);
        artemia = (TextView)view.findViewById(R.id.djumlahartemia);
        kcl = (TextView)view.findViewById(R.id.djumlahkcl);
        azomit = (TextView)view.findViewById(R.id.djumlahzomit);
        monodon = (TextView)view.findViewById(R.id.djumlahmonodon);
        atbak = (TextView)view.findViewById(R.id.djumlahatbak);
        ragi = (TextView)view.findViewById(R.id.djumlahragi);
        daunkates = (TextView)view.findViewById(R.id.djumlahdaunkates);
        jamu = (TextView)view.findViewById(R.id.djumlahjamu);
        ro2 = (TextView)view.findViewById(R.id.djumlahro2);
        virkon = (TextView)view.findViewById(R.id.djumlahvirkon);
        ariakekuro = (TextView)view.findViewById(R.id.djumlahariakekuro);
        kapuraktif = (TextView)view.findViewById(R.id.djumlahkapuraktif);
        dolomit = (TextView)view.findViewById(R.id.djumlahdolomit);
        kaptan = (TextView)view.findViewById(R.id.djumlahkaptan);
        abusekam = (TextView)view.findViewById(R.id.djumlahabusekam);
        molase = (TextView)view.findViewById(R.id.djumlahmolase);
        probion = (TextView)view.findViewById(R.id.djumlahprobion);

        talkalinmag = (TextView)view.findViewById(R.id.waktualkalinimag);
        tartemia = (TextView)view.findViewById(R.id.waktuartemia);
        tkcl = (TextView)view.findViewById(R.id.waktukcl);
        tazomit = (TextView)view.findViewById(R.id.waktuazomit);
        tmonodon = (TextView)view.findViewById(R.id.waktumonodon);
        tatbak = (TextView)view.findViewById(R.id.waktuatbak);
        tragi = (TextView)view.findViewById(R.id.wakturagi);
        tdaunkates = (TextView)view.findViewById(R.id.waktudaunkates);
        tjamu = (TextView)view.findViewById(R.id.waktujamu);
        tro2 = (TextView)view.findViewById(R.id.wakturo2);
        tvirkon = (TextView)view.findViewById(R.id.waktuvirkon);
        tariakekuro = (TextView)view.findViewById(R.id.waktuariakekuro);
        tkapuraktif = (TextView)view.findViewById(R.id.waktukapuraktif);
        tdolomit = (TextView)view.findViewById(R.id.waktudolomit);
        tkaptan = (TextView)view.findViewById(R.id.waktukaptan);
        tabusekam = (TextView)view.findViewById(R.id.waktuabusekam);
        tmolase = (TextView)view.findViewById(R.id.waktumolase);
        tprobion = (TextView)view.findViewById(R.id.waktuprobion);

        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Alkalinmag").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAlkalinmag requestUpdateAlkalinmag = dataSnapshot.getValue(RequestUpdateAlkalinmag.class);
                alkalinmag.setText(requestUpdateAlkalinmag.getJumlahalkalinmag());
                if (alkalinmag.getText().toString() == "0.0"){
                    alkalinmag.setText("");
                }
                talkalinmag.setText(requestUpdateAlkalinmag.getWaktualkalinmag());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Abusekam").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAbusekam requestUpdateAbusekam = dataSnapshot.getValue(RequestUpdateAbusekam.class);
                abusekam.setText(requestUpdateAbusekam.getJumlahabusekam());
                if (abusekam.getText().toString() == "0.0"){
                    abusekam.setText("");
                }
                tabusekam.setText(requestUpdateAbusekam.getWaktuabusekam());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Ariakekuro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAriakekuro requestUpdateAriakekuro = dataSnapshot.getValue(RequestUpdateAriakekuro.class);
                ariakekuro.setText(requestUpdateAriakekuro.getJumlahariakekuro());
                if (ariakekuro.getText().toString() == "0.0"){
                    ariakekuro.setText("");
                }
                tariakekuro.setText(requestUpdateAriakekuro.getWaktuariakekuro());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Artemia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateArtemia requestUpdateArtemia = dataSnapshot.getValue(RequestUpdateArtemia.class);
                artemia.setText(requestUpdateArtemia.getJumlahartemia());
                if (artemia.getText().toString() == "0.0"){
                    artemia.setText("");
                }
                tartemia.setText(requestUpdateArtemia.getWaktuartemia());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Atbak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAtbak requestUpdateAtbak = dataSnapshot.getValue(RequestUpdateAtbak.class);
                atbak.setText(requestUpdateAtbak.getJumlahatbak());
                if (atbak.getText().toString() == "0.0"){
                    atbak.setText("");
                }
                tatbak.setText(requestUpdateAtbak.getWaktuatbak());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Azomit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAzomit requestUpdateAzomit = dataSnapshot.getValue(RequestUpdateAzomit.class);
                azomit.setText(requestUpdateAzomit.getJumlahazomit());
                if (azomit.getText().toString() == "0.0"){
                    azomit.setText("");
                }
                tazomit.setText(requestUpdateAzomit.getWaktuazomit());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Daunkates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateDaunkates requestUpdateDaunkates = dataSnapshot.getValue(RequestUpdateDaunkates.class);
                daunkates.setText(requestUpdateDaunkates.getJumlahdaunkates());
                if (daunkates.getText().toString() == "0.0"){
                    daunkates.setText("");
                }
                tdaunkates.setText(requestUpdateDaunkates.getWaktudaunkates());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Dolomit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateDolomit requestUpdateDolomit = dataSnapshot.getValue(RequestUpdateDolomit.class);
                dolomit.setText(requestUpdateDolomit.getJumlahdolomit());
                if (dolomit.getText().toString() == "0.0"){
                    dolomit.setText("");
                }
                tdolomit.setText(requestUpdateDolomit.getWaktudolomit());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Jamu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateJamu requestUpdateJamu = dataSnapshot.getValue(RequestUpdateJamu.class);
                jamu.setText(requestUpdateJamu.getJumlahjamu());
                if (jamu.getText().toString() == "0.0"){
                    jamu.setText("");
                }
                tjamu.setText(requestUpdateJamu.getWaktujamu());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("KCL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateKCL requestUpdateKCL = dataSnapshot.getValue(RequestUpdateKCL.class);
                kcl.setText(requestUpdateKCL.getJumlahkcl());
                if (kcl.getText().toString() == "0.0"){
                    kcl.setText("");
                }
                tkcl.setText(requestUpdateKCL.getWaktukcl());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Kaptan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateKaptan requestUpdateKaptan = dataSnapshot.getValue(RequestUpdateKaptan.class);
                kaptan.setText(requestUpdateKaptan.getJumlahkaptan());
                if (kaptan.getText().toString() == "0.0"){
                    kaptan.setText("");
                }
                tkaptan.setText(requestUpdateKaptan.getWaktukaptan());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Kapuraktif").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateKapuraktif requestUpdateKapuraktif = dataSnapshot.getValue(RequestUpdateKapuraktif.class);
                kapuraktif.setText(requestUpdateKapuraktif.getJumlahkapuraktif());
                if (kapuraktif.getText().toString() == "0.0"){
                    kapuraktif.setText("");
                }
                tkapuraktif.setText(requestUpdateKapuraktif.getWaktukapuraktif());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Molase").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateMolase requestUpdateMolase = dataSnapshot.getValue(RequestUpdateMolase.class);
                molase.setText(requestUpdateMolase.getJumlahmolase());
                if (molase.getText().toString() == "0.0"){
                    molase.setText("");
                }
                tmolase.setText(requestUpdateMolase.getWaktumolase());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Monodon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdatemonodon requestUpdatemonodon = dataSnapshot.getValue(RequestUpdatemonodon.class);
                monodon.setText(requestUpdatemonodon.getJumlahmonodon());
                if (monodon.getText().toString() == "0.0"){
                    monodon.setText("");
                }
                tmonodon.setText(requestUpdatemonodon.getWaktumonodon());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Probion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateProbion requestUpdateProbion = dataSnapshot.getValue(RequestUpdateProbion.class);
                probion.setText(requestUpdateProbion.getJumlahprobion());
                if (probion.getText().toString() == "0.0"){
                    probion.setText("");
                }
                tprobion.setText(requestUpdateProbion.getWaktuprobion());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("RO2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateRO2 requestUpdateRO2 = dataSnapshot.getValue(RequestUpdateRO2.class);
                ro2.setText(requestUpdateRO2.getJumlahro2());
                if (ro2.getText().toString() == "0.0"){
                    ro2.setText("");
                }
                tro2.setText(requestUpdateRO2.getWakturo2());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Ragi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateRagi requestUpdateRagi = dataSnapshot.getValue(RequestUpdateRagi.class);
                ragi.setText(requestUpdateRagi.getJumlahragi());
                if (ragi.getText().toString() == "0.0"){
                    ragi.setText("");
                }
                tragi.setText(requestUpdateRagi.getWakturagi());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Virkon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateVirkon requestUpdateVirkon = dataSnapshot.getValue(RequestUpdateVirkon.class);
                virkon.setText(requestUpdateVirkon.getJumlahvirkon());
                if (virkon.getText().toString() == "0.0"){
                    virkon.setText("");
                }
                tvirkon.setText(requestUpdateVirkon.getWaktuvirkon());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //mRecycler = (RecyclerView)view.findViewById(R.id.list_detai_perlakuan);
        //mRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        //FirebaseRecyclerOptions<RequestDataPerlakuan> options =
          //      new FirebaseRecyclerOptions.Builder<RequestDataPerlakuan>()
            //            .setQuery(FirebaseDatabase.getInstance().getReference().child("Data User").child(user.getDisplayName()).child("Database")
              //                  .child(sessions.getData()).child("Perlakuan"), RequestDataPerlakuan.class)
                //        .build();
        //adapterPerlakuan = new PostAdapterPerlakuan(options, view.getContext());
       // mRecycler.setAdapter(adapterPerlakuan);

        //mManager = new LinearLayoutManager(view.getContext());
        //mManager.setReverseLayout(true);
        //mManager.setStackFromEnd(true);
//        mRecycler.setLayoutManager(mManager);


        return view;
    }
  /*  @Override
    public void onStart() {
        super.onStart();
        if (adapterPerlakuan != null) {
            adapterPerlakuan.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapterPerlakuan != null) {
            adapterPerlakuan.stopListening();
        }
    }*/

  private void b1(){

  }
}
