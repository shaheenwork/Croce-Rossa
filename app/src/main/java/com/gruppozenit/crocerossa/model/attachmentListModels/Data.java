
package com.gruppozenit.crocerossa.model.attachmentListModels;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("AttachmentList")
    @Expose
    private List<AttachmentList> attachmentList = null;
    @SerializedName("adminStatus")
    @Expose
    private String adminStatus;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


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
    private final static long serialVersionUID = -1713852264803518064L;

    protected Data(Parcel in) {
        in.readList(this.attachmentList, (AttachmentList.class.getClassLoader()));
        this.adminStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public List<AttachmentList> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentList> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(attachmentList);
        dest.writeValue(adminStatus);
    }

    public int describeContents() {
        return  0;
    }

}
