package com.gruppozenit.crocerossa.model.messageDetailsModels;

import android.os.Parcel;
import android.os.Parcelable;

public class GetMessageDetailsInput implements Parcelable {

    int messaggio_id,deviceId;

    protected GetMessageDetailsInput(Parcel in) {
        messaggio_id = in.readInt();
        deviceId = in.readInt();
    }

    public GetMessageDetailsInput() {
    }

    public static final Creator<GetMessageDetailsInput> CREATOR = new Creator<GetMessageDetailsInput>() {
        @Override
        public GetMessageDetailsInput createFromParcel(Parcel in) {
            return new GetMessageDetailsInput(in);
        }

        @Override
        public GetMessageDetailsInput[] newArray(int size) {
            return new GetMessageDetailsInput[size];
        }
    };

    public int getMessaggio_id() {
        return messaggio_id;
    }

    public void setMessaggio_id(int messaggio_id) {
        this.messaggio_id = messaggio_id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(messaggio_id);
        parcel.writeInt(deviceId);
    }
}
