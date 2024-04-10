package com.example.lockup.utils;

import android.util.Log;

import com.example.lockup.adapters.ListAdapter;
import com.example.lockup.models.AppModel;

import java.util.ArrayList;

public class FiltersUtils {
    public void setAppModelArrayList(ArrayList<AppModel> appModelArrayList) {
        this.appModelArrayList = appModelArrayList;
    }

    ArrayList<AppModel> appModelArrayList=new ArrayList<>();

    public FiltersUtils(ArrayList<AppModel> appModelArrayList) {
        this.appModelArrayList = appModelArrayList;
    }

    //    public
    public static ArrayList<AppModel> filter(String text,ArrayList<AppModel> appModelArrayList) {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "filter: model array list is empty");
            return null; // Return if the list is null
        }

        ArrayList<AppModel> filteredList = new ArrayList<>();
        for (AppModel appModel : appModelArrayList) {
            if (appModel.getNameModel().toLowerCase().startsWith(text.toLowerCase())) {
                filteredList.add(appModel);
                appModel.setFiltered(true); // Set the flag to indicate that this item is filtered
                Log.d("FreeGuy", "filter: model array list is Full");
            } else {
                appModel.setFiltered(false); // Set the flag to indicate that this item is not filtered
            }
        }
//        adapter.filterList(filteredList);
        return  filteredList;
    }
    public static ArrayList<AppModel> filterLocked(boolean showLockedApps, ArrayList<AppModel> appModelArrayList) {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "filter: model array list is empty");
            return null; // Return if the list is null
        }

        ArrayList<AppModel> filteredList = new ArrayList<>();
        for (AppModel appModel : appModelArrayList) {
            if (appModel.isBool() == showLockedApps) {
                filteredList.add(appModel);
                Log.d("FreeGuy", "filter: model array list is Full");
            }
        }/*
        adapter=new ListAdapter(recyclerView.getContext(), filteredList);
        recyclerView.setAdapter(adapter);*/
        return filteredList;
    }
    public static ArrayList<AppModel> removeFilter(ArrayList<AppModel> appModelArrayList) {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "removeFilter: model array list is empty");
            return null; // Return if the list is null
        }

        // Reload the original list of apps
//        adapter.filterList(appModelArrayList);
       /* adapter=new ListAdapter(recyclerView.getContext(), appModelArrayList);
        recyclerView.setAdapter(adapter);*/
        return appModelArrayList;
    }


}
