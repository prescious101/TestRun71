package com.prototype.project.testrun71.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.prototype.project.testrun71.Model.ChildData;
import com.prototype.project.testrun71.R;

public class ChildViewAdapter extends FirestoreRecyclerAdapter<ChildData,ChildViewAdapter.ChildHolder> {

    public ChildViewAdapter(@NonNull FirestoreRecyclerOptions<ChildData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ChildHolder holder, int position, @NonNull ChildData model) {
        holder.textViewChild.setText(model.getChildName());
    }

    @NonNull
    @Override
    public ChildHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child,
                viewGroup, false);
        return new ChildHolder(v);
    }

    class ChildHolder extends RecyclerView.ViewHolder {
        TextView textViewChild;

        public ChildHolder(View itemView) {
            super(itemView);
            textViewChild = itemView.findViewById(R.id.tvChild);
        }
    }
}
