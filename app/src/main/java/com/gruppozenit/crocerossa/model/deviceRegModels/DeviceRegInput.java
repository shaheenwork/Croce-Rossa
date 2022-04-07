package com.gruppozenit.crocerossa.model.deviceRegModels;

import android.os.Parcel;
import android.os.Parcelable;

public class DeviceRegInput implements Parcelable {

    String Nome, Cognome, Email, DeviceToken;
    int DeviceID=0;
    int TokenStatus;
    int Societa_Id;
    int Ruolo_Id;
    public DeviceRegInput() {
    }

    protected DeviceRegInput(Parcel in) {
        Nome = in.readString();
        Cognome = in.readString();
        Email = in.readString();
        DeviceToken = in.readString();
        DeviceID = in.readInt();
        TokenStatus = in.readInt();
        Societa_Id = in.readInt();
        Ruolo_Id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nome);
        dest.writeString(Cognome);
        dest.writeString(Email);
        dest.writeString(DeviceToken);
        dest.writeInt(DeviceID);
        dest.writeInt(TokenStatus);
        dest.writeInt(Societa_Id);
        dest.writeInt(Ruolo_Id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceRegInput> CREATOR = new Creator<DeviceRegInput>() {
        @Override
        public DeviceRegInput createFromParcel(Parcel in) {
            return new DeviceRegInput(in);
        }

        @Override
        public DeviceRegInput[] newArray(int size) {
            return new DeviceRegInput[size];
        }
    };

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        this.Cognome = cognome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.DeviceToken = deviceToken;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }
    public int getSocieta_Id() {
        return Societa_Id;
    }

    public void setSocieta_Id(int societa_Id) {
        Societa_Id = societa_Id;
    }


    public int getTokenStatus() {
        return TokenStatus;
    }

    public void setTokenStatus(int tokenStatus) {
        TokenStatus = tokenStatus;
    }

    public int getRuolo_Id() {
        return Ruolo_Id;
    }

    public void setRuolo_Id(int ruolo_Id) {
        Ruolo_Id = ruolo_Id;
    }
}
