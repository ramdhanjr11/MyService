package com.muramsyah.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException
/***
 * Logika ketika menggunakan service
 * 1. Menekan tombol ui
 * 2. Menjalankan service melalui intent, startService
 * Lalu setelah logika diatas dijalankan maka metode onStartCommand pada MyService akan dijalankan
 */
class MyService : Service() {

    companion object {
        internal val TAG = MyService::class.java.simpleName
    }

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onBind(intent: Intent): IBinder {
       throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service dijalankan...")
        serviceScope.launch {
            delay(3000)
            stopSelf()
            Log.d(TAG, "Service dihentikan")
        }
        return START_STICKY
        /***
         * Pada metode tersebut kita menjalankan sebuah background process untuk melakukan simulasi proses yang sulit. Dan ia berjalan secara asynchonous
         * START_STICKY = Menandakan bahwa bila service tersebut dimatikan oleh sistem android karena kekurangan memori, ia akan diciptakan kembali jika sudah ada memori yang bisa digunakan.
         * stopSelf() = berfungsi untuk memberhentikan atau mematikan MyService dari sistem android
         */
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "onDestroy: ")
    }
}