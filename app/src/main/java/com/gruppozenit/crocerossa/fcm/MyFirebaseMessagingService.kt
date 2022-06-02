package com.gruppozenit.crocerossa.fcm

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gruppozenit.crocerossa.MainActivity
import com.gruppozenit.crocerossa.login.LoginActivity
import com.gruppozenit.crocerossa.login.LoginWaitActivity
import com.gruppozenit.crocerossa.utils.Consts
import com.gruppozenit.crocerossa.utils.PrefManager
import com.gruppozenit.crocerossa.utils.Utils

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private var prefManager: PrefManager? = null


    override fun onMessageReceived(p0: RemoteMessage) {
        val (messagesListActivitypendingIntent, loginActivitypendingIntent, loginWaitActivityPendingIntent) = init()

        manageNotifications(
            p0,
            messagesListActivitypendingIntent,
            loginWaitActivityPendingIntent,
            loginActivitypendingIntent
        )

    }

    private fun manageNotifications(
        remoteMessage: RemoteMessage?,
        messagesListActivitypendingIntent: PendingIntent,
        loginWaitActivityPendingIntent: PendingIntent,
        loginActivitypendingIntent: PendingIntent
    ) {
        if (remoteMessage!!.data != null) {

            val title = remoteMessage.data.getValue("Title")
            val body = remoteMessage.data.getValue("Body")
            val type = remoteMessage.data.getValue("Type")

            when (type) {
                Consts.NOTIF_NEW_MSG -> {

                    if (prefManager!!.notificationONorOFF) {
                        val notificationBuilder = Utils.createNotificationBuilder(
                            this,
                            Consts.NEW_MSG_NOTIF_CHANNEL_ID,
                            body!!,
                            title!!
                        )
                        notificationBuilder.setContentIntent(messagesListActivitypendingIntent)
                        val notificationManager = NotificationManagerCompat.from(this)
                        notificationManager.notify(
                            Consts.NEW_MSG_NOTIF_ID,
                            notificationBuilder.build()
                        )
                    }

                    sendNewMessageBroadcast()

                }
                Consts.NOTIF_ADMIN_APPROVAL -> {

                    val access = remoteMessage.data.getValue("Access")

                    prefManager!!.setIsApproved(access.toInt())

                    when {
                        access.toInt() == Consts.ACCESS_APPROVED -> {
                            prefManager!!.isLogin = true
                            if (prefManager!!.notificationONorOFF) {
                                val notificationBuilder = Utils.createNotificationBuilder(
                                    this,
                                    Consts.ADMIN_APPROVAL_NOTIF_CHANNEL_ID,
                                    body!!,
                                    title!!
                                )
                                notificationBuilder.setContentIntent(
                                    messagesListActivitypendingIntent
                                )
                                val notificationManager = NotificationManagerCompat.from(this)
                                notificationManager.notify(
                                    Consts.ADMIN_APPROVAL_NOTIF_ID,
                                    notificationBuilder.build()
                                )
                            }
                        }
                        access.toInt() == Consts.ACCESS_PENDING -> {
                            prefManager!!.isLogin = true
                            if (prefManager!!.notificationONorOFF) {
                                val notificationBuilder = Utils.createNotificationBuilder(
                                    this,
                                    Consts.ADMIN_APPROVAL_NOTIF_CHANNEL_ID,
                                    body!!,
                                    title!!
                                )
                                notificationBuilder.setContentIntent(loginWaitActivityPendingIntent)
                                val notificationManager = NotificationManagerCompat.from(this)
                                notificationManager.notify(
                                    Consts.ADMIN_APPROVAL_NOTIF_ID,
                                    notificationBuilder.build()
                                )
                            }

                        }
                        access.toInt() == Consts.ACCESS_REJECTED -> {
                            Utils.clearUserDetails(prefManager!!)
                            prefManager!!.setDeviceId(0)
                            if (prefManager!!.notificationONorOFF) {
                                val notificationBuilder = Utils.createNotificationBuilder(
                                    this,
                                    Consts.ADMIN_APPROVAL_NOTIF_CHANNEL_ID,
                                    body!!,
                                    title!!
                                )
                                notificationBuilder.setContentIntent(loginActivitypendingIntent)
                                val notificationManager = NotificationManagerCompat.from(this)
                                notificationManager.notify(
                                    Consts.ADMIN_APPROVAL_NOTIF_ID,
                                    notificationBuilder.build()
                                )
                            }

                        }
                    }

                    sendAdminStatusBroadcast(access.toInt())

                }
                Consts.NOTIF_USER_REMOVED -> {

                    sendAdminStatusBroadcast(Consts.ACCESS_REMOVED)
                    Utils.clearUserDetails(prefManager!!)
                    prefManager!!.setDeviceId(0)
                    if (prefManager!!.notificationONorOFF) {
                        val notificationBuilder = Utils.createNotificationBuilder(
                            this,
                            Consts.ADMIN_APPROVAL_NOTIF_CHANNEL_ID,
                            body!!,
                            title!!
                        )
                        notificationBuilder.setContentIntent(loginActivitypendingIntent)
                        val notificationManager = NotificationManagerCompat.from(this)
                        notificationManager.notify(
                            Consts.ADMIN_APPROVAL_NOTIF_ID,
                            notificationBuilder.build()
                        )
                    }

                }
            }


        }
    }


    /* private fun sendBroadcast(access: Int)
     {
         val intent = Intent()
         intent.action = Consts.ACTION_ADMIN_STATUS
         intent.putExtra(Consts.KEY_ACCESS, access)
         sendBroadcast(intent)
     }*/


    private fun sendAdminStatusBroadcast(access: Int) {
        try {
            val broadCastIntent = Intent()
            broadCastIntent.putExtra(Consts.KEY_ACCESS, access)
            broadCastIntent.action = Consts.ACTION_ADMIN_STATUS

            sendBroadcast(broadCastIntent)

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun sendNewMessageBroadcast() {
        try {
            val broadCastIntent = Intent()
            broadCastIntent.action = Consts.ACTION_NEW_MESSAGE

            sendBroadcast(broadCastIntent)

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }


    private fun init(): Triple<PendingIntent, PendingIntent, PendingIntent> {
        prefManager = PrefManager.getInstance(this)

        val maintActivityIntent = Intent(this, MainActivity::class.java)
        val loginActivityIntent = Intent(this, LoginActivity::class.java)
        val loginWaitActivityIntent = Intent(this, LoginWaitActivity::class.java)
        maintActivityIntent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        loginActivityIntent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        loginWaitActivityIntent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val messagesListActivitypendingIntent =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getActivity(
                    this,
                    0,
                    maintActivityIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            } else {
                PendingIntent.getActivity(
                    this,
                    0, maintActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT
                )

            }
        val loginActivitypendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this,
                0,
                loginActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                0, loginActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

        }
        val loginWaitActivityPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this,
                0,
                loginWaitActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                0, loginWaitActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )

        }


        return Triple(
            messagesListActivitypendingIntent,
            loginActivitypendingIntent,
            loginWaitActivityPendingIntent
        )
    }

    override fun onNewToken(token: String) {
        prefManager = PrefManager.getInstance(this)
        prefManager!!.fcmToken = token

    }
}