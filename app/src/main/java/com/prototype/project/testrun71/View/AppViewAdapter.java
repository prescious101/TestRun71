package com.prototype.project.testrun71.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.prototype.project.testrun71.Model.ChildData;
import com.prototype.project.testrun71.Model.LocalData;
import com.prototype.project.testrun71.R;

import java.util.ArrayList;

public class AppViewAdapter extends FirestoreRecyclerAdapter<ChildData, AppViewAdapter.AppHolder> {

    private static final String KEY_TEXT_ARRAYLIST = "ARRAYLISTPKG";
    private static final String KEY_TEXT_ARRAYLIST1 = "ARRAYLISTNAME";
    private ArrayList<String> blockPkgName = new ArrayList<>();
    private ArrayList<String> blockAppName = new ArrayList<>();
    private Context mContext;
    private LocalData localData = new LocalData();

    public AppViewAdapter(@NonNull FirestoreRecyclerOptions<ChildData> options, Context mContext) {
        super(options);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull final AppViewAdapter.AppHolder holder, final int position, @NonNull final ChildData model) {
        holder.textViewAppName.setText(model.getChildAppName());
        holder.textViewPkgName.setText(model.getChildPkgApps());
        final String appName = model.getChildPkgApps();
        final String pkgName = model.getChildAppName();
        holder.mItem.setSelected(getItem(position).isSelected());

        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                getItem(pos).setSelected(!getItem(pos).isSelected());
                AppViewAdapter.this.notifyDataSetChanged();

                if(blockPkgName.contains(null) && blockAppName.contains(null)) {
                    blockPkgName.add("Init");
                    blockAppName.add("Init");
                } else if(!blockPkgName.contains(appName) && !blockPkgName.contains("Init")
                        && !blockAppName.contains(pkgName) && !blockAppName.contains("Init")){
                    blockPkgName.add(pkgName);
                    blockAppName.add(appName);
                    Toast.makeText(mContext, blockPkgName.toString(), Toast.LENGTH_SHORT).show();
                }else {
                    blockPkgName.remove(pkgName);
                    blockAppName.remove(appName);
                    Toast.makeText(mContext, blockPkgName.toString(), Toast.LENGTH_SHORT).show();
                }

                localData.saveArrayListPkgName(blockPkgName,KEY_TEXT_ARRAYLIST);
                localData.saveArrayListAppName(blockAppName,KEY_TEXT_ARRAYLIST1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_applist,
                viewGroup, false);
        return new AppHolder(v);
    }

    class AppHolder extends RecyclerView.ViewHolder {
        TextView textViewAppName;
        TextView textViewPkgName;
        RelativeLayout mItem;

        public AppHolder(@NonNull View itemView) {
            super(itemView);
            textViewAppName = itemView.findViewById(R.id.Apk_Name);
            textViewPkgName = itemView.findViewById(R.id.Apk_Package_Name);
            mItem = itemView.findViewById(R.id.mainLayout);
        }
    }

}
