package com.example.lockup.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lockup.MainActivity;
import com.example.lockup.R;
import com.example.lockup.adapters.ListAdapter;
import com.example.lockup.models.AppModel;
//import com.example.lockup.utils.AppListArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AppListFragment extends Fragment {

public static ArrayList<AppModel> appModelArrayList;
    public static ListAdapter adapter;
//    EditText searchEditText;
private static RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_app_list, container, false);

        if (appModelArrayList == null) {
            // Data has not been loaded yet, so load it
            appModelArrayList = MainActivity.appListArrayList;
        }
        recyclerView = view.findViewById(R.id.app_list_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Get all apps initially
            appModelArrayList = MainActivity.appListArrayList;
            Collections.sort(appModelArrayList, new Comparator<AppModel>() {
                @Override
                public int compare(AppModel appModel1, AppModel appModel2) {
                    return appModel1.getNameModel().compareToIgnoreCase(appModel2.getNameModel());
                }
            });
            // Initialize RecyclerView

            // Sort the app list

            // Set adapter
            adapter = new ListAdapter(getContext(), appModelArrayList);
            recyclerView.setAdapter(adapter);



        // Inflate the layout for this fragment
        return view;
    }
    public static void filter(String text) {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "filter: model array list is empty");
            return; // Return if the list is null
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
        adapter.filterList(filteredList);
    }

  /*  public static void filter(String text) {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "filter: model array list is empty");
            return; // Return if the list is null
        }

        ArrayList<AppModel> filteredList = new ArrayList<>();
        for (AppModel appModel : appModelArrayList) {
            if (appModel.getNameModel().toLowerCase().startsWith(text.toLowerCase())) {
                filteredList.add(appModel);
                Log.d("FreeGuy", "filter: model array list is Full");

            }
        }
        adapter.filterList(filteredList);
    }*/
    public static void filterLocked(boolean showLockedApps) {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "filter: model array list is empty");
            return; // Return if the list is null
        }

        ArrayList<AppModel> filteredList = new ArrayList<>();
        for (AppModel appModel : appModelArrayList) {
            if (appModel.isBool() == showLockedApps) {
                filteredList.add(appModel);
                Log.d("FreeGuy", "filter: model array list is Full");
            }
        }
        adapter=new ListAdapter(recyclerView.getContext(), filteredList);
        recyclerView.setAdapter(adapter);
    }
    public static void removeFilter() {
        if (appModelArrayList == null) {
            Log.d("FreeGuy", "removeFilter: model array list is empty");
            return; // Return if the list is null
        }

        // Reload the original list of apps
//        adapter.filterList(appModelArrayList);
        adapter=new ListAdapter(recyclerView.getContext(), appModelArrayList);
        recyclerView.setAdapter(adapter);
    }



}