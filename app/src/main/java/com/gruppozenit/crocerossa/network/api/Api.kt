package com.zenith.eteam.chronology.chronology1.network.api

import com.gruppozenit.crocerossa.model.attachmentListModels.GetAttachmentListResponse
import com.gruppozenit.crocerossa.model.attachmentStatusModels.UpdateAttachmentInput
import com.gruppozenit.crocerossa.model.attachmentStatusModels.AttachmentUpdateResponse
import com.gruppozenit.crocerossa.model.deviceRegModels.DeviceRegInput
import com.gruppozenit.crocerossa.model.deviceRegModels.DeviceRegResponse
import com.gruppozenit.crocerossa.model.messageDetailsModels.GetMessageDetailsInput
import com.gruppozenit.crocerossa.model.messageDetailsModels.GetMessageDetailsResponse
import com.gruppozenit.crocerossa.model.messageListModels.GetMessageListResponse
import com.gruppozenit.crocerossa.model.soceitaRuoloListModels.GetSocietaRuoloListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.HashMap

interface Api {

    @POST("RegisterDevice")
    fun mRegisterDevice(@Body deviceRegInput: DeviceRegInput): Call<DeviceRegResponse>

    @POST("UpdateAttachmentReadStatus")
    fun mUpdateAttachmentReadStatus(@Header("device_token") header: String, @Body deviceRegInput: UpdateAttachmentInput): Call<AttachmentUpdateResponse>

    @POST("GetMessageDetails")
    fun mGetMessageDetails(@Header("device_token") header: String, @Body getMessageDetailsInput: GetMessageDetailsInput): Call<GetMessageDetailsResponse>

    @POST("GetMessageList")
    fun mGetMessages(@Header("device_token") header: String, @Body queryMap: HashMap<String, Int>): Call<GetMessageListResponse>

    @POST("GetAttachmentList")
    fun mGetAttachmentList(@Header("device_token") header: String, @Body queryMap: HashMap<String, Int>): Call<GetAttachmentListResponse>

    @POST("GetSocietaAndRuoloList")
    fun mGetSocietaAndRuoloList(@Header("auth_key") header: String): Call<GetSocietaRuoloListResponse>


}
