
package com.gruppozenit.crocerossa.model.soceitaRuoloListModels;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Societum implements Serializable, Parcelable
{

    @SerializedName("societa_Id")
    @Expose
    private Integer societaId;
    @SerializedName("societa_Name")
    @Expose
    private String societaName;
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
    public final static Creator<Societum> CREATOR = new Creator<Societum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Societum createFromParcel(Parcel in) {
            return new Societum(in);
        }

        public Societum[] newArray(int size) {
            return (new Societum[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1522153765856066349L;

    protected Societum(Parcel in) {
        this.societaId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.societaName = ((String) in.readValue((String.class.getClassLoader())));
        this.createdByFk = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.createdOn = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedByFk = ((Object) in.readValue((Object.class.getClassLoader())));
        this.updatedOn = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Societum() {
    }

    public Integer getSocietaId() {
        return societaId;
    }

    public void setSocietaId(Integer societaId) {
        this.societaId = societaId;
    }

    public String getSocietaName() {
        return societaName;
    }

    public void setSocietaName(String societaName) {
        this.societaName = societaName;
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
        dest.writeValue(societaId);
        dest.writeValue(societaName);
        dest.writeValue(createdByFk);
        dest.writeValue(createdOn);
        dest.writeValue(updatedByFk);
        dest.writeValue(updatedOn);
    }

    public int describeContents() {
        return  0;
    }

}
