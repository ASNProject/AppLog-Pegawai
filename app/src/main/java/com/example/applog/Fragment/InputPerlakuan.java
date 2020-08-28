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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applog.Activity.MenuInput;
import com.example.applog.Adapter.RequestAbusekam;
import com.example.applog.Adapter.RequestAlkalinmag;
import com.example.applog.Adapter.RequestAriakekuro;
import com.example.applog.Adapter.RequestArtemia;
import com.example.applog.Adapter.RequestAtbak;
import com.example.applog.Adapter.RequestAzomit;
import com.example.applog.Adapter.RequestDataAir;
import com.example.applog.Adapter.RequestDataPanen;
import com.example.applog.Adapter.RequestDataPerlakuan;
import com.example.applog.Adapter.RequestDaunkates;
import com.example.applog.Adapter.RequestDolomit;
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
import com.example.applog.Adapter.RequestUpdatePanen;
import com.example.applog.Adapter.RequestUpdatePerlakuan;
import com.example.applog.Adapter.RequestUpdateProbion;
import com.example.applog.Adapter.RequestUpdateRO2;
import com.example.applog.Adapter.RequestUpdateRagi;
import com.example.applog.Adapter.RequestUpdateVirkon;
import com.example.applog.Adapter.RequestUpdatemonodon;
import com.example.applog.Adapter.RequestVirkon;
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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InputPerlakuan extends Fragment {
    private EditText alkalinmag, kcl, azomit, monodon, atbak, ragi, daunkates, jamu, ro2, virkon, ariakekuro, kapuraktif, dolomit, kaptan, abusekam, artemia, molase, probion, jalkalinmag, jkcl, jazomit, jmonodon, jatbak, jragi, jdaunkates, jjamu, jro2, jvirkon, jariakekuro, jkapuraktif, jdolomit, jkaptan, jabusekam, jartemia, jmolase, jprobion;
    private Spinner talkalinmag, tkcl, tazomit, tartemia, tmonodon, tatbak, tragi, tdaunkates, tjamu, tro2, tvirkon, tariakekuro, tkapuraktif, tdolomit, tkaptan, tabusekam, tmolase, tprobion;
    private Button salkalinmag, sartemia, skcl, sazomit, smonodon, satbak, sragi, sdaunkates, sjamu, sro2, svirkon, sariakekuro, skapuraktif, sdolomit, skaptan, sabusekam, smolase, sprobion;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    private EditText tanggalanp;
    private TextView palkalinmag, pkcl, pazomit, pmonodon, patbak, pragi, pdaunkates, pjamu, pro2, pvirkon, pariakekuro, pkapuraktif, pdolomit, pkaptan, pabusekam, partemia, pmolase, pprobion;

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
        tanggalanp = (EditText) view.findViewById(R.id.tanggalansad);
        tanggalanp.setText(date_n);
       // hperlakuan = (EditText)view.findViewById(R.id.ijumlahalkalinmag);
       // simpan = (Button)view.findViewById(R.id.buttonsimpanalkalinmag);
        alkalinmag = (EditText)view.findViewById(R.id.ijumlahalkalinmag);
        artemia = (EditText)view.findViewById(R.id.ijumlahartemia);
        kcl = (EditText)view.findViewById(R.id.ijumlahkcl);
        azomit = (EditText)view.findViewById(R.id.ijumlahazomit);
        monodon = (EditText)view.findViewById(R.id.ijumlahmonodon);
        atbak = (EditText)view.findViewById(R.id.ijumlahatbak);
        ragi = (EditText)view.findViewById(R.id.ijumlahragi);
        daunkates = (EditText)view.findViewById(R.id.ijumlahdaunkates);
        jamu = (EditText)view.findViewById(R.id.ijumlahjamu);
        ro2 = (EditText)view.findViewById(R.id.ijumlahro2);
        virkon = (EditText)view.findViewById(R.id.ijumlahvirkon);
        ariakekuro = (EditText)view.findViewById(R.id.ijumlahariakekuro);
        kapuraktif = (EditText)view.findViewById(R.id.ijumlahkapuraktif);
        dolomit = (EditText)view.findViewById(R.id.ijumlahdolomit);
        kaptan = (EditText)view.findViewById(R.id.ijumlahkapntan);
        abusekam = (EditText)view.findViewById(R.id.ijumlahabusekam);
        molase = (EditText)view.findViewById(R.id.ijumlahmolase);
        probion = (EditText)view.findViewById(R.id.ijumlahprobion);

        palkalinmag = (TextView) view.findViewById(R.id.pancinganalkalinmag);
        partemia = (TextView)view.findViewById(R.id.pancinganartemia);
        pkcl = (TextView)view.findViewById(R.id.pancingankcl);
        pazomit = (TextView)view.findViewById(R.id.pancinganazomit);
        pmonodon = (TextView)view.findViewById(R.id.pancinganmonodon);
        patbak = (TextView)view.findViewById(R.id.pancinganatbak);
        pragi = (TextView)view.findViewById(R.id.pancinganragi);
        pdaunkates = (TextView)view.findViewById(R.id.pancingandaunkates);
        pjamu = (TextView)view.findViewById(R.id.pancinganjamu);
        pro2 = (TextView)view.findViewById(R.id.pancinganro2);
        pvirkon = (TextView)view.findViewById(R.id.pancinganvirkon);
        pariakekuro = (TextView)view.findViewById(R.id.pancinganariaekuro);
       pkapuraktif = (TextView)view.findViewById(R.id.pancingankapuraktif);
        pdolomit = (TextView)view.findViewById(R.id.pancingandolomit);
        pkaptan = (TextView)view.findViewById(R.id.pancingankaptan);
        pabusekam = (TextView)view.findViewById(R.id.pancinganabusekam);
        pmolase = (TextView)view.findViewById(R.id.pancinganmolase);
        pprobion = (TextView)view.findViewById(R.id.pancinganprobion);

        talkalinmag = (Spinner)view.findViewById(R.id.spinneralkalinmag);
        tartemia = (Spinner)view.findViewById(R.id.spinnerartemia);
        tkcl = (Spinner)view.findViewById(R.id.spinnerkcl);
        tazomit = (Spinner)view.findViewById(R.id.spinnerazomit);
        tmonodon = (Spinner)view.findViewById(R.id.spinnermonodon);
        tatbak = (Spinner)view.findViewById(R.id.spinneratbak);
        tragi = (Spinner)view.findViewById(R.id.spinnerragi);
        tdaunkates = (Spinner)view.findViewById(R.id.spinnerdaunkates);
        tjamu = (Spinner)view.findViewById(R.id.spinnerjamu);
        tro2 = (Spinner)view.findViewById(R.id.spinnerro2);
        tvirkon = (Spinner)view.findViewById(R.id.spinnervirkon);
        tariakekuro = (Spinner)view.findViewById(R.id.spinnerariakekuro);
        tkapuraktif = (Spinner)view.findViewById(R.id.spinnerkapuraktif);
        tdolomit = (Spinner)view.findViewById(R.id.spinnerdolomit);
        tkaptan = (Spinner)view.findViewById(R.id.spinnerkaptan);
        tabusekam = (Spinner)view.findViewById(R.id.spinnerabusekam);
        tmolase = (Spinner)view.findViewById(R.id.spinnermolase);
        tprobion = (Spinner)view.findViewById(R.id.spinnerprobion);

        salkalinmag = (Button)view.findViewById(R.id.buttonsimpanalkalinmag);
        sartemia = (Button)view.findViewById(R.id.buttonsimpanartemia);
        skcl = (Button)view.findViewById(R.id.buttonsimpankcl);
        sazomit = (Button)view.findViewById(R.id.buttonsimpanazomit);
        smonodon = (Button)view.findViewById(R.id.buttonsimpanmonodon);
        satbak = (Button)view.findViewById(R.id.buttonsimpanatbak);
        sragi = (Button)view.findViewById(R.id.buttonsimpanragi);
        sdaunkates = (Button)view.findViewById(R.id.buttonsimpandaunkates);
        sjamu = (Button)view.findViewById(R.id.buttonsimpanjamu);
        sro2 = (Button)view.findViewById(R.id.buttonsimpanro2);
        svirkon = (Button)view.findViewById(R.id.buttonsimpanvirkon);
        sariakekuro = (Button)view.findViewById(R.id.buttonsimpanariakekuro);
        skapuraktif = (Button)view.findViewById(R.id.buttonsimpankapuraktif);
        sdolomit = (Button)view.findViewById(R.id.buttonsimpandolomit);
        skaptan = (Button)view.findViewById(R.id.buttonsimpankaptan);
        sabusekam = (Button)view.findViewById(R.id.buttonsimpanabusekam);
        smolase = (Button)view.findViewById(R.id.buttonsimpanmolase);
        sprobion = (Button)view.findViewById(R.id.buttonsimpanprobion);

        b1();
        b2();
        b3();
        b4();
        b5();
        b6();
        b7();
        b8();
        b9();
        b10();
        b11();
        b12();
        b13();
        b14();
        b15();
        b16();
        b17();
        b18();

        return view;
    }
    private void dataalkalinmag(RequestAlkalinmag requestAlkalinmag){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Alkalinmag").push().setValue(requestAlkalinmag)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatealkalinmag(RequestUpdateAlkalinmag requestUpdateAlkalinmag){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Alkalinmag").setValue(requestUpdateAlkalinmag)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datakcl(RequestKCL requestKCL){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("KCL").push().setValue(requestKCL)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateKCL(RequestUpdateKCL requestUpdateKCL){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("KCL").setValue(requestUpdateKCL)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataazomit(RequestAzomit requestAzomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Azomit").push().setValue(requestAzomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatezomit(RequestUpdateAzomit requestUpdateAzomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Azomit").setValue(requestUpdateAzomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataalmonodon(RequestMonodon requestMonodon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Monodon").push().setValue(requestMonodon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatemonodon(RequestUpdatemonodon requestUpdatemonodon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Monodon").setValue(requestUpdatemonodon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataatbak(RequestAtbak requestAtbak){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Atbak").push().setValue(requestAtbak)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatetatbak(RequestUpdateAtbak requestUpdateAtbak){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Atbak").setValue(requestUpdateAtbak)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataragi(RequestRagi requestRagi){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Ragi").push().setValue(requestRagi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateragi(RequestUpdateRagi requestUpdateRagi){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Ragi").setValue(requestUpdateRagi)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datadaunkates(RequestDaunkates requestDaunkates){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Daunkates").push().setValue(requestDaunkates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedaunkates(RequestUpdateDaunkates requestUpdateDaunkates){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Daunkates").setValue(requestUpdateDaunkates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datajamu(RequestJamu requestJamu){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Jamu").push().setValue(requestJamu)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatejamu(RequestUpdateJamu requestUpdateJamu){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Jamu").setValue(requestUpdateJamu)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataro2(RequestRO2 requestRO2){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("RO2").push().setValue(requestRO2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatero2(RequestUpdateRO2 requestUpdateRO2){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("RO2").setValue(requestUpdateRO2)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datavirkon(RequestVirkon requestVirkon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Virkon").push().setValue(requestVirkon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatevirkon(RequestUpdateVirkon requestUpdateVirkon){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Virkon").setValue(requestUpdateVirkon)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataariakekuro(RequestAriakekuro requestAriakekuro){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Ariakekuro").push().setValue(requestAriakekuro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateariakekuro(RequestUpdateAriakekuro requestUpdateAriakekuro){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Ariakekuro").setValue(requestUpdateAriakekuro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datakapuraktif(RequestKapuraktif requestKapuraktif){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Kapuraktif").push().setValue(requestKapuraktif)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatekapuraktif(RequestUpdateKapuraktif requestUpdateKapuraktif){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Kapuraktif").setValue(requestUpdateKapuraktif)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datadolomit(RequestDolomit requestDolomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Dolomit").push().setValue(requestDolomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedolomit(RequestUpdateDolomit requestUpdateDolomit){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Alkalinmag").setValue(requestUpdateDolomit)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datakaptan(RequestKaptan requestKaptan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Kaptan").push().setValue(requestKaptan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatekaptan(RequestUpdateKaptan requestUpdateKaptan){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Kaptan").setValue(requestUpdateKaptan)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataabusekam(RequestAbusekam requestAbusekam){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Abusekam").push().setValue(requestAbusekam)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateabusekam(RequestUpdateAbusekam requestUpdateAbusekam){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Abusekam").setValue(requestUpdateAbusekam)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void datamolase(RequestMolase requestMolase){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Molase").push().setValue(requestMolase)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatemolase(RequestUpdateMolase requestUpdateMolase){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Molase").setValue(requestUpdateMolase)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataprobion(RequestProbion requestProbion){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Probion").push().setValue(requestProbion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateprobion(RequestUpdateProbion requestUpdateProbion){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Probion").setValue(requestUpdateProbion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void dataartemia(RequestArtemia requestArtemia){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("Perlakuan").child("Artemia").push().setValue(requestArtemia)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updateartemia(RequestUpdateArtemia requestUpdateArtemia){
        final FirebaseUser user = mAuth.getCurrentUser();
        sessions =new SharePreferences(this.getActivity());
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData())
                .child("UpdatePerlakuan").child("Artemia").setValue(requestUpdateArtemia)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }


    private void b1(){
    mDatabase = FirebaseDatabase.getInstance().getReference();
    mAuth = FirebaseAuth.getInstance();
    sessions = new SharePreferences(this.getActivity());
    FirebaseUser user = mAuth.getCurrentUser();
    mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
            .child(sessions.getData()).child("UpdatePerlakuan").child("Alkalinmag").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            RequestUpdateAlkalinmag requestUpdateAlkalinmag = dataSnapshot.getValue(RequestUpdateAlkalinmag.class);
            String datatanggal = requestUpdateAlkalinmag.getTanggalalkalinmag();
            String inputantanggal = date_n;

            DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date tglawal = (Date) date.parse(inputantanggal);
                Date tglakhir = (Date) date.parse(datatanggal);
                long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                palkalinmag.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    salkalinmag.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            int data = Integer.parseInt(palkalinmag.getText().toString());
            if (data == 0) {
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
                AlertDialog alertDialog = alertDialogB.create();
                alertDialog.show();
            } else  {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                alertDialogBuilder.setTitle("Pemberitahuan");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tanggalperlakuan = date_n;
                                String jumlahalkalinmag = alkalinmag.getText().toString();
                                String waktualkalinmag = (String) talkalinmag.getSelectedItem();
                                dataalkalinmag(new RequestAlkalinmag(tanggalperlakuan, jumlahalkalinmag, waktualkalinmag));
                                updatealkalinmag(new RequestUpdateAlkalinmag(tanggalperlakuan, jumlahalkalinmag, waktualkalinmag));
                                Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                alkalinmag.setText("");
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
}
    private void b2(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("KCL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateKCL requestUpdateKCL = dataSnapshot.getValue(RequestUpdateKCL.class);
                String datatanggal = requestUpdateKCL.getTanggalkcl();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pkcl.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        skcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pkcl.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahkcl = kcl.getText().toString();
                                    String waktukcl = (String) tkcl.getSelectedItem();
                                    datakcl(new RequestKCL(tanggalperlakuan, jumlahkcl, waktukcl));
                                    updateKCL(new RequestUpdateKCL(tanggalperlakuan, jumlahkcl, waktukcl));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    kcl.setText("");
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
    }
    private void b3(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Azomit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateAzomit requestUpdateAzomit = dataSnapshot.getValue(RequestUpdateAzomit.class);
                String datatanggal = requestUpdateAzomit.getTanggalazomit();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pazomit.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sazomit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pazomit.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahazomit = azomit.getText().toString();
                                    String waktuszomit = (String) tazomit.getSelectedItem();
                                    dataazomit(new RequestAzomit(tanggalperlakuan, jumlahazomit, waktuszomit));
                                    updatezomit(new RequestUpdateAzomit(tanggalperlakuan, jumlahazomit, waktuszomit));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    azomit.setText("");
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
    }
    private void b4(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Monodon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdatemonodon requestUpdatemonodon = dataSnapshot.getValue(RequestUpdatemonodon.class);
                String datatanggal = requestUpdatemonodon.getTanggalmonodon();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pmonodon.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        smonodon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pmonodon.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahmonodon = monodon.getText().toString();
                                    String waktumonodon = (String) tmonodon.getSelectedItem();
                                    dataalmonodon(new RequestMonodon(tanggalperlakuan, jumlahmonodon, waktumonodon));
                                    updatemonodon(new RequestUpdatemonodon(tanggalperlakuan, jumlahmonodon, waktumonodon));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    monodon.setText("");
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
    }
    private void b5(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Atbak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateAtbak requestUpdateAtbak = dataSnapshot.getValue(RequestUpdateAtbak.class);
                String datatanggal = requestUpdateAtbak.getTanggalatbak();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    patbak.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        satbak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(patbak.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahatbak = atbak.getText().toString();
                                    String waktuatbak = (String) tatbak.getSelectedItem();
                                    dataatbak(new RequestAtbak(tanggalperlakuan, jumlahatbak, waktuatbak));
                                    updatetatbak(new RequestUpdateAtbak(tanggalperlakuan, jumlahatbak, waktuatbak));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    atbak.setText("");
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
    }
    private void b6(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Ragi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateRagi requestUpdateRagi = dataSnapshot.getValue(RequestUpdateRagi.class);
                String datatanggal = requestUpdateRagi.getTanggalragi();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pragi.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sragi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pragi.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahragi = ragi.getText().toString();
                                    String wakturagi = (String) tragi.getSelectedItem();
                                    dataragi(new RequestRagi(tanggalperlakuan, jumlahragi, wakturagi));
                                    updateragi(new RequestUpdateRagi(tanggalperlakuan, jumlahragi, wakturagi));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    ragi.setText("");
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
    }
    private void b7(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Daunkates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateDaunkates requestUpdateDaunkates = dataSnapshot.getValue(RequestUpdateDaunkates.class);
                String datatanggal = requestUpdateDaunkates.getTanggaldaunkates();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pdaunkates.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sdaunkates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pdaunkates.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahdaunkates = daunkates.getText().toString();
                                    String waktudaunkates = (String) tdaunkates.getSelectedItem();
                                    datadaunkates(new RequestDaunkates(tanggalperlakuan, jumlahdaunkates, waktudaunkates));
                                    updatedaunkates(new RequestUpdateDaunkates(tanggalperlakuan, jumlahdaunkates, waktudaunkates));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    daunkates.setText("");
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
    }
    private void b8(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Jamu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateJamu requestUpdateJamu = dataSnapshot.getValue(RequestUpdateJamu.class);
                String datatanggal = requestUpdateJamu.getTanggaljamu();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pjamu.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sjamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pjamu.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahjamu = jamu.getText().toString();
                                    String waktujamu = (String) tjamu.getSelectedItem();
                                    datajamu(new RequestJamu(tanggalperlakuan, jumlahjamu, waktujamu));
                                    updatejamu(new RequestUpdateJamu(tanggalperlakuan, jumlahjamu, waktujamu));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    jamu.setText("");
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
    }
    private void b9(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("RO2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateRO2 requestUpdateRO2 = dataSnapshot.getValue(RequestUpdateRO2.class);
                String datatanggal = requestUpdateRO2.getTanggalro2();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pro2.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pro2.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahro2 = ro2.getText().toString();
                                    String wakturo2 = (String) tro2.getSelectedItem();
                                    dataro2(new RequestRO2(tanggalperlakuan, jumlahro2, wakturo2));
                                    updatero2(new RequestUpdateRO2(tanggalperlakuan, jumlahro2, wakturo2));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    ro2.setText("");
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
    }
    private void b10(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Virkon").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateVirkon requestUpdateVirkon = dataSnapshot.getValue(RequestUpdateVirkon.class);
                String datatanggal = requestUpdateVirkon.getTanggalvirkon();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pvirkon.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        svirkon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pvirkon.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahvirkon = virkon.getText().toString();
                                    String waktuvirkon = (String) tvirkon.getSelectedItem();
                                    datavirkon(new RequestVirkon(tanggalperlakuan, jumlahvirkon, waktuvirkon));
                                    updatevirkon(new RequestUpdateVirkon(tanggalperlakuan, jumlahvirkon, waktuvirkon));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    virkon.setText("");
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
    }
    private void b11(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Ariakekuro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateAriakekuro requestUpdateAriakekuro = dataSnapshot.getValue(RequestUpdateAriakekuro.class);
                String datatanggal = requestUpdateAriakekuro.getTanggalariakekuro();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pariakekuro.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sariakekuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pariakekuro.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahariakekuro = ariakekuro.getText().toString();
                                    String waktuariakekuro = (String) tariakekuro.getSelectedItem();
                                    dataariakekuro(new RequestAriakekuro(tanggalperlakuan, jumlahariakekuro, waktuariakekuro));
                                    updateariakekuro(new RequestUpdateAriakekuro(tanggalperlakuan, jumlahariakekuro, waktuariakekuro));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    ariakekuro.setText("");
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
    }
    private void b12(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Kapuraktif").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateKapuraktif requestUpdateKapuraktif = dataSnapshot.getValue(RequestUpdateKapuraktif.class);
                String datatanggal = requestUpdateKapuraktif.getTanggalkapuraktif();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pkapuraktif.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        skapuraktif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pkapuraktif.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahkapuraktif = kapuraktif.getText().toString();
                                    String waktukapuraktif = (String) tkapuraktif.getSelectedItem();
                                    datakapuraktif(new RequestKapuraktif(tanggalperlakuan, jumlahkapuraktif, waktukapuraktif));
                                    updatekapuraktif(new RequestUpdateKapuraktif(tanggalperlakuan, jumlahkapuraktif, waktukapuraktif));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    kapuraktif.setText("");
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
    }
    private void b13(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Dolomit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateDolomit requestUpdateDolomit = dataSnapshot.getValue(RequestUpdateDolomit.class);
                String datatanggal = requestUpdateDolomit.getTanggaldolomit();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pdolomit.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sdolomit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pdolomit.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahdolomit = dolomit.getText().toString();
                                    String waktudolomit = (String) tdolomit.getSelectedItem();
                                    datadolomit(new RequestDolomit(tanggalperlakuan, jumlahdolomit, waktudolomit));
                                    updatedolomit(new RequestUpdateDolomit(tanggalperlakuan, jumlahdolomit, waktudolomit));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    dolomit.setText("");
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
    }
    private void b14(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Kaptan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateKaptan requestUpdateKaptan = dataSnapshot.getValue(RequestUpdateKaptan.class);
                String datatanggal = requestUpdateKaptan.getTanggalkaptan();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pkaptan.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        skaptan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pkaptan.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahkaptan = kaptan.getText().toString();
                                    String waktukaptan = (String) tkaptan.getSelectedItem();
                                    datakaptan(new RequestKaptan(tanggalperlakuan, jumlahkaptan, waktukaptan));
                                    updatekaptan(new RequestUpdateKaptan(tanggalperlakuan, jumlahkaptan, waktukaptan));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    kaptan.setText("");
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
    }
    private void b15(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Abusekam").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateAbusekam requestUpdateAbusekam = dataSnapshot.getValue(RequestUpdateAbusekam.class);
                String datatanggal = requestUpdateAbusekam.getTanggalabusekam();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pabusekam.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sabusekam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pabusekam.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahabusekam = abusekam.getText().toString();
                                    String waktuabusekam = (String) tabusekam.getSelectedItem();
                                    dataabusekam(new RequestAbusekam(tanggalperlakuan, jumlahabusekam, waktuabusekam));
                                    updateabusekam(new RequestUpdateAbusekam(tanggalperlakuan, jumlahabusekam, waktuabusekam));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    abusekam.setText("");
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
    }
    private void b16(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Artemia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateArtemia requestUpdateArtemia = dataSnapshot.getValue(RequestUpdateArtemia.class);
                String datatanggal = requestUpdateArtemia.getTanggalartemia();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    partemia.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sartemia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(partemia.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahartemia = artemia.getText().toString();
                                    String waktuartemia = (String) tartemia.getSelectedItem();
                                    dataartemia(new RequestArtemia(tanggalperlakuan, jumlahartemia, waktuartemia));
                                    updateartemia(new RequestUpdateArtemia(tanggalperlakuan, jumlahartemia, waktuartemia));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    artemia.setText("");
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
    }
    private void b17(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Molase").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateMolase requestUpdateMolase = dataSnapshot.getValue(RequestUpdateMolase.class);
                String datatanggal = requestUpdateMolase.getTanggalmolase();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pmolase.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        smolase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pmolase.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahmolase = molase.getText().toString();
                                    String waktumolase = (String) tmolase.getSelectedItem();
                                    datamolase(new RequestMolase(tanggalperlakuan, jumlahmolase, waktumolase));
                                    updatemolase(new RequestUpdateMolase(tanggalperlakuan, jumlahmolase, waktumolase));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    molase.setText("");
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
    }
    private void b18(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("UpdatePerlakuan").child("Probion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                RequestUpdateProbion requestUpdateProbion = dataSnapshot.getValue(RequestUpdateProbion.class);
                String datatanggal = requestUpdateProbion.getTanggalprobion();
                String inputantanggal = date_n;

                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(inputantanggal);
                    Date tglakhir = (Date) date.parse(datatanggal);
                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    pprobion.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sprobion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                int data = Integer.parseInt(pprobion.getText().toString());
                if (data == 0) {
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
                    AlertDialog alertDialog = alertDialogB.create();
                    alertDialog.show();
                } else  {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Pemberitahuan");
                    alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                            .setCancelable(false)
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tanggalperlakuan = date_n;
                                    String jumlahprobion = probion.getText().toString();
                                    String waktuprobion = (String) tprobion.getSelectedItem();
                                    dataprobion(new RequestProbion(tanggalperlakuan, jumlahprobion, waktuprobion));
                                    updateprobion(new RequestUpdateProbion(tanggalperlakuan, jumlahprobion, waktuprobion));
                                    Toast.makeText(v.getContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                    probion.setText("");
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
    }


}
