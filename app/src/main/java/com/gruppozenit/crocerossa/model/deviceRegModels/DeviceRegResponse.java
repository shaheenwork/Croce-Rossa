
package com.gruppozenit.crocerossa.model.deviceRegModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceRegResponse implements Parcelable
{

    @SerializedName("result")
    @Expose
    private Result result;
    public final static Parcelable.Creator<DeviceRegResponse> CREATOR = new Creator<DeviceRegResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DeviceRegResponse createFromParcel(Parcel in) {
            return new DeviceRegResponse(in);
        }

        public DeviceRegResponse[] newArray(int size) {
            return (new DeviceRegResponse[size]);
        }

    }
    ;

    protected DeviceRegResponse(Parcel in) {
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public DeviceRegResponse() {
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
