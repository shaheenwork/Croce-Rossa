
package com.gruppozenit.crocerossa.model.messageDetailsModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMessageDetailsResponse implements Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Parcelable.Creator<GetMessageDetailsResponse> CREATOR = new Creator<GetMessageDetailsResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetMessageDetailsResponse createFromParcel(Parcel in) {
            return new GetMessageDetailsResponse(in);
        }

        public GetMessageDetailsResponse[] newArray(int size) {
            return (new GetMessageDetailsResponse[size]);
        }

    }
    ;

    protected GetMessageDetailsResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public GetMessageDetailsResponse() {
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
