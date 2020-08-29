package com.example.applog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.applog.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterGaleri extends RecyclerView.Adapter<AdapterGaleri.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;

    public AdapterGaleri(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.galeri_item, parent, false);
       return new ImageViewHolder(view) {
       };
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload upload = mUploads.get(position);
        holder.tang.setText(upload.getmTanggal());
        holder.nama.setText(upload.getmName());
        holder.keterangan.setText(upload.getmDeskripsi());
        Picasso.get()
                .load(upload.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView tang, nama, keterangan;
        public ImageView gambar;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            tang = itemView.findViewById(R.id.tanggalgaleri);
            nama = itemView.findViewById(R.id.isinamagambar);
            keterangan = itemView.findViewById(R.id.isiketerangan);
            gambar =itemView.findViewById(R.id.gambargaleri);

        }
    }
}
