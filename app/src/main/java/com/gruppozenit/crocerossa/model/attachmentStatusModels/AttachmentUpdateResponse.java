
package com.gruppozenit.crocerossa.model.attachmentStatusModels;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachmentUpdateResponse implements Serializable, Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Creator<AttachmentUpdateResponse> CREATOR = new Creator<AttachmentUpdateResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AttachmentUpdateResponse createFromParcel(Parcel in) {
            return new AttachmentUpdateResponse(in);
        }

        public AttachmentUpdateResponse[] newArray(int size) {
            return (new AttachmentUpdateResponse[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5028338430449892584L;

    protected AttachmentUpdateResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public AttachmentUpdateResponse() {
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
