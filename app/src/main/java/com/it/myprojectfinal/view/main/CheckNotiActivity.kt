package com.it.myprojectfinal.view.main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.ResponseCheckNoti
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.ui.notifications.PresenterFragment

class CheckNotiActivity : AppCompatActivity() {

    lateinit var mPreferrences : Preferrences
    val checknoti = PresenterFragment()
    val deleteCheck = MainPresenter()
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var Builer: Notification.Builder
    private val ChannelID = "com.example.join_sport_app.ui.home"
    private val description = "notification Join Sport"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_noti)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        setapi()
    }

    private fun setapi() {
       mPreferrences = Preferrences(this)
        val status = "ผู้ใช้ทั่วไป"
        checknoti.GetCheckNoti(
            mPreferrences.getUserId().toString(),
            status,
            {
                for (i in it.results.indices){
                showNotificationStadium(it.results[i].message,it.results[i].nt_id,it.results[i].locality)

                deleteCheck.DeleteCheckPersenterRx(
                    it.results[i].nt_id,
                    {

                    },
                    {

                    }
                )
                }
            },
            {
                finish()
            })
    }

    private fun showNotificationStadium(message: String, nftId: Int,noticID:String){
        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("notic_id",noticID)
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(ChannelID,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
            Builer = Notification.Builder(this,ChannelID)
                .setContentTitle("ศูนย์กลางช่วยเหลือผู้ประเหตุสาธารณภัย")
                .setContentText("สถานะการเเจ้งเหตุของคุณ :"+message)
                .setSmallIcon(R.drawable.bell)
//                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.stadium))
                .setContentIntent(pendingIntent)

        }
        else{
            Builer = Notification.Builder(this)
                .setContentTitle("ศูนย์กลางช่วยเหลือผู้ประเหตุสาธารณภัย")
                .setContentText("สถานะการเเจ้งเหตุของคุณ :"+message)
                .setSmallIcon(R.drawable.bell)
//                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.stadium))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1000,Builer.build())
//        setAPINotificationDelete(nftId)\
        finish()
    }
}

