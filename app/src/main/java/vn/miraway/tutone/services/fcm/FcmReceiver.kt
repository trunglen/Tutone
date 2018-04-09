package vn.miraway.tutone.services.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class FcmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()
    }
}