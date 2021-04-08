package com.muramsyah.myservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {

    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }

    private var mBinder = MyBinder()
    private val startTime = System.currentTimeMillis()

    private var mTimer: CountDownTimer = object : CountDownTimer(100_000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val elapsedTime = System.currentTimeMillis() - startTime
            Log.d(TAG, "onTick: $elapsedTime")
        }

        override fun onFinish() {
            TODO("Not yet implemented")
        }

    }
    /**
     * Ketika CountDownTimer dijalankan, countdown timer akan berjalan sampai 100.000 milisecond atau 100 detik.
     * Intervalnya setiap 1.000 milisecond atau 1 detik akan menampilkan log. Hitungan mundur tersebut berfungsi untuk melihat proses terikatnya kelas MyBoundService ke MainActivity.
     */

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    /**
     * Metode onCreate() dipanggil ketika memulai pembentukan kelas MyBoundService.
     */

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        mTimer.start()
        return mBinder
        TODO("Return the communication channel to the service.")
    }

    /**
     * ada metode onBind(), service akan berjalan dan diikatkan atau ditempelkan dengan activity pemanggil.
     * Pada metode ini juga, mTimer akan mulai berjalan.
     */

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        mTimer.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }

    internal inner class MyBinder : Binder() {
        val getService: MyBoundService = this@MyBoundService
    }
    /**
     * Kode di atas adalah kelas yang dipanggil di metode onServiceConnected untuk memanggil kelas service.
     * Fungsinya untuk mengikat kelas service. Kelas MyBinder yang diberi turunan kelas Binder, mempunyai fungsi
     * untuk melakukan mekanisme pemanggilan prosedur jarak jauh.
     */
}