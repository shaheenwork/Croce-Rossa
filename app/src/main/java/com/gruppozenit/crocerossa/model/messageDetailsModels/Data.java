
package com.gruppozenit.crocerossa.model.messageDetailsModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("MessageDetailsList")
    @Expose
    private MessageDetailsList messageDetailsList;
    @SerializedName("adminStatus")
    @Expose
    private String adminStatus;
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
        this.messageDetailsList = ((MessageDetailsList) in.readValue((MessageDetailsList.class.getClassLoader())));
        this.adminStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public MessageDetailsList getMessageDetailsList() {
        return messageDetailsList;
    }

    public void setMessageDetailsList(MessageDetailsList messageDetailsList) {
        this.messageDetailsList = messageDetailsList;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(messageDetailsList);
        dest.writeValue(adminStatus);
    }

    public int describeContents() {
        return  0;
    }

}
