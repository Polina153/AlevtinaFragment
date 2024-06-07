package com.example.alevtinafragment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class City  implements Parcelable {

    private final int imageIndex;
    private final String cityName;

    public City(int imageIndex, String cityName) {
        this.imageIndex = imageIndex;
        this.cityName = cityName;
    }

    protected City(Parcel in) {
        imageIndex = in.readInt();
        cityName = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public int getImageIndex() {
        return imageIndex;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(imageIndex);
        parcel.writeString(cityName);
    }
}

