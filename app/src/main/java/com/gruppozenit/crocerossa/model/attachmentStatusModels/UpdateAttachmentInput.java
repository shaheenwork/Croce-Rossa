package com.gruppozenit.crocerossa.model.attachmentStatusModels;

public class UpdateAttachmentInput {

    Integer DeviceID,Messaggio_Details_ID;


    public Integer getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(Integer deviceID) {
        DeviceID = deviceID;
    }

    public Integer getMessaggio_Details_ID() {
        return Messaggio_Details_ID;
    }

    public void setMessaggio_Details_ID(Integer messaggio_Details_ID) {
        Messaggio_Details_ID = messaggio_Details_ID;
    }

    public UpdateAttachmentInput(Integer deviceID, Integer messaggio_Details_ID) {
        DeviceID = deviceID;
        Messaggio_Details_ID = messaggio_Details_ID;
    }
}
