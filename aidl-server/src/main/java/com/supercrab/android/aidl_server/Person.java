package com.supercrab.android.aidl_server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description:
 * Author:          peixuan.xie
 * Time:            29/03/2018 3:44 PM
 */

public class Person implements Parcelable {
    private String name;
    private int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    protected Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}