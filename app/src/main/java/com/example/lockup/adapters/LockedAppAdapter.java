package com.example.lockup.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockup.MainActivity;
import com.example.lockup.R;
import com.example.lockup.database.LockUpHelper;
import com.example.lockup.models.AppModel;

import java.util.ArrayList;

public class LockedAppAdapter extends RecyclerView.Adapter<LockedAppAdapter.ViewHolderLocked> {
Context context;
    ArrayList<AppModel> arrayListLocked;

    public LockedAppAdapter(Context context, ArrayList<AppModel> arrayListLocked) {
        this.context = context;
        this.arrayListLocked = arrayListLocked;
    }

    @NonNull
    @Override
    public LockedAppAdapter.ViewHolderLocked onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_locked_app_list,parent,false);

        ViewHolderLocked viewHolder= new ViewHolderLocked(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull LockedAppAdapter.ViewHolderLocked holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderLocked extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        SwitchCompat lockSwitch;
        CardView cardView;
        public ViewHolderLocked(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.nameView);
            imageView=itemView.findViewById(R.id.imgView);
            lockSwitch=itemView.findViewById(R.id.lock_switch);
            cardView=itemView.findViewById(R.id.cardLock);
        }
    }
}
