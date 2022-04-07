
package com.gruppozenit.crocerossa.model.attachmentListModels;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAttachmentListResponse implements Serializable, Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Creator<GetAttachmentListResponse> CREATOR = new Creator<GetAttachmentListResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetAttachmentListResponse createFromParcel(Parcel in) {
            return new GetAttachmentListResponse(in);
        }

        public GetAttachmentListResponse[] newArray(int size) {
            return (new GetAttachmentListResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8508379197900289408L;

    protected GetAttachmentListResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public GetAttachmentListResponse() {
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
