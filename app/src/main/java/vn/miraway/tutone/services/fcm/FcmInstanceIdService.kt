package vn.miraway.tutone.services.fcm

import com.google.firebase.iid.FirebaseInstanceIdService
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId



class FcmInstanceIdService :FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "fcm_token: " + refreshedToken!!)
//        sendRegistrationToServer(refreshedToken)
    }
}