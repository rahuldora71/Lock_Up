package com.example.lockup.utils;

import com.example.lockup.models.AppModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AppListAscendingOrderByName {


    public static ArrayList<AppModel> appAscendingOrderByName(ArrayList<AppModel> appList) {
        Collections.sort(appList, new Comparator<AppModel>() {
            @Override
            public int compare(AppModel appModel1, AppModel appModel2) {
                return appModel1.getNameModel().compareToIgnoreCase(appModel2.getNameModel());
            }
        });
        return appList;
    }
}
