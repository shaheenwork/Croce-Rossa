
package com.gruppozenit.crocerossa.model.messageDetailsModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageAttachment implements Parcelable
{

    @SerializedName("messaggio_Details_ID")
    @Expose
    private Integer messaggioDetailsID;
    @SerializedName("file_path")
    @Expose
    private String filePath;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private Integer type;
    public final static Parcelable.Creator<MessageAttachment> CREATOR = new Creator<MessageAttachment>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MessageAttachment createFromParcel(Parcel in) {
            return new MessageAttachment(in);
        }

        public MessageAttachment[] newArray(int size) {
            return (new MessageAttachment[size]);
        }

    }
    ;

    protected MessageAttachment(Parcel in) {
        this.messaggioDetailsID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.filePath = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MessageAttachment() {
    }

    public MessageAttachment(Integer messaggioDetailsID, String filePath, String title, Integer type) {
        this.messaggioDetailsID = messaggioDetailsID;
        this.filePath = filePath;
        this.title = title;
        this.type = type;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(messaggioDetailsID);
        dest.writeValue(filePath);
        dest.writeValue(title);
        dest.writeValue(type);
    }

    public int describeContents() {
        return  0;
    }

}
