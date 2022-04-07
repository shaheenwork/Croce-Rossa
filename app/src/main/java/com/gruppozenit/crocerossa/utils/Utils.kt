package com.gruppozenit.crocerossa.utils

import android.app.Notification.DEFAULT_SOUND
import android.app.Notification.DEFAULT_VIBRATE
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.gruppozenit.crocerossa.R
import org.apache.commons.io.FileUtils
import java.io.File

object Utils {

    private var isConnectedNetwork: Boolean = false

    fun createNotificationBuilder(context: Context, notificationChannel_id: String, Message: String, title: String): NotificationCompat.Builder {
        val picture = Utils.decodeResource(context.resources, R.drawable.logo1, 64, 64)
        return NotificationCompat.Builder(context, notificationChannel_id)
              //  .setLargeIcon(picture)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(context.resources.getColor(R.color.colorPrimaryDark))
                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentText(Message)
                .setDefaults(DEFAULT_SOUND or DEFAULT_VIBRATE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
    }


    fun decodeResource(res: Resources, resId: Int,
                       reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    private fun calculateInSampleSize(
            options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }


     fun isInternetAvailable(context: Context): Boolean {
        isConnectedNetwork = ConnectivityReceiver.isConnected
         if (!isConnectedNetwork){
             Toast.makeText(context,context.getString(R.string.no_internet_connection),Toast.LENGTH_LONG).show()
         }
        return isConnectedNetwork
    }

    fun deleteAllOldFiles(path:String) {
        try {
            val dir = File(path)
            FileUtils.deleteDirectory(dir)
        } catch (e: Exception) {
        }

    }

    fun clearUserDetails(prefManager: PrefManager){
        prefManager.isLogin=false
        prefManager.nome=""
        prefManager.societa=""
        prefManager.ruolo=""
        prefManager.cognome=""
    }

}
