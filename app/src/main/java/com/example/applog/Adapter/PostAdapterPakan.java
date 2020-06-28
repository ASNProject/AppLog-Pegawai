package com.example.applog.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class PostAdapterPakan extends FirebaseRecyclerAdapter<RequestDataPakan, PostAdapterPakan.PostViewHolder> {
    private Context context;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    public PostAdapterPakan(@NonNull FirebaseRecyclerOptions<RequestDataPakan> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder postViewHolder, final int i, @NonNull RequestDataPakan requestDataPakan) {
        postViewHolder.stanggalpakan.setText(requestDataPakan.getTanggalpakan());
        postViewHolder.skodepakan.setText(requestDataPakan.getKodepakan());
        postViewHolder.sjam6.setText(requestDataPakan.getJam6());
        postViewHolder.sjam10.setText(requestDataPakan.getJam10());
        postViewHolder.sjam14.setText(requestDataPakan.getJam14());
        postViewHolder.sjam18.setText(requestDataPakan.getJam18());
        postViewHolder.sjam22.setText(requestDataPakan.getJam22());
        postViewHolder.stotalharianpakan.setText(requestDataPakan.getJumlahharian());
        postViewHolder.stotalpakansemua.setText(requestDataPakan.getJumlahtotal());
        postViewHolder.sketeranganpakan.setText(requestDataPakan.getKeteranganpakan());
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
                                        .child(sessions.getData()).child("Pakan").child(getRef(i).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
                .inflate(R.layout.item_detail_pakan, parent, false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView stanggalpakan;
        TextView seditpakan;
        TextView sdelete;
        TextView skodepakan;
        TextView sjam6;
        TextView sjam10;
        TextView sjam14;
        TextView sjam18;
        TextView sjam22;
        TextView stotalharianpakan;
        TextView stotalpakansemua;
        TextView sketeranganpakan;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            stanggalpakan = itemView.findViewById(R.id.tanggalanpakan);
            seditpakan = itemView.findViewById(R.id.editpakan);
            skodepakan = itemView.findViewById(R.id.kodepakan);
            sjam6 = itemView.findViewById(R.id.jam6);
            sjam10 = itemView.findViewById(R.id.jam10);
            sjam14 = itemView.findViewById(R.id.jam14);
            sjam18 = itemView.findViewById(R.id.jam18);
            sjam22 = itemView.findViewById(R.id.jam22);
            stotalharianpakan = itemView.findViewById(R.id.totalharianpakan);
            stotalpakansemua = itemView.findViewById(R.id.totalpakansemua);
            sketeranganpakan = itemView.findViewById(R.id.keteranganpakan);
            sdelete = itemView.findViewById(R.id.deletepakan);

        }
    }
}
