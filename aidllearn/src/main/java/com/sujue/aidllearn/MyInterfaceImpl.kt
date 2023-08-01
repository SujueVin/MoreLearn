package com.sujue.aidllearn

import android.os.RemoteException
import android.util.Log


class MyInterfaceImpl() : IMyAidlInterface.Stub() {
    @Throws(RemoteException::class)
    override fun doSomething() {
        // 执行一些操作
        Log.d("AIDL","hello")
    }

    @Throws(RemoteException::class)
    override fun calculateSum(a: Int, b: Int): Int {
        return a + b
    }

    @Throws(RemoteException::class)
    override fun concatenateStrings(str1: String, str2: String): String? {
        return str1 + str2
    }


}