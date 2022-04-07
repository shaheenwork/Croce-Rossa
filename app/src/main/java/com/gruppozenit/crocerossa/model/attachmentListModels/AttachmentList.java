
package com.gruppozenit.crocerossa.model.attachmentListModels;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachmentList implements Serializable, Parcelable
{

    @SerializedName("messaggio_Id")
    @Expose
    private Integer messaggioId;
    @SerializedName("messaggio_Details_ID")
    @Expose
    private Integer messaggioDetailsID;
    @SerializedName("file_path")
    @Expose
    private String filePath;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("adminName")
    @Expose
    private String adminName;

    @SerializedName("date")
    @Expose
    private String date;



    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("fileReadStatus")
    @Expose
    private Boolean fileReadStatus;
    public final static Creator<AttachmentList> CREATOR = new Creator<AttachmentList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AttachmentList createFromParcel(Parcel in) {
            return new AttachmentList(in);
        }

        public AttachmentList[] newArray(int size) {
            return (new AttachmentList[size]);
        }

    }
    ;
    private final static long serialVersionUID = -442279902769806522L;

    protected AttachmentList(Parcel in) {
        this.messaggioId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.messaggioDetailsID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.filePath = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.adminName = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.fileReadStatus = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public AttachmentList() {
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMessaggioId() {
        return messaggioId;
    }

    public void setMessaggioId(Integer messaggioId) {
        this.messaggioId = messaggioId;
    }

    public Integer getMessaggioDetailsID() {
        return messaggioDetailsID;
    }

    public void setMessaggioDetailsID(Integer messaggioDetailsID) {
        this.messaggioDetailsID = messaggioDetailsID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getFileReadStatus() {
        return fileReadStatus;
    }

    public void setFileReadStatus(Boolean fileReadStatus) {
        this.fileReadStatus = fileReadStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(messaggioId);
        dest.writeValue(messaggioDetailsID);
        dest.writeValue(filePath);
        dest.writeValue(title);
        dest.writeValue(date);
        dest.writeValue(adminName);
        dest.writeValue(type);
        dest.writeValue(fileReadStatus);
    }

    public int describeContents() {
        return  0;
    }

}
