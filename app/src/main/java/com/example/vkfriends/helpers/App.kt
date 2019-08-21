package com.example.vkfriends.helpers

import android.R
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.vkfriends.activities.LoginActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler


class App : Application() {

    private val NOTIFY_ID = 101

    private val tokenTracker = object : VKTokenExpiredHandler {
        override fun onTokenExpired() {

            Toast.makeText(applicationContext, "Сеанс авторизации истек, доступ закрыт", Toast.LENGTH_LONG).show()

            val notificationIntent = Intent(applicationContext, LoginActivity::class.java)
            val contentIntent = PendingIntent.getActivity(
                applicationContext,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )

            val res = applicationContext.getResources()

            // до версии Android 8.0 API 26
            val builder = NotificationCompat.Builder(applicationContext)

            builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_menu_add)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Авторизация")
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Истек срок действия текущей сессии авторизации") // Текст уведомления
                // необязательные настройки
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_menu_add)) // большая
                // картинка
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Последнее китайское предупреждение!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true) // автоматически закрыть уведомление после нажатия

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // Альтернативный вариант
            // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFY_ID, builder.build())
        }
    }

    override fun onCreate() {
        super.onCreate()
        VK.initialize(applicationContext)
        VK.addTokenExpiredHandler(tokenTracker)
    }

}