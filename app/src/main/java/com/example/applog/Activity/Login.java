package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private TextView register;
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button daftar;
    SharePreferences sessions;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        email = findViewById(R.id.iemaillogin);
        password = findViewById(R.id.ipasswordlogin);
        daftar = findViewById(R.id.buttonlogin);
        register = findViewById(R.id.masukregister);

        sessions = new SharePreferences(Login.this.getApplicationContext());
        String gemail = sessions.getEmail();
        String gpassword = sessions.getPassword();
        email.setText(gemail);
        password.setText(gpassword);

        if (email.getText().toString().length() > 0 && password.getText().toString().length() > 0){
           Intent i = new Intent(Login.this, Dashboard.class);
           startActivity(i);
           finish();
        }
        else if ((email.getText().toString().length()==0) && (password.getText().toString().length()==0)){
            daftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String statusemail = email.getText().toString();
                    String setpassword = password.getText().toString();

                    if (statusemail.equals("")){
                        email.setError("Masukkan alamat email");
                        email.requestFocus();
                        return;
                    }
                    else if (setpassword.equals("")){
                        password.setError("Masukkan password");
                        password.requestFocus();
                        return;
                    }
                    else {
                        mAuth.signInWithEmailAndPassword(statusemail, setpassword)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            Intent i = new Intent(Login.this, Dashboard.class);
                                            startActivity(i);
                                            finish();
                                            String Email = String.valueOf(email.getText());
                                            String Password = String.valueOf(password.getText());
                                            sessions.setEmail(Email);
                                            sessions.setPassword(Password);
                                            finish();
                                            progressDialog.dismiss();
                                        }
                                        else {
                                            Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Memuat Data");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            });
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                finish();
            }
        });
    }
}
