package com.example.applog.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostAdapterSampling extends FirebaseRecyclerAdapter<RequestDataSampling, PostAdapterSampling.PostViewHolder> {
    private Context context;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    public PostAdapterSampling(@NonNull FirebaseRecyclerOptions<RequestDataSampling> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder postViewHolder, final int i, @NonNull RequestDataSampling requestDataSampling) {
         postViewHolder.stanggalsampling.setText(requestDataSampling.getTanggalsampling());
         postViewHolder.ssp.setText(requestDataSampling.getSp());
         postViewHolder.sjumlahtebarrata2.setText(requestDataSampling.getJumlahtebarsamplings());
         postViewHolder.smbw.setText(requestDataSampling.getMbw());
         postViewHolder.spakanperhari.setText(requestDataSampling.getPakanseharisampling());
         postViewHolder.stotalpakan.setText(requestDataSampling.getTotalpakansampling());
         postViewHolder.sfr.setText(requestDataSampling.getFr());
         postViewHolder.spopulasi.setText(requestDataSampling.getPopulasi());
         postViewHolder.sadgmingguan.setText(requestDataSampling.getAdgmingguan());
         postViewHolder.sbiomass.setText(requestDataSampling.getBiomass());
         postViewHolder.skonsumsifeed.setText(requestDataSampling.getKonsumsifeed());
         postViewHolder.sfcr.setText(requestDataSampling.getFcr());
         postViewHolder.susia.setText(requestDataSampling.getUsia());
        postViewHolder.sdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mAuth = FirebaseAuth.getInstance();
                sessions = new SharePreferences(v.getContext());
                final FirebaseUser user = mAuth.getCurrentUser();
                AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(context);
                alertdialogBuilder.setTitle("Konfirmasi");
                alertdialogBuilder.setMessage("Anda yakin ingin menghapus data ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                                        .child(sessions.getData()).child("Sampling").child(getRef(i).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context, "Data Berhasil Hapus", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertdialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_sampling, parent, false);
       return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView stanggalsampling;
        TextView seditsampling;
        TextView sdelete;
        TextView stanggaltebar;
        TextView sjumlahtebarrata2;
        TextView smbw;
        TextView spakanperhari;
        TextView stotalpakan;
        TextView stanggalpakan;
        TextView sfr;
        TextView spopulasi;
        TextView sadgmingguan;
        TextView sbiomass;
        TextView skonsumsifeed;
        TextView sfcr;
        TextView ssp;
        TextView susia;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
           stanggalsampling = itemView.findViewById(R.id.dtanggalsampling);
//            seditsampling = itemView.findViewById(R.id.editsampling);
        //    stanggaltebar = itemView.findViewById(R.id.tanggaltebar);
            sjumlahtebarrata2 =itemView.findViewById(R.id.djumlahtebarrata2);
            smbw = itemView.findViewById(R.id.dmgw);
            spakanperhari = itemView.findViewById(R.id.dpakanperhari);
            stotalpakan = itemView.findViewById(R.id.dtotolpakan);
        //   stanggalpakan = itemView.findViewById(R.id.tanggalpakan);
            sfr = itemView.findViewById(R.id.dfr);
            spopulasi =itemView.findViewById(R.id.dpopulasi);
            sadgmingguan = itemView.findViewById(R.id.dadgmingguan);
            sbiomass = itemView.findViewById(R.id.dbiomass);
            skonsumsifeed = itemView.findViewById(R.id.dkonsumsifeed);
            sfcr = itemView.findViewById(R.id.dfcr);
            sdelete = itemView.findViewById(R.id.deletesampling);
            ssp = itemView.findViewById(R.id.dsp);
            susia = itemView.findViewById(R.id.dusia);
        }
    }
}
