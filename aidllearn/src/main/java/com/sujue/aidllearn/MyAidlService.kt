package com.sujue.aidllearn

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log

class MyAidlService : Service() {
    override fun onBind(p0: Intent?): IBinder {
        return iBinder
    }
    private val iBinder = object : IMyAidlInterface.Stub() {
        @Throws(RemoteException::class)
        override fun doSomething() {
            // 执行一些操作
            Log.d("AIDL","hello doSomething")
        }

        @Throws(RemoteException::class)
        override fun calculateSum(a: Int, b: Int): Int {
            Log.d("AIDL","hello add")
            return a + b + Data.get()
        }

        @Throws(RemoteException::class)
        override fun concatenateStrings(str1: String, str2: String): String? {
            return str1 + str2
        }
    }
}