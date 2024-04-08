package com.example.lockup.models;

import android.graphics.drawable.Drawable;

public class AppModel {
    Drawable imageModel;
    String nameModel;
    String packageNameModel;
    boolean bool;

    public AppModel(Drawable imageModel, String nameModel, String packageNameModel, boolean bool, boolean isFiltered) {
        this.imageModel = imageModel;
        this.nameModel = nameModel;
        this.packageNameModel = packageNameModel;
        this.bool = bool;
        this.isFiltered = isFiltered;
    }

    boolean isFiltered; // Add a flag to indicate whether the item is filtered or not

    public boolean isFiltered() {
        return isFiltered;
    }

    public void setFiltered(boolean filtered) {
        isFiltered = filtered;
    }


    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }



    public AppModel(String nameModel, String packageNameModel, boolean bool) {
        this.nameModel = nameModel;
        this.packageNameModel = packageNameModel;
        this.bool = bool;
    }

    public AppModel(Drawable imageModel, String nameModel, String packageNameModel, boolean bool) {
        this.imageModel = imageModel;
        this.nameModel = nameModel;
        this.packageNameModel = packageNameModel;
        this.bool = bool;
    }

    public AppModel(String nameModel, Drawable imageModel, String packageNameModel) {
        this.imageModel = imageModel;
        this.nameModel = nameModel;
        this.packageNameModel = packageNameModel;
    }



    public String getPackageNameModel() {
        return packageNameModel;
    }

    public void setPackageNameModel(String packageNameModel) {
        this.packageNameModel = packageNameModel;
    }

    public AppModel() {
    }

    public Drawable getImageModel() {
        return imageModel;
    }

    public void setImageModel(Drawable imageModel) {
        this.imageModel = imageModel;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public AppModel(String textView, Drawable imageView) {
        this.imageModel = imageView;
        this.nameModel = textView;
    }


}
/*


import android.os.Parcel;
import android.os.Parcelable;

public class AppModel implements Parcelable {
    String imageModel; // Store image path or identifier here
    String nameModel;

    public AppModel() {
    }

    public String getImageModel() {
        return imageModel;
    }

    public void setImageModel(String imageModel) {
        this.imageModel = imageModel;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public AppModel(String nameModel, String imageModel) {
        this.nameModel = nameModel;
        this.imageModel = imageModel;
    }

    // Parcelable implementation
    protected AppModel(Parcel in) {
        imageModel = in.readString();
        nameModel = in.readString();
    }

    public static final Parcelable.Creator<AppModel> CREATOR = new Creator<AppModel>() {
        @Override
        public AppModel createFromParcel(Parcel in) {
            return new AppModel(in);
        }

        @Override
        public AppModel[] newArray(int size) {
            return new AppModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageModel);
        dest.writeString(nameModel);
    }
}




*/




