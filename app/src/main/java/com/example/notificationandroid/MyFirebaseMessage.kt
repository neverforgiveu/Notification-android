package com.example.notificationandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.swap.DateUtil
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.jetbrains.anko.intentFor

class MyFirebaseMessage : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("Firebase", "newtoken$token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(msg: RemoteMessage) {
        super.onMessageReceived(msg)
        msg?.notification?.body?.let { Log.e("firebase message", it) }
        msg.notification?.title?.let { msg.notification?.body?.let { it1 -> notify(it, it1) } }     //用remoteMessage . notification . title/body  取得字串資料，剩下就是一直alt+enter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun notify(title: String , message: String){
        val cancelIntent = intentFor<MainActivity>()
        val channel = NotificationChannel("1", "test", NotificationManager.IMPORTANCE_HIGH)
        val piCancel = PendingIntent.getActivity(this, R.string.app_name, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, "1")


        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.apple);

        val notify = builder.setDeleteIntent(piCancel)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.phone)
            .setTicker("你有一個通知")
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //通知音效
            .setLights(Color.RED, 1000, 1000) //燈
            .setUsesChronometer(true)
            .setSubText("${DateUtil.nowDateTime}")
            .setContentTitle(title)
            .setContentText(message).build()
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.createNotificationChannel(channel)
        notifyMgr.notify(R.string.app_name, notify)

    }


}
