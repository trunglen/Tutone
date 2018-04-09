package vn.miraway.tutone.services.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import vn.miraway.tutone.common.Constant


class FcmService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "message_from_fcm: " + remoteMessage!!.from!!)
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
            } else {
                // Handle message within 10 seconds
            }

        }
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)
            val intent = Intent()
            intent.action = Constant.FCM_RECEIVER
            sendBroadcast(intent)
        }
    }
}