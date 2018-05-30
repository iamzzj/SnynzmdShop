package com.snynzmd.shop.utils;

/**
 * Created by z on 2017/12/21.
 */

public class Global {
    /**
     后台地址：http://113.105.139.43:6630

     微信端地址：http://113.105.139.43:6631

     经销商接口地址：http://113.105.139.43:6632

     入库接口地址：http://113.105.139.43:6633

     出库接口地址：http://113.105.139.43:6634

     独立APP接口地址：http://113.105.139.43:6635
     */
    public static final String NAME = "SnynzmdShop";
    public static final String APPKEY = "WL";
    public static final String API_ROOT = "http://jifen.quanyuank.com:6801/";
    public static final String API = API_ROOT+"Services/WL/YoSoftWLService.asmx?wsdl";
    public static final String NAME_SPACE = "http://www.yosoft.cn/";

    //.l.txt  Y
    public static final String URLIN = "http://jifen.quanyuank.com:6803/upload/up.asp";
    //.o.txt .b.txt Y B
    public static final String URLOUTBACK = "http://jifen.quanyuank.com:6804/upload/up.asp";

    public static final String UPDATE_ROOT = API_ROOT;
    /*{"versionCode":"1","versionName":"1.0","note":"修复若干bug"}*/
    public static final String UPDATE_VER = "pda_ver.txt";
    public static final String UPDATE_APK = UPDATE_ROOT+"pda_apk.apk";
}
