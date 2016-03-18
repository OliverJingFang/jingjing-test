package com.jingjing.mytest.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: KindyFung.
 * CreateTime:  2016/3/18 15:00
 * Email：fangjing@medlinker.com.
 * Description:
 */
public class TestData implements Parcelable {

    private int id;
    private String  des;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    @Override
    public String toString() {
        return "TestData{" +
                "id=" + id +
                ", des='" + des + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.des);
    }

    public TestData() {
    }

    protected TestData(Parcel in) {
        this.id = in.readInt();
        this.des = in.readString();
    }

    public static final Parcelable.Creator<TestData> CREATOR = new Parcelable.Creator<TestData>() {
        @Override
        public TestData createFromParcel(Parcel source) {
            return new TestData(source);
        }

        @Override
        public TestData[] newArray(int size) {
            return new TestData[size];
        }
    };
}
