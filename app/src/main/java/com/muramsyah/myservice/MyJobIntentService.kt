package com.muramsyah.myservice

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

/**
 * saat kelas ini dijalankan, Service akan melakukan pemrosesan obyek intent yang dikirimkan
 * dan menjalankan suatu proses yang berjalan di background.
 */

// Extend kelas ke kelas JobIntentService
class MyJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 1000
        internal const val EXTRA_DURATION = "extra_duration"
        private val TAG = MyJobIntentService::class.java.simpleName

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork: Mulai.....")
        val duration = intent.getLongExtra(EXTRA_DURATION, 0)
        try {
            Thread.sleep(duration)
            Log.d(TAG, "onHandleWork: Selesai.....")
        } catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
        /***
         * Metode ini dijalankan pada thread terpisah secara asynchronous.
         * IntentService tak perlu mematikan dirinya sendiri. service ini akan berhenti dengan sendirinya ketika sudah selesai menyelesaikan tugasnya.
         */
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

}