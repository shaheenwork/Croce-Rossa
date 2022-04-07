
package com.gruppozenit.crocerossa.model.deviceRegModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("DeviceID")
    @Expose
    private String deviceID;

    @SerializedName("approvedStatus")
    @Expose
    private Boolean approvedStatus;

    @SerializedName("TokenStatus")
    @Expose
    private Integer TokenStatus;

    public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

    protected Data(Parcel in) {
        this.deviceID = ((String) in.readValue((String.class.getClassLoader())));
        this.approvedStatus = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.TokenStatus = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Data() {
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(deviceID);
        dest.writeValue(approvedStatus);
        dest.writeValue(TokenStatus);
    }

    public int describeContents() {
        return  0;
    }


    public Boolean getApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(Boolean approvedStatus) {
        this.approvedStatus = approvedStatus;
    }


    public Integer getTokenStatus() {
        return TokenStatus;
    }

    public void setTokenStatus(Integer tokenStatus) {
        TokenStatus = tokenStatus;
    }
}
