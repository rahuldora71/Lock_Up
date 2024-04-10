package com.example.lockup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lockup.adapters.ListAdapter;
import com.example.lockup.models.AppModel;
//import com.example.lockup.utils.AppListArray;
import com.example.lockup.receivers.BroadcastForServiceStart;
import com.example.lockup.utils.AppListAscendingOrderByName;
import com.example.lockup.utils.DataFromDbUtils;
import com.example.lockup.utils.DbUpdateUtils;
import com.example.lockup.utils.FiltersUtils;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    public  Context context=MainActivity.this;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Button all_apps_btn , locked_app_btn, renewBtn;
    private EditText search_view;
    private  RecyclerView recyclerView;
    public  ArrayList<AppModel> appListArrayList;
    private ListAdapter adapter;
    private ProgressBar progressBar;
    private ImageView unlock_image;
    private  boolean isloading=true;
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 100;

//private ActivityMainBinding binding;


    BroadcastForServiceStart broadcast=new BroadcastForServiceStart();


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager.requestPermissions(this);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_tool);
        navigationView = findViewById(R.id.nav_view);
        all_apps_btn=findViewById(R.id.all_app_btn);
        locked_app_btn=findViewById(R.id.locked_app_btn);
        search_view=findViewById(R.id.search_app);
        renewBtn=findViewById(R.id.renewBtn);
        progressBar=findViewById(R.id.progress);
        recyclerView =findViewById(R.id.app_list_recycler_view);
        unlock_image=findViewById(R.id.empty_view);

        all_apps_btn.setBackgroundColor(getResources().getColor(R.color.blue));
        search_view.clearFocus();

      IntentFilter filter=new IntentFilter();
        filter.addAction("RestartService");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(broadcast,filter,RECEIVER_EXPORTED);
        }else {
            registerReceiver(broadcast,filter);

        }
        if (!Settings.canDrawOverlays(this)) {
            askForOverlayPermission();
        }
        sendBroadcast(new Intent("RestartService"));

        progressBar.setVisibility(View.VISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        new Thread(() -> {
            DbUpdateUtils.synchronizeAppsInDatabase(context);
            appListArrayList=DataFromDbUtils.fatchFromDb(context, appListArrayList);
            appListArrayList=AppListAscendingOrderByName.appAscendingOrderByName(appListArrayList);
            // Update UI on the main thread with the loaded data
            runOnUiThread(() -> {
                // Initialize fragments
              adapterCall(appListArrayList);
                // Set initial fragment
             Log.d("ArraySizeDisplay", "onCreate: "+appListArrayList.size());

                progressBar.setVisibility(View.GONE);
                isloading=false;
            });
        }).start();

renewBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        isloading=true;
progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
//        adapterCall(appListArrayList=null);
            recyclerView.setVisibility(View.GONE);
        new Thread(() -> {
            // Load essential data from the database asynchronously

            appListArrayList=null;
                DbUpdateUtils.synchronizeAppsInDatabase(context);
                appListArrayList=DataFromDbUtils.fatchFromDb(context, appListArrayList);

            AppListAscendingOrderByName.appAscendingOrderByName(appListArrayList);

            // Update UI on the main thread with the loaded data
            runOnUiThread(() -> {
                // Initialize fragments
               adapterCall(appListArrayList);
                // Set initial fragment
//                setFragment(appListFragment);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                isloading=false;
                Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
            });
        }).start();


    }
});



            //Code for navigation drawer start
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Toast.makeText(this, "Running okay", Toast.LENGTH_SHORT).show();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.opt_download) {
                    Toast.makeText(MainActivity.this, "Download", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.opt_home) {
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.opt_login) {
                    Toast.makeText(MainActivity.this, "Log In", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.opt_search) {
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.opt_setting) {
                    Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });




        all_apps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renewBtn.setVisibility(View.VISIBLE);
                search_view.setEnabled(true);

                search_view.setFocusable(true);
                recyclerView.setVisibility(View.VISIBLE);
                unlock_image.setVisibility(View.GONE);
                adapterCall(appListArrayList);

                all_apps_btn.setBackgroundColor(getResources().getColor(R.color.blue));
                locked_app_btn.setBackgroundColor(getResources().getColor(R.color.btn_color));
            }
        });
        Dialog dialog=new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.app_layout);




            locked_app_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (isloading) {
                    }else {

                        renewBtn.setVisibility(View.GONE);
                        search_view.setEnabled(false);

                        if (FiltersUtils.filterLocked(true, appListArrayList).isEmpty()) {
                            unlock_image.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            Toast.makeText(context, "You don't have any locked app", Toast.LENGTH_SHORT).show();
                        }else {    adapterCall(FiltersUtils.filterLocked(true, appListArrayList));


                        }
                        all_apps_btn.setBackgroundColor(getResources().getColor(R.color.btn_color));
                        locked_app_btn.setBackgroundColor(getResources().getColor(R.color.blue));
                    }
                }
            });

        search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapterCall(FiltersUtils.filter(s.toString(),appListArrayList));


            }

            @Override
            public void afterTextChanged(Editable s) {
//                    filter(s.toString());
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            PermissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    @Override
    protected void onResume() {
        super.onResume();

    }
    private void askForOverlayPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // Permission granted
            } else {
                // Permission denied
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DbUpdateUtils.synchronizeAppsInDatabase(context);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }
    private void adapterCall(ArrayList<AppModel> appListArrayList){
        adapter = new ListAdapter(context, appListArrayList);
        recyclerView.setAdapter(adapter);

    }
    public void customToast(Context context, String message, Drawable icon){
        View view=LayoutInflater.from(context).inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_layout),false );

        Toast toast=new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(LayoutInflater.from(context).inflate(R.layout.custom_toast, (ViewGroup) view,false ));
        toast.show();

        TextView textView=view.findViewById(R.id.toast_text);
        textView.setText(message);

        ImageView imageView=view.findViewById(R.id.toast_image);
        imageView.setImageDrawable(icon);
    }

}