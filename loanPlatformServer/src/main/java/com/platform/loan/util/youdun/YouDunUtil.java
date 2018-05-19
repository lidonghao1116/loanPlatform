/**
 * Alipay.com Inc. Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.platform.loan.util.youdun;

import com.sun.deploy.net.HttpResponse;

/**
 *
 * @author caogu.wyp
 * @version $Id: YouDunUtil.java, v 0.1 2018-05-20 上午1:53 caogu.wyp Exp $$
 */
public class YouDunUtil {


    private static final CloseableHttpClient client = HttpClientBuilder.create().build();

    private static String fformatStr = "/dsp-front/4.1/dsp-front/default/pubkey/%s/" +
                                       "product_code/%s/out_order_id/%s/signature/%s";




    private static HttpResponse makePostRequest(String uri, String jsonData)
            throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(URIUtil.encodeQuery(uri, "utf-8"));
        httpPost.setHeader("Connection","close");
        httpPost.setEntity(new StringEntity(jsonData, "UTF-8"));
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        return client.execute(httpPost);
    }

    public static String apiCall(String url,String pubkey,String secretkey,String
            serviceCode, String outOrderId,Map<String, String> parameter) throws Exception{
        if (parameter == null || parameter.isEmpty())
            throw new Exception("error ! the parameter Map can't be null.");

        StringBuffer bodySb = new StringBuffer("{");
        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            bodySb.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("',");
        }
        String bodyStr = bodySb.substring(0, bodySb.length() - 1) + "}";
        String signature = md5(bodyStr + "|" + secretkey);
        url += String.format(fformatStr, pubkey, serviceCode, System.currentTimeMillis(), signature);
        System.out.println("requestUrl=>" + url);
        System.out.println("request parameter body=>" + bodyStr);
        HttpResponse r = makePostRequest(url, bodyStr);
        return EntityUtils.toString(r.getEntity());
    }

    private static String md5(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.toString().getBytes("utf-8"));
        return bytesToHex(md.digest());
    }


    /**
     *字节转换为16进制字符串
     */
    private static String byteToHex(byte ch) {
        String str[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
        return str[ch >> 4 & 0xF] + str[ch & 0xF];
    }


}
