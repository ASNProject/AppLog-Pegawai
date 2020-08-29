package com.example.applog.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChartPakan extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    LineChart lineChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    SharePreferences sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_pakan);
        sessions = new SharePreferences(ChartPakan.this.getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        lineChart = findViewById(R.id.chratpakan);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child("Data User").child(user.getDisplayName()).child("Database").child(sessions.getData()).child("Pakan");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataValas = new ArrayList<Entry>();

                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        RequestDataPakan requestDataPakan = dataSnapshot1.getValue(RequestDataPakan.class);
                        float xValue = Float.parseFloat(String.valueOf(requestDataPakan.getUsia()));
                        float yValue = Float.parseFloat(String.valueOf(requestDataPakan.getJumlahharian()));
                        dataValas.add(new Entry(xValue, yValue));
                    }
                    showChart(dataValas);
                }
                else {
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showChart(ArrayList<Entry> dataValas) {
        lineDataSet.setValues(dataValas);
        lineDataSet.setLabel("Jumlah harian pakan");
        iLineDataSets.clear();;
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
        lineDataSet.setDrawIcons(false);
        lineDataSet.enableDashedLine(10f, 5f, 0f);
        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet.setColor(Color.DKGRAY);
        lineDataSet.setCircleColor(Color.DKGRAY);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
    }
}