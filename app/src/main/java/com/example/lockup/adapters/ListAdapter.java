package com.example.lockup.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockup.R;
import com.example.lockup.database.LockUpDbAssign;
import com.example.lockup.database.LockUpHelper;
import com.example.lockup.fragment.AppListFragment;
import com.example.lockup.models.AppModel;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Context context;
    ArrayList<AppModel> arrayList;

    public ListAdapter(Context context, ArrayList<AppModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.app_layout,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int p=position;
        AppModel currentItem = arrayList.get(p);
//        Uri uri = Uri.parse(arrayList.get(position).getImageModel());
//        holder.imageView.setImageURI(uri);
        holder.textView.setText(arrayList.get(position).getNameModel());
        holder.imageView.setImageDrawable(arrayList.get(position).getImageModel());
        holder.lockSwitch.setChecked(arrayList.get(p).isBool());
//        LockUpHelper lockUpHelper=LockUpHelper.getDb(context);
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.lockSwitch.isChecked()){
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(arrayList.get(p).getPackageNameModel(),false);
                    holder.lockSwitch.setChecked(false);
                }else if (!holder.lockSwitch.isChecked()){
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(arrayList.get(p).getPackageNameModel(),true);
                    holder.lockSwitch.setChecked(true);
                }

            }
        });*/
        // Set OnClickListener on the card view
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LockUpHelper lockUpHelper = LockUpHelper.getDb(context);
                boolean newCheckedState = !currentItem.isBool(); // Toggle the state
                currentItem.setBool(newCheckedState); // Update the state in the model
                holder.lockSwitch.setChecked(newCheckedState); // Update the switch state in the UI
                // Update the database
                lockUpHelper.lockUpDao().updateBooleanValueByPackageName(currentItem.getPackageNameModel(), newCheckedState);
            }
        });
        holder.lockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LockUpHelper lockUpHelper = LockUpHelper.getDb(context);
                if (isChecked){
                    currentItem.setBool(isChecked);
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(currentItem.getPackageNameModel(), isChecked);
                } else if (!isChecked) {
                    currentItem.setBool(isChecked);
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(currentItem.getPackageNameModel(), isChecked);
                }
            }
        });
      /*  holder.lockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked != currentItem.isBool()) {
                    LockUpHelper lockUpHelper = LockUpHelper.getDb(context);
                    currentItem.setBool(isChecked); // Update the state in the model
                    // Update the database
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(currentItem.getPackageNameModel(), isChecked);
                }
            }
        });*/


       /* holder.lockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Check if a record with the same package name already exists
//                int count = lockUpHelper.lockUpDao().countAppsByPackageName(arrayList.get(p).getPackageNameModel());
                if (isChecked) {
                    lockUpHelper.lockUpDao().addApp(
                            new LockUpDbAssign(arrayList.get(p).getNameModel(),arrayList.get(p).getPackageNameModel())
                    );
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(arrayList.get(p).getPackageNameModel(),true);
                }


                else if (!isChecked){
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(arrayList.get(p).getPackageNameModel(),false);

//                    lockUpHelper.lockUpDao().deleteApp(arrayList.get(p).getPackageNameModel()

                            new LockUpDbAssign(arrayList.get(p).getPackageNameModel().toString())

//                    );
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        SwitchCompat lockSwitch;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.nameView);
            imageView=itemView.findViewById(R.id.imgView);
            lockSwitch=itemView.findViewById(R.id.lock_switch);
            cardView=itemView.findViewById(R.id.cardLock);

        }
    }
    // Method to filter the app list
    public void filterList(ArrayList<AppModel> filteredList) {
        arrayList = filteredList;
        notifyDataSetChanged();
    }


}
