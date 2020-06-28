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

public class PostAdapterPanen extends FirebaseRecyclerAdapter<RequestDataPanen, PostAdapterPanen.PostViewHolder> {
    private Context context;
    private DatabaseReference mDatabase;
    SharePreferences sessions;
    private FirebaseAuth mAuth;
    public PostAdapterPanen(@NonNull FirebaseRecyclerOptions<RequestDataPanen> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder postViewHolder, final int i, @NonNull RequestDataPanen requestDataPanen) {
    postViewHolder.tanggalpanen.setText(requestDataPanen.getTanggalpanen());
    postViewHolder.doc.setText(requestDataPanen.getDoc());
    postViewHolder.tonase.setText(requestDataPanen.getTonase());
    postViewHolder.abw.setText(requestDataPanen.getAbw());
    postViewHolder.size.setText(requestDataPanen.getSize());
    postViewHolder.populasipanen.setText(requestDataPanen.getPopulasipanen());
    postViewHolder.tonha.setText(requestDataPanen.getTonha());
    postViewHolder.totalpopulasi.setText(requestDataPanen.getTotalpopulasi());
    postViewHolder.panentotal.setText(requestDataPanen.getPanentotal());
    postViewHolder.totalsr.setText(requestDataPanen.getTotalsr());
    postViewHolder.totalpakan.setText(requestDataPanen.getTotalpakan());
    postViewHolder.fcrtotal.setText(requestDataPanen.getFcrtotal());
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
                                    .child(sessions.getData()).child("Panen").child(getRef(i).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
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
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_panen, parent, false);
        return new PostAdapterPanen.PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
    TextView tanggalpanen;
    TextView doc;
    TextView tonase;
    TextView abw;
    TextView size;
    TextView populasipanen;
    TextView tonha;
    TextView totalpopulasi;
    TextView panentotal;
    TextView totalsr;
    TextView totalpakan;
    TextView fcrtotal;
    TextView edit;
    TextView delete;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        tanggalpanen = itemView.findViewById(R.id.tanggalanpanen);
        doc = itemView.findViewById(R.id.ddoc);
        tonase = itemView.findViewById(R.id.dtonase);
        abw = itemView.findViewById(R.id.dabw);
        size = itemView.findViewById(R.id.dsize);
        populasipanen = itemView.findViewById(R.id.dpopulasipanen);
        tonha = itemView.findViewById(R.id.dton);
        totalpopulasi = itemView.findViewById(R.id.dtotalpopulasi);
        panentotal = itemView.findViewById(R.id.dpanentotal);
        totalsr = itemView.findViewById(R.id.dtotalsr);
        totalpakan = itemView.findViewById(R.id.dtotalpakan);
        fcrtotal = itemView.findViewById(R.id.dfcrtotal);
        edit = itemView.findViewById(R.id.editpanen);
        delete = itemView.findViewById(R.id.deletepanen);
    }
}
}
