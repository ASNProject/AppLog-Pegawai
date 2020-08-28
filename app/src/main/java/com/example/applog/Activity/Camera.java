package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.applog.Adapter.Upload;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import pl.aprilapps.easyphotopicker.EasyImage;

public class Camera extends AppCompatActivity {
    private Button btnSimpan;
    private ImageView imageView;
    private EditText nama, tanggal, deskripsi;
    public static final int REQUEST_CODE_CAMERA = 001;
    public static final int REQUEST_CODE_GALLERY = 002;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private Uri mImageUri;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    SharePreferences sessions;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        sessions = new SharePreferences(Camera.this.getApplicationContext());


        btnSimpan = (Button) findViewById(R.id.ambilgambars);
        imageView = (ImageView) findViewById(R.id.takephoto);
        nama = (EditText) findViewById(R.id.namafile);
        tanggal = (EditText) findViewById(R.id.tanggalgambar);
        deskripsi = (EditText) findViewById(R.id.keterangangambar);
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tanggal.setText(date_n);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData()).child("uploads");
        RequestImages();
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            uploadFile();
            }
        });


    }

    private void RequestImages() {
        CharSequence[] item = {"Kamera", "Galeri"};
        AlertDialog.Builder request = new AlertDialog.Builder(this)
                .setTitle("Ambil dari:")
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                EasyImage.openCamera(Camera.this, REQUEST_CODE_CAMERA);
                                break;
                            case 1:
                                EasyImage.openGallery(Camera.this, REQUEST_CODE_GALLERY);
                                break;
                        }
                    }
                });
        request.create();
        request.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type){
                    case REQUEST_CODE_CAMERA:
                        mImageUri = data.getData();
                        Glide.with(Camera.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                        break;

                    case REQUEST_CODE_GALLERY:
                        mImageUri = data.getData();
                        Glide.with(Camera.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                        break;
                }
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {

            }
        });

    }
        private String getFileExtentions(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtentions(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            }, 5000);
                            Toast.makeText(Camera.this, "Upload Berhasil", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                           // Upload upload = new Upload(nama.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                            Upload upload = new Upload(nama.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(), tanggal.getText().toString(), deskripsi.getText().toString());
                            String uploadId = mDatabase.push().getKey();
                            mDatabase.child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Camera.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            //double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                           // mProgressBar.setProgess(int) progress);
                            progressDialog = new ProgressDialog(Camera.this);
                            progressDialog.setMessage("Mengupload Data");
                            progressDialog.setIndeterminate(false);
                            progressDialog.setCancelable(true);
                            progressDialog.show();
                        }
                    });
        }
        else {
            Toast.makeText(this, "Tidak ada gambar", Toast.LENGTH_SHORT).show();
        }
    }


}


