package com.example.lockup.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lockup.MainActivity;
import com.example.lockup.R;
import com.example.lockup.adapters.ListAdapter;
import com.example.lockup.adapters.LockedAppAdapter;
import com.example.lockup.database.LockUpHelper;
import com.example.lockup.models.AppModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*

public class LockedAppListFragment extends Fragment {

//    MainActivity mainActivity=new MainActivity();
    RecyclerView recyclerView;
    ListAdapter lockedAppAdapter;
    ArrayList<AppModel> arrayLockedList=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_locked_app_list, container, false);
        // Inflate the layout for this fragment

        LockUpHelper lockUpHelper=LockUpHelper.getDb(getContext());
        ArrayList<AppModel> arrayLockedList=new ArrayList<>();

        for (int i=0;i<lockUpHelper.lockUpDao().getAllApps().size();i++){
            String app_Name=lockUpHelper.lockUpDao().getAllApps().get(i).getAppName();
            String appPackage=lockUpHelper.lockUpDao().getAllApps().get(i).getPackageName();
            Drawable appIcon= getIconDrawableFromPackageName(appPackage);

            boolean appbool=lockUpHelper.lockUpDao().getAllApps().get(i).isaBoolean();
            if (appbool){
                arrayLockedList.add(new AppModel(appIcon,app_Name,appPackage,appbool));
            }

        }
        Collections.sort(arrayLockedList, new Comparator<AppModel>() {
            @Override
            public int compare(AppModel appModel1, AppModel appModel2) {
                return appModel1.getNameModel().compareToIgnoreCase(appModel2.getNameModel());
            }
        });

        recyclerView = view.findViewById(R.id.locked_app_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lockedAppAdapter= new ListAdapter(getContext(),arrayLockedList);
        recyclerView.setAdapter(lockedAppAdapter);
        return view;
    }
    public Drawable getIconDrawableFromPackageName(String packageName) {
        try {
            PackageManager packageManager = getContext().getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            return appInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // Handle exception if package name is not found
            return null;
        }
    }


}*/
public class LockedAppListFragment extends Fragment {

    RecyclerView recyclerView;
    ListAdapter lockedAppAdapter;
    ArrayList<AppModel> arrayLockedList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_locked_app_list, container, false);
        recyclerView = view.findViewById(R.id.locked_app_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update data every time the fragment is resumed
        updateLockedAppList();
    }

    private void updateLockedAppList() {
        LockUpHelper lockUpHelper = LockUpHelper.getDb(getContext());
        arrayLockedList.clear(); // Clear previous data
        for (int i = 0; i < lockUpHelper.lockUpDao().getAllApps().size(); i++) {
            String app_Name = lockUpHelper.lockUpDao().getAllApps().get(i).getAppName();
            String appPackage = lockUpHelper.lockUpDao().getAllApps().get(i).getPackageName();
            Drawable appIcon = getIconDrawableFromPackageName(appPackage);
            boolean appbool = lockUpHelper.lockUpDao().getAllApps().get(i).isaBoolean();
            if (appbool) {
                arrayLockedList.add(new AppModel(appIcon, app_Name, appPackage, appbool));
            }
        }
        // Sort the list
        Collections.sort(arrayLockedList, new Comparator<AppModel>() {
            @Override
            public int compare(AppModel appModel1, AppModel appModel2) {
                return appModel1.getNameModel().compareToIgnoreCase(appModel2.getNameModel());
            }
        });
        // Set the adapter with the updated data
        if (lockedAppAdapter == null) {
            lockedAppAdapter = new ListAdapter(getContext(), arrayLockedList);
            recyclerView.setAdapter(lockedAppAdapter);
        } else {
            lockedAppAdapter.notifyDataSetChanged();
        }
    }

    public Drawable getIconDrawableFromPackageName(String packageName) {
        try {
            PackageManager packageManager = getContext().getPackageManager();
            ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, 0);
            return appInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // Handle exception if package name is not found
            return null;
        }
    }
}
