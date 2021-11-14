package ru.gb.course1.handler_and_service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.gb.course1.handler_and_service.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "@@@@"

    private lateinit var binding: ActivityMainBinding

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(TAG, "onServiceConnected() called with: name = $name, binder = $binder")
            val myBinder = binder as MyService.MyBinder
            myBinder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected() called with: name = $name")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyService::class.java)

        binding.startButton.setOnClickListener {
            serviceIntent.putExtra("message", "ololo")
            startService(serviceIntent)
        }

        binding.stopButton.setOnClickListener {
            stopService(serviceIntent)
        }

        binding.bindButton.setOnClickListener {
            bindService(serviceIntent, connection, BIND_AUTO_CREATE)
        }
        binding.unbindButton.setOnClickListener {
            unbindService(connection)
        }
    }

}