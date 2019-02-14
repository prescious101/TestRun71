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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.prototype.project.testrun71.Model.ChildData;
import com.prototype.project.testrun71.Model.LocalData;
import com.prototype.project.testrun71.R;
import com.prototype.project.testrun71.View.AppViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppFragment extends Fragment {

    private static final String KEY_TEXT_ARRAYLIST = "ARRAYLISTPKG";
    private static final String KEY_TEXT_ARRAYLIST1 = "ARRAYLISTNAME";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference appRef = db.collection("eric");
    private AppViewAdapter adapter;
    private LocalData sharedPref = new LocalData();
    private ArrayList<String> pkgName = new ArrayList<>();
    private ArrayList<String> appName = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apps, container, false);

        Button button = (Button) rootView.findViewById(R.id.btnBlock);
        Query query = appRef.orderBy("childName", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<ChildData> options = new FirestoreRecyclerOptions.Builder<ChildData>()
                .setQuery(query, ChildData.class)
                .build();

        adapter = new AppViewAdapter(options,getContext());

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_apps);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false));
        recyclerView.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pkgName = sharedPref.getArrayListPkgName(KEY_TEXT_ARRAYLIST);
                appName = sharedPref.getArrayListAppName(KEY_TEXT_ARRAYLIST1);

                Map<String, Object> oof = new HashMap<>();
                for (int i = 0 ; i < pkgName.size(); i++){
                    oof.put("childPkgName",pkgName.get(i));
                    oof.put("childAppName",appName.get(i));
                    db.collection("Block Apps of Fred").document(pkgName.get(i)).set(oof);
                }
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
