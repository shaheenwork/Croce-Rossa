
package com.gruppozenit.crocerossa.model.soceitaRuoloListModels;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSocietaRuoloListResponse implements Serializable, Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Creator<GetSocietaRuoloListResponse> CREATOR = new Creator<GetSocietaRuoloListResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetSocietaRuoloListResponse createFromParcel(Parcel in) {
            return new GetSocietaRuoloListResponse(in);
        }

        public GetSocietaRuoloListResponse[] newArray(int size) {
            return (new GetSocietaRuoloListResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1983641389985899239L;

    protected GetSocietaRuoloListResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public GetSocietaRuoloListResponse() {
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(result);
    }

    public int describeContents() {
        return  0;
    }

}
