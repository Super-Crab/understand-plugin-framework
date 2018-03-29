package com.supercrab.android.aidl_client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.supercrab.android.aidl_server.IRemoteAidl;
import com.supercrab.android.aidl_server.Person;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity implements View.OnClickListener {
    private IRemoteAidl iRemoteAidl=null;
    private EditText etNum1,etNum2,etResult;
    private Button btnCalculate;
    private ArrayList<Person> datas;
    private TextView tv_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindService();
    }

    private void initView() {
        etNum1= (EditText) findViewById(R.id.et_num1);
        etNum2= (EditText) findViewById(R.id.et_num2);
        etResult= (EditText) findViewById(R.id.et_result);
        btnCalculate= (Button) findViewById(R.id.btn_calculate);
        tv_desc= (TextView) findViewById(R.id.tv_desc);
        btnCalculate.setOnClickListener(this);
    }

    private void bindService() {
        Intent intent=new Intent();
        intent.setComponent(new ComponentName("com.supercrab.android.aidl_server","com.supercrab.android.aidl_server.IRemoteService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);

    }


    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iRemoteAidl=   IRemoteAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iRemoteAidl=null;
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    @Override
    public void onClick(View v) {
        int num1=Integer.parseInt(etNum1.getText().toString().trim());
        int num2=Integer.parseInt(etNum2.getText().toString().trim());

        try {
            int result=iRemoteAidl.add(num1,num2);
            etResult.setText(result+"");
            datas= (ArrayList<Person>) iRemoteAidl.addPerson(new Person("小a",21));
            Iterator i=datas.iterator();
            while (i.hasNext()){
                tv_desc.setText(i.next().toString());
            }

        } catch (RemoteException e) {
            e.printStackTrace();
            etResult.setText("失败");
        }
    }
}
