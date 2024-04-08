package com.example.lockup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.lockup.database.LockUpDbAssign;
import com.example.lockup.database.LockUpHelper;
import com.example.lockup.fragment.AppListFragment;
import com.example.lockup.fragment.LockedAppListFragment;
import com.example.lockup.models.AppModel;
//import com.example.lockup.utils.AppListArray;
import com.example.lockup.receivers.BroadcastForServiceStart;
import com.example.lockup.services.ServiceBroadcastReceiver;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    public static Context context;
    Toolbar toolbar;
    public static ArrayList<AppModel> appListArrayList=new ArrayList<>();
    NavigationView navigationView;
    Button all_apps_btn , locked_app_btn, renewBtn,fragmentOpen;
//    public Button addDialog=findViewById(R.id.addDialog);
    EditText search_view;
    ProgressBar progressBar;
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 100;

    BroadcastForServiceStart broadcast=new BroadcastForServiceStart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Code for navigation drawer start

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.my_tool);
        navigationView = findViewById(R.id.nav_view);
        all_apps_btn=findViewById(R.id.all_app_btn);
        locked_app_btn=findViewById(R.id.locked_app_btn);
        search_view=findViewById(R.id.search_app);
        renewBtn=findViewById(R.id.renewBtn);
        progressBar=findViewById(R.id.progress);
        fragmentOpen=findViewById(R.id.frag);
//        addDialog=findViewById(R.id.addDialog);

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

//        startActivities(new Intent[]{new Intent(ACTION_ACCESSIBILITY_SETTINGS)});

        progressBar.setVisibility(View.VISIBLE);






     /*   IntentFilter filter=new IntentFilter();
        filter.addAction("RestartService");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(broadcast,filter,RECEIVER_EXPORTED);
        }*/

        fragmentOpen.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent("RestartService"));

                // Get FragmentManager
//                Intent ser=new Intent(MainActivity.this, AppLaunchService.class);
//                Intent ser=new Intent(MainActivity.this, ServiceForGettingPkg.class);
               /* Context context1=MainActivity.this;
                Intent serviceIntent = new Intent(context1, ServiceBroadcastReceiver.class);
                context1.startService(serviceIntent);*/
               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context1.startForegroundService(serviceIntent);
                } else {
                    context1.startService(serviceIntent);
                }*/
//                sendBroadcast(new Intent("RestartService"));

//                ser.putExtra("dialog",R.id.lock_screen);

//                startService(ser);

            }
        });
        AppListFragment appListFragment = new AppListFragment();
        LockedAppListFragment lockedAppListFragment = new LockedAppListFragment();
        new Thread(() -> {
            // Load essential data from the database asynchronously
            if (fatchFromDb().size()<1){
                addAppsToDb();
                appListArrayList=fatchFromDb();
            }else {

                appListArrayList=fatchFromDb();
            }

            // Update UI on the main thread with the loaded data
            runOnUiThread(() -> {
                // Initialize fragments



                // Set initial fragment
                setFragment(appListFragment);
                progressBar.setVisibility(View.GONE);
            });
        }).start();

//        addAppsToDb();
renewBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
        addAppsToDb();
        appListArrayList=fatchFromDb();
//        AppListFragment.appModelArrayList=appListArrayList;
progressBar.setVisibility(View.GONE);

        Toast.makeText(MainActivity.this, "End", Toast.LENGTH_SHORT).show();
    }
});

/*
//         Code to retrive the list of application installed in my phone

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = (PackageManager) getApplicationContext().getPackageManager();

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent,0);


        for(ResolveInfo resolveInfo : appList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            Drawable appIcon=resolveInfo.loadIcon(packageManager);
            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;

            appListArrayList.add(new AppModel(appName,appIcon,packageName));
//            Log.d("App_Pack", packageName+"  :  "+activityName);
        }
//       End of Code to retrive the list of application installed in my phone
*/


        // Retrieve the ArrayList<AppModel> from the Intent
      /*  Intent intent = getIntent();
        appListArrayList = intent.getParcelableArrayListExtra("appList");*/

// Now you have the appListArrayList in the OtherActivity
// You can use it as needed








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



       /* // Initialize fragments
        AppListFragment appListFragment = new AppListFragment();
        LockedAppListFragment lockedAppListFragment = new LockedAppListFragment();

        // Set initial fragment
        setFragment(appListFragment);*/

        // Set click listeners for buttons
        all_apps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setFragment(appListFragment);
// To filter and show only unlocked apps
//                AppListFragment.filter(false);
                AppListFragment.removeFilter();
                all_apps_btn.setBackgroundColor(getResources().getColor(R.color.blue));
                locked_app_btn.setBackgroundColor(getResources().getColor(R.color.btn_color));
            }
        });
        Dialog dialog=new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.app_layout);

        locked_app_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To filter and show only locked apps
                AppListFragment.filterLocked(true);
//                setFragment(lockedAppListFragment);
                all_apps_btn.setBackgroundColor(getResources().getColor(R.color.btn_color));
                locked_app_btn.setBackgroundColor(getResources().getColor(R.color.blue));

            }
        });

        search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AppListFragment.filter(s.toString());


            }

            @Override
            public void afterTextChanged(Editable s) {
//                    filter(s.toString());
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    // for Fragment call and replace

   // Method to set fragment in the fragment container
   private void setFragment(Fragment fragment) {
       getSupportFragmentManager().beginTransaction()
               .replace(R.id.main_fragment_container, fragment)
               .commit();
   }


   private  ArrayList<AppModel> fatchFromDb(){
    ArrayList<AppModel>arrayList=new ArrayList<>();
       LockUpHelper lockUpHelper=LockUpHelper.getDb(getApplicationContext());

       for (int i=0;i<lockUpHelper.lockUpDao().getAllApps().size();i++){
       String app_Name=lockUpHelper.lockUpDao().getAllApps().get(i).getAppName();
       String appPackage=lockUpHelper.lockUpDao().getAllApps().get(i).getPackageName();
       Drawable appIcon= getIconDrawableFromPackageName(appPackage);
           boolean appbool;
       if (lockUpHelper.lockUpDao().getAllApps().get(i).isaBoolean()){
            appbool=lockUpHelper.lockUpDao().getAllApps().get(i).isaBoolean();
       }else {
           appbool=false;
           }

       arrayList.add(new AppModel(appIcon,app_Name,appPackage,appbool));

       }
return arrayList;
   }
    public Drawable getIconDrawableFromPackageName(String packageName) {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            return appInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // Handle exception if package name is not found
            return null;
        }
    }


public void addAppsToDb() {
    //         Code to retrive the list of application installed in my phone
    LockUpHelper lockUpHelper=LockUpHelper.getDb(getApplicationContext());

    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    PackageManager packageManager = (PackageManager) getApplicationContext().getPackageManager();

    List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent,0);
    List<String> installedPackageNames = new ArrayList<>();
    List<String> packageNamesInDatabase = new ArrayList<>();
    List<LockUpDbAssign> appsInDatabase = lockUpHelper.lockUpDao().getAllApps();
    for(ResolveInfo resolveInfo : appList) {
        String appName = resolveInfo.loadLabel(packageManager).toString();
        Drawable appIcon=resolveInfo.loadIcon(packageManager);
        String packageName = resolveInfo.activityInfo.packageName;
        String activityName = resolveInfo.activityInfo.name;
//        packageNamesInDatabase.add(packageName);
        Log.d("bigestpgesack", packageName);
        installedPackageNames.add(packageName);
        int count = lockUpHelper.lockUpDao().countAppsByPackageName(packageName);
        if (count==0){
            lockUpHelper.lockUpDao().addApp(
                    new LockUpDbAssign(appName,packageName,false)
            );
        }




//        appListArrayList.add(new AppModel(appName,appIcon,packageName));
//            Log.d("App_Pack", packageName+"  :  "+activityName);
    }
    // Retrieve the list of installed package names on the device
//    List<String> installedPackageNames = new ArrayList<>();
/*    int i=0;
    for (ResolveInfo resolveInfo : appList) {

        installedPackageNames.add(resolveInfo.activityInfo.packageName);
        Log.d("installedPackageNames",i+" "+resolveInfo.activityInfo.packageName );
        i++;
    }*/
    // Retrieve the list of package names stored in the database
//    List<String> packageNamesInDatabase = new ArrayList<>();
//    List<LockUpDbAssign> appsInDatabase = lockUpHelper.lockUpDao().getAllApps();
    int a=0;
    for (LockUpDbAssign app : appsInDatabase) {

        packageNamesInDatabase.add(app.getPackageName());
        Log.d("packageNamesInDatabase",a+" "+app.getPackageName() );
        a++;

    }

    // Compare installed apps with apps in the database
    for (LockUpDbAssign app : appsInDatabase) {
        String packageName = app.getPackageName();
        if (!installedPackageNames.contains(packageName)) {
            // If the app is not installed, delete it from the database
            lockUpHelper.lockUpDao().deleteAppByPackageName(packageName);
        }
    }

//       End of Code to retrive the list of application installed in my phone
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

}