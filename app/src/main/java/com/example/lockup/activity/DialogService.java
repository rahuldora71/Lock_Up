package com.example.lockup.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lockup.R;

public class DialogService {

    public static void showDialog(Context context) {
        // Create a dialog instance
        Dialog dialog = new Dialog(context);
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.lock_screen,null,false);
        dialog.setContentView(view);

        // Initialize dialog components
//        TextView dialogTitle = dialog.findViewById(R.id.dialog_title);
//        Button dialogButton = dialog.findViewById(R.id.dialog_button);

        // Set title
//        dialogTitle.setText(title);

        // Set button click listener to dismiss the dialog
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        // Show the dialog
        dialog.show();
    }
}
