/*
package com.example.lockup.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lockup.R;

public class CustomToast {
    public  void customToast(Context context, String message, Drawable icon){
        View view=LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        ViewGroup viewGroup= view.findViewById(R.id.toast_layout);

    Toast toast=new Toast(context);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setView(LayoutInflater.from(context).inflate(R.layout.custom_toast, viewGroup ));
    toast.show();

    TextView textView=view.findViewById(R.id.toast_text);
    textView.setText(message);
    ImageView imageView=view.findViewById(R.id.toast_image);
    imageView.setImageDrawable(icon);
    }
}
*/
