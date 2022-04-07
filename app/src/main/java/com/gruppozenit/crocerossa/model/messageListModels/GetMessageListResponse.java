
package com.gruppozenit.crocerossa.model.messageListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMessageListResponse implements Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Parcelable.Creator<GetMessageListResponse> CREATOR = new Creator<GetMessageListResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetMessageListResponse createFromParcel(Parcel in) {
            return new GetMessageListResponse(in);
        }

        public GetMessageListResponse[] newArray(int size) {
            return (new GetMessageListResponse[size]);
        }

    }
    ;

    protected GetMessageListResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public GetMessageListResponse() {
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
