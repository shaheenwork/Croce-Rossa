
package com.gruppozenit.crocerossa.model.messageListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageList implements Parcelable
{

    @SerializedName("messaggio_Id")
    @Expose
    private Integer messaggioId;

 @SerializedName("readAllAttachments")
    @Expose
    private Boolean readAllAttachments;


    @SerializedName("testo")
    @Expose
    private String testo;

  @SerializedName("titolo")
    @Expose
    private String titolo;




    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("userImage")
    @Expose
    private String userImage;



    @SerializedName("message_Date")
    @Expose
    private String messageDate;
    @SerializedName("readMessage")
    @Expose
    private Boolean readMessage;

    @SerializedName("attachmentCount")
    @Expose
    private Integer attachmentCount;

    public final static Parcelable.Creator<MessageList> CREATOR = new Creator<MessageList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MessageList createFromParcel(Parcel in) {
            return new MessageList(in);
        }

        public MessageList[] newArray(int size) {
            return (new MessageList[size]);
        }

    }
    ;

    protected MessageList(Parcel in) {
        this.messaggioId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.readAllAttachments = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.testo = ((String) in.readValue((String.class.getClassLoader())));
        this.titolo = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((String) in.readValue((String.class.getClassLoader())));
        this.userImage = ((String) in.readValue((String.class.getClassLoader())));
        this.messageDate = ((String) in.readValue((String.class.getClassLoader())));
        this.readMessage = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.attachmentCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public MessageList() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Integer getMessaggioId() {
        return messaggioId;
    }

    public void setMessaggioId(Integer messaggioId) {
        this.messaggioId = messaggioId;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public Boolean getReadMessage() {
        return readMessage;
    }

    public void setReadMessage(Boolean readMessage) {
        this.readMessage = readMessage;
    }

    public Integer getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(Integer attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public Boolean getReadAllAttachments() {
        return readAllAttachments;
    }

    public void setReadAllAttachments(Boolean readAllAttachments) {
        this.readAllAttachments = readAllAttachments;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(messaggioId);
        dest.writeValue(readAllAttachments);
        dest.writeValue(attachmentCount);
        dest.writeValue(testo);
        dest.writeValue(titolo);
        dest.writeValue(user);
        dest.writeValue(userImage);
        dest.writeValue(messageDate);
        dest.writeValue(readMessage);
    }

    public int describeContents() {
        return  0;
    }

}
