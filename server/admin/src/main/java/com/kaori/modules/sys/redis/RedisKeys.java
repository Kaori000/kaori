package com.kaori.modules.sys.redis;

/**
 * Redis所有Keys
 *
 */
public class RedisKeys {

    public static final String configPre = "sys:config:";
    public static final String dictPre = "sys:dict:";



    public static String getSysConfigKey(String key){
        return configPre + key;
    }
    public static String getSysDictKey(String type, String code){
        return dictPre + type + ":" + code;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }

}
