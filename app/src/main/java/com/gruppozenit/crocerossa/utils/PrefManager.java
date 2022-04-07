package com.gruppozenit.crocerossa.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    // ===========================================================
    // Constants
    // ===========================================================


    private static final String TAG_NAME = "chronology.prefs";
    private static final String IS_LOGIN = "is_login";
    private static final String IS_APPROVED = "is_approved";
    private static final String NOME = "nome";
    private static final String COGNOME = "cognome";
    private static final String SOCIETA = "societa";
    private static final String RUOLO = "ruolo";
    private static final String SHOW_INFO_PAGE = "show_info_page";

    private static final String FCM_TOKEN = "fcm_token";
    private static final String DEVICE_ID = "device_id";
    private static final String NOTIFICATION_ON_OFF = "notification_on_off";
    private static final String DARKkMODE_ON_OFF = "darkmode_on_off";
    private static final String LOCK_ON_OFF = "lock_on_off";


    private static PrefManager instance;

    private SharedPreferences prefs;

    private PrefManager(Context context) {
        prefs = context.getSharedPreferences(TAG_NAME, Context.MODE_PRIVATE);
    }

    public static PrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new PrefManager(context);
        }
        return instance;
    }


    private void putString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @SuppressWarnings("unused")
    private void putLong(String key, long value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    @SuppressWarnings("unused")
    private void putInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    @SuppressWarnings("unused")
    private void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @SuppressWarnings("unused")
    public SharedPreferences getSharedPrefs() {
        return prefs;
    }


    public void clearData() {
        prefs.edit().clear().apply();
    }

    public boolean isLogin() {
        return prefs.getBoolean(IS_LOGIN, false);
    }

    public void setLogin(boolean isEnabled) {
        putBoolean(IS_LOGIN, isEnabled);
    }

    public int isApproved() {
        return prefs.getInt(IS_APPROVED, Consts.Companion.getACCESS_NOT_SET());
    }

    public void setIsApproved(int isApproved) {
        putInt(IS_APPROVED, isApproved);
    }

    public String getFcmToken() {
        return prefs.getString(FCM_TOKEN, null);
    }

    public void setFcmToken(String token) {
        putString(FCM_TOKEN, token);
    }


    public int getDeviceID() {
        return prefs.getInt(DEVICE_ID, 0);
    }

    public void setDeviceId(int id) {
        putInt(DEVICE_ID, id);
    }

    public String getNome() {
        return prefs.getString(NOME, "");
    }

    public void setNome(String nome) {
        putString(NOME, nome);
    }

    public String getCognome() {
        return prefs.getString(COGNOME, "");
    }

    public void setCognome(String cognome) {
        putString(COGNOME, cognome);
    }

    public String getSocieta() {
        return prefs.getString(SOCIETA, "");
    }

    public void setSocieta(String societa) {
        putString(SOCIETA, societa);
    }


    public String getRuolo() {
        return prefs.getString(RUOLO, "");
    }

    public void setRuolo(String ruolo) {
        putString(RUOLO, ruolo);
    }


    public boolean showInfoPage() {
        return prefs.getBoolean(SHOW_INFO_PAGE, false);
    }

    public void setInfoPageFlag(boolean f) {
        putBoolean(SHOW_INFO_PAGE, f);
    }


    public boolean getNotificationONorOFF() {
        return prefs.getBoolean(NOTIFICATION_ON_OFF, true);
    }

    public void setNotificationOnOff(boolean onOrOff) {
        putBoolean(NOTIFICATION_ON_OFF, onOrOff);
    }

public boolean getDarkModeONorOFF() {
        return prefs.getBoolean(DARKkMODE_ON_OFF, false);
    }

    public void setDARKkMODE_ON_OFF(boolean onOrOff) {
        putBoolean(DARKkMODE_ON_OFF, onOrOff);
    }
public boolean getLockONorOFF() {
        return prefs.getBoolean(LOCK_ON_OFF, false);
    }

    public void setLockOnOff(boolean onOrOff) {
        putBoolean(LOCK_ON_OFF, onOrOff);
    }


}