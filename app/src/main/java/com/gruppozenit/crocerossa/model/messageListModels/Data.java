
package com.gruppozenit.crocerossa.model.messageListModels;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("MessageList")
    @Expose
    private List<MessageList> messageList = null;
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
        in.readList(this.messageList, (com.gruppozenit.crocerossa.model.messageListModels.MessageList.class.getClassLoader()));
        this.adminStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public List<MessageList> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageList> messageList) {
        this.messageList = messageList;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(messageList);
        dest.writeValue(adminStatus);
    }

    public int describeContents() {
        return  0;
    }

}
