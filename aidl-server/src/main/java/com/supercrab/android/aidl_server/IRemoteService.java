package com.supercrab.android.aidl_server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
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
    private static final String TAG = "IRemoteService";
    private static final String PACKAGE_SAYHI = "com.supercrab.android.aidl_client";
    private ArrayList<Person> persons;

    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return mBinder;
    }

    private IBinder mBinder = new IRemoteAidl.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d(TAG, "服务已开启" + num1 + "   " + num2);
            return num1 * num2;
        }

        @Override
        public List<Person> addPerson(Person p) throws RemoteException {
            persons.add(p);
            return persons;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {

            String packageName = null;
            String[] packages = IRemoteService.this.getPackageManager().
                    getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.d(TAG, "onTransact: " + packageName);
            if (!PACKAGE_SAYHI.equals(packageName)) {
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };
}