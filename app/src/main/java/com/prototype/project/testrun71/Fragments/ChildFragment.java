package com.prototype.project.testrun71.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.prototype.project.testrun71.Model.ChildData;
import com.prototype.project.testrun71.R;
import com.prototype.project.testrun71.View.ChildViewAdapter;

public class ChildFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference childkRef = db.collection("Child");

    private ChildViewAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_child, container, false);

        Query query = childkRef.orderBy("childName",Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<ChildData> options = new FirestoreRecyclerOptions.Builder<ChildData>()
                .setQuery(query, ChildData.class)
                .build();

        adapter = new ChildViewAdapter(options);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_child);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    @Override
    public  void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
