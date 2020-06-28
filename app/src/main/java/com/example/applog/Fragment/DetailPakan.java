package com.example.applog.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.applog.Adapter.PostAdapterAir;
import com.example.applog.Adapter.PostAdapterPakan;
import com.example.applog.Adapter.RequestDataAir;
import com.example.applog.Adapter.RequestDataPakan;
import com.example.applog.Adapter.RequestDataPanen;
import com.example.applog.R;
import com.example.applog.SharePreference.SharePreferences;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DetailPakan extends Fragment {
    private DatabaseReference mDatabase;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private FirebaseAuth mAuth;
    private PostAdapterPakan adapterPakan;
    SharePreferences sessions;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        sessions = new SharePreferences(this.getActivity());
        FirebaseUser user = mAuth.getCurrentUser();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_pakan, container, false);
        mDatabase.child("Data User").child(user.getDisplayName()).child("Database")
                .child(sessions.getData()).child("Pakan");
        mRecycler = (RecyclerView)view.findViewById(R.id.list_detai_pakan);
        mRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FirebaseRecyclerOptions<RequestDataPakan> options =
                new FirebaseRecyclerOptions.Builder<RequestDataPakan>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Data User").child(user.getDisplayName()).child("Database")
                                .child(sessions.getData()).child("Pakan"), RequestDataPakan.class)
                        .build();
        adapterPakan = new PostAdapterPakan(options, view.getContext());
        mRecycler.setAdapter(adapterPakan);

        mManager = new LinearLayoutManager(view.getContext());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (adapterPakan != null) {
            adapterPakan.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapterPakan != null) {
            adapterPakan.stopListening();
        }
    }
}
