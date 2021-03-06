package com.example.notificationandroid

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.swap.DateUtil
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor



class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAnalytics = Firebase.analytics






        bt1.setOnClickListener{
            sendCounterNotify("Android", "通知成功")

            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_ID, "id")
                param(FirebaseAnalytics.Param.ITEM_NAME, "name")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "type")
            }
        }


        btSignUp.setOnClickListener{
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP){
                param(FirebaseAnalytics.Param.METHOD, "method")
            }
        }


        btShare.setOnClickListener {
             firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE){
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "contentType")
            }

        }


    }


    @SuppressLint("NewApi")
    private fun  sendCounterNotify(title: String, message: String) {
        val cancelIntent = intentFor<MainActivity>()
        val channel = NotificationChannel("1", "test", NotificationManager.IMPORTANCE_HIGH)
        val piCancel = PendingIntent.getActivity(
            this,
            R.string.app_name,
            cancelIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(this, "1")


        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.apple);









        val notify = builder.setDeleteIntent(piCancel)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.phone)
                .setTicker("你有一個通知")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //通知音效
                .setLights(Color.RED, 1000, 1000) //燈
                .setUsesChronometer(true)

                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(this.resources, R.drawable.apple))
                        .bigLargeIcon(bitmap)
                )  //大圖通知
                 //.addAction(R.drawable.ic_launcher_background,"取消",piCancel)

                .setSubText("${DateUtil.nowDateTime}")
                .setContentTitle(title)
                .setContentText(message).build()
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyMgr.createNotificationChannel(channel)
        notifyMgr.notify(R.string.app_name, notify)

    }

}