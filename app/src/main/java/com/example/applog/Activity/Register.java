package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applog.R;
import com.example.applog.Adapter.Request_Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailregister, usernameregister,
            passwordregister, alamatregister,
            notelfonregister;
    private Button register;
    private String sPid, sPnama, sPemail, sPalamat;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailregister = findViewById(R.id.iemailregister);
        usernameregister = findViewById(R.id.iusernmaeregister);
        passwordregister = findViewById(R.id.ipasswordregister);
        alamatregister = findViewById(R.id.ialamtregister);
        notelfonregister = findViewById(R.id.inotelfonregister);
        register = findViewById(R.id.buttonregister);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailregister.getText().toString();
                final String username = usernameregister.getText().toString();
                String password = passwordregister.getText().toString();
                String alamat = alamatregister.getText().toString();
                String notelp = notelfonregister.getText().toString();

                if (email.equals("")){
                    emailregister.setError("Masukkan alamat email");
                    emailregister.requestFocus();
                    return;
                }
                if (username.equals("")){
                    usernameregister.setError("Masukkan username");
                    usernameregister.requestFocus();
                    return;
                }
                if (password.equals("")){
                    passwordregister.setError("Masukkan password");
                    passwordregister.requestFocus();
                    return;
                }
                if (alamat.equals("")){
                    alamatregister.setError("Masukkan alamat");
                    alamatregister.requestFocus();
                    return;
                }
                if (notelp.equals("")){
                    notelfonregister.setError("Masukkan nomor telfon");
                    notelfonregister.requestFocus();
                    return;
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    if (user !=null){
                                        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(username)
                                                .build();

                                        user.updateProfile(profile)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            progressDialog.dismiss();
                                                            Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                                                            Intent i = new Intent(Register.this, Login.class);
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                    }
                                                });
                                    }
                                }
                                else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                        Toast.makeText(getApplicationContext(), "Akun sudah terdaftar", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(Register.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                }
                            });
                    submitUser(new Request_Register(email, username, password, alamat, notelp));
                    progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setMessage("Menyimpan Data");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            }
        });

    }

    private void submitUser(Request_Register request_register) {
        String username = usernameregister.getText().toString();
        mDatabase.child("Data User")
                .child(username)
                .setValue(request_register)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        emailregister.setText("");
                        usernameregister.setText("");
                        passwordregister.setText("");
                        alamatregister.setText("");
                        notelfonregister.setText("");
                    }
                });
    }
}
