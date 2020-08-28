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

public class PostAdapterPerlakuan extends FirebaseRecyclerAdapter<RequestDataPerlakuan, PostAdapterPerlakuan.PostViewHolder> {
    private Context context;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    public PostAdapterPerlakuan(@NonNull FirebaseRecyclerOptions<RequestDataPerlakuan> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder postViewHolder, final int i, @NonNull RequestDataPerlakuan requestDataPerlakuan) {
       /* postViewHolder.tanggalperlakuan.setText(requestDataPerlakuan.getTanggalperlakuan());
        postViewHolder.perlakuan.setText(requestDataPerlakuan.getPerlakuan());
        postViewHolder.delete.setOnClickListener(new View.OnClickListener() {
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
                                        .child(sessions.getData()).child("Perlakuan").child(getRef(i).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        });*/
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_perlakuan, parent, false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
        TextView tanggalperlakuan;
        TextView edit;
        TextView delete;
        TextView perlakuan;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggalperlakuan = itemView.findViewById(R.id.tanggalanperlakuan);
            edit = itemView.findViewById(R.id.editperlakuan);
            delete = itemView.findViewById(R.id.deleteperlakuan);
            perlakuan = itemView.findViewById(R.id.dperlakuan);
        }
    }
}
