package com.gruppozenit.crocerossa.utils

interface Consts {
    companion object {



        val ACTION_NEW_MESSAGE = "New_Message"
        const val AUTH_KEY = "4CB3701A-CFAF-4F54-916B-F4613063F42F"
        val ACTION_ADMIN_STATUS = "ADMIN_STATUS"
        val KEY_ACCESS = "ACCESS"
        val ACCESS_APPROVED =  1
        val ACCESS_REJECTED =  0
        val ACCESS_PENDING =  -1
        val ACCESS_REMOVED =  2
        val ACCESS_NOT_SET =  4
        val KEY_MSG_DATE = "message_date"
        val KEY_DEVICE_ID ="DeviceId"
        val KEY_MSG_ID ="messaggio_id"
        val FILE = "file"
        val FILE_TYPE_PDF = 0
        val FILE_TYPE_IMAGE = 1
        val FILE_TYPE_VIDEO = 3
        val FILE_TYPE_AUDIO = 2



        //Notification
        val NOTIF_NEW_MSG = "2"
        val NOTIF_ADMIN_APPROVAL = "1"
        val NOTIF_USER_REMOVED = "3"
        val NEW_MSG_NOTIF_CHANNEL_ID = "0"
        val NEW_MSG_NOTIF_ID = 1
        val ADMIN_APPROVAL_NOTIF_ID = 2
        val NEW_MSG_NOTIF_CHANNEL_NAME = "New_Msg_channel"
        val ADMIN_APPROVAL_NOTIF_CHANNEL_ID="1"
        val ADMIN_APPROVAL_NOTIF_CHANNEL_NAME="Admin_Approval_Notification"



        //VIDEO-AUDIO PLAY
         val KEY_VIDEO_URI: String? = "video_uri"
        //Minimum Video you want to buffer while Playing
        const val MIN_BUFFER_DURATION: Int = 3000

        //Max Video you want to buffer during PlayBack
        const val MAX_BUFFER_DURATION = 5000

        //Min Video you want to buffer before start Playing it
        const val MIN_PLAYBACK_START_BUFFER = 1500

        //Min video You want to buffer when user resumes video
        const val MIN_PLAYBACK_RESUME_BUFFER = 5000


        const val TOKEN_ALREADY_EXIST_STATUS =3

        const val READ =1
        const val UN_READ =0

    }


}
