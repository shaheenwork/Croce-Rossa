
package com.gruppozenit.crocerossa.model.sedeListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSedeListResponse implements Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Parcelable.Creator<GetSedeListResponse> CREATOR = new Creator<GetSedeListResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetSedeListResponse createFromParcel(Parcel in) {
            return new GetSedeListResponse(in);
        }

        public GetSedeListResponse[] newArray(int size) {
            return (new GetSedeListResponse[size]);
        }

    }
    ;

    protected GetSedeListResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public GetSedeListResponse() {
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
