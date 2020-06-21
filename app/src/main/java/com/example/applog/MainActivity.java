package com.example.applog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.applog.Activity.Login;
import com.example.applog.SharePreference.SharePreferences;

public class MainActivity extends AppCompatActivity {
    private TextView cleartext;
    private Button btnclear;
    private EditText isiclear;
    SharePreferences sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cleartext = findViewById(R.id.cleartext);
        btnclear = findViewById(R.id.clear);
        isiclear = findViewById(R.id.asal);
        sessions = new SharePreferences(MainActivity.this.getApplicationContext());
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clears = isiclear.getText().toString();
                sessions.setPassword(clears);
                sessions.setEmail(clears);
            }
        });
    }
}
