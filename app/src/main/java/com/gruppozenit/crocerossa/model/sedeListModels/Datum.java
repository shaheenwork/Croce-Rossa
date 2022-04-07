
package com.gruppozenit.crocerossa.model.sedeListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable
{

    @SerializedName("sedeID")
    @Expose
    private Integer sedeID;
    @SerializedName("sede_Name")
    @Expose
    private String sedeName;
    @SerializedName("active")
    @Expose
    private Integer active;
    public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;

    protected Datum(Parcel in) {
        this.sedeID = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sedeName = ((String) in.readValue((String.class.getClassLoader())));
        this.active = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Datum() {
    }

    public Integer getSedeID() {
        return sedeID;
    }

    public void setSedeID(Integer sedeID) {
        this.sedeID = sedeID;
    }

    public String getSedeName() {
        return sedeName;
    }

    public void setSedeName(String sedeName) {
        this.sedeName = sedeName;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sedeID);
        dest.writeValue(sedeName);
        dest.writeValue(active);
    }

    public int describeContents() {
        return  0;
    }

}
