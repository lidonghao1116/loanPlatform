/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.cache;

import java.util.HashMap;
import java.util.Map;

/**
 *  
 * @author caogu.wyp
 * @version $Id: SimpleCacheUtil.java, v 0.1 2018-05-07 上午1:19 caogu.wyp Exp $$
 */
public class SimpleCacheUtil {

    private static Map<String, String> imageCodeCache = new HashMap<String, String>();
    private static Map<String, String> SMSCodeCache   = new HashMap<String, String>();

    public static void addImageCode(String key, String value) {
        imageCodeCache.put(key, value);
    }

    public static void addSMSCode(String phoneNo, String bizType, String value) {
        SMSCodeCache.put((buildSmsStoreKey(phoneNo, bizType)), value);
    }

    public static String getImageCode(String key) {
        return imageCodeCache.get(key);
    }

    public static String getSMSCode(String phoneNo, String bizType) {

        return SMSCodeCache.get((buildSmsStoreKey(phoneNo, bizType)));
    }

    private static String buildSmsStoreKey(String phoneNo, String bizType) {
        return phoneNo + bizType;
    }

    public static String removeImageCode(String key) {
        return imageCodeCache.remove(key);
    }

    public static String removeSMSCode(String phoneNo, String bizType) {

        return SMSCodeCache.remove(buildSmsStoreKey(phoneNo, bizType));
    }

}
