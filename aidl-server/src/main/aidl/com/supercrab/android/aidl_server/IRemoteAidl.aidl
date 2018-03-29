// IRemoteAidl.aidl
package com.supercrab.android.aidl_server;

import com.supercrab.android.aidl_server.Person;

interface IRemoteAidl {
   int add(int num1,int num2);//计算2个数之和
     //传参时除了Java基本类型以及String，CharSequence之外的类型
       //都需要在前面加上定向tag，具体加什么量需而定
   List<Person>addPerson(in Person p);//添加一个parcelable
}