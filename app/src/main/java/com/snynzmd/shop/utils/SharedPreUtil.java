package com.snynzmd.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by z on 2018/3/2.
 */

public class SharedPreUtil {
    private static final String LOGIN = "login";
    private static final String LOGIN_USER = "user";
    private static final String LOGIN_PWD = "pwd";
    private static final String LOGIN_ISSAVEPWD = "isSave";
    private static final String LOGIN_ISAUTOLOGIN = "isAutoLogin";

    public static void saveLogin(Context context , String user, String pwd, boolean isSave, boolean isAuto){
        SharedPreferences sharedUser = context.getSharedPreferences(LOGIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedUser.edit();
        editor.putString(LOGIN_USER,user);
        if (isSave){
            editor.putString(LOGIN_PWD,pwd);
        }else {
            editor.putString(LOGIN_PWD,"");
        }
        editor.putBoolean(LOGIN_ISSAVEPWD,isSave);
        editor.putBoolean(LOGIN_ISAUTOLOGIN,isAuto);
        editor.commit();
    }

    public static String getLoginUser(Context context){
        SharedPreferences sharedUser = context.getSharedPreferences(LOGIN,Context.MODE_PRIVATE);
        return sharedUser.getString(LOGIN_USER,"");
    }

    public static String getLoginPwd(Context context){
        SharedPreferences sharedUser = context.getSharedPreferences(LOGIN,Context.MODE_PRIVATE);
        return sharedUser.getString(LOGIN_PWD,"");
    }

    public static boolean getIsSave(Context context){
        SharedPreferences sharedUser = context.getSharedPreferences(LOGIN,Context.MODE_PRIVATE);
        return sharedUser.getBoolean(LOGIN_ISSAVEPWD,false);
    }

    public static boolean getIsAuto(Context context){
        SharedPreferences sharedUser = context.getSharedPreferences(LOGIN,Context.MODE_PRIVATE);
        return sharedUser.getBoolean(LOGIN_ISAUTOLOGIN,false);
    }

    public static void clearLogin(Context context){
        SharedPreferences sharedUser = context.getSharedPreferences(LOGIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedUser.edit();
        editor.putString(LOGIN_PWD,"");
        editor.putBoolean(LOGIN_ISAUTOLOGIN,false);
        editor.commit();
    }

}
