package com.example.applog.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferences {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;
    private static final String PREF_NAME="Pegawai";

    public SharePreferences (Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();
    }

    public void setData (String data){
        editor.putString("data", data);
        editor.commit();
    }
    public String getData (){
        return pref.getString("data", null);
    }
    public void setEmail (String email){
        editor.putString("email", email);
        editor.commit();
    }
    public String getEmail (){
        return pref.getString("email", null);
    }

    public void setPassword (String password){
        editor.putString("password", password);
        editor.commit();
    }
    public String getPassword (){
        return pref.getString("password", null);
    }


}
