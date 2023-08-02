package com.sujue.aidltest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.sujue.aidllearn.IMyAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MAINACTIVITY"
    //private lateinit var myInterface: IMyAidlInterface
    private var myInterface: IMyAidlInterface? = null
    // 绑定到服务
    private var serviceConnection :ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                myInterface = IMyAidlInterface.Stub.asInterface(iBinder)
                Log.d(TAG, "Service has connected")
            }

            override fun onServiceDisconnected(componentName: ComponentName) {
                Log.d(TAG, "Service has unexpectedly disconnected")
                // 服务断开连接
                //myInterface = null
            }
        }
        //通过bindService启动服务
        val intent = Intent()
        intent.component = ComponentName("com.sujue.aidllearn", "com.sujue.aidllearn.MyAidlService")
        bindService(intent, serviceConnection as ServiceConnection, Context.BIND_AUTO_CREATE)


        addBtn.setOnClickListener {
            myInterface?.doSomething()
            val result = myInterface?.calculateSum(1,5)
            addResult.text = result.toString()
        }
    }
}
