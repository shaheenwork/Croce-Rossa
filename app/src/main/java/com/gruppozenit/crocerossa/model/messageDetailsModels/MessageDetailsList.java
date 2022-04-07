
package com.gruppozenit.crocerossa.model.messageDetailsModels;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageDetailsList implements Parcelable
{

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

    @SerializedName("messageAttachments")
    @Expose
    private List<MessageAttachment> messageAttachments = null;
    public final static Parcelable.Creator<MessageDetailsList> CREATOR = new Creator<MessageDetailsList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public MessageDetailsList createFromParcel(Parcel in) {
            return new MessageDetailsList(in);
        }

        public MessageDetailsList[] newArray(int size) {
            return (new MessageDetailsList[size]);
        }

    }
    ;

    protected MessageDetailsList(Parcel in) {
        this.testo = ((String) in.readValue((String.class.getClassLoader())));
        this.titolo = ((String) in.readValue((String.class.getClassLoader())));
        this.userImage = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.messageAttachments, (com.gruppozenit.crocerossa.model.messageDetailsModels.MessageAttachment.class.getClassLoader()));
    }

    public MessageDetailsList() {
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

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public List<MessageAttachment> getMessageAttachments() {
        return messageAttachments;
    }

    public void setMessageAttachments(List<MessageAttachment> messageAttachments) {
        this.messageAttachments = messageAttachments;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(testo);
        dest.writeValue(titolo);
        dest.writeValue(userImage);
        dest.writeValue(user);
        dest.writeList(messageAttachments);
    }

    public int describeContents() {
        return  0;
    }

}
