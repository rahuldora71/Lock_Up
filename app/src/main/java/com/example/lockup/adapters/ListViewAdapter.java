/*
package com.example.lockup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.lockup.R;
import com.example.lockup.database.LockUpHelper;
import com.example.lockup.models.AppModel;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<AppModel> {


    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<AppModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // Inflate the layout file for the item
        View view = LayoutInflater.from(getContext()).inflate(R.layout.app_layout, parent, false);

        // Get the views from the layout file
        TextView textView = view.findViewById(R.id.nameView);
        ImageView imageView = view.findViewById(R.id.imgView);
        SwitchCompat lockSwitch = view.findViewById(R.id.lock_switch);

        // Get the current item
        AppModel currentItem = getItem(position);

        // Set the data for the item
        textView.setText(currentItem.getNameModel());
        imageView.setImageDrawable(currentItem.getImageModel());
        lockSwitch.setChecked(currentItem.isBool());

        // Set the onClickListener for the lock switch
        lockSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LockUpHelper lockUpHelper = LockUpHelper.getDb(getContext());
                if (isChecked){
                    currentItem.setBool(isChecked);
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(currentItem.getPackageNameModel(), isChecked);
                    Toast.makeText(getContext(), currentItem.getNameModel()+" is locked", Toast.LENGTH_SHORT).show();
                } else if (!isChecked) {
                    currentItem.setBool(isChecked);
                    lockUpHelper.lockUpDao().updateBooleanValueByPackageName(currentItem.getPackageNameModel(), isChecked);
                    Toast.makeText(getContext(), currentItem.getNameModel()+" is Unlocked", Toast.LENGTH_SHORT).show();
                }
            }
            });

        return view;
    }
}
*/
