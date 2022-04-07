
package com.gruppozenit.crocerossa.model.soceitaRuoloListModels;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ruolo implements Serializable, Parcelable
{

    @SerializedName("ruolo_Id")
    @Expose
    private Integer ruoloId;
    @SerializedName("ruolo_Name")
    @Expose
    private String ruoloName;
    @SerializedName("created_By_Fk")
    @Expose
    private Integer createdByFk;
    @SerializedName("created_On")
    @Expose
    private String createdOn;
    @SerializedName("updated_By_Fk")
    @Expose
    private Object updatedByFk;
    @SerializedName("updated_On")
    @Expose
    private Object updatedOn;
    public final static Creator<Ruolo> CREATOR = new Creator<Ruolo>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Ruolo createFromParcel(Parcel in) {
            return new Ruolo(in);
        }

        public Ruolo[] newArray(int size) {
            return (new Ruolo[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1434308375770063702L;

    protected Ruolo(Parcel in) {
        this.ruoloId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.ruoloName = ((String) in.readValue((String.class.getClassLoader())));
        this.createdByFk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedByFk = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updatedOn = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Ruolo() {
    }

    public Integer getRuoloId() {
        return ruoloId;
    }

    public void setRuoloId(Integer ruoloId) {
        this.ruoloId = ruoloId;
    }

    public String getRuoloName() {
        return ruoloName;
    }

    public void setRuoloName(String ruoloName) {
        this.ruoloName = ruoloName;
    }

    public Integer getCreatedByFk() {
        return createdByFk;
    }

    public void setCreatedByFk(Integer createdByFk) {
        this.createdByFk = createdByFk;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Object getUpdatedByFk() {
        return updatedByFk;
    }

    public void setUpdatedByFk(Object updatedByFk) {
        this.updatedByFk = updatedByFk;
    }

    public Object getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Object updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ruoloId);
        dest.writeValue(ruoloName);
        dest.writeValue(createdByFk);
        dest.writeValue(createdOn);
        dest.writeValue(updatedByFk);
        dest.writeValue(updatedOn);
    }

    public int describeContents() {
        return  0;
    }

}
