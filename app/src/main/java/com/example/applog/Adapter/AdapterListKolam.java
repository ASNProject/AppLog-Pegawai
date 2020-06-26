package com.example.applog.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applog.Activity.Dashboard;
import com.example.applog.Activity.MenuInput;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterListKolam extends RecyclerView.Adapter<AdapterListKolam.ListViewHolder> {

    Context context;
    ArrayList<Request_Data_Kolam> request_data_kolams;
    SharePreferences sessions;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    public AdapterListKolam(Context c,ArrayList<Request_Data_Kolam> p){
        context = c;
        request_data_kolams = p;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_kolam, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.namakolam.setText(request_data_kolams.get(position).getNamakolam());
        holder.pilihkolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data =  holder.namakolam.getText().toString();
                sessions = new SharePreferences(context.getApplicationContext());
                sessions.setData(data);

                Intent i = new Intent(context.getApplicationContext(), MenuInput.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                ((Dashboard)context).finish();


            }
        });
        holder.deletkolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data =  holder.namakolam.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                final FirebaseUser user = mAuth.getCurrentUser();
                mDatabase = FirebaseDatabase.getInstance().getReference();
                AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(context);
                alertdialogBuilder.setTitle("Konfirmasi");
                alertdialogBuilder.setMessage("Anda yakin ingin menghapus data ini?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                                        .child(data).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent i = new Intent(context.getApplicationContext(), Dashboard.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
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
    @Override
    public int getItemCount() {
        return request_data_kolams.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView namakolam;
        Button pilihkolam, deletkolam;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            namakolam = (TextView) itemView.findViewById(R.id.onamakolam);
            pilihkolam = (Button) itemView.findViewById(R.id.buttonpilih);
            deletkolam = (Button) itemView.findViewById(R.id.buttondeletekolam);
        }
    }
}
