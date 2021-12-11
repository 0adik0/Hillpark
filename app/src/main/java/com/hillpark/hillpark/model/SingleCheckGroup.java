package com.hillpark.hillpark.model;

import android.os.Parcel;

import com.thoughtbot.expandablecheckrecyclerview.models.SingleCheckExpandableGroup;

import java.util.List;

public class SingleCheckGroup extends SingleCheckExpandableGroup {

    public SingleCheckGroup(String title, List items) {
        super(title, items);
    }

    protected SingleCheckGroup(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SingleCheckGroup> CREATOR = new Creator<SingleCheckGroup>() {
        @Override
        public SingleCheckGroup createFromParcel(Parcel in) {
            return new SingleCheckGroup(in);
        }

        @Override
        public SingleCheckGroup[] newArray(int size) {
            return new SingleCheckGroup[size];
        }
    };
}
