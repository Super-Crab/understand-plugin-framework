package com.supercrab.android.aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:          peixuan.xie
 * Time:            29/03/2018 3:47 PM
 */

public class IRemoteService extends Service {
    private ArrayList<Person> persons;

    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return mBinder;
    }

    private IBinder mBinder = new IRemoteAidl.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d("TAG", "服务已开启");
            return num1 * num2;
        }

        @Override
        public List<Person> addPerson(Person p) throws RemoteException {
            persons.add(p);
            return persons;
        }
    };
}