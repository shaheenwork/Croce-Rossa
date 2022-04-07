
package com.gruppozenit.crocerossa.model.soceitaRuoloListModels;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("societa")
    @Expose
    private List<Societum> societa = null;
    @SerializedName("ruolo")
    @Expose
    private List<Ruolo> ruolo = null;
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
    private final static long serialVersionUID = 9132323204342325881L;

    protected Data(Parcel in) {
        in.readList(this.societa, (com.gruppozenit.crocerossa.model.soceitaRuoloListModels.Societum.class.getClassLoader()));
        in.readList(this.ruolo, (com.gruppozenit.crocerossa.model.soceitaRuoloListModels.Ruolo.class.getClassLoader()));
    }

    public Data() {
    }

    public List<Societum> getSocieta() {
        return societa;
    }

    public void setSocieta(List<Societum> societa) {
        this.societa = societa;
    }

    public List<Ruolo> getRuolo() {
        return ruolo;
    }

    public void setRuolo(List<Ruolo> ruolo) {
        this.ruolo = ruolo;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(societa);
        dest.writeList(ruolo);
    }

    public int describeContents() {
        return  0;
    }

}
