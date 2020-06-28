package com.example.applog.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applog.Activity.Dashboard;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostAdapterAir extends FirebaseRecyclerAdapter<RequestDataAir, PostAdapterAir.PostViewBolder > {
    private Context context;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;

    public PostAdapterAir(@NonNull FirebaseRecyclerOptions<RequestDataAir> options, Context context) {
        super(options);
        this.context = context;

    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewBolder postViewBolder, final int i, @NonNull RequestDataAir requestDataAir) {
        postViewBolder.stanggal.setText(requestDataAir.getTanggalair());
        postViewBolder.stinggiair.setText(requestDataAir.getTinggiair());
        postViewBolder.sdopagi.setText(requestDataAir.getDopagi());
        postViewBolder.sdomalam.setText(requestDataAir.getDomalam());
        postViewBolder.sphpgi.setText(requestDataAir.getPhpagi());
        postViewBolder.sphmalm.setText(requestDataAir.getPhmalam());
        postViewBolder.ssuhu.setText(requestDataAir.getSuhu());
        postViewBolder.sca.setText(requestDataAir.getCas());
        postViewBolder.smg.setText(requestDataAir.getMg());
        postViewBolder.sno2.setText(requestDataAir.getNo2());
        postViewBolder.sno3.setText(requestDataAir.getNo3());
        postViewBolder.snh3.setText(requestDataAir.getNh3());
        postViewBolder.sdelete.setOnClickListener(new View.OnClickListener() {
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
                                        .child(sessions.getData()).child("Air").child(getRef(i).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //     Intent i = new Intent(context.getApplicationContext(), Dashboard.class);
                                        //   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        //   context.startActivity(i);
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
    public PostViewBolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_air, parent, false);
        return new PostViewBolder(view);
    }

    class PostViewBolder extends RecyclerView.ViewHolder{
        TextView stanggal;
        TextView sedit;
        TextView sdelete;
        TextView stinggiair;
        TextView sdopagi;
        TextView sdomalam;
        TextView sphpgi;
        TextView sphmalm;
        TextView ssuhu;
        TextView sca;
        TextView smg;
        TextView sno2;
        TextView sno3;
        TextView snh3;
        TextView salkalinitas;

        public PostViewBolder(@NonNull View itemView) {
            super(itemView);
            stanggal = itemView.findViewById(R.id.tanggalan);
            sedit = itemView.findViewById(R.id.edit);
            sdelete = itemView.findViewById(R.id.delete);
            stinggiair = itemView.findViewById(R.id.tinggiair);
            sdopagi = itemView.findViewById(R.id.dopagi);
            sdomalam = itemView.findViewById(R.id.domalam);
            sphpgi = itemView.findViewById(R.id.phpagi);
            sphmalm = itemView.findViewById(R.id.phsore);
            ssuhu = itemView.findViewById(R.id.suuhu);
            sca =itemView.findViewById(R.id.ca);
            smg = itemView.findViewById(R.id.mg);
            sno2 = itemView.findViewById(R.id.no2);
            sno3 =itemView.findViewById(R.id.no3);
            snh3 = itemView.findViewById(R.id.nh3);
            salkalinitas = itemView.findViewById(R.id.alkalinitas);
        }
    }
}
